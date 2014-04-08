package com.sakebook.android.referrertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by sakemotoshinya on 2014/03/23.
 */
public class GooglePlayReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(MyApplication.TAG, "onReceive");
        if (intent != null && !"".equals(intent.getStringExtra("referrer"))) {
            String referrer = intent.getStringExtra("referrer");
            Log.i(MyApplication.TAG, "referrerAll: "+referrer);
            referrer = Uri.decode(referrer);
            Log.i(MyApplication.TAG, "referrerDecode: "+referrer);
            Uri uri = Uri.parse("?" + referrer);
            String campaign = "";
            campaign = uri.getQueryParameter("utm_campaign");
            Log.i(MyApplication.TAG, "utm_campaign: "+campaign);

            if (!TextUtils.isEmpty(campaign)) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                preferences.edit().putString("referrer", campaign).commit();

                MyObserver.getInstance().notifyObserver();
            }

        }
    }
}
