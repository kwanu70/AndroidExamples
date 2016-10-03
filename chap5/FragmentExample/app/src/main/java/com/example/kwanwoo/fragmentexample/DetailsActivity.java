package com.example.kwanwoo.fragmentexample;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class DetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        DetailsFragment details = new DetailsFragment();
        details.setSelection(getIntent().getIntExtra("index",-1));
        getFragmentManager().beginTransaction().replace(R.id.details, details).commit();
    }
}
