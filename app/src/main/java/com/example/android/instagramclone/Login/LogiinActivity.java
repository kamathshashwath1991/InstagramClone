package com.example.android.instagramclone.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.instagramclone.Home.HomeActivity;
import com.example.android.instagramclone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by shash on 9/8/2017.
 */

public class LogiinActivity extends AppCompatActivity {
    private static final String TAG = "LogiinActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Context mContext;
    private ProgressBar mProgressBar;
    private EditText mEmail, mPassword;
    private TextView mPleaseWait;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mPleaseWait = (TextView) findViewById(R.id.pleaseWait);
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        mContext = LogiinActivity.this;

        Log.d(TAG, "onCreate: started login activity");

        mPleaseWait.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);

        setupFirebaseAuth();
        init();
    }

    private boolean isStringNull(String string){
        Log.d(TAG, "isStringNull: check if the string is null");

        if (string.equals("")){
            return true;
        }
        else {
            return false;
        }
    }

    private void init(){
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: attempting to log in");
                String email= mEmail.getText().toString();
                String password= mPassword.getText().toString();

                if (isStringNull(email) && isStringNull(password)){
                    Toast.makeText(mContext,"You must fill out all the fields",Toast.LENGTH_SHORT).show();
                }
                else {
                   mAuth.signInWithEmailAndPassword(email,password)
                           .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   if (!task.isSuccessful()){
                                       Toast.makeText(mContext,getString(R.string.auth_failed),
                                               Toast.LENGTH_SHORT).show();
                                   }
                                   else {
                                       Log.d(TAG, "Successfully Logged in");
                                       Toast.makeText(mContext,getString(R.string.auth_success),
                                               Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });
                }
            }
        });

        TextView linkSignUp= (TextView) findViewById(R.id.link_signup);
        linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to register activity");
                Intent intent= new Intent(LogiinActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        if (mAuth.getCurrentUser()!=null){
            Intent intent= new Intent(LogiinActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Set up of Firebase
     */
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
