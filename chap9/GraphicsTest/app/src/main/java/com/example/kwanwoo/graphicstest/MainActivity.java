package com.example.kwanwoo.graphicstest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    final int BITMAP_DRAWING = 0;
    final int CIRCLE_DRAWING = 1;
    AnimatedView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (AnimatedView)findViewById(R.id.animatedView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.bitmap:
                Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ball);
                view.setBitmap(bitmap);
                break;
            case R.id.circle:
                view.setBitmap(null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
