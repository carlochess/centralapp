package com.greenbug.carlos.Historial;

import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.greenbug.carlos.centralapp.R;
import com.greenbug.carlos.data.Registro;
import com.greenbug.carlos.red.RedHistorial;



import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class Historial extends ActionBarActivity {
    ArrayList<Registro> registros;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    BarChart grafico;
    String fechaUltimo = getFechaActual()+" 00:00:00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView t = (TextView) findViewById(R.id.textView);
        grafico = (BarChart) findViewById(R.id.barchart);
        grafico.setShowDecimal(true);
        t.setText("Historial hoy ("+getFechaActual());
        prepararDatos();
        dibujarGrafico();
        try {
            RedHistorial.descargarHistorialDia(fechaUltimo, this);
        }catch (Exception e){
            Log.e("Hola", "Error");
        }
    }

    public String getFechaActual(){
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    void prepararDatos(){
        registros = Registro.toArrayList(Registro.find(Registro.class, "DATE(hora) = DATE( ? ) ORDER BY hora ASC", getFechaActual()));
        if(registros.size()>0)
            fechaUltimo = registros.get(registros.size()-1).getHora();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.historial, menu);
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

    String colores[] = new String[]{
            "#FE6DA8","#56B7F1","#CDA67F","#FED70E"
    };

    public void dibujarGrafico(){
        grafico.clearChart();
        for(int i =0; i< registros.size(); i++) {
            String fechaHora[] = registros.get(i).hora.split(" ");
            grafico.addBar(new BarModel(fechaHora[1], registros.get(i).calificacion*20, Color.parseColor(colores[i%4])));
        }
        grafico.startAnimation();
    }

    public void actualizarVista(final Registro[] regs) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                registros.addAll(Arrays.asList(regs));
                dibujarGrafico();
            }
        });
    }
}
