package com.example.chrome.aircovg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by ayush AS on 1/1/17.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent nIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(nIntent);

                finish();
            }
        }, 2000);

    }
}