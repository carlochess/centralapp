package com.greenbug.carlos.centralapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.AsyncTask;

import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.greenbug.carlos.data.Noticia;



public class Inicio extends Activity {

    LinearLayout linear;
    RelativeLayout relative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(primeraVez()) {
            setContentView(R.layout.activity_inicio);

            /*
            final WindowManager.LayoutParams param=new WindowManager.LayoutParams();
            param.flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            final View view=findViewById(R.id.hola);
            final ViewGroup parent=(ViewGroup)view.getParent();
            if(parent!=null)
                parent.removeView(view);
            param.format= PixelFormat.RGBA_8888;
            param.type=WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            param.gravity= Gravity.TOP|Gravity.LEFT;
            param.width=parent!=null? WindowManager.LayoutParams.WRAP_CONTENT:view.getLayoutParams().width;
            param.height=parent!=null? WindowManager.LayoutParams.WRAP_CONTENT:view.getLayoutParams().height;
            final WindowManager wmgr=(WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            wmgr.addView(view,param);
            */
            Button escanear = (Button) findViewById(R.id.button);
            Button continuar = (Button) findViewById(R.id.button2);
            escanear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tomarCodigo();
                }
            });

            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    guardarCodigo(((TextView) findViewById(R.id.textView)).getText().toString());
                }
            });
            relative = (RelativeLayout) findViewById(R.id.relative);
            linear = (LinearLayout) findViewById(R.id.linear);
            iniciarBaseDeDatos();
        }else{
            entrar();
        }
    }

    void iniciarBaseDeDatos(){
            new AsyncTask<Void, Void, Boolean>() {
                protected Boolean doInBackground(Void... params) {
                    Noticia n = new Noticia();
                    n.save();
                    n.delete();
                    return true;
                }
                protected void onPostExecute(Boolean msg) {
                    mostrarInterfaz();
                }
            }.execute(null, null, null);
    }

    private void mostrarInterfaz() {
        relative.setVisibility(View.VISIBLE);
        linear.setVisibility(View.VISIBLE);
    }

    void tomarCodigo(){
        IntentIntegrator i = new IntentIntegrator(this);
        i.setPrompt("Coloca un carné de la U");
        i.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
            } else {
                guardarCodigo(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public boolean primeraVez(){
        SharedPreferences sharedpreferences = getSharedPreferences("centralapp", Context.MODE_PRIVATE);
        String codigo = sharedpreferences.getString("codigo", "");
        Log.e("Entrar", codigo);
        return codigo.isEmpty();
    }

    public void entrar(){
        startActivity(new Intent(this, com.greenbug.carlos.Portal.portal.class));
    }

    private void guardarCodigo(String codigo) {
        SharedPreferences sharedpreferences = getSharedPreferences("centralapp", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("codigo", codigo);
        editor.apply();
        entrar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inicio, menu);
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
