package com.sahil.dailyneed.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.sahil.dailyneed.common.UserTypeActivity;

import java.util.HashMap;

public class SessionManger {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREFER_NAME = "DailyNeed";
    private static final String IS_USER_LOGIN = "user";
    private static final String IS_SHOP_LOGIN = "shop";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_Id = "id";
    public static final String KEY_NAME = "name";


    @SuppressLint("CommitPrefEdits")
    public SessionManger(Context context) {
        _context = context;
        preferences = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }


    public void CreateInstallerLogin(boolean shop, boolean user, String email, String password, String name, String id) {
        editor.putBoolean(IS_USER_LOGIN, user);
        editor.putBoolean(IS_SHOP_LOGIN, shop);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_Id,id);

        editor.commit();
    }
//    public void CreateBrokerLogin(String email, String password) {
//        editor.putBoolean(IS_BROKER_LOGIN, true);
//        editor.putString(KEY_EMAIL, email);
//        editor.putString(KEY_PASSWORD, password);
//        editor.commit();
//    }

    public HashMap<String, String> getUSerDeatis() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_Id, preferences.getString(KEY_Id, null));
        user.put(KEY_EMAIL, preferences.getString(KEY_EMAIL, null));
        user.put(KEY_NAME, preferences.getString(KEY_NAME, null));

        return user;
    }

    public void logoutUser() {

        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, UserTypeActivity.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);
    }

    public boolean isUserLogin() {
        return preferences.getBoolean(IS_USER_LOGIN, false);
    }

    public boolean isShoperLogin() {
        return preferences.getBoolean(IS_SHOP_LOGIN, false);
    }
}
