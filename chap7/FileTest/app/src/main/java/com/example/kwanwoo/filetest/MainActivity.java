package com.example.kwanwoo.filetest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "FILETEST";
    private String state;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText input = (EditText) findViewById(R.id.editText1);
        result = (TextView) findViewById(R.id.textView1);
        Button iSave = (Button) findViewById(R.id.button1);
        Button iLoad = (Button) findViewById(R.id.button2);
        Button eSave = (Button) findViewById(R.id.button3);
        Button eLoad = (Button) findViewById(R.id.button4);
        Button epSave = (Button) findViewById(R.id.button5);
        Button epLoad = (Button) findViewById(R.id.button6);

        iSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = input.getText().toString();

                try {
                    FileOutputStream fos = openFileOutput
                            ("myfile.txt", // 파일명 지정
                                    Context.MODE_APPEND);// 저장모드
                    PrintWriter out = new PrintWriter(fos);
                    out.println(data);
                    out.close();

                    result.setText("file saved");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        iLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 파일의 내용을 읽어서 TextView 에 보여주기
                try {
                    // 파일에서 읽은 데이터를 저장하기 위해서 만든 변수
                    StringBuffer data = new StringBuffer();
                    FileInputStream fis = openFileInput("myfile.txt");//파일명
                    BufferedReader buffer = new BufferedReader
                            (new InputStreamReader(fis));
                    String str = buffer.readLine(); // 파일에서 한줄을 읽어옴
                    while (str != null) {
                        data.append(str + "\n");
                        str = buffer.readLine();
                    }
                    result.setText(data);
                    buffer.close();
                } catch (FileNotFoundException e) {
                    result.setText("File Not Found");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        eSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isExternalStorageWritable())
                    return;     // 외부메모리를 사용하지 못하면 끝냄
                requestPermission();

                String data = input.getText().toString();
                Log.i(TAG, getLocalClassName() + ":file save start");
                try {
                    File path = Environment.getExternalStoragePublicDirectory
                            (Environment.DIRECTORY_DOWNLOADS);
//                  File path = Environment.getExternalStorageDirectory();
                    File f = new File(path, "external.txt"); // 경로, 파일명
                    FileWriter write = new FileWriter(f, true);

                    PrintWriter out = new PrintWriter(write);
                    out.println(data);
                    out.close();
                    result.setText("저장완료");
                    Log.i(TAG, getLocalClassName() + ":file saved");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        eLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isExternalStorageReadable())
                    return;     // 외부메모리를 사용하지 못하면 끝냄
                requestPermission();

                try {
                    StringBuffer data = new StringBuffer();
                    File path = Environment.getExternalStoragePublicDirectory
                            (Environment.DIRECTORY_DOWNLOADS);
//                    File path = Environment.getExternalStorageDirectory();
                    File f = new File(path, "external.txt");

                    BufferedReader buffer = new BufferedReader
                            (new FileReader(f));
                    String str = buffer.readLine();
                    while (str != null) {
                        data.append(str + "\n");
                        str = buffer.readLine();
                    }
                    result.setText(data);
                    buffer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        epSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isExternalStorageWritable())
                    return;     // 외부메모리를 사용하지 못하면 끝냄
                requestPermission();

                Log.i(TAG, getLocalClassName() + ":file save start");
                try {
                    File f = new File(getExternalFilesDir(null), "demofile.jpg"); // 경로, 파일명
                    InputStream is = getResources().openRawResource(R.raw.ballons);
                    OutputStream os = new FileOutputStream(f);
                    byte[] data = new byte[is.available()];
                    is.read(data);
                    os.write(data);
                    is.close();
                    os.close();

                    result.setText("저장완료");
                    Log.i(TAG, getLocalClassName() + ":file saved");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    void requestPermission() {
        final int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            result.setText("외부메모리 읽기 쓰기 모두 가능");
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            result.setText("외부메모리 읽기만 가능");
            return true;
        }
        return false;
    }
}
