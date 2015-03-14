package com.greenbug.carlos.data;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Iterator;

public class Menu extends SugarRecord<Menu> {
    String fecha, diaNombre, horaconsulta,sopa, jugo, arroz, carne, ensalada;
    int semana;
    String anomalias;

    public Menu() {}

    public String toString(){
        return sopa+" "+jugo+" "+arroz+" "+carne+" "+ensalada;
    }

    public int getSemana() {
        return semana;
    }

    public String getFecha(){ return fecha;}

    public String getDianombre() {
        return diaNombre;
    }

    public String getHoraconsulta() {
        return horaconsulta;
    }

    public String getSopa() {
        return sopa;
    }

    public String getJugo() {
        return jugo;
    }

    public String getArroz() {
        return arroz;
    }

    public String getCarne() {
        return carne;
    }

    public String getEnsalada() {
        return ensalada;
    }

    public String getAnomalias() {
        return anomalias;
    }

    public static <T> ArrayList<T> toArrayList(Iterator<T> iter) {
        ArrayList<T> copia = new ArrayList<T>();
        if(null == iter)
            return copia;
        while (iter.hasNext())
            copia.add(iter.next());
        return copia;
    }
}
