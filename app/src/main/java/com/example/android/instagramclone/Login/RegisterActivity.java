package com.example.android.instagramclone.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.instagramclone.R;
import com.example.android.instagramclone.Utils.FirebaseMethods;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by shash on 9/8/2017.
 */

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String email,username, password;
    private EditText mEmail,mPassword,mUsername;
    private Button btnRegister;
    private ProgressBar mProgressBar;
    private Context mContext;
    private FirebaseMethods firebaseMethods;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private String append= " ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.d(TAG, "onCreate: started Register activity");
        mContext= RegisterActivity.this;
        firebaseMethods= new FirebaseMethods(mContext);

        initWidgets();
        setupFirebaseAuth();
        init();
    }

    private void init(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email= mEmail.getText().toString();
                password= mPassword.getText().toString();
                username= mUsername.getText().toString();

                if (checkInputs(email,password,username)){
                    mProgressBar.setVisibility(View.VISIBLE);
                    firebaseMethods.registerNewEmail(email,password,username);
                }
            }
        });

    }

    private boolean checkInputs(String email, String password, String username) {
        Log.d(TAG, "checkInputs: checking inputs from null values");
        if (email.equals("") || password.equals("") || username.equals("")){
            Toast.makeText(mContext, "All input fields must be filled", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Initializing widgets
     */
    private void initWidgets(){
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);

        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        mUsername= (EditText) findViewById(R.id.input_username);
        btnRegister= (Button) findViewById(R.id.btn_register);
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


    /**
     * Set up of Firebase
     */
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth");
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase= FirebaseDatabase.getInstance();
        myRef= mFirebaseDatabase.getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //check for the username
                            if (firebaseMethods.checkIfUsernameExists(username,dataSnapshot)){
                                append = myRef.getKey().substring(3,10);
                                Log.d(TAG, "onDataChange: username already exists, Appending random string to name" + append);
                            }
                            username = username + append;

                            firebaseMethods.addNewUser(email,username," "," "," ");
                            Toast.makeText(mContext,"SignUp successfull. Sending email verification", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
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
