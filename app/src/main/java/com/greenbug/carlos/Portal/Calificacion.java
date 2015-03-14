package com.greenbug.carlos.Portal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.greenbug.carlos.centralapp.R;
import com.greenbug.carlos.red.RedPortal;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calificacion extends ActionBarActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    Bitmap bitmap;
    private RedPortal redPortal;
    private String idCelular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final boolean restaurante = getIntent().getExtras().getBoolean("restaurante");
        setContentView(R.layout.dialogo);
        redPortal= new RedPortal();
        Button camaraButon = (Button) findViewById(R.id.button);
        camaraButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        Button enviarButton = (Button) findViewById(R.id.button2);
        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float calificacion = ((RatingBar) findViewById(R.id.ratingBar)).getRating();
                enviarVoto(calificacion, restaurante);
            }
        });
        SharedPreferences settings = getSharedPreferences("centralapp", Context.MODE_PRIVATE);
        idCelular = settings.getString("idCelular", "");
    }

    private void enviarVoto(final float calificacion,final boolean restaurante) {
        if(bitmap==null){
            try {
                redPortal.enviarVoto(calificacion, null, restaurante,idCelular);
            } catch (Exception e) {
                Log.e("", "Error al enviar calificacion "+ e.toString());
            }
        }else {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
            byte[] byteArray;
            try {
                byteArray = stream.toByteArray();
                redPortal.enviarVoto(calificacion, byteArray, restaurante,idCelular);
            }catch (Exception e){
                Log.e("", "Error al enviar imagen calificacion");
                return;
            }
        }
        Toast.makeText(this, "Gracias", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
                mCurrentPhotoPath  = photoFile.getAbsolutePath();
            } catch (IOException ex) {
                Log.e("Camara", "Error al crear el archivo");
                return;
            }

            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            ImageView img = new ImageView(this);
            setPic(img);
            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            preview.addView(img);
        }else if(resultCode == RESULT_CANCELED){
            Toast.makeText(this,"Actividad cancelada", Toast.LENGTH_LONG).show();
        }
    }

    private void setPic(ImageView mImageView) {
        int targetW = 150;
        int targetH = 150;

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        //bmOptions.inPurgeable = true;

        bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
    }


    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        return File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
    }
    //-------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calificacion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
