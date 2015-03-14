package com.greenbug.carlos.Moddle;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HtmlNotificacionesparser {
    private ArrayList<String> notificaciones;

    HtmlNotificacionesparser(String html){
        notificaciones = new ArrayList<String>();
        Document doc = Jsoup.parse(html);
        hallarNotificaiones(doc);
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

    public void hallarNotificaiones(Document doc) {
        Elements nombreSemestres = doc.getElementsByClass("activity");
        for(Element semestre : nombreSemestres){
            notificaciones.add(semestre.text());
        }
    }

    public boolean tieneNotificaciones(){
        return notificaciones.size() >0;
    }

    public ArrayList<String> getNotificaciones(){
        return notificaciones;
    }
}

