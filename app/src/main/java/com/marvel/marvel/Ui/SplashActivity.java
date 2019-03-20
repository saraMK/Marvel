package com.marvel.marvel.Ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.marvel.marvel.R;

import java.util.Date;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d("setContentView",new Date().getTime()+"");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the LoginActivityAct-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, MarvelHerosActivity.class);
                startActivity(mainIntent);
                finish();

            }
        }, 2000);
    }
}
