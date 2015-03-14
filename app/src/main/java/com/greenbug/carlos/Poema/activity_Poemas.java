package com.greenbug.carlos.Poema;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.*;
import com.google.android.gms.common.GooglePlayServicesClient.*;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.greenbug.carlos.centralapp.R;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.greenbug.carlos.data.Poema;
import com.greenbug.carlos.red.RedPoemas;

import java.util.ArrayList;


public class activity_Poemas extends ActionBarActivity   implements SwipeRefreshLayout.OnRefreshListener,
    View.OnClickListener, ConnectionCallbacks, OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    SwipeRefreshLayout swipeLayout;
    private GridviewAdapter mAdapter;
    private ArrayList<Poema> poemas;
    private GridView gridView;
    private long ultimoLista=-1;
    //------- G +
    private static final String TAG = "ExampleActivity";

    private ProgressDialog mConnectionProgressDialog;
    private GoogleApiClient mPlusClient;
    private ConnectionResult mConnectionResult;

    SignInButton entrar;
    Button adios,publicar;
    TextView oExclusivo;
    String nombreCuenta="";
    //------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poemas);
        prepareList();

        mAdapter = new GridviewAdapter(this,poemas);

        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(mAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
                verPoemas(position);
            }
        });
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeLayout.setOnRefreshListener(this);
        /*swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);*/

        try {
            Log.e("Hola", "Leyendo poemas");
            RedPoemas.descargarPoemas(ultimoLista, this);
        }catch (Exception e){
            Log.e("Hola", "Error");
        }
        // Se tiene que mostrar esta barra de progreso si no se resuelve el fallo de conexión.
        mConnectionProgressDialog = new ProgressDialog(this);
        mConnectionProgressDialog.setMessage("Signing in...");
        entrar = (SignInButton) findViewById(R.id.sign_in_button);
        adios = (Button) findViewById(R.id.sign_out_button);
        oExclusivo = (TextView) findViewById(R.id.o);
        publicar = (Button) findViewById(R.id.publicar);

        entrar.setOnClickListener(this);
        adios.setOnClickListener(this);
        publicar.setOnClickListener(this);
        iniciarCliente();
        if( true /* Es primera vez */) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
    }

    public void iniciarCliente(){
        if(mPlusClient== null) {
            mPlusClient = new GoogleApiClient.Builder(this)
                    .addApi(Plus.API)
                    .addScope(Plus.SCOPE_PLUS_PROFILE)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
    }

    public void onRefresh() {
        try {
            RedPoemas.descargarPoemas(ultimoLista,this);
        }catch (Exception e){
            Log.e("Hola", "Error");
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(false);
            }
        }, 5000);
    }

    public void verPoemas(int position){
        Intent intent = new Intent(this, activity_Poema.class);
        intent.putExtra("idPoema", poemas.get(position).getId());
        startActivity(intent);
    }

    public void prepareList()
    {
        poemas = Poema.toArrayList(Poema.find(Poema.class,"",new String[]{},"","id DESC","20"));
        if(poemas.size()>0){
            ultimoLista = ((poemas.get(0)).getIdPoema());
        }
    }

    ///----------- G+


    @Override
    protected void onStart() {
        super.onStart();
        if(mPlusClient!=null)
            mPlusClient.connect();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if(mPlusClient!=null && mPlusClient.isConnected())
            mPlusClient.disconnect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
                    0).show();
            return;
        }

        if (!mIntentInProgress) {
            mConnectionResult = result;

            if (mSignInClicked) {
                resolveSignInError();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        if (requestCode == RC_SIGN_IN) {
            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if (!mPlusClient.isConnecting()) {
                mPlusClient.connect();
            }
        }
    }

    @Override
    public void onConnected(Bundle b) {
        nombreCuenta = Plus.AccountApi.getAccountName(mPlusClient);
        int posicionArroba = nombreCuenta.indexOf("@");
        String dominioCorreo = nombreCuenta.substring(posicionArroba);
        mConnectionProgressDialog.dismiss();
        if(!dominioCorreo.equals("@correounivalle.edu.co")){
            Toast.makeText(this, "Lo sentimos, solo Univalle", Toast.LENGTH_LONG).show();
            desconectar();
            return;
        }
        entrar.setVisibility(View.GONE);
        adios.setVisibility(View.VISIBLE);
        publicar.setVisibility(View.VISIBLE);
        oExclusivo.setVisibility(View.VISIBLE);
        Toast.makeText(this, nombreCuenta + " is connected.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "disconnected");
    }

    @Override
    public void onDisconnected() {
        Log.d(TAG, "disconnected");
    }

    private static final int RC_SIGN_IN = 0;
    private boolean mIntentInProgress;
    private boolean mSignInClicked;

    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mPlusClient.connect();
            }
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.sign_in_button && !mPlusClient.isConnected()) {
            if (!mPlusClient.isConnecting()) {
                mSignInClicked = true;
                resolveSignInError();
            }
        }
        if (view.getId() == R.id.sign_out_button) {
            desconectar();
        }
        if(view.getId() == R.id.publicar){
            Intent i = new Intent(this, PublicarPoema.class);
            i.putExtra("cuenta", nombreCuenta);
            startActivity(i);
        }
    }

    public void desconectar(){
        if (mPlusClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mPlusClient);
            mPlusClient.disconnect();
            entrar.setVisibility(View.VISIBLE);
            adios.setVisibility(View.GONE);
            publicar.setVisibility(View.GONE);
            oExclusivo.setVisibility(View.GONE);
        }
    }

    //------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.poemas, menu);
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

    public void actualizarLista(final Poema[] p) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ultimoLista=p[0].getId();
                for(Poema a : p)
                    poemas.add(0,a);
                mAdapter.notifyDataSetChanged();
            }
        });
    }


}
