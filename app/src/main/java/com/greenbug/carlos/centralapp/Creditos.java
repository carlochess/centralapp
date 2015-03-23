package com.greenbug.carlos.centralapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;


public class Creditos extends ActionBarActivity implements SurfaceHolder.Callback {

    private static final String TAG = "Creditos";
    Bitmap b;
    Paint w;
    Rect dest;
    double tiempoInicia=System.currentTimeMillis();
    Pintor thread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SurfaceView view = new SurfaceView(this);
        setContentView(view);
        view.getHolder().addCallback(this);
        b = BitmapFactory.decodeResource(getResources(), R.drawable.mapa);
        w = new Paint();
        thread = new Pintor(view.getHolder(), this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int frmt, int w, int h) {
        //tryDrawing(holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // try again shutting down the thread
            }
        }
    }
    int x = 20;
    int y = 20;
    private void update() {
        //x = (x+1)%400;
    }

    private void drawMyStuff(final Canvas canvas) {
        dest = canvas.getClipBounds();
        Random random = new Random();
        long tiempoActual=System.currentTimeMillis();
        canvas.drawRGB(255, 128, 128);
        if(dest!=null) {
            //if(tiempoInicia- tiempoActual>60) {
                Rect src = new Rect(x, y, 400+x, 600+y);
                canvas.drawBitmap(b, src, dest, w);
                tiempoInicia = tiempoActual;
                Log.i(TAG, "Drawing...");
            //}
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

        } if (event.getAction() == MotionEvent.ACTION_MOVE) {
            x = (int)event.getX();
            y = (int)event.getY();

        } if (event.getAction() == MotionEvent.ACTION_UP) {

        }
        return true;
    }

    class Pintor extends Thread {
        SurfaceHolder surfaceHolder;
        Creditos gamePanel;
        public Pintor(SurfaceHolder surfaceHolder, Creditos gamePanel) {
            super();
            this.surfaceHolder = surfaceHolder;
            this.gamePanel = gamePanel;
        }

        private boolean running;
        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            Canvas canvas;
            while (running) {
                canvas = null;
                // try locking the canvas for exclusive pixel editing
                // in the surface
                try {
                    canvas = this.surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        // update game state
                        this.gamePanel.update();
                        // render state to the screen
                        // draws the canvas on the panel
                        this.gamePanel.drawMyStuff(canvas);
                        try {
                            Thread.sleep(60);
                        } catch (InterruptedException e) {}
                    }
                } finally {
                    // in case of an exception the surface is not left in
                    // an inconsistent state
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }	// end finally
            }
        }
    }
}