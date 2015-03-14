package com.greenbug.carlos.red;

import android.util.Log;

import com.google.gson.Gson;
import com.greenbug.carlos.Historial.Historial;
import com.greenbug.carlos.data.Registro;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RedHistorial {
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    public static void descargarHistorialDia(String fecha, final Historial act) throws Exception {
        RequestBody formBody = new FormEncodingBuilder()
                .add("dia", fecha+"")
                .build();
        Request request = new Request.Builder()
                .url(UtilRed.dirIp+"centralApp/historial/getHistorialDia")
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
                String str = response.body().string();
                Log.e("RedHistorial", str);
                Respuesta res = gson.fromJson(str, Respuesta.class);
                if(res.error==0) {
                    if(res.registros != null) {
                        for (int i = res.registros.length - 1; i >= 0; i--) {
                            Registro reg = res.registros[i];
                            reg.save();
                        }
                    }
                    if(act!= null && res.registros!= null && res.registros.length > 0){
                        act.actualizarVista(res.registros);
                    }
                }
            }
        });
    }

    class Respuesta {
        Registro registros[];
        String log;
        int error;
    }
}
