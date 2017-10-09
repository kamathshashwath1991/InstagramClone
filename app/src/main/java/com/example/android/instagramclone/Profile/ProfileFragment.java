package com.example.android.instagramclone.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.instagramclone.R;
import com.example.android.instagramclone.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by shash on 9/12/2017.
 */

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";
    private static final int ACTIVITY_NUM= 4;

    private TextView mPosts, mFollowers, mFollowing, mDisplayName, mUsername, mWebsite, mDesription;
    private ProgressBar mProgressBar;
    private CircleImageView mProfilePhoto;
    private GridView gridView;
    private Toolbar toolbar;
    private ImageView profileMenu;
    private BottomNavigationViewEx bottomNavigationView;
    private Context mContext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        mDisplayName = (TextView) view.findViewById(R.id.display_name);
        mUsername= (TextView) view.findViewById(R.id.username);
        mWebsite= (TextView) view.findViewById(R.id.website);
        mDesription= (TextView) view.findViewById(R.id.description);
        mProfilePhoto= (CircleImageView) view.findViewById(R.id.profile_photo);
        mPosts= (TextView) view.findViewById(R.id.tvPosts);
        mFollowers= (TextView) view.findViewById(R.id.tvFollowers);
        mFollowing= (TextView) view.findViewById(R.id.tvFollowing);
        mProgressBar= (ProgressBar) view.findViewById(R.id.profileProgressBar);
        gridView= (GridView) view.findViewById(R.id.gridView);
        toolbar= (Toolbar) view.findViewById(R.id.profileToolBar);
        profileMenu= (ImageView) view.findViewById(R.id.profileMenu);
        bottomNavigationView= (BottomNavigationViewEx) view.findViewById(R.id.bottomNavViewBar);
        mContext= getActivity();

        setupBottomNavigationView();
        setupToolBar();
        Log.d(TAG, "onCreateView: started");
        return view;
    }

    private void setupToolBar(){
        ((ProfileActivity)getActivity()).setSupportActionBar(toolbar);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to account settings");
                Intent intent= new Intent(mContext,AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupBottomNavigationView(){
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView);

        BottomNavigationViewHelper.enableNavigation(mContext,bottomNavigationView);
        Menu menu= bottomNavigationView.getMenu();
        MenuItem menuItem= menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
