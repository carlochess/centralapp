package com.greenbug.carlos.red;

import android.util.Log;

import com.google.gson.Gson;
import com.greenbug.carlos.Galeria.Galeria;
import com.greenbug.carlos.data.Imagen;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RedGaleria {
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    public static void descargarImagenes(long ultimoId, final Galeria act) throws Exception {
        RequestBody formBody = new FormEncodingBuilder()
                .add("idUltimaImagen", ultimoId+"")
                .build();
        Request request = new Request.Builder()
                .url(UtilRed.dirIp+"centralApp/galeria/getUltimasimagenes")
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
                Respuesta res = gson.fromJson(response.body().string(), Respuesta.class);
                if(res.error==0) {
                    if(res.imagenes != null) {
                        for (int i = res.imagenes.length - 1; i >= 0; i--) {
                            Imagen img = res.imagenes[i];
                            Log.e("Recibida", img.toString());
                            img.save();
                        }
                    }
                    if(act!= null && res.imagenes!= null && res.imagenes.length > 0){
                        act.actualizarLista(res.imagenes);
                    }
                }
            }
        });
    }

    public static void denunciarImagen(int posicion) {
        RequestBody formBody = new FormEncodingBuilder()
                .add("idImagen", posicion+"")
                .build();
        Request request = new Request.Builder()
                .url(UtilRed.dirIp+"centralApp/galeria/denunciarFoto")
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
                Respuesta res = gson.fromJson(response.body().string(), Respuesta.class);
            }
        });
    }

    class Respuesta {
        String ultimoId;
        Imagen imagenes[];
        String log;
        int error;
    }
}
