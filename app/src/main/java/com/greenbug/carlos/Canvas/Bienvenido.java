package com.greenbug.carlos.Canvas;

import android.content.Context;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.TextView;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.greenbug.carlos.centralapp.R;

import java.util.Locale;

public class Bienvenido extends ActionBarActivity  implements SensorListener {

    private static final int FORCE_THRESHOLD = 350;
    private static final int TIME_THRESHOLD = 100;
    private static final int SHAKE_TIMEOUT = 500;
    private static final int SHAKE_DURATION = 1000;
    private static final int SHAKE_COUNT = 3;

    private SensorManager mSensorMgr;
    private float mLastX=-1.0f, mLastY=-1.0f, mLastZ=-1.0f;
    private long mLastTime;
    private OnShakeListener mShakeListener = new OnShakeListener() {
        @Override
        public void onShake() {
            vib.vibrate(400);
        }
    };
    private Context mContext;
    private int mShakeCount = 0;
    private long mLastShake;
    private long mLastForce;
    Vibrator vib;
    TextView t;
    WebView webview;
    TextToSpeech ttobj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String codigo = getIntent().getExtras().getString("codigo");
        setContentView(R.layout.activity_bienvenido);
        t = (TextView) findViewById(R.id.textView);
        ttobj=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                Locale locSpanish = new Locale("spa", "MEX");
                ttobj.setLanguage(locSpanish);
            }
        });
        mContext= this;
        //vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        webview= new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);
        // Aquí se deben quitar las cookies
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();

        // Agregue el código javascript, haga el submit y luego...

        webview.addJavascriptInterface(new MetodosJavascript(this), "HtmlViewer");
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                webview.loadUrl(
                        "javascript: " +
                                "if(document.getElementsByName('barcode')[0]== null){ " +
                                "  var presentePrestamosActuales= $('*:contains(\"No tiene ejemplares prestados\")').size() == 0; " +
                                "  var texto = $.trim($(\"#pagecontent\").clone().remove().end().text());" +
                                "  var valor = texto.substring(texto.indexOf(\"Su multa pendiente es:\")+\"Su multa pendiente es:\".length+1);" +
                                " if (presentePrestamosActuales){"+
                                "  window.HtmlViewer.showHTML('<html><head></head><body><table>'+$(\"table\")[6].innerHTML+'</table><table>'+$(\"table.show-details\")[0].innerHTML+'</table><p id=\"deuda\">'+valor+'</p></body></html>');" +
                                " }else {"+
                                "  window.HtmlViewer.showHTML('<html><head></head><body><table>'+$(\"table\")[6].innerHTML+'</table><p id=\"deuda\">'+valor+'</p></body></html>');" +
                                " }" +
                                "  $(\"a[title=Cerrar Sesión]\")[0].click(); " +
                                "}else{" +
                                "  document.getElementsByName(\"barcode\")[0].value = \""+codigo+"\"; verify($(\"form[name=LoginForm]\")[0]); $(\"form[name=LoginForm]\")[0].submit();;" +
                                "}");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
        webview.loadUrl("http://opac.univalle.edu.co:8000/cgi-olib/?infile=user.glu&auth_this=y&style=user");

    }

    class MetodosJavascript {

        private Context ctx;

        MetodosJavascript(Context ctx) {
            this.ctx = ctx;
        }



        @JavascriptInterface
        public void showHTML(final String html) {
            Document doc;
            String resultado="";
            try {
                doc = Jsoup.parse(html);
                Elements tablas = doc.select("table");

                Elements kelime = tablas.get(0).select("tr");
                int m=0;
                for (Element sectd : kelime) {
                    Elements tds = sectd.select("td");
                    if(m==0) {
                        ttobj.speak(tds.get(1).text(), TextToSpeech.QUEUE_ADD, null);
                        m++;
                    }
                    resultado += tds.get(0).text()+": "+tds.get(1).text()+"\n";
                }

                if(tablas.size() > 1){
                    Elements prestamosActuales = tablas.get(1).select("tr");
                    for (int i=1; i < prestamosActuales.size(); i++) {
                        Element sectd = prestamosActuales.get(i);
                        Elements tds = sectd.select("td");
                        resultado +=
                                tds.get(1).text()+" "+
                                        tds.get(3).text()+" "+
                                        tds.get(4).text()+" "+
                                        tds.get(5).text()+" ";
                        resultado += "\n";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            final String res = resultado;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    t.setText(res);
                }
            });
        }
    }


    //-------------------
    public interface OnShakeListener
    {
        public void onShake();
    }

    public void resume() {
        mSensorMgr = (SensorManager)mContext.getSystemService(Context.SENSOR_SERVICE);
        if (mSensorMgr == null) {
            throw new UnsupportedOperationException("Sensors not supported");
        }
        boolean supported = mSensorMgr.registerListener(this, SensorManager.SENSOR_ACCELEROMETER, SensorManager.SENSOR_DELAY_GAME);
        if (!supported) {
            mSensorMgr.unregisterListener(this, SensorManager.SENSOR_ACCELEROMETER);
            //Toast.makeText(this, "Sin V", Toast.LENGTH_SHORT);
        }
    }

    public void pause() {
        if (mSensorMgr != null) {
            mSensorMgr.unregisterListener(this, SensorManager.SENSOR_ACCELEROMETER);
            mSensorMgr = null;
        }
    }

    public void onAccuracyChanged(int sensor, int accuracy) { }

    public void onSensorChanged(int sensor, float[] values)
    {
        if (sensor != SensorManager.SENSOR_ACCELEROMETER) return;
        long now = System.currentTimeMillis();

        if ((now - mLastForce) > SHAKE_TIMEOUT) {
            mShakeCount = 0;
        }

        if ((now - mLastTime) > TIME_THRESHOLD) {
            long diff = now - mLastTime;
            float speed = Math.abs(values[SensorManager.DATA_X] + values[SensorManager.DATA_Y] + values[SensorManager.DATA_Z] - mLastX - mLastY - mLastZ) / diff * 10000;
            if (speed > FORCE_THRESHOLD) {
                if ((++mShakeCount >= SHAKE_COUNT) && (now - mLastShake > SHAKE_DURATION)) {
                    mLastShake = now;
                    mShakeCount = 0;
                    if (mShakeListener != null) {
                        mShakeListener.onShake();
                    }
                }
                mLastForce = now;
            }
            mLastTime = now;
            mLastX = values[SensorManager.DATA_X];
            mLastY = values[SensorManager.DATA_Y];
            mLastZ = values[SensorManager.DATA_Z];
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bienvenido, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
