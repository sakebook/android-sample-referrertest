package com.sakebook.android.referrertest;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by sakemoto on 2014/04/01.
 */
public class MyObserver{
    private static MyObserver ourInstance = new MyObserver();
    private static List<ReceiveObserver> mObservers = new CopyOnWriteArrayList<ReceiveObserver>();

    public static MyObserver getInstance() {
        return ourInstance;
    }

    private MyObserver() {
    }

    public void notifyObserver() {
        /* 登録されている全監視対象に変更を通知 */
        for (int i=0; i<mObservers.size(); i++) {
            mObservers.get(i).onReceived();
        }
    }

    public void addObserver(ReceiveObserver observer) {
        mObservers.add(observer);
    }

    public void removeObserver(ReceiveObserver observer) {
        mObservers.remove(observer);
    }
}
