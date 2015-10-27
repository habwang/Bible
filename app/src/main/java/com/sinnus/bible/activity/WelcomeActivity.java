package com.sinnus.bible.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.sinnus.bible.R;
import com.sinnus.bible.bean.Book;

import java.util.ArrayList;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView tv = (TextView) findViewById(R.id.welcome_label);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();
        tv.setPadding(0, height / 3, 0, 0);
        System.out.println(width);
        System.out.println(height);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //WelcomeActivity.buildBook();
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                WelcomeActivity.this.startActivity(intent);
                WelcomeActivity.this.finish();
            }
        }, 1000);

    }

}
