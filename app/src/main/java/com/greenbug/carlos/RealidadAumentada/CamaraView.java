package com.greenbug.carlos.RealidadAumentada;

import android.content.Context;
import android.content.res.Configuration;

import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

public class CamaraView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private Camera camara;

    public CamaraView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public CamaraView(Context context, AttributeSet set) {
        super(context, set);
        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            camara = Camera.open();
            Configuration c = getResources().getConfiguration();
            if(c.orientation == Configuration.ORIENTATION_PORTRAIT ) {
                camara.setDisplayOrientation(90);
            }
            camara.setPreviewDisplay(this.holder);
        } catch (IOException ioe) {
            ioe.printStackTrace(System.out);
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
        Camera.Parameters parametros = camara.getParameters();
        List<Camera.Size> csc = parametros.getSupportedPreviewSizes();
        Camera.Size cs = null;
        for (Camera.Size s : csc) {
            if (s.width == 640 && s.height == 480) {
                cs = s;
                break;
            }
        }
        if (cs == null)
            cs = csc.get(csc.size() / 2);
        parametros.setPictureSize(cs.width, cs.height);
        camara.setParameters(parametros);
        camara.startPreview();
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camara.setPreviewCallback(null);
        camara.stopPreview();
        camara.release();
        camara = null;
    }
}
