package com.greenbug.carlos.Galeria;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.greenbug.carlos.centralapp.R;
import com.greenbug.carlos.red.UtilRed;
import com.squareup.picasso.Picasso;

public class activityIMG extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int idImagen = getIntent().getExtras().getInt("imagen");
        setContentView(R.layout.activity_activity_img);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView img = (ImageView) findViewById(R.id.imageView);

        String url = UtilRed.dirIp+"centralApp/public/imagenes/restaurante/"+idImagen+".jpeg";
        Log.e("Img", url);
        Picasso.with(this).load(url)
                .centerCrop().placeholder(R.drawable.sample_0).fit().into(img);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_img, menu);
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
