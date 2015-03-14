package com.greenbug.carlos.data;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Noticia extends SugarRecord<Noticia> {
    String titulo;
    String fecha;
    String autor;
    String contenido;
    int idNoticia;

    public Noticia(){

    }

    public int getIdNoticia() {
        return idNoticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public String getAutor() {
        return autor;
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
