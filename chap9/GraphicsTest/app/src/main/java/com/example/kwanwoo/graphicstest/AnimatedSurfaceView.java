package com.example.kwanwoo.graphicstest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by kwanwoo on 2016. 11. 5..
 */

public class AnimatedSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private BallAnimation thread;
    private ArrayList<Ball> arBall = new ArrayList<Ball>();
    private Bitmap bitmap;
    SurfaceHolder holder;


    public AnimatedSurfaceView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);

        thread = new BallAnimation();
    }


    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread.start();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {}

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        try {
            thread.join();
        } catch (InterruptedException e) {}
    }

    class BallAnimation extends Thread {
        public void run() {
            while (true) {
                Canvas canvas  = holder.lockCanvas(null);

                canvas.drawColor(Color.WHITE);
                synchronized (holder) {
                    for (int idx=0; idx<arBall.size(); idx++) {
                        Ball B = arBall.get(idx);
                        B.move(getWidth(),getHeight());
                        B.draw(canvas);
                    }
                }
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }


    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x =  (int)event.getX();
            int y =  (int)event.getY();

            arBall.add(new Ball(x, y, bitmap));

            return true;
        }
        return false;
    }
}
