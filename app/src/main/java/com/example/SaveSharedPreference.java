package com.example;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference
{
//    static final String prefEmail= "email";
//    static final String prefPass="pass";
    static final String  prefFlag="0";


    public static String getFlag(Context ctx) {
        SharedPreferences readUserFlag=getSharedPreferences(ctx);
        String userFlag=readUserFlag.getString(prefFlag,"0");
        return userFlag;
    }

    public static void setFlag(Context ctx,int flag) {
        SharedPreferences flagUser=getSharedPreferences(ctx) ;
        SharedPreferences.Editor editor=flagUser.edit();
        editor.putString(prefFlag, String.valueOf(flag));
        editor.commit();




    }


    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

//    public static void setEmail(Context ctx, String userEmail)
//    {
//        SharedPreferences email=getSharedPreferences(ctx) ;
//        SharedPreferences.Editor editor=email.edit();
//        editor.putString(prefEmail,userEmail);
//        editor.commit();
//
//
//    }

//    public static String getEmail(Context ctx)
//    {
//        SharedPreferences readUserEmail=getSharedPreferences(ctx);
//        String userEmail=readUserEmail.getString(prefEmail,"");
//        return userEmail;
//    }
//
//
//    public static void setPassword(Context ctx, String userPassword)
//    {
//        SharedPreferences pass=getSharedPreferences(ctx) ;
//        SharedPreferences.Editor editor=pass.edit();
//        editor.putString(prefPass,userPassword);
//        editor.commit();
//
//
//    }
//
//    public static String getPass(Context ctx)
//    {
//        SharedPreferences readUserPass=getSharedPreferences(ctx);
//        String userPass=readUserPass.getString(prefPass,"");
//        return userPass;
//    }
}