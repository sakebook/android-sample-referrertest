package com.sakebook.android.referrertest;

import android.app.Application;
import android.util.Log;

/**
 * Created by sakemoto on 2014/04/01.
 */
public class MyApplication extends Application{
    public static final String TAG = "referrertest";
    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        super.onCreate();
    }

}
