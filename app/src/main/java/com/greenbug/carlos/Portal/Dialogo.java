package com.greenbug.carlos.Portal;

import com.greenbug.carlos.centralapp.R;
import com.greenbug.carlos.red.RedPortal;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class Dialogo extends Dialog {
    private CameraPreview mPreview;
    private Camera mCamera;
    private Context c;
    private boolean tieneCamara;
    private boolean estaOculta = true, restaurante;
    private String idCelular;
    public Dialogo (final Context context, boolean restaurante)
    {
        super(context);
        c = context;
        this.restaurante =restaurante;
        SharedPreferences settings = context.getSharedPreferences("centralapp", Context.MODE_PRIVATE);
        idCelular = settings.getString("idCelular", "");
        /*int textViewId = getContext().getResources().getIdentifier("android:id/alertTitle", null, null);
        TextView tv = (TextView) findViewById(textViewId);
        tv.setTextColor(getContext().getResources().getColor(R.color.background_floating_material_dark));*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogo);
        this.setTitle("¿Qué tan lleno está?");
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        Button camaraButon = (Button) findViewById(R.id.button);
        final Button enviarButton = (Button) findViewById(R.id.button2);
        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float calificacion = ((RatingBar) findViewById(R.id.ratingBar)).getRating();
                enviarVoto(calificacion, restaurante);
                //dialog.hide();
            }
        });
        camaraButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
                if (estaOculta) {
                    mCamera = getCameraInstance();
                    if (mCamera != null) {
                        mPreview = new CameraPreview(c, mCamera);
                        if (mPreview != null) {
                            preview.getLayoutParams().height = 300;
                            if (preview != null) {
                                preview.addView(mPreview);
                                ((Button) v).setText("Ocultar Camara");
                                enviarButton.setText("Capturar y enviar");
                            } else {
                                Log.e("Error", "Error al encontrar el frameLayout");
                            }
                        }
                    }
                } else {
                    preview.removeAllViews();
                    preview.getLayoutParams().height = 0;
                    ((Button) v).setText("Agregar Imagen");
                    enviarButton.setText("Enviar");
                }
                estaOculta = !estaOculta;
            }
        });
    }

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open();
            Camera.Parameters mParameters = c.getParameters();
            c.setParameters(mParameters);
        }
        catch (Exception e){
            Log.e("Camara", "No se pudo obtener la camara");
        }
        return c;
    }

    private void enviarVoto(final float calificacion,final boolean restaurante) {
        if(estaOculta){
            try {
                RedPortal.enviarVoto(calificacion, null, restaurante,idCelular);
                dismiss();
            } catch (Exception e) {
                Log.e("", "Error al enviar imagen");
            }
        }else {
            Camera.PictureCallback rawCallback = new Camera.PictureCallback() {
                public void onPictureTaken(byte[] data, Camera camera) {
                    if (null == data) {
                        Log.e("Camara", "La foto no ha sido tomada");
                        return;
                    }
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length, options);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                    //rotar(bmp, 90);
                    Log.e("Camara", "Enviando Foto");
                    try {
                        RedPortal.enviarVoto(calificacion, stream.toByteArray(), restaurante,idCelular);
                    } catch (Exception e) {
                        Log.e("", "Error al enviar imagen");
                    }
                    dismiss();
                }
            };
            Log.e("Camara", "Tomando foto");
            mCamera.takePicture(null, null, rawCallback);
        }
        Toast.makeText(c, "Gracias por votar", Toast.LENGTH_LONG).show();
    }
    public Bitmap rotar(Bitmap bitmap, int degree) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix mtx = new Matrix();
        mtx.postRotate(degree);

        return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
    }
}
