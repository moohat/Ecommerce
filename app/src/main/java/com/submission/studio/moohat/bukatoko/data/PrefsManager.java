package com.submission.studio.moohat.bukatoko.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;

public class PrefsManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private Context context;

    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "TokoOnlinePref";
    private static final String IS_LOGIN = "isLoggedIn";
    private static final String SESS_ID= "id";
    private static final String SESS_NAME= "name";
    private static final String SESS_EMAIL= "email";
    private static final String SESS_PASS= "password";


    public PrefsManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String id, String name, String email, String password){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(SESS_ID, id);
        editor.putString(SESS_NAME, name);
        editor.putString(SESS_EMAIL, email);
        editor.putString(SESS_PASS, password);

        editor.commit();

        Toast.makeText(context, "berhail masuk sebagai "+name, Toast.LENGTH_SHORT).show();

    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();

        user.put(SESS_ID, pref.getString(SESS_ID, null));
        user.put(SESS_NAME, pref.getString(SESS_NAME, null));
        user.put(SESS_EMAIL, pref.getString(SESS_EMAIL, null));
        user.put(SESS_PASS, pref.getString(SESS_PASS, null));
        return user;
    }


    public void logoutUser(){
        editor.clear();
        editor.commit();
        Toast.makeText(context, "Keluar. ", Toast.LENGTH_SHORT).show();

    }

   public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
   }
}
