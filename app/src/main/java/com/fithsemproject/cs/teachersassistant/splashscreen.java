package com.fithsemproject.cs.teachersassistant;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                setContentView(R.layout.splash);

        int secondsDelayed=1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(splashscreen.this, MainActivity.class));
                finish();
                }
            }, secondsDelayed * 3000);
        }
    }


//        EasySplashScreen config= new EasySplashScreen(splashscreen.this)
//                .withFullScreen()
//                .withTargetActivity(MainActivity.class)
//                .withSplashTimeOut(5000)
//                .withLogo(R.mipmap.ic_launcher)
//                .withHeaderText("Teacher's Assistant")
//                .withFooterText("Copyright 2017")
//                .withBeforeLogoText("Welcome")
//                .withAfterLogoText("Get started");
//
//        config.getHeaderTextView().setTextColor(android.graphics.Color.WHITE);
//        config.getFooterTextView().setTextColor(android.graphics.Color.WHITE);
//        config.getAfterLogoTextView().setTextColor(android.graphics.Color.WHITE);
//        config.getBeforeLogoTextView().setTextColor(android.graphics.Color.WHITE);
//
//        View view=config.create();
//        setContentView(view);



