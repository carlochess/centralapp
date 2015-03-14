package com.greenbug.carlos.Canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.greenbug.carlos.centralapp.R;

public class Vista extends SurfaceView implements SurfaceHolder.Callback{

    public int width;
    public int height;
    private Bitmap mBitmap;
    public Canvas mCanvas;
    private Path mPath;
    Context context;
    private Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 5;
    //---
    Bitmap bitmap;

    //---
    public Vista(Context c) {
        super(c);
        context = c;
        getHolder().addCallback(this);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bad3);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas c = holder.lockCanvas(null);
        draw(c);
        holder.unlockCanvasAndPost(c);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override

    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(bitmap, 10, 10, null);
    }
}
