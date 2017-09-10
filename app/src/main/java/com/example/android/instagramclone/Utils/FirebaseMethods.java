package com.example.android.instagramclone.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.android.instagramclone.Models.User;
import com.example.android.instagramclone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

/**
 * Created by shash on 9/9/2017.
 */

public class FirebaseMethods {
    private static final String TAG = "FirebaseMethods";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    private Context mContext;


    public FirebaseMethods(Context context) {
        mAuth= FirebaseAuth.getInstance();
        this.mContext= context;

        if (mAuth.getCurrentUser()!=null){
            userID= mAuth.getCurrentUser().getUid();
        }
    }

    public boolean checkIfUsernameExists(String username, DataSnapshot dataSnapshot){
        Log.d(TAG, "checkIfUsernameExists: " + username );

        User user= new User();

        for (DataSnapshot ds: dataSnapshot.getChildren()){
            Log.d(TAG, "checkIfUsernameExists: datasnapshot: " + ds);
            user.setUsername(ds.getValue(User.class).getUsername());

            if (StringManipulation.expandUsername(user.getUsername()).equals(username)){
                Log.d(TAG, "checkIfUsernameExists: Found a match: " +user.getUsername());
                return true;
            }
        }
        return false;
    }

    /**
     * Register the user entered email, password, username to the firebase backend
     * @param email
     * @param password
     * @param username
     */
    public void registerEmail(final String email, String password, final String username){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Toast.makeText(mContext,R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if (task.isSuccessful()){
                            userID= mAuth.getCurrentUser().getUid();
                            Log.d(TAG, "onComplete: AuthState Changed" + userID);
                        }

                    }
                });
    }
}
