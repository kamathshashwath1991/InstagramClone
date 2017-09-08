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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.android.instagramclone.R;
import com.example.android.instagramclone.Utils.BottomNavigationViewHelper;
import com.example.android.instagramclone.Utils.GridImageAdapter;
import com.example.android.instagramclone.Utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

/**
 * Created by shash on 8/6/2017.
 */

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private Context mContext= ProfileActivity.this;
    private static final int ACTIVITY_NUM= 4;
    private ProgressBar mProgressBar;
    private ImageView profilePhoto;
    private static final int NUM_GRID_COLOMNS=3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: started");
        setContentView(R.layout.activity_profile);

        setupBottomNavigationView();
        setupToolBar();
        setupActivityWidgets();
        setProfilePhoto();
        tempGridSetup();
    }

    private void tempGridSetup(){
        ArrayList<String> imgURLs= new ArrayList<String>();
        imgURLs.add("https://pbs.twimg.com/profile_images/616076655547682816/6gMRtQyY.jpg");
        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
        imgURLs.add("http://i.imgur.com/EwZRpvQ.jpg");
        imgURLs.add("http://i.imgur.com/JTb2pXP.jpg");
        imgURLs.add("https://i.redd.it/59kjlxxf720z.jpg");
        imgURLs.add("https://i.redd.it/pwduhknig00z.jpg");
        imgURLs.add("https://i.redd.it/clusqsm4oxzy.jpg");
        imgURLs.add("https://i.redd.it/svqvn7xs420z.jpg");
        imgURLs.add("http://i.imgur.com/j4AfH6P.jpg");
        imgURLs.add("https://i.redd.it/89cjkojkl10z.jpg");
        imgURLs.add("https://i.redd.it/aw7pv8jq4zzy.jpg");

        setupImageGrid(imgURLs);
    }

    private void setupImageGrid(ArrayList<String> imgUrls){
        GridView gridView= (GridView) findViewById(R.id.gridView);

        int gridWidth= getResources().getDisplayMetrics().widthPixels;
        int imageWidth= gridWidth/NUM_GRID_COLOMNS;

        gridView.setColumnWidth(imageWidth);
        GridImageAdapter gridImageAdapter= new GridImageAdapter(mContext,R.layout.layout_grid_imageview," ", imgUrls);
        gridView.setAdapter(gridImageAdapter);
    }

    private void setProfilePhoto(){
        Log.d(TAG, "setProfilePhoto: setting profile photo");
        String imgURL= "www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge/public/article_images/2016/08/ac-lloyd.jpg?itok=bb72IeLf";
        UniversalImageLoader.setImage(imgURL,profilePhoto, mProgressBar ,"https://");
    }

    private void setupActivityWidgets(){
        mProgressBar= (ProgressBar) findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        profilePhoto= (ImageView) findViewById(R.id.profile_photo);

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
