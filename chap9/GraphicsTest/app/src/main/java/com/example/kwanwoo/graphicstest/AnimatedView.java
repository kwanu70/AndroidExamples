package com.example.kwanwoo.graphicstest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Kwanwoo on 2016-11-01.
 */

public class AnimatedView extends View {

    private Paint paint;
    private ArrayList<Ball> arBall = new ArrayList<Ball>();
    private Bitmap bitmap;

    public AnimatedView(Context context) {
        super(context);
        init();
    }

    public AnimatedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);

    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void updateAnimation() {
        Ball B;
        for (int idx=0; idx<arBall.size(); idx++) {
            B = arBall.get(idx);
            B.move(getWidth(),getHeight());
        }
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Ball B;
        for (int idx=0; idx<arBall.size(); idx++) {
            B = arBall.get(idx);
            B.draw(canvas);
        }
        updateAnimation();
    }


    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x =  (int)event.getX();
            int y =  (int)event.getY();
            arBall.add(new Ball(x, y, bitmap));

            invalidate();
            return true;
        }
        return false;
    }
}
