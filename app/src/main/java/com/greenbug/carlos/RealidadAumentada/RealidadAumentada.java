package com.greenbug.carlos.RealidadAumentada;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.greenbug.carlos.centralapp.R;


public class RealidadAumentada extends Activity{
    private LeyendaView mDrawView;
    LocationManager locMgr;
    private String TAG = "RealidadAumentada";
    private SensorManager mSensorManager;
    private Sensor mSensor;

    private final SensorEventListener mListener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent event) {
            if (mDrawView != null) {
                // Angulo entre el norte magnetico y la dirección en la que apunta del celular
                // Azimuth
                // 0=Norte, 90=Este, 180=Sur, 270=Oeste
                mDrawView.setCorreccion(event.values[0]);
                mDrawView.invalidate();
            }
        }
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    LocationProvider high;
    LocationListener l;
    String provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        setContentView(R.layout.activity_realidad_aumentada);

        final Criteria c = new Criteria();
        /*c.setAccuracy(Criteria.ACCURACY_FINE);
        c.setAltitudeRequired(false);
        c.setBearingRequired(false);
        c.setSpeedRequired(false);
        c.setCostAllowed(true);
        c.setPowerRequirement(Criteria.POWER_HIGH);*/

        locMgr = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if ( !locMgr.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            mostrarMensajeGPS();
        }
        provider = locMgr.getBestProvider(c, true);
        high = locMgr.getProvider(provider);
        String proveedores ="";
        for(String providers : locMgr.getProviders(c, true)){
            proveedores += providers;
        }
        Log.e("", "Proveedores " + proveedores);

        l = new LocationListener() {

            public void onLocationChanged(Location location) {
                mDrawView.setPosicion(location.getLatitude(), location.getLongitude());
                mDrawView.invalidate();
            }

            public void onStatusChanged(String s, int i, Bundle bundle) {}

            public void onProviderEnabled(String s) {
                String prov = locMgr.getBestProvider(c, true);
                locMgr.requestLocationUpdates(prov, 0, 0f, this);
                //onLocationChanged(location);
                Log.e("", "Habilitado el prov " + s);
            }

            public void onProviderDisabled(String s) {
                String prov = locMgr.getBestProvider(c, true);
                locMgr.requestLocationUpdates(prov, 0, 0f, this);
                //onLocationChanged(location);
                Log.e("", "Deshabilitado el prov " + s);
            }
        };
        locMgr.requestLocationUpdates(high.getName(), 0, 0f, l);
        mDrawView = (LeyendaView) findViewById(R.id.drawSurfaceView);
    }

    private void mostrarMensajeGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Parece que tu GPS no esta encendido, ¿Quieres encenderlo?")
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

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mListener, mSensor, SensorManager.SENSOR_DELAY_GAME);
        locMgr.requestLocationUpdates(provider, 0, 0, l);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mListener);
        locMgr.removeUpdates(l);
        super.onPause();
    }
}
