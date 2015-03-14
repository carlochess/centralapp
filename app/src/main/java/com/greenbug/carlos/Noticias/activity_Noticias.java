package com.greenbug.carlos.Noticias;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.greenbug.carlos.centralapp.R;
import com.greenbug.carlos.data.Noticia;
import com.greenbug.carlos.red.RedNoticias;
import com.greenbug.carlos.red.UtilRed;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class activity_Noticias extends ActionBarActivity {
    AdaptadorNoticias adaptadorNoticias;
    private ArrayList<Noticia> noticias;
    private long ultimoLista=-1;
    private Context contexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        contexto = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ListView codeLearnLessons = (ListView)findViewById(R.id.listView);
        cargarDatos();
        adaptadorNoticias = new AdaptadorNoticias(noticias);
        codeLearnLessons.setAdapter(adaptadorNoticias);
        try {
            Log.e("Hola", "Leyendo ultimas noticias");

            RedNoticias.descargarNoticias(ultimoLista, this);
        }catch (Exception e){
            Log.e("Hola", "Error");
        }
        //traerN  oticias();
    }

    public void cargarDatos()
    {
        noticias = Noticia.toArrayList(Noticia.find(Noticia.class,"",new String[]{},"","id DESC","20"));
        if(noticias.size()>0){
            ultimoLista = ((noticias.get(0)).getIdNoticia());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.noticias, menu);
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

    public void actualizarLista(final Noticia[] n) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ultimoLista=n[0].getIdNoticia();
                for(Noticia a : n)
                    noticias.add(0,a);
                adaptadorNoticias.notifyDataSetChanged();
            }
        });
    }

    public void traerNoticias(){
        String url = "http://www.univalle.edu.co/noticiasyeventos/rss/canales/noticias/noticias.php";
        NoticiasUv obj = new NoticiasUv(url);
        obj.fetchXML();
        //while(obj.parsingComplete);
    }

    class AdaptadorNoticias  extends BaseAdapter {
        ArrayList<Noticia> noticias;
        AdaptadorNoticias(ArrayList<Noticia> a){
            noticias = a;
        }
        @Override
        public int getCount() {
            return noticias.size();
        }

        @Override
        public Object getItem(int i) {
            return noticias.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null)
            {
                LayoutInflater inflater = (LayoutInflater) activity_Noticias.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.listviewnoticias, viewGroup,false);
            }
            TextView titulo = (TextView)view.findViewById(R.id.textView);
            TextView contenido = (TextView)view.findViewById(R.id.carne);
            ImageView img = (ImageView) view.findViewById(R.id.imageView);
            Noticia noticia = noticias.get(i);

            String url = UtilRed.dirIp+"centralApp/public/imagenes/noticias/"+noticia.getIdNoticia()+".jpg";
            Log.e("Cargando noticia", url);
            Picasso.with(contexto).load(url).resize(150,150)
                    .centerCrop().placeholder(R.drawable.sample_0).into(img);
            titulo.setText(noticia.getTitulo()+" - "+noticia.getFecha());
            contenido.setText(noticia.getContenido());
            return view;
        }
    }
}
