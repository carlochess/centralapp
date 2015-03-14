package com.greenbug.carlos.Moddle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.greenbug.carlos.centralapp.R;

import java.util.ArrayList;


public class actividadOpMoodle extends ActionBarActivity {
    private final  String PREFS_NAME = "centralapp";
    private final String llaveMaterias = "materiasMoodle";
    String materias="";
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    LinearLayout l;
    ArrayList<Semestre> semestres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String primeraVez = intent.getStringExtra("inicial");
        setContentView(R.layout.activity_actividad_opciones_moodle);
        TextView nombrePersona = (TextView) findViewById(R.id.textView);
        nombrePersona.setText("Hola "+Modlefication.htmlObj.usuario);
        cargarPreferencias();

        semestres = Modlefication.htmlObj.objSemestres;
        l = (LinearLayout) findViewById(R.id.linearlayout2);
        if(primeraVez.equals("si")) {
            preferencias();
        }else{
            revisarNotificiones();
        }
    }

    private void revisarNotificiones(){
        borrarLayout();
        // Imprimir las matearias que estan siendo monitoreadas
        for(Materia m : semestres.get(0).materias){
            if(existe(m.nombre)){
                // Colocar el titulo y luego las actividades - Encontrar <p class="activity">
                TextView titulo = new TextView(this);
                titulo.setText(m.nombre);
                // titulo.setId(Layout+i);
                Modlefication.revisarNotificacionesMateria(m.url, this);
                l.addView(titulo);
            }
        }
    }

    private void preferencias() {
        borrarLayout();
        TextView t = new TextView(this);
        t.setText("Selecciona las materias que quieras que Moodlefication se haga cargo.");
        /*t.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        t.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;*/
        t.setPadding(10,0,10,10);
        Button continuar = new Button(this);
        continuar.setText("Continuar");
        ExpandableListView listView = new ExpandableListView(this);
        l.addView(t);
        l.addView(continuar);
        l.addView(listView);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revisarNotificiones();
                guardarPreferencias();
            }
        });

        //ExpandableListView listView = (ExpandableListView) findViewById(R.id.laptop_list);
        ListaMaterias adapter = new ListaMaterias(this, semestres);
        listView.setAdapter(adapter);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (groupPosition == 0) {
                    Materia m = semestres.get(groupPosition).materias.get(childPosition);

                    RelativeLayout rel = (RelativeLayout) v;
                    CheckedTextView c = (CheckedTextView) rel.getChildAt(0);
                    if (c.isChecked()) {
                        Toast.makeText(getApplicationContext(), "Eliminada", Toast.LENGTH_SHORT).show();
                        eliminarMateria(m.nombre);
                    } else {
                        Toast.makeText(getApplicationContext(), "Agregada", Toast.LENGTH_SHORT).show();
                        agregarMateria(m.nombre);
                    }
                    Log.e("Materias", materias);
                    c.toggle();
                    return true;
                }
                return false;
            }
        });
    }

    private void borrarLayout() {
        if(l.getChildCount() > 0) l.removeAllViews();
    }

    public void cargarPreferencias(){
        settings = getSharedPreferences(PREFS_NAME, 0);
        materias = settings.getString(llaveMaterias,"");
        editor = settings.edit();
        editor.apply();
    }

    public void guardarPreferencias(){
        editor.putString(llaveMaterias, materias);
        editor.commit();
    }

    public boolean existe(String materia){
        return materias.contains(materia);
    }

    public boolean agregarMateria(String materia){
        if(!existe(materia)){
            materias+=materia+"&";
            return true;
        }
        return false;
    }

    public boolean eliminarMateria(String materia){
        if(existe(materia)){
            int inicioStr = materias.indexOf(materia);
            int finalStr = inicioStr+materia.length()+1;
            String copia = materias;
            materias = materias.substring(0, inicioStr)+ copia.substring(finalStr);
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actividad_opciones_moodle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Log.e("Accion", "Configuracion");
            preferencias();
            return true;
        }
        if (id == R.id.sesion) {
            editor.putString("loginMoodle", "");
            editor.putString("passwordMoodle", "");
            editor.putString("materiasMoodle", "");
            editor.commit();
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void añadirNotificaciones(final ArrayList<String> notificiones){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (String n : notificiones) {
                    TextView notificacion = new TextView(getApplicationContext());
                    notificacion.setText(n);
                    l.addView(notificacion);
                }
            }
        });
    }
}
