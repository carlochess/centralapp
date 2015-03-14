package com.greenbug.carlos.red;

import android.util.Log;

import com.google.gson.Gson;
import com.greenbug.carlos.Noticias.activity_Noticias;
import com.greenbug.carlos.data.Noticia;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RedNoticias {
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    public static void descargarNoticias(Long idUltimaNoticia, final activity_Noticias act) throws Exception {
        RequestBody formBody = new FormEncodingBuilder()
                .add("idUltimaNoticia", idUltimaNoticia+"")
                .build();
        Request request = new Request.Builder()
                .url(UtilRed.dirIp+"centralApp/noticias/getNoticiasDia")
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
                Log.e("Noticias", respuesta);
                Respuesta res = gson.fromJson(respuesta, Respuesta.class);
                if(res.error==0) {
                    if(res.noticias != null) {
                        for (int i = res.noticias.length - 1; i >= 0; i--) {
                            Noticia noticia = res.noticias[i];
                            noticia.save();
                        }
                    }
                    if(act!= null && res.noticias!= null && res.noticias.length > 0){
                        act.actualizarLista(res.noticias);
                    }
                }
            }
        });
    }

    class Respuesta {
        Noticia noticias[];
        String log;
        int error;
    }
}
