package com.example.android.instagramclone.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.android.instagramclone.R;

import java.util.ArrayList;

/**
 * Created by shash on 9/6/2017.
 */

public class AccountSettingsActivity extends AppCompatActivity{
    private static final String TAG = "AccountSettingsActivity";
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsettings);
        mContext= AccountSettingsActivity.this;
        Log.d(TAG, "onCreate: started");
        setupSettingsList();

        ImageView backArrow= (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: inside backarrow click method");
                finish();
            }
        });
    }

    private void setupSettingsList(){
        Log.d(TAG, "setupSettingsList: intialize List of settings activity");
        ListView listView= (ListView) findViewById(R.id.lvAccountSettings);

        ArrayList<String> options= new ArrayList<>();
        options.add(getString(R.string.edit_profile));
        options.add(getString(R.string.sign_out));

        ArrayAdapter adapter= new ArrayAdapter(mContext,android.R.layout.simple_list_item_1,options);
        listView.setAdapter(adapter);
    }
}
