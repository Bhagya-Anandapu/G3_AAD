package com.rahul.diceroller2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent iHome=new Intent(SplashActivity2.this,MainActivity.class);
                startActivity(iHome);
            }
        }, 5000);



    }
}
