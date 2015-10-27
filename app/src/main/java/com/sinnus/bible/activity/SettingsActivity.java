package com.sinnus.bible.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sinnus.bible.R;

public class SettingsActivity extends BaseActivity {
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setImmersedStatusBar();

//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//                    .add(android.R.id.content, new SettingsFragment())
//                    .commit();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == android.R.id.home){
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void setImmersedStatusBar(){
        int status_bar_height_id = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int status_bar_height = getResources().getDimensionPixelSize(status_bar_height_id);

        View view = findViewById(R.id.settings_main);
        view.setPadding(0, status_bar_height, 0, 0);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mToolbar.setTitle("设置");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
