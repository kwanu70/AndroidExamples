package com.example.kwanwoo.sharedpreferencetest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String PREFERENCES_GROUP = "MyPreference";
    public static final String PREFERENCES_ATTR = "TextInput";
    SharedPreferences setting;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setting = getSharedPreferences(PREFERENCES_GROUP, MODE_PRIVATE);
        final EditText textInput = (EditText) findViewById(R.id.textInput1);
        textInput.setText(retrieveText());

        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = textInput.getText().toString();
                saveText(name);
            }
        });

    }

    private String retrieveText() {
        String initText = "";
        if (setting.contains(PREFERENCES_ATTR)) {
            initText = setting.getString(PREFERENCES_ATTR, "");
        }
        return initText;
    }

    private void saveText(String text) {
        SharedPreferences.Editor editor = setting.edit();
        editor.putString(PREFERENCES_ATTR, text);
        editor.commit();
    }
}