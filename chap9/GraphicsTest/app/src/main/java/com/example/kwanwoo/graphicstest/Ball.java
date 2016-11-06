package com.example.kwanwoo.graphicstest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

import java.util.Random;

/**
 * Created by kwanwoo on 2016. 11. 5..
 */

public class Ball {
    final int RAD = 24;     // 볼의 반지름
    int mX, mY;             // 볼의 중심 좌표
    int mWidth, mHeight;    // 볼의 넓이와 높이

    int dx, dy;             // 볼의 이동 x방향,y방향 속도 값

    int color;
    Bitmap bitmap;

    public Ball(int x, int y, Bitmap bitmap) {
        mX = x;
        mY = y;

        if (bitmap != null) {               // 비트맵이 설정된 경우
            this.bitmap = bitmap;
            mWidth = bitmap.getWidth();     // 비트맵의 가로 폭으로로부터 Ball 폭 계산
            mHeight = bitmap.getHeight();   // 비트뱁의 세로 길이로부터 Ball 높이 계산
        } else {
            mWidth=mHeight=RAD*2;           // 원의 반지름 (RAD)의 2배가 Ball의 폭과 높이
        }

        Random Rnd = new Random();
        do {
            dx = Rnd.nextInt(11)-5;     // -5 ~ 5 중 난수로 x방향 속도 설정
            dy = Rnd.nextInt(11)-5;     // -5 ~ 5 중 난수로 y방향 속도 설정
        } while(dx==0 || dy==0);        //  0은 제외

        // 임의의 색상 설정
        color = Color.rgb(Rnd.nextInt(256), Rnd.nextInt(256),Rnd.nextInt(256));

    }

    public void draw(Canvas canvas) {
        if (bitmap == null) {

            Paint paint = new Paint();

            for (int r = RAD, alpha = 1; r > 4; r--, alpha += 5) { // 바깥쪽은 흐릿하게 안쪽은 진하게 그려지는 원
                paint.setColor(Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color)));
                canvas.drawCircle(mX + RAD, mY + RAD, r, paint);
            }
        } else
            canvas.drawBitmap(bitmap, mX,mY,null);  // 비트맵 그리기
    }

    void move(int width, int height) {
        mX += dx;       // x 좌표값을 dx 만큼 증가
        mY += dy;       // y 좌표값을 dy 만큼 증가

        if (mX<0 || mX > width- mWidth) {   // 화면 좌우 경계에 닿은 경우
            dx *= -1;                       // 좌우 방향 반전
        }
        if (mY<0  || mY > height- mHeight) {    // 화면 상하 경계에 닿은 경우
            dy *= -1;                           // 상하 방향 반전

        }
    }
}
