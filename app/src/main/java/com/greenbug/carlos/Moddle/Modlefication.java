package com.greenbug.carlos.Moddle;

import android.os.Environment;
import android.util.Log;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Modlefication {
    static String login, password;
    static String html;
    static public Htmlparser htmlObj;
    static public OkHttpClient client;
    public static void asignarDatos(String l,String p){
        login = l;
        password = p;
    }

    //-----------------------------
    public static void revisarNotificacionesMateria(String url, final actividadOpMoodle act ){
        //int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "Opera/9.80 (Windows NT 6.0; U; cs) Presto/2.5.22 Version/10.50")
                .addHeader("Accept-Encoding", "gzip,deflate")
                        //.addHeader("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                .build();
        try {
            client.newCall(request).enqueue(new Callback() {
                @Override public void onFailure(Request request, IOException throwable) {
                    throwable.printStackTrace();
                }
                @Override public void onResponse(Response response) throws IOException {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    html = response.body().string();
                    Log.e("Notificaciones", html);
                    HtmlNotificacionesparser h = new HtmlNotificacionesparser(html);
                    if(h != null && h.tieneNotificaciones()){
                        act.añadirNotificaciones(h.getNotificaciones());
                    }
                }
            });
        }catch (Exception e){

        }
    }
    //-----------------------------

    public static void conectar(final com.greenbug.carlos.Portal.portal p, final boolean antiguo){
        /*html = prueba.html;
        htmlObj = new Htmlparser(html);
        p.entrarActividadMoodle();*/
        if(null == client) {
            client = getUnsafeOkHttpClient();
            client.setConnectTimeout(5, TimeUnit.SECONDS);
            client.setWriteTimeout(5, TimeUnit.SECONDS);
            client.setReadTimeout(10, TimeUnit.SECONDS);
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            client.setCookieHandler(cookieManager);
            /*File directorio = crearDirectorio("cache-Moodle");
            try {
                cacheResponse(directorio, client);
            }catch (Exception e){

            }*/
        }

        RequestBody body = new FormEncodingBuilder()
                .add("username", login)
                .add("password", password)
                .build();
        //int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
        Request request = new Request.Builder()
                .url("https://campusvirtual.univalle.edu.co/moodle/login/index.php")
                .header("User-Agent", "Opera/9.80 (Windows NT 6.0; U; cs) Presto/2.5.22 Version/10.50")
                .addHeader("Accept-Encoding", "gzip,deflate")
                //.addHeader("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                .post(body)
                .build();
        try {
            client.newCall(request).enqueue(new Callback() {
                @Override public void onFailure(Request request, IOException throwable) {
                    throwable.printStackTrace();
                }
                @Override public void onResponse(Response response) throws IOException {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    html = response.body().string();
                    htmlObj = new Htmlparser(html);
                    if(htmlObj.usuario != null){
                        p.entrarActividadMoodle(login, password,antiguo);
                    }
                }
            });
        }catch (Exception e){

        }
    }

    public static void cacheResponse(File cacheDirectory, OkHttpClient client) throws Exception {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(cacheDirectory, cacheSize);
        client.setCache(cache);
    }

    public static File crearDirectorio(String path) {
        File file = new File(Environment.getExternalStorageDirectory(), path);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("Modlefication", "Problema al crear carpeta de caché");
            }
        }
        return file;
    }



    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setSslSocketFactory(sslSocketFactory);
            okHttpClient.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
