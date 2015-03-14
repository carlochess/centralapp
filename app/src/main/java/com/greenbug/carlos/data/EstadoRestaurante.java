package com.greenbug.carlos.data;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Iterator;

public class EstadoRestaurante extends SugarRecord<EstadoRestaurante> {
    String año, mes, dia, dianombre, horaconsulta;
    float calificacion;

    public EstadoRestaurante() {

    }

    public String getAño() {
        return año;
    }

    public String getMes() {
        return mes;
    }

    public String getDia() {
        return dia;
    }

    public String getDianombre() {
        return dianombre;
    }

    public String getHoraconsulta() {
        return horaconsulta;
    }

    public float getCalificacion() {
        return calificacion;
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
