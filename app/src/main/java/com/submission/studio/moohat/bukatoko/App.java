package com.submission.studio.moohat.bukatoko;

import android.app.Application;
import android.util.Log;

import com.submission.studio.moohat.bukatoko.data.PrefsManager;

public class App extends Application {

    public static PrefsManager prefsManager;

    @Override
    public void onCreate() {
        super.onCreate();

        prefsManager = new PrefsManager(this);


        Log.e("_LogBase", "testing");
    }
}
