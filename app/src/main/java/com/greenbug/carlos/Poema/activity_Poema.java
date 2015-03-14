package com.greenbug.carlos.Poema;

import android.content.Intent;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenbug.carlos.centralapp.R;
import com.greenbug.carlos.data.Poema;
import com.greenbug.carlos.red.UtilRed;
import com.squareup.picasso.Picasso;

public class activity_Poema extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        long idPoema = intent.getLongExtra("idPoema", 0);
        Toast.makeText(this, idPoema+"", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_poema);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Poema p = Poema.findById(Poema.class,   idPoema);
        String html = p.getContenido();
        String mime = "text/html";
        String encoding = "utf-8";
        TextView titulo = (TextView) findViewById(R.id.textView);
        titulo.setText(p.getTitulo());
        WebView b = (WebView) findViewById(R.id.webView);
        b.getSettings().setJavaScriptEnabled(false);
        ImageView img = (ImageView) findViewById(R.id.imageView);
        String url = UtilRed.dirIp+"centralApp/public/imagenes/poemas/"+idPoema+".jpg";
        Picasso.with(this).load(url).resize(150,150)
                .centerCrop().placeholder(R.drawable.sample_0).into(img);
        b.loadDataWithBaseURL(null, html, mime, encoding, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.poema, menu);
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
