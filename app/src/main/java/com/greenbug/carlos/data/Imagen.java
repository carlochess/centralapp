package com.greenbug.carlos.data;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Imagen extends SugarRecord<Imagen> {
    String hora;
    String fecha;
    String formato;
    String nombre;
    int baneada = 0;
    int idImagen;
    public Imagen(){

    }

    public Imagen(int idImagen, String hora, String fecha, String formato, String nombre) {
        this.hora = hora;
        this.fecha = fecha;
        this.formato = formato;
        this.nombre = nombre;
        baneada = 0;
        this.idImagen = idImagen;
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

    public String getHora() {
        return hora;
    }

    public String getFecha() {
        return fecha;
    }

    public String getFormato() {
        return formato;
    }

    public String getNombre() {
        return nombre;
    }

    public int getBaneada() {
        return baneada;
    }

    public int getIdImagen() {
        return idImagen;
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "hora='" + hora + '\'' +
                ", fecha='" + fecha + '\'' +
                ", formato='" + formato + '\'' +
                ", nombre='" + nombre + '\'' +
                ", baneada=" + baneada +
                ", idImagen=" + idImagen +
                '}';
    }
}
