package com.zing.wordbook;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.zing.wordbook.view.RoundRectImageView;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        String[] strings = {
                "Great minds have purpose, others have wishes.",
                "Being single is better than being in an unfaithful relationship.",
                "If you find a path with no obstacles, it probably doesn’t lead anywhere.",
                "Getting out of bed in winter is one of life’s hardest mission.",
                "The future is scary but you can’t just run to the past cause it’s familiar.",
                "I love it when I catch you looking at me then you smile and look away." ,
                "Having a calm smile to face with being disdained indicates kind of confidence.",
                "Success is the ability to go from one failure to another with no loss of enthusiasm."
        };
        TextView tv_str = (TextView) findViewById(R.id.tv_str);

        RoundRectImageView rectImageView = (RoundRectImageView) findViewById(R.id.imageView);
        rectImageView.setRoundPx(1000);

        tv_str.setText(strings[new Random().nextInt(strings.length)]);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.setFlags(1);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    /**
     * 禁用返回
     * @param keyCode  keyCode
     * @param event event
     * @return boolean
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return !(keyCode == KeyEvent.KEYCODE_BACK);
    }
}
