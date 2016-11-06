package com.example.kwanwoo.graphicstest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kwanwoo on 2016. 11. 6..
 */

public class CustomView extends View {

    private Paint paint;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(getResources().getDisplayMetrics().density*20);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(100,100, 200, 200,paint);


        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        canvas.drawBitmap(bitmap, 100,300,null);
    }
}
