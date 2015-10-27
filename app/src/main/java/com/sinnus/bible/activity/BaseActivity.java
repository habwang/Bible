package com.sinnus.bible.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sinnus.bible.R;
import com.sinnus.bible.util.ThemeUtil;

/**
 * Created by sinnus on 2015/9/1.
 */
public class BaseActivity extends AppCompatActivity {
    public Toolbar mToolbar = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTheme();
    }

    public void initTheme(){
        ThemeUtil.setTheme(this, ThemeUtil.getCurrentTheme(this));
    }
    public void setImmersedStatusBar(){
        int status_bar_height_id = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int status_bar_height = getResources().getDimensionPixelSize(status_bar_height_id);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        View view = findViewById(R.id.settings_main);
        view.setPadding(0, status_bar_height, 0, 0);
        mToolbar.setTitle("设置");
    }

}
