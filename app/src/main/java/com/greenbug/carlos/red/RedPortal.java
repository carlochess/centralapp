package com.greenbug.carlos.red;

import android.util.Log;

import com.google.gson.Gson;
import com.greenbug.carlos.Portal.portal;
import com.greenbug.carlos.data.Menu;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RedPortal {
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();
    private static final MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpg");

    public static void descargarPortal(String fecha, final portal act) throws Exception {
        RequestBody formBody = new FormEncodingBuilder()
                .add("fecha", fecha+"")
                .build();
        Request request = new Request.Builder()
                .url(UtilRed.dirIp+"centralApp/portal/actualidad")
                .post(formBody)
                .build();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setWriteTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Algo malo sucedio: " + response);
                String respuesta = response.body().string();
                Respuesta res = gson.fromJson(respuesta, Respuesta.class);
                if(res.error==0) {
                    if(act!= null){
                        act.refrescarPortal(res.estadoRestaurantes, res.estadoTickets, res.menu);
                    }
                }
            }
        });
    }



    public static void enviarVoto(float calificion, byte[] imagen, boolean restaurante, String identificacion) throws Exception {
        RequestBody requestBody;
        if(null != imagen) {
            requestBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addPart(
                            Headers.of("Content-Disposition", "form-data; name=\"voto\""),
                            RequestBody.create(null, calificion + ""))
                    .addPart(
                            Headers.of("Content-Disposition", "form-data; name=\"id\""),
                            RequestBody.create(null, identificacion))
                    .addPart(
                            Headers.of("Content-Disposition", "form-data;  filename=\"imagen\" name=\"image\""),
                            RequestBody.create(MEDIA_TYPE_JPEG, imagen))
                    .build();
        }else{
            requestBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addPart(
                            Headers.of("Content-Disposition", "form-data; name=\"voto\""),
                            RequestBody.create(null, calificion + ""))
                    .build();
        }
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setWriteTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);

        String url = (restaurante)? UtilRed.dirIp+"centralApp/portal/votarRestaurante" :
                UtilRed.dirIp+"centralApp/portal/votarTickets";
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Log.e("Respuesta", "Enviando");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful())
                    throw new IOException("Algo malo sucedio: " + response);
                Log.e("Respuesta", response.body().string());
            }
        });
    }

    class Respuesta {
        float estadoRestaurantes, estadoTickets ;
        Menu menu;
        String log;
        int error;
    }
}
