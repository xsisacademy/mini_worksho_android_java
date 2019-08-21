package com.workshop.android.java;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.workshop.android.java.utilities.SessionManager;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //code untuk fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);


        //logic utk jeda waktu sekian detik
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //cek flag login
                if(SessionManager.cekLogin(context)){
                    //sudah login
                    pindahKeHomeScreen();
                }
                else{
                    //belum login
                    pindahKeLoginScreen();
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 3000);
    }

    private void pindahKeLoginScreen(){
        Intent intent = new Intent(context,
                LoginActivity.class);
        startActivity(intent);

        finish();
    }

    private void pindahKeHomeScreen(){
        Intent intent = new Intent(context,
                MainActivity.class);
        startActivity(intent);

        finish();
    }
}
