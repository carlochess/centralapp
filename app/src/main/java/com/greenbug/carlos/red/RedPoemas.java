package com.greenbug.carlos.red;

import android.util.Log;
import com.google.gson.Gson;
import com.greenbug.carlos.Poema.PublicarPoema;
import com.greenbug.carlos.Poema.activity_Poemas;
import com.greenbug.carlos.data.Poema;
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


public class RedPoemas {
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    public static void descargarPoemas(long ultimoLista, final activity_Poemas act) throws Exception {
        RequestBody formBody = new FormEncodingBuilder()
                .add("idUltimoPoema", ultimoLista+"")
                .build();
        Request request = new Request.Builder()
                .url(UtilRed.dirIp+"centralApp/poemas/getUltimosPoemas")
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
                Log.e("Hola", respuesta);
                Respuesta res = gson.fromJson(respuesta, Respuesta.class);
                if(res.error==0) {
                    if(res.poemas != null) {
                        for (int i = res.poemas.length - 1; i >= 0; i--) {
                            Poema poe = res.poemas[i];
                            poe.save();
                        }
                    }
                    if(act!= null && res.poemas!= null && res.poemas.length > 0){
                        act.actualizarLista(res.poemas);
                    }
                }
            }
        });
    }

    private static final MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpg");

    public static void enviarPoemas( byte[] imagen, String titulo, String contenido, String fecha, String autor, String idTelefono, final PublicarPoema p) throws Exception {
        RequestBody requestBody;

        if(null != imagen) {
            requestBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addPart(
                            Headers.of("Content-Disposition", "form-data; name=\"titulo\""),
                            RequestBody.create(null, titulo))
                    .addPart(
                            Headers.of("Content-Disposition", "form-data; name=\"contenido\""),
                            RequestBody.create(null, contenido))
                    .addPart(
                            Headers.of("Content-Disposition", "form-data; name=\"fecha\""),
                            RequestBody.create(null, fecha))
                    .addPart(
                            Headers.of("Content-Disposition", "form-data; name=\"autor\""),
                            RequestBody.create(null, autor))
                    .addPart(
                            Headers.of("Content-Disposition", "form-data; name=\"idTelefono\""),
                            RequestBody.create(null, idTelefono))
                    .addPart(
                            Headers.of("Content-Disposition", "form-data;  filename=\"imagen\" name=\"image\""),
                            RequestBody.create(MEDIA_TYPE_JPEG, imagen))
                    .build();
        }else{
            requestBody = new MultipartBuilder()
                    .type(MultipartBuilder.FORM)
                    .addPart(
                            Headers.of("Content-Disposition", "form-data; name=\"titulo\""),
                            RequestBody.create(null, titulo))
                    .addPart(
                            Headers.of("Content-Disposition", "form-data; name=\"contenido\""),
                            RequestBody.create(null, contenido))
                    .addPart(
                            Headers.of("Content-Disposition", "form-data; name=\"fecha\""),
                            RequestBody.create(null, fecha))
                    .addPart(
                            Headers.of("Content-Disposition", "form-data; name=\"autor\""),
                            RequestBody.create(null, autor))
                    .addPart(
                            Headers.of("Content-Disposition", "form-data; name=\"idTelefono\""),
                            RequestBody.create(null, idTelefono))
                    .build();
        }

        String url = UtilRed.dirIp+"centralApp/poemas/agregarPoema";
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
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
                if (!response.isSuccessful())
                    throw new IOException("Algo malo sucedio: " + response);
                Log.e("Respuesta", response.body().string());
                p.resultadoEnvio(true);
            }
        });
    }
    // enviar último id
    // recibir el obj json
    // decodificarlo y
    // actualizar vista y bd
    class Respuesta {
        Poema poemas[];
        String log;
        int error;
    }
}



