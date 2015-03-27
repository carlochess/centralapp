package com.greenbug.carlos.RealidadAumentada;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.greenbug.carlos.centralapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LeyendaView extends View implements View.OnTouchListener{

    private double correccion = 0d;
    private Punto miPosicion = new Punto(3.3735442, -76.5328917, "Yo");
    private Paint pintor = new Paint();
    private Bitmap[] puntosRA, puntosRadar;
    private Bitmap radar;
    private double anchoPantalla, altoVentana = 0d;
    private DecimalFormat df = new DecimalFormat("#.00");
    public static ArrayList<Punto> puntos = new ArrayList<Punto>();

    public LeyendaView(Context context, AttributeSet set) {
        super(context, set);
        pintor.setColor(Color.rgb(255, 200, 0));
        pintor.setTextSize(50);
        pintor.setStrokeWidth(getPxFromDpi(getContext(), 2));
        pintor.setAntiAlias(true);

        radar = BitmapFactory.decodeResource(context.getResources(), R.drawable.radar);

        puntosRA = new Bitmap[puntos.size()];
        for (int i = 0; i < puntosRA.length; i++)
            puntosRA[i] = BitmapFactory.decodeResource(context.getResources(), R.drawable.marcador);

        puntosRadar = new Bitmap[puntos.size()];
        for (int i = 0; i < puntosRadar.length; i++)
            puntosRadar[i] = BitmapFactory.decodeResource(context.getResources(), R.drawable.puntoradar);
        setOnTouchListener(this);
    }
    public void setCorreccion(float correccion) {
        this.correccion = correccion;
    }

    public void setPosicion(double latitud, double longitud) {
        miPosicion.latitud = latitud;
        miPosicion.longitud = longitud;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        anchoPantalla = (double) w;
        altoVentana = (double) h;
    }

    @Override
    public void onDraw(Canvas canvas){
        canvas.drawBitmap(radar, 0, 0, pintor);
        int radarCentreX = radar.getWidth() / 2;
        int radarCentreY = radar.getHeight() / 2;
        Paint.Style s = pintor.getStyle();

        //pintor.setStyle(Paint.Style.FILL);
        pintor.setColor(Color.WHITE);
        canvas.drawRect(200, 30, canvas.getWidth(), 100, pintor);
        pintor.setStyle(Paint.Style.STROKE);
        pintor.setColor(Color.rgb(255, 200, 0));
        pintor.setStyle(s);
        pintor.setTextSize(50);
        canvas.drawText(seleccionado, 200, 80, pintor);
        pintor.setTextSize(30);
        for (int i = 0; i < puntosRadar.length; i++) {
            Bitmap blip = puntosRadar[i];
            Bitmap spot = puntosRA[i];
            Punto u = puntos.get(i);
            double dist = calcularDistancia(miPosicion.latitud, miPosicion.longitud, u.latitud, u.longitud);/* distanciaEnMetros(miPosicion, u);*/
            u.distc = dist;
            if (blip == null || spot == null)
                continue;
            /*if(dist < 700 && i==1){
                continue;
            }*/
            if(dist > 70)
                dist = 70;
            double angle = rumbo(miPosicion.latitud, miPosicion.longitud, u.latitud, u.longitud) - correccion;
            double xPos, yPos;
            if(angle < 0)
                angle = (angle+360)%360;
            xPos = Math.sin(Math.toRadians(angle)) * dist;
            yPos = Math.sqrt(Math.pow(dist, 2) - Math.pow(xPos, 2));
            if (angle > 90 && angle < 270)
                yPos *= -1;
            int blipCentreX = blip.getWidth() / 2;
            int blipCentreY = blip.getHeight() / 2;
            xPos = xPos - blipCentreX;
            yPos = yPos + blipCentreY;
            canvas.drawBitmap(blip, (radarCentreX + (int) xPos), (radarCentreY - (int) yPos), pintor);

            double posInPx = angle * (anchoPantalla / 90d);
            int spotCentreX = spot.getWidth() / 2;
            int spotCentreY = spot.getHeight() / 2;
            xPos = posInPx - spotCentreX;
            u.seDibuja = true;
            if (angle <= 45)
                u.x = (float) ((anchoPantalla / 2) + xPos);
            else if (angle >= 315)
                u.x = (float) ((anchoPantalla / 2) - ((anchoPantalla *4) - xPos));
            else{
                u.x = (float)(anchoPantalla *9); // No en la pantalla
                u.seDibuja = false;
            }
            if(u.seDibuja){
                revisarColisiones(u, i);
            }
            u.y = (float) altoVentana /2 + spotCentreY;
        }
        for(int i = 0; i < puntosRadar.length; i++){
            Punto u = puntos.get(i);
            if(u.seDibuja){
                Bitmap spot = puntosRA[i];
                canvas.drawBitmap(spot, u.x-5, u.y, pintor); //camera spot
                pintor.setTextSize(30);
                canvas.drawText(u.descripcion, u.x, u.y, pintor); //text
                pintor.setTextSize(20);
                if(u.distc>1000){
                    canvas.drawText(df.format(u.distc/1000)+"km", u.x+30, u.y+20, pintor);
                }else{
                    canvas.drawText(df.format(u.distc)+"m", u.x+30, u.y+20, pintor);
                }
                u.seDibuja = false;
            }
        }
    }

    public void revisarColisiones(Punto p, int lim){
        for(int i = 0; i < lim; i++){
            Punto u = puntos.get(i);
            int delta = 200;
            if(u.seDibuja && u.x-delta < p.x && u.x + delta > p.x){
                if(u.distc < p.distc){
                    u.seDibuja = true;
                    p.seDibuja = false;
                }else {
                    u.seDibuja = false;
                    p.seDibuja = true;
                }
            }
        }
    }
    //-------------
    public static int getPxFromDpi(Context _context, int _px){
        int value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (float) _px, _context.getResources().getDisplayMetrics());
        return value;
    }

    public static float calcularDistancia(double startLati, double startLongi, double goalLati, double goalLongi){
        float[] resultArray = new float[99];
        Location.distanceBetween(startLati, startLongi, goalLati, goalLongi, resultArray);
        return resultArray[0];
    }
    /*
     Se define el rumbo como el ángulo medido en el plano horizontal entre el norte y la dirección
     de avance del barco, medido en círculo, es decir, de 0º a 360º
     */
    static double rumbo(double lat1, double lon1, double lat2, double lon2) {
        double longDiff = Math.toRadians(lon2 - lon1);
        double la1 = Math.toRadians(lat1);
        double la2 = Math.toRadians(lat2);
        double y = Math.sin(longDiff) * Math.cos(la2);
        double x = Math.cos(la1) * Math.sin(la2) - Math.sin(la1) * Math.cos(la2) * Math.cos(longDiff);
        double result = Math.toDegrees(Math.atan2(y, x));
        return (result+360.0d)%360.0d;
    }

    static {
        puntos.add(new Punto(90d, 110.8000, "Polo Norte"));
        puntos.add(new Punto(3.3735442, -76.5328917, "305 Ágora")); ///
        puntos.add(new Punto(3.3735442, -76.5328917, "310 Bienestar")); ///
        puntos.add(new Punto(3.3735442, -76.5328917, "Univalle"));
        puntos.add(new Punto(3.3797247, -76.5369683, "384 Salud y Geograf"));
        puntos.add(new Punto(3.3777954, -76.5343595, "352 Herbario"));
        puntos.add(new Punto(3.3775355, -76.5324175, "334 Ingenierías"));
        puntos.add(new Punto(3.3774154, -76.5328123, "335 Ingenierías"));
        puntos.add(new Punto(3.3771752, -76.5351902, "317 CREE"));
        puntos.add(new Punto(3.3771321, -76.5325625, "336 Ing. Quimica"));
        puntos.add(new Punto(3.3768489, -76.5344222, "318 Biblioteca"));
        puntos.add(new Punto(3.3770009, -76.5321699, "338 Ing. Alimentos"));
        puntos.add(new Punto(3.3766656, -76.5359209, "316 FAI"));
        puntos.add(new Punto(3.3766526, -76.5351489, "315 E. Lenguaje"));
        puntos.add(new Punto(3.3759211, -76.5337139, "320 Ciencias"));
        puntos.add(new Punto(3.3767353, -76.5326715, "333 Primipalandia"));
        puntos.add(new Punto(3.3766003, -76.5321445, "340 Ing. Mecánica"));
        puntos.add(new Punto(3.3759167, -76.5349526, "314 FAI"));
        puntos.add(new Punto(3.3762171, -76.5329201, "332 Auds"));
        puntos.add(new Punto(3.3755205, -76.5327718, "331 Ing. Sistemas"));
        puntos.add(new Punto(3.3762346, -76.5321424, "341 Ing. Ambiental"));
        puntos.add(new Punto(3.3760005, -76.530646,  "342 CECIM"));
        puntos.add(new Punto(3.3754238, -76.534821,  "313 FAI"));
        puntos.add(new Punto(3.3756998, -76.5321453, "344 Ing. Mecánica"));
        puntos.add(new Punto(3.375345, -76.5321082,  "345 Ingenierías"));
        puntos.add(new Punto(3.3750267, -76.5343896, "301 Admon Central"));
        puntos.add(new Punto(3.3749771, -76.5321257, "346 Ing. Topográfica"));
        puntos.add(new Punto(3.3747893, -76.5328937, "355 Ingenierías"));
        puntos.add(new Punto(3.3747185, -76.532505,  "357 Ing. Industrial y Estadística"));
        puntos.add(new Punto(3.3747524, -76.5322521, "348 Ing. Mat"));
        puntos.add(new Punto(3.3748509, -76.5318635, "347 Ing. Mat"));
        puntos.add(new Punto(3.3745799, -76.5331897, "354 IEE"));
        puntos.add(new Punto(3.3745461, -76.5327703, "336 Lab. de Alta Tensión"));
        puntos.add(new Punto(3.374441, -76.5325057,  "351 Ing. Mecánica"));
        puntos.add(new Punto(3.374583, -76.5320054,  "348 "));
        puntos.add(new Punto(3.3743025, -76.5321017, "350 Ing Civil y Geomática"));
        puntos.add(new Punto(3.3742999, -76.5329672, "353 Ing Eléctrica y Electrónica"));
        puntos.add(new Punto(3.3732745, -76.5353513, "388 IEP"));
        puntos.add(new Punto(3.3731776, -76.531256,  "358 Microestación"));
        puntos.add(new Punto(3.3730176, -76.5358494, "387 Socioeconomia"));
        puntos.add(new Punto(3.37259, -76.5351329,   "Cafeteria Central"));
        puntos.add(new Punto(3.3727304, -76.5306686, "359 CDP de Fundición y Soldadura"));
        puntos.add(new Punto(3.3726299, -76.5303118, "360 Laboratorio de Hidráulica"));
        puntos.add(new Punto(3.3720768,-76.529714,   "Ingeominas")); //
        puntos.add(new Punto(3.3726926, -76.5363929, "386 Humanidades"));
        puntos.add(new Punto(3.3723759, -76.5364376, "385 Humanidades"));
        puntos.add(new Punto(3.3720018, -76.5364992, "384 Geografía y S. Médico"));
        puntos.add(new Punto(3.3716986, -76.5362556, "383 Comunicación Social"));
        puntos.add(new Punto(3.3713476, -76.5360135, "336 Arquitectura y Diseño"));
        puntos.add(new Punto(3.3713183, -76.5349618, "380 Arquitectura"));
        puntos.add(new Punto(3.3710582, -76.5357591, "381 Edu. Física"));
        puntos.add(new Punto(3.3705847,-76.5327593,  "390 CDU"));
        puntos.add(new Punto(3.3705984, -76.533444,  "Piscina CDU"));
    }
    String seleccionado="";
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            float x = event.getX();
            float y = event.getY();
            int menorDistancia = Integer.MAX_VALUE;
            Punto mejor=null;
            for(Punto u : puntos){
                if(u.x < x && u.x + 200> x && u.distc < menorDistancia){
                    mejor = u;
                    menorDistancia = (int)u.distc;
                }
            }
            if(mejor!=null)
                seleccionado = mejor.descripcion;
                //Log.e("", mejor.descripcion);
        }
        return true;
    }
}
