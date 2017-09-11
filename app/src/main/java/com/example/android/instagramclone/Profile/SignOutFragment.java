package com.example.android.instagramclone.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.instagramclone.Login.LogiinActivity;
import com.example.android.instagramclone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by shash on 9/6/2017.
 */

public class SignOutFragment extends Fragment {
    private static final String TAG = "SignOutFragment";

    //firebase stuff
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressBar mProgressBar;
    private TextView tvSignout;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_signout,container,false);

        tvSignout= (TextView) view.findViewById(R.id.tvConfirmSignout);
        mProgressBar= (ProgressBar) view.findViewById(R.id.progressBar);
        Button btnConfirmSignOut= (Button) view.findViewById(R.id.btnConfirmSignOut);

        mProgressBar.setVisibility(View.GONE);
        setupFirebaseAuth();

        btnConfirmSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: attempting to singing out");
                mProgressBar.setVisibility(View.VISIBLE);
                mAuth.signOut();
                getActivity().finish();
            }
        });

        return view;
    }

    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out and navigating to login activity");
                    Intent intent= new Intent(getActivity(),LogiinActivity.class);
                    startActivity(intent);

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
