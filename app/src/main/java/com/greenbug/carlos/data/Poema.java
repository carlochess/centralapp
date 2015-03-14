package com.greenbug.carlos.data;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Poema  extends SugarRecord<Poema> {
    String titulo;
    String fecha;
    String autor;
    String contenido;
    int idPoema;
    public Poema(){
        this(1, "Veredas de Buenos Aires","Julio Cortazar", "<span style=\"font-size: 12pt; line-height:12pt\"><span style=\"color: green\">De pibes la llamamos la vedera\n" +
                "<br>y a ella le gustó que la quisiéramos.\n" +
                "<br>En su lomo sufrido dibujamos tantas rayuelas.\n" +
                "<br>\n" +
                "<br>Después, ya más compadres, taconeando,\n" +
                "<br>dimos vueltas manzana con la barra,\n" +
                "<br>silbando fuerte para que la rubia\n" +
                "<br>del almacén saliera a la ventana.\n" +
                "<br>\n" +
                "<br>A mi me tocó un día irme muy lejos\n" +
                "<br>pero no me olvidé de las vederas.\n" +
                "<br>Aquí o allá las siento en los tamangos\n" +
                "<br>como la fiel caricia de mi tierra.</span></span>","19/01/2015");
    }
    public Poema(int idPoema, String titulo,String autor,String contenido){
        this(idPoema, titulo,autor,contenido, "Fecha de hoy");
    }
    public Poema(int idPoema, String titulo,String autor,String contenido,String fecha){
        this.titulo = titulo;
        this.autor = autor;
        this.contenido = contenido;
        this.fecha = fecha;
        this.idPoema=idPoema;
    }

    public int getIdPoema() {
        return idPoema;
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
