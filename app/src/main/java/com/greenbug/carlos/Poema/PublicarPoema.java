package com.greenbug.carlos.Poema;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.greenbug.carlos.centralapp.R;
import com.greenbug.carlos.red.RedPoemas;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PublicarPoema extends ActionBarActivity {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    private static int RESULT_LOAD_IMAGE = 1;
    Bitmap b;
    String cuenta;
    ProgressDialog cargando;
    EditText titulo;
    EditText contenido;
    String idTelefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        cuenta = intent.getStringExtra("cuenta");
        setContentView(R.layout.activity_publicar_poema);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button agregarPoema = (Button) findViewById(R.id.button);
        titulo = (EditText) findViewById(R.id.editText);
        contenido = (EditText) findViewById(R.id.EditText02);
        idTelefono = "";
        agregarPoema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarPoema();
            }
        });
        // Botón imagen local
        Button agregarImagen = (Button) findViewById(R.id.button2);
        agregarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

    }

    public void enviarPoema(){
        //  byte[] imagen, String titulo, String contenido, String fecha, String cuenta, String idTelefono
        try {
            Log.e("Poema", "Enviando poema");
            if(b!=null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                RedPoemas.enviarPoemas(stream.toByteArray(), titulo.getText().toString(), contenido.getText().toString(), getFechaActual(), cuenta, idTelefono, this);
            }else{
                RedPoemas.enviarPoemas(null, titulo.getText().toString(), contenido.getText().toString(), getFechaActual(), cuenta, idTelefono, this);
            }
            cargando = new ProgressDialog(this);
            cargando.setMessage("Enviando Poema");
            cargando.show();
        }catch (Exception e){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            b = BitmapFactory.decodeFile(picturePath);
            imageView.setImageBitmap(b);
        }
    }

    public String getFechaActual(){
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.publicar_poema, menu);
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

    public void resultadoEnvio(boolean res){
        if(res){
            Toast.makeText(this, "Enviado", Toast.LENGTH_LONG).show();
            cargando.dismiss();
        }
    }
}
