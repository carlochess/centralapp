package com.greenbug.carlos.centralapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;


public class Reto extends Activity {
    TextView vista;
    Location usuario,destino;
    LocationManager locationManager;
    SharedPreferences prefs;
    SharedPreferences.Editor editor ;
    float latitud[] = new float[]{3.3758472f, 3.3760641f,3.371843f,3.371214f};
    float longitud[] = new float[]{-76.5326928f, -76.5343565f,-76.532616f,-76.535641f};
    int n;
    LinearLayout l;
    float distanciaFic = 0.0f;
    DecimalFormat df = new DecimalFormat("#.00");
    /**
     Pelicula
     3.3758472,-76.5326928

     Biblioteca
     3.3760641,-76.5343565

     Cancha futbol
     3.371843,-76.532616

     Edu física
     3.371214,-76.535641
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reto);
        prefs = getSharedPreferences("Puntos", MODE_PRIVATE);
        editor = prefs.edit();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        editor.putString("p", "");
        editor.commit();

        if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            mostrarMensajeGPS();
        }
        usuario = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        String nivel = nivel();

        if(nivel.equals("-1")){
            guardarNivel(0);
            n++;
        }
        l = (LinearLayout) findViewById(R.id.cosa);
        n = Integer.parseInt(nivel);

        destino = new Location("");
        destino.setLongitude(longitud[n]);
        destino.setLatitude(latitud[n]);
        vista = (TextView) findViewById(R.id.vi);

        actualizarDistancia();
    }
    LocationListener locationListener ;
    String provider;
    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 0, 0, locationListener );
    }

    @Override
    protected void onPause() {
        locationManager.removeUpdates(locationListener );
        super.onPause();
    }

    public void actualizarFondo(float distancia){
        int limite = 1200;
        int c = Color.RED;
        if(distancia < limite) {
            float t = distancia / limite;
            float r = (1 - t) * 255;
            float g = 0;
            float b = t * 255;
            c = new Color().argb(250, (int) r, (int) g, (int) b);
        }
        l.setBackgroundColor(c);
    }


    private void guardarNivel(int i) {
        editor.putString("nivel", i+"");
        editor.apply();
    }

    public String nivel(){
        return prefs.getString("nivel", "-1");
    }


    private void mostrarMensajeGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Parece que tu GPS no ha sido iniciado, ¿Quieres encenderlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alert.show();
            }
        });
    }

    private void mostrarMensajesObjetivo(String g) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(g)
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.dismiss();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAccuracy(Criteria.ACCURACY_FINE );
        criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
        criteria.setSpeedRequired(true);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);


        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                //if(isBetterLocation(usuario, location)){
                    usuario = location;
                    actualizarDistancia();
                //}
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };
        provider = locationManager.getBestProvider(criteria, true);
        locationManager.requestLocationUpdates(provider, 1L, 2F, locationListener);
    }

    public void actualizarDistancia(){
        if(destino==null || usuario== null) {
            Log.e("", "No puedo hallar  la posición del usuario");
            return;
        }
        if(vista==null ) {
            Log.e("", "Vista nula");
            return;
        }
        float distancia = calcularDistancia(usuario.getLatitude(), usuario.getLongitude(), destino.getLatitude(), destino.getLongitude());
        vista.setText("Distancia: "+ df.format(distancia)+"m");
        actualizarFondo(distancia);
        if(distancia<25){
            subirNivel();
        }
    }

    public void subirNivel(){
        n++;
        if(n > latitud.length){
            mostrarMensajesObjetivo("¡Ganaste!");
        }else{
            mostrarMensajesObjetivo("¡Has encontrado un helado!");
            destino = new Location("");
            destino.setLongitude(longitud[n]);
            destino.setLatitude(latitud[n]);
            guardarNivel(n);
        }
    }

    public static float calcularDistancia(double startLati, double startLongi, double goalLati, double goalLongi){
        float[] resultArray = new float[99];
        Location.distanceBetween(startLati, startLongi, goalLati, goalLongi, resultArray);
        return resultArray[0];
    }
}
