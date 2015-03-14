package com.greenbug.carlos.data;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Registro extends SugarRecord<Registro> {

    public float calificacion; // "3.33333"
    public String hora; // "2015-02-08 15:27:24"
    public String llave; // "llave":"2015-02-08-15-1"
    public int cuartil; // [0-3]

    public Registro() {}

    @Override
    public String
    toString() {
        return "Registro{" +
                "calificacion=" + calificacion +
                ", hora='" + hora + '\'' +
                ", llave='" + llave + '\'' +
                ", cuartil=" + cuartil +
                '}';
    }

    public Registro(float calificacion, String hora, int cuartil, String llave) {
        this.calificacion = calificacion;
        this.hora = hora;
        this.cuartil = cuartil;
        this.llave = llave;
    }

    public String getLlave(){
        return llave;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public String getHora() {
        return hora;
    }

    public int getCuartil() {
        return cuartil;
    }

    public static <T> ArrayList<T> toArrayList(Iterator<T> iter) {
        ArrayList<T> copia = new ArrayList<T>();
        if(null == iter)
            return copia;
        while (iter.hasNext())
            copia.add(iter.next());
        return copia;
    }
    public static <T> ArrayList<T> toArrayList(List<T> iter) {
        ArrayList<T> copia = new ArrayList<T>();
        if(null == iter)
            return copia;
        for(T t : iter){
            copia.add(t);
        }
        return copia;
    }
}
