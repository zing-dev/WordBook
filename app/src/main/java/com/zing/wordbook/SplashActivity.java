package com.zing.wordbook;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.zing.wordbook.view.RoundRectImageView;

import java.util.Calendar;
import java.util.Random;

public class SplashActivity extends AppCompatActivity {

    public static String[] strings = {
            "Great minds have purpose, others have wishes.",
            "Being single is better than being in an unfaithful relationship.",
            "If you find a path with no obstacles, it probably doesn’t lead anywhere.",
            "Getting out of bed in winter is one of life’s hardest mission.",
            "The future is scary but you can’t just run to the past cause it’s familiar.",
            "I love it when I catch you looking at me then you smile and look away.",
            "Having a calm smile to face with being disdained indicates kind of confidence.",
            "Success is the ability to go from one failure to another with no loss of enthusiasm."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView tv_str = (TextView) findViewById(R.id.tv_str);
        final TextView tv_count = (TextView) findViewById(R.id.tv_count);

        RoundRectImageView rectImageView = (RoundRectImageView) findViewById(R.id.imageView);
        rectImageView.setRoundPx(1000);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i++ < 5) {
                    int hour, minute, second;
                    Calendar now = Calendar.getInstance();
                    Calendar instance = Calendar.getInstance();
                    instance.set(Calendar.HOUR_OF_DAY, 18);
                    instance.set(Calendar.MINUTE, 0);
                    instance.set(Calendar.SECOND, 0);
                    long timeInMillis = instance.getTimeInMillis();
                    long diff = (timeInMillis - now.getTimeInMillis()) / 1000;
                    if (diff < 0)
                        diff += 24 * 3600;
                    hour = (int) diff / 3600;
                    minute = (int) (diff - hour * 3600) / 60;
                    second = (int) diff % 60;
                    System.out.println("还剩" + hour + "小时" + minute + "分" + second + "秒下班了");
                    tv_count.setText("还剩" + hour + "小时" + minute + "分" + second + "秒下班了");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

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
     *
     * @param keyCode keyCode
     * @param event   event
     * @return boolean
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return !(keyCode == KeyEvent.KEYCODE_BACK);
    }
}
