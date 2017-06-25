package com.zing.wordbook;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.setFlags(1);
                startActivity(intent);
                finish();
            }
        }, 3000);
//        try {
//            Thread.sleep(30000);
//            Intent intent = new Intent(this,MainActivity.class);
//            intent.setFlags(1);
//            startActivity(intent);
//            finish();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
