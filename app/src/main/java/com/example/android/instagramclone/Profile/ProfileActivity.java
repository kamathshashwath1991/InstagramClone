package com.example.android.instagramclone.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.android.instagramclone.R;
import com.example.android.instagramclone.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

/**
 * Created by shash on 8/6/2017.
 */

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private Context mContext= ProfileActivity.this;
    private static final int ACTIVITY_NUM= 4;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started");
        setContentView(R.layout.activity_profile);
        mProgressBar= (ProgressBar) findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        setupBottomNavigationView();
        setupToolBar();
    }

    private void setupToolBar(){
        Toolbar toolbar= (Toolbar) findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        ImageView profileMenu= (ImageView) findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to account settings");
                Intent intent= new Intent(mContext,AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    //Bottom Navigation setup
    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx= (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext,bottomNavigationViewEx);
        Menu menu= bottomNavigationViewEx.getMenu();
        MenuItem menuItem= menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
