package com.example.android.instagramclone.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started");
        
        setContentView(R.layout.activity_profile);
        setupBottomNavigationView();
    }

    private void setupToolBar(){
        Toolbar toolbar= (Toolbar) findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d(TAG, "onMenuItemClick: clicked menu item" + item);
                switch (item.getItemId()){
                    case R.id.profileMenu:
                        Log.d(TAG, "onMenuItemClick: Navogaitng to profile preferences");
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu,menu);
        return true;
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
