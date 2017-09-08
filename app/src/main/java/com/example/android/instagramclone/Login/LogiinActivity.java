package com.example.android.instagramclone.Login;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.instagramclone.R;

/**
 * Created by shash on 9/8/2017.
 */

public class LogiinActivity extends AppCompatActivity {
    private static final String TAG = "LogiinActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: started login activity");
    }
}
