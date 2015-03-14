package com.greenbug.carlos.Moddle;


import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Htmlparser {
    public static final String ACCESO_EXISTOSO = "Usted se ha identificado como ";
    public static final String SALIR = "(Salir)";
    public static String usuario;
    public static ArrayList<Semestre> objSemestres;

    Htmlparser(String html){
        Document doc = Jsoup.parse(html);
        usuario = nombreUsuario(doc);
        Log.e("Moodlefication", usuario);
        objSemestres = new ArrayList<Semestre>();
        hallarMaterias(doc);
    }

    public int ocurrencias(char aguja, String pajar){
        int contador = 0;
        for(int i=0; i< pajar.length(); i++){
            if(pajar.charAt(i)==aguja)
                contador++;
        }
        return contador;
    }

    public String ucwords(String palabra){
        String resultado = "";
        for(String a : palabra.split(" ")){
            String copia = (a.charAt(0)+"").toUpperCase();
            a = a.toLowerCase().substring(1);
            resultado += copia+a+" ";
        }
        return resultado.trim();
    }

    public void hallarMaterias(Document doc) {
        Elements nombreSemestres = doc.getElementsByClass("course_tittle_block");
        Elements semestres = doc.getElementsByClass("grupo_semestre");
        int index = 0;
        for(Element semestre : semestres){
            Semestre objSemestre =new Semestre(nombreSemestres.get(index).text());
            Elements materias = semestre.getElementsByTag("a");
            for(Element materia : materias){
                Materia objMareria = new Materia(ucwords(materia.text()), ucwords(materia.attr("href")));
                objSemestre.materias.add(objMareria);
            }
            objSemestres.add(objSemestre);
            index++;
        }
    }

    public String nombreUsuario(Document doc) {
        Element divLogInfo = doc.getElementsByClass("logininfo").first();
        if(divLogInfo!=null){
            String textoLogin = divLogInfo.text();
            if(textoLogin.contains(ACCESO_EXISTOSO)){
                int inicioNombre = ACCESO_EXISTOSO.length();
                int finNombre = textoLogin.indexOf(SALIR);
                String nombreUsuario = textoLogin.substring(inicioNombre,finNombre);
                return ucwords(nombreUsuario);
            }
        }
        return null;
    }
}

class Semestre{
   public String nombre;
   public ArrayList<Materia> materias;
   public Semestre(String nombre){
       this.nombre = nombre;
       materias = new ArrayList<Materia>();
   }

}
class Materia{
    public String nombre,url;
    public Materia(String nombre,String url){
        this.nombre = nombre;
        this.url = url;
    }
}
