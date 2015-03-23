package com.greenbug.carlos.Galeria;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.greenbug.carlos.centralapp.R;
import com.greenbug.carlos.data.Imagen;
import com.greenbug.carlos.red.RedGaleria;
import com.greenbug.carlos.red.UtilRed;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Galeria extends ActionBarActivity {
    Context contexto;
    ArrayList<Imagen> imagenes;
    long ultimoLista=-1;
    ImageAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        contexto = this;
        imagenes = new ArrayList<Imagen>();
        preparElementos();
        GridView gridview = (GridView) findViewById(R.id.gridview);
        adaptador = new ImageAdapter(this);
        gridview.setAdapter(adaptador);
        gridview.setLongClickable(true);

        if(primeraVez()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
            builder.setMessage("Manten presionada una foto para denunciarla")
                    .setTitle("Denunciar");
            builder.setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int posicion, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
                builder.setMessage("¿Quieres denunciar esta fotografía?")
                        .setTitle("Denunciar");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(contexto, "Denunciada", Toast.LENGTH_SHORT).show();
                        denunciarFoto(posicion);
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                eliminarMensaje();
                return true;
            }
        });


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                detallesImagen(imagenes.get(position).getIdImagen());
            }
        });
        try {
            Log.e("Galeria", "Ultimo de la lista " + ultimoLista);
            RedGaleria.descargarImagenes(ultimoLista, this);
        }catch (Exception e){
            Log.e("Hola", "Error");
        }
    }

    private void eliminarMensaje() {
        SharedPreferences sharedpreferences = getSharedPreferences("centralapp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("primeraVezGaleria", "cierto");
        editor.apply();
    }

    private void denunciarFoto(final int posicion){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Eliminarla de la bd
                Imagen img =imagenes.get(posicion);
                // long id = img.getId();
                // Eliminarla de la vista
                if(posicion!=-1)
                    imagenes.remove(posicion);
                adaptador.notifyDataSetChanged();
                img.delete();
                // disminuir su reputacion en el server
                RedGaleria.denunciarImagen(img.getIdImagen());
            }
        });

    }

    public boolean primeraVez(){
        SharedPreferences sharedpreferences = getSharedPreferences("centralapp", Context.MODE_PRIVATE);
        String codigo = sharedpreferences.getString("primeraVezGaleria", "");
        return codigo.isEmpty();
    }
    private void detallesImagen(int idImg) {
        Intent t = new Intent(this, activityIMG.class);
        t.putExtra("imagen", idImg);
        startActivity(t);
    }

    public void preparElementos(){
        imagenes = Imagen.toArrayList(Imagen.find(Imagen.class,"",new String[]{},"","id DESC","20"));
        if(imagenes.size()>0){
            ultimoLista = ((imagenes.get(0)).getIdImagen());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.galeria, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.action_fecha) {
            //desplegarFecha();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void actualizarLista(final Imagen[] imgs) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ultimoLista=imgs[0].getIdImagen();
                Log.e("Galeria", "Ultimo de la lista " + ultimoLista);
                for(Imagen a : imgs)
                    imagenes.add(0,a);
                adaptador.notifyDataSetChanged();
            }
        });
    }

    private class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return imagenes.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            if (view == null) {
                LayoutInflater li = getLayoutInflater();
                view = li.inflate(R.layout.row_grid, null);

                GridViewItem tv = (GridViewItem)view.findViewById(R.id.item_image);
                TextView t = (TextView) view.findViewById(R.id.grid_item_text);
                String fecha = imagenes.get(position).getFecha()+" "+imagenes.get(position).getHora();
                t.setText(fechas(fecha));
                String url = UtilRed.dirIp+"centralApp/public/imagenes/restaurante/"+imagenes.get(position).getIdImagen()+".jpeg";
                Picasso.with(contexto).load(url)//.resize(50, 50)
                        .centerCrop().fit().placeholder(R.drawable.sample_0).into(tv);
            }
            return view;
        }

        public String fechas(String date){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            Date startDate,endDate;
            try{
                startDate = formatter.parse(date);
                endDate   = cal.getTime();
            }catch(Exception  e){
                System.out.println("Error de parseo");
                return "";
            }

            long duration  = endDate.getTime() - startDate.getTime();

            long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
            long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
            if(diffInSeconds < 60){
                return ("Hace "+diffInSeconds+" s");
            }
            else if(diffInMinutes < 60){
                return ("Hace "+diffInMinutes+" m");
            }
            else if(diffInHours < 24){
                return ("Hace "+diffInHours+" h");
            }
            return ("Hace "+diffInHours/24+" dia"+ ((diffInHours/24==1)?"":"s"));
        }


    }
}
