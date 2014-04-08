package com.sakebook.android.referrertest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements ReceiveObserver{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(MyApplication.TAG, "onCreate");
        setContentView(R.layout.activity_main);

        /* 呼ばれても良い状態になったら監視対象にする */
        MyObserver.getInstance().addObserver(this);

        ((TextView)findViewById(R.id.hello)).setText("normal launch");

        /* 行いたい処理 */
        doSomeThing();
    }

    @Override
    protected void onStart() {
        Log.i(MyApplication.TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(MyApplication.TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.i(MyApplication.TAG, "onStop");
        MyObserver.getInstance().removeObserver(this);
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.i(MyApplication.TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.i(MyApplication.TAG, "onDestroy");
        super.onDestroy();
    }

    private String getReferrer() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getString("referrer", null);
    }

    private void resetReferrer() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().remove("referrer").commit();
    }

    @Override
    public void onReceived() {
        Log.i(MyApplication.TAG, "onReceived");
        /* 監視対象から外す */
        MyObserver.getInstance().removeObserver(this);
        /* 行いたい処理 */
        doSomeThing();
    }

    private void doSomeThing() {
        Log.i(MyApplication.TAG, "doSomeThing");
        String referrer = getReferrer();
        /* 通常起動、及びReceiverスレッドより先行した場合はリファラがない。 */
        ((TextView)findViewById(R.id.referrer)).setText("referrer: "+referrer);
        if (TextUtils.isEmpty(referrer)) {
            return;
        }
        /* 本当にしたいこと */
        ((TextView)findViewById(R.id.hello)).setText("special launch");
        ((TextView)findViewById(R.id.referrer)).setText("referrer: "+referrer);
        resetReferrer();
    }
}
