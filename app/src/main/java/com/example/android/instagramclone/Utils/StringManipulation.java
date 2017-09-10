package com.example.android.instagramclone.Utils;

/**
 * Created by shash on 9/10/2017.
 */

public class StringManipulation {

    public static String expandUsername(String username){
        return username.replace("."," ");
    }

    public static String condenseUsername(String username){
        return username.replace(" ", ".");
    }
}
