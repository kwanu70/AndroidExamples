package com.example.kwanwoo.sqlitedbtest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    final static String TAG="SQLITEDBTEST";
    private MyDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new MyDBHelper(this);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title = (EditText)findViewById(R.id.title1);
                try {
                    String sql = String.format (
                            "INSERT INTO schedule (_id, title, datetime)\n"+
                            "VALUES (NULL, '%s', '%s')",
                            title.getText(),getDateTime());
                    helper.getWritableDatabase().execSQL(sql);
                } catch (SQLException e) {
                    Log.e(TAG,"Error in inserting into DB");
                }
            }
        });

        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title2 = (EditText)findViewById(R.id.title2);
                try {
                    String sql = String.format (
                                "DELETE FROM schedule\n"+
                                "WHERE title = '%s'",
                                title2.getText());
                                 //   "WHERE title = 'meeting'";
                    helper.getWritableDatabase().execSQL(sql);
                } catch (SQLException e) {
                    Log.e(TAG,"Error in deleting recodes");
                }
            }
        });

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title3 = (EditText)findViewById(R.id.title3);
                EditText title4 = (EditText)findViewById(R.id.title4);
                try {
                    String sql = String.format (
                            "UPDATE  schedule\n"+
                            "SET title = '%s'\n"+
                            "WHERE title='%s'",
                                   title4.getText(), title3.getText()) ;
                    helper.getWritableDatabase().execSQL(sql);
                } catch (SQLException e) {
                    Log.e(TAG,"Error in updating recodes");
                }
            }
        });

        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView result = (TextView)findViewById(R.id.result);
                String sql = "Select * FROM schedule";
                Cursor cursor = helper.getReadableDatabase().rawQuery(sql,null);
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append(cursor.getString(1)+"\t");
                    buffer.append(cursor.getString(2)+"\n");
                }
                result.setText(buffer);

            }
        });

    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                Date date = new Date();
        return dateFormat.format(date);

    }
}
