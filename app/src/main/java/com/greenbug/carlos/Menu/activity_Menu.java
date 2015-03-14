package com.greenbug.carlos.Menu;

import android.content.Context;
import android.os.StrictMode;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenbug.carlos.centralapp.R;
import com.greenbug.carlos.data.Menu;
import com.greenbug.carlos.red.RedMenu;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import it.sephiroth.android.library.widget.HListView;


public class activity_Menu extends ActionBarActivity {
    List<Menu> menus;
    AdaptadorMenus adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //--------------
        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        //args.putInt(CaldroidFragment.START_DAY_OF_WEEK, CaldroidFragment.MONDAY);
        caldroidFragment.setArguments(args);
        FragmentTransaction k = getSupportFragmentManager().beginTransaction();
        k.replace(R.id.calendar1, caldroidFragment);
        k.commit();
        /*
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final CaldroidListener listener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                Toast.makeText(getApplicationContext(), dateFormat.format(date),
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onChangeMonth(int month, int year) {
                String text = "month: " + month + " year: " + year;
                Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT).show();
            }
        };

        caldroidFragment.setCaldroidListener(listener);*/
        //---------

        int semana = -1;
        try {
            semana = getSemana();
        }catch (Exception e ){

        }
        menus = Menu.findWithQuery(Menu.class, "Select * from Menu where semana = ?", semana+"");

        // reviso los días de la semana que faltan
        ArrayList<String> dias = new ArrayList<String>();
        try {
            dias = getDiasHabiles();
        }catch (ParseException e){

        }
        String faltan = "";
        for(String m : dias){
            boolean esta = false;
            for(Menu menu : menus){
                if(menu.getFecha().equals(m)){
                    esta = true;
                    break;
                }
            }
            if(!esta)
                faltan += m +",";
        }

        if(faltan.length()>2){
            faltan = faltan.substring(0, faltan.length()-1);
        }

        //Si no existe entonces carguelo desde la red.
        try {
            RedMenu.descargarMenuSemana(faltan, this);
        }catch (Exception e){

        }
        //----------------------
        HListView vistaMenus = (HListView)findViewById(R.id.listView);
        adp = new AdaptadorMenus(menus);
        vistaMenus.setAdapter(adp);
    }

    public static String getActualDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new java.util.Date();
        return dateFormat.format(date);
    }

    public int getSemana() throws Exception {
        String input = getActualDate();
        String format = "yyyy-MM-dd";

        SimpleDateFormat df = new SimpleDateFormat(format);
        Date date = df.parse(input);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public static ArrayList<String> getDiasHabiles() throws ParseException {
        String input = getActualDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(input);

        Calendar now = Calendar.getInstance();
        now.setTime(date);

        ArrayList<String> dias = new ArrayList<String>();
        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 2;
        now.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 5; i++) {
            dias.add(format.format(now.getTime()));
            now.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dias;
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
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

    public void actualizarVista(final Menu menu[]) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                menus.addAll(Arrays.asList(menu));
                adp.notifyDataSetChanged();
            }});
    }

    class AdaptadorMenus  extends BaseAdapter {
        List<Menu> menus;
        AdaptadorMenus(List<Menu> a){
            menus = a;
        }
        @Override
        public int getCount() {
            return menus.size();
        }

        @Override
        public Object getItem(int i) {
            return menus.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null)
            {
                LayoutInflater inflater = (LayoutInflater) activity_Menu.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.listviewitemmenu, viewGroup,false);
            }
            Menu menu = menus.get(i);
            TextView dia = (TextView)view.findViewById(R.id.dia);
            TextView sopa = (TextView)view.findViewById(R.id.sopa);
            TextView arroz = (TextView)view.findViewById(R.id.arroz);
            TextView jugo = (TextView)view.findViewById(R.id.jugo);
            TextView ensalada = (TextView)view.findViewById(R.id.ensalada);
            TextView carne = (TextView)view.findViewById(R.id.carne);
            dia.setText(menu.getDianombre());
            sopa.setText(menu.getSopa());
            arroz.setText(menu.getArroz());
            jugo.setText(menu.getJugo());
            ensalada.setText(menu.getEnsalada());
            carne.setText(menu.getCarne());
            return view;
        }
    }
}
