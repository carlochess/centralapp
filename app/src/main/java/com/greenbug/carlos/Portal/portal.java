package com.greenbug.carlos.Portal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.text.Html;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.greenbug.carlos.Canvas.Bienvenido;
import com.greenbug.carlos.GCM.GCM;
import com.greenbug.carlos.Menu.activity_Menu;
import com.greenbug.carlos.Poema.activity_Poemas;
import com.greenbug.carlos.Galeria.Galeria;
import com.greenbug.carlos.Historial.Historial;
import com.greenbug.carlos.Noticias.activity_Noticias;
import com.greenbug.carlos.centralapp.R;
import com.greenbug.carlos.Moddle.Modlefication;
import com.greenbug.carlos.Moddle.actividadOpMoodle;
import com.greenbug.carlos.RealidadAumentada.RealidadAumentada;
import com.greenbug.carlos.centralapp.Reto;
import com.greenbug.carlos.red.RedPortal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class portal extends Activity {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SharedPreferences settings;
    private TextView estadoCentral,estadoTickets,menu;
    private Context c;
    private boolean tieneCamara;
    private Dialog dialog;
    ProgressDialog cargando;

    @Override
    protected void onPause() {
        super.onPause();
        if(dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);
        c = this;
        PackageManager packageManager = c.getPackageManager();
        tieneCamara = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA);
        listeners();

        settings = getSharedPreferences("centralapp", 0);
        actualizarPortal();

        GCM g = new GCM(c);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    /*
    public enum subMenums {
        ESTADO_CENTRAL,
        GALERIA,
        HISTORIAL,
        ESTADO_TICKETS,
        NOTICIAS,
        MENU,
        MENSAJES,
        POEMAS,
        ACERCA_DE
    }*/
    public void listeners(){
        TextView galeria = (TextView ) findViewById(R.id.galeria);
        TextView histoial = (TextView ) findViewById(R.id.histoial);
        TextView noticias = (TextView ) findViewById(R.id.noticias);
        TextView moodle = (TextView ) findViewById(R.id.moodle);
        TextView poemas = (TextView ) findViewById(R.id.poemas);
        TextView retos = (TextView ) findViewById(R.id.retos);
        estadoCentral = (TextView ) findViewById(R.id.estadoCentral);
        estadoTickets = (TextView ) findViewById(R.id.estadoTickets);
        menu = (TextView ) findViewById(R.id.menu);

        estadoCentral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ObjectAnimator.ofFloat(estadoCentral, "rotationY", 0, 90).start();
                click(0);
            }
        });

        noticias.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(c, Reto.class);
                startActivity(intent);
                return false;
            }
        });
        poemas.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                /*Intent intent = new Intent(c, RealidadAumentada.class);
                startActivity(intent);*/
                return false;
            }
        });

        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(1);
            }
        });
        histoial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(2);
            }
        });
        estadoTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(3);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(4);
            }
        });
        noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(5);
            }
        });
        moodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(6);
            }
        });
        poemas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(7);
            }
        });

        /* ImageView img = (ImageView) findViewById(R.id.imgNoticias); relativo
        RelativeLayout lay = (RelativeLayout) findViewById(R.id.relativo);
        com.nineoldandroids.view.ViewPropertyAnimator.animate(lay).setDuration(2000).rotationYBy(720).translationX(120).translationY(-120);*/

        /*ValueAnimator colorAnim = ObjectAnimator.ofInt(retos, "backgroundColor",0xFFFF8080, 0xFF8080FF);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();*/

        retos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(8);
            }
        });
    }



    public void click(int position) {
        Intent intent;
        switch (position){
            case 0: // Estado Central
                crearDialogoCalificacion(true);
                break;
            case 1: // Galeria
                intent = new Intent(this, Galeria.class);
                intent.putExtra("MENSAJE", "Galeria");
                startActivity(intent);
                break;
            case 2: // Historial
                intent = new Intent(this, Historial.class);
                startActivity(intent);
                break;
            case 3: // Estado tickets
                crearDialogoCalificacion(false);
                break;
            case 4: // Menu
                intent = new Intent(this, activity_Menu.class);
                startActivity(intent);
                break;
            case 5: // Noticias
                intent = new Intent(this, activity_Noticias.class);
                startActivity(intent);
                break;
            case 6: // moodlefication
                crearDialogoMoodlefication();
                break;
            case 7: // poemas
                intent = new Intent(c, RealidadAumentada.class);
                startActivity(intent);
                break;
            case 8: // Biblio
                final EditText input = new EditText(this);
                new AlertDialog.Builder(this)
                        .setTitle("Biblioteca")
                        .setMessage("Ingresa tu código")
                        .setView(input)
                        .setPositiveButton("Entrar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                llamarBiblioteca(input.getText().toString());
                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                }).show();

                break;
        }
    }

    public void llamarBiblioteca(String cod) {
        Intent intent = new Intent(this, Bienvenido.class);
        intent.putExtra("codigo", cod);
        startActivity(intent);
    }

    //--------------------

    private void crearDialogoMoodlefication() {
        final SharedPreferences settings = getSharedPreferences("centralapp", 0);
        String materias = settings.getString("materiasMoodle","");
        // Si tenemos los datos del usuario
        if(materias!=null && materias.length()>1){
            String login = settings.getString("loginMoodle","");
            String password = settings.getString("passwordMoodle","");
            conectar(login,password, true);
            cargando = new ProgressDialog(c);
            cargando.setMessage("Entrando al moodle");
            cargando.show();
        }else {
            dialog = new Dialog(this);
            dialog.setTitle("Modlefication");
            dialog.setContentView(R.layout.dialogomoodle);
            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            dialog.show();
            final EditText login = (EditText) dialog.findViewById(R.id.loginEdit);
            final EditText password = (EditText) dialog.findViewById(R.id.passwordEdit);

            Button entrar = (Button) dialog.findViewById(R.id.btnentrar);
            entrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    conectar(login.getText().toString(), password.getText().toString(), false);
                    dialog.dismiss();
                    cargando = new ProgressDialog(c);
                    cargando.setMessage("Entrando al moodle");
                    cargando.show();
                }
            });
        }
    }
    public void conectar(String login, String password, boolean antiguo){
        Modlefication.asignarDatos(login, password);
        Modlefication.conectar(this,antiguo);
    }

    public void entrarActividadMoodle(String login, String password, boolean antiguo){
        if (Modlefication.htmlObj.usuario != null) {
            //--- Si el login es existoso
            if(!antiguo) {

                SharedPreferences.Editor editor = settings.edit();
                editor.putString("loginMoodle", login);
                editor.putString("passwordMoodle", password);
                editor.apply();
            }
            //------------
            Intent intent = new Intent(this,actividadOpMoodle.class);
            intent.putExtra("inicial", (antiguo)?"no":"si");
            startActivity(intent);
        }else {
            this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(c, "Login incorrecto", Toast.LENGTH_SHORT).show();
                }
            });
        }
        cargando.dismiss();
    }

    private void crearDialogoCalificacion(final boolean restaurante) {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion < Build.VERSION_CODES.HONEYCOMB){
            Intent intent = new Intent(this, Calificacion.class);
            intent.putExtra("restaurante", restaurante);
            startActivity(intent);
        } else {
            Dialogo d = new Dialogo(this, restaurante);
            d.show();
        }
    }

    public String getActualDate(){
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    public void actualizarPortal(){
        // Verificamos cuando fue la última solicitud,
        String ultimaSolicitud = settings.getString("ultimaSolicitud","");
        if(ultimaSolicitud == null) {
            iniciarRepetitivo();
            return;
        }
        String fechaActual = getActualDate();
        long diffInMinutes = 5;
        try {
            Date startDate = dateFormat.parse(ultimaSolicitud);
            Date endDate = dateFormat.parse(fechaActual);
            long duration  = endDate.getTime() - startDate.getTime();
            diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        }catch (ParseException e){

        }
        // Si han pasado > 5 min, entonces envielo,
        if(diffInMinutes > 5) {
            iniciarRepetitivo();
        }
        else {
            // De lo contrario, ESPERA = 5 - (ultima solicitud-tiempo actual)
            int ESPERA = 5-(int)diffInMinutes;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    iniciarRepetitivo();
                }
            }, ESPERA);
        }
    }

    public void iniciarRepetitivo() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            // SI esta entre 11 am y 3 pm Y lunes a viernes
                            verificarHorario();

                        } catch (Exception e) {

                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 1000*60*1);
    }

    public void verificarHorario() throws Exception{
        //Date fecha = dateFormat.parse(getActualDate());
        RedPortal.descargarPortal(getActualDate(), this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.portal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void refrescarPortal(final float estadoRestaurante,final  float estadoTickes, final com.greenbug.carlos.data.Menu menuDelDia) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                estadoCentral.setText(Html.fromHtml("Estado central" + "<br/>" + (estadoRestaurante*20) + " %"));
                estadoTickets.setText(Html.fromHtml("Estado tickets"+"<br/>"+ (estadoTickes*20) + " %"));
                if(menuDelDia!= null)
                    menu.setText(Html.fromHtml("Menu"+"<br/><small>"+menuDelDia+"</small>"));
            }
        });
    }

    //---------------------- GCM

}