package com.maheshgaya.android.displayjoke;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

/**
 * Created by Mahesh Gaya on 12/4/16.
 */

public class JokeActivity extends AppCompatActivity {
    private static final String TAG = JokeActivity.class.getSimpleName();
    public static final String JOKE_KEY = "joke";
    public static final String JOKE_FRAGMENT_TAG = "JTAG";
    private Toolbar mToolbar;
    private String mJoke;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        mToolbar = (Toolbar)findViewById(R.id.toolbar_joke);
        setupToolbar();
        if (getIntent().getExtras() != null){
            mJoke = getIntent().getStringExtra(JokeActivity.JOKE_KEY);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public String getJokeFromIntent(){
        return mJoke;
    }

    private void setupToolbar(){
        //shows Action bar
        setSupportActionBar(mToolbar);
        //adds back button
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
