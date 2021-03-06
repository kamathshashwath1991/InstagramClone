package com.example.android.instagramclone.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.android.instagramclone.R;
import com.example.android.instagramclone.Utils.BottomNavigationViewHelper;
import com.example.android.instagramclone.Utils.SectionStatePagerAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

/**
 * Created by shash on 9/6/2017.
 */

public class AccountSettingsActivity extends AppCompatActivity{
    private static final String TAG = "AccountSettingsActivity";
    private Context mContext;

    private SectionStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;
    private static final int ACTIVITY_NUM= 4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsettings);
        mContext= AccountSettingsActivity.this;
        Log.d(TAG, "onCreate: started");
        mViewPager= (ViewPager) findViewById(R.id.container);
        mRelativeLayout= (RelativeLayout) findViewById(R.id.relLayout1);

        setupSettingsList();
        setupBottomNavigationView();
        setUpFragment();

        ImageView backArrow= (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: inside backarrow click method");
                finish();
            }
        });
    }

    private void setUpFragment(){
        pagerAdapter= new SectionStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EditProfileFragment(), getString(R.string.edit_profile_fragment));
        pagerAdapter.addFragment(new SignOutFragment(), getString(R.string.sign_out_fragment));
    }

    private void setViewPager(int fragmentNumber){
        mRelativeLayout.setVisibility(View.INVISIBLE);
        Log.d(TAG, "setViewPager: Navigating to fragment number " + fragmentNumber);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(fragmentNumber);
    }

    private void setupSettingsList(){
        Log.d(TAG, "setupSettingsList: intialize List of settings activity");
        ListView listView= (ListView) findViewById(R.id.lvAccountSettings);

        ArrayList<String> options= new ArrayList<>();
        options.add(getString(R.string.edit_profile_fragment));
        options.add(getString(R.string.sign_out_fragment));

        ArrayAdapter adapter= new ArrayAdapter(mContext,android.R.layout.simple_list_item_1,options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: navigating to fragment number" + position);
                setViewPager(position);
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
