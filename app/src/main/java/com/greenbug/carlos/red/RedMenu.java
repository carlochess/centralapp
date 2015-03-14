package com.greenbug.carlos.red;

import android.util.Log;

import com.google.gson.Gson;
import com.greenbug.carlos.Menu.activity_Menu;
import com.greenbug.carlos.data.Menu;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class RedMenu {
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    public static void descargarMenuSemana(String faltantes, final activity_Menu act) throws Exception {
        RequestBody formBody = new FormEncodingBuilder()
                .add("dias", faltantes)
                .build();
        Request request = new Request.Builder()
                .url(UtilRed.dirIp+"centralApp/menu/getMenuDias")
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
                Log.e("Menu", str);
                Respuesta res = gson.fromJson(str, Respuesta.class);
                if(res.error==0 && !(null == res.menu)) {
                    for(Menu menu : res.menu) {
                        menu.save();
                    }
                    if(act!= null){
                        act.actualizarVista(res.menu);
                    }
                }
            }
        });
    }
    class Respuesta {
        String ultimoId;
        Menu[] menu;
        String log;
        int error;
    }
}



