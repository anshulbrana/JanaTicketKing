package com.example.janaticketking;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class SplashScreenActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView= (TextView) findViewById(R.id.textView);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.hometransition);
        imageView.startAnimation(myanim);
        textView.startAnimation(myanim);

        final Intent i = new Intent(this, WelcomeActivity.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                     startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
        verifyPermissions();


    }


    private boolean verifyPermissions() {
        boolean flag = false;
        String[] permissions = {Manifest.permission.CALL_PHONE};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED
        ) {
            flag = true;
        } else {
            ActivityCompat.requestPermissions(SplashScreenActivity.this, permissions, 101);
        }
        return flag;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        verifyPermissions();
    }


}
