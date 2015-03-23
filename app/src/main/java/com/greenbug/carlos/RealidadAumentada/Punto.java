package com.greenbug.carlos.RealidadAumentada;

public class Punto extends android.graphics.Point{
    public double longitud = 0f;
    public double latitud = 0f;
    public double distc = 0f;
    public String descripcion;
    public float x, y = 0;
    public boolean seDibuja = false;
    public Punto(double lat, double lon, String desc) {
        this.latitud = lat;
        this.longitud = lon;
        this.descripcion = desc;
    }
}
