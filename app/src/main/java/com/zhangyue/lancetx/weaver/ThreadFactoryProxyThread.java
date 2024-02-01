package com.zhangyue.lancetx.weaver;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ThreadFactoryProxyThread extends Thread {
    private final static String TAG = "FactoryProxy";
    private Runnable target;
    public ThreadFactoryProxyThread() {
        this(null, null, "", 0);
    }

    public ThreadFactoryProxyThread(@Nullable Runnable target) {
        this(null, target, "", 0);
    }

    public ThreadFactoryProxyThread(ThreadGroup group, Runnable target) {
        this(group, target, "", 0);
    }

    public ThreadFactoryProxyThread(@NonNull String name) {
        this(null, null, name, 0);
    }

    public ThreadFactoryProxyThread(ThreadGroup group, String name) {
        this(group, null, name, 0);
    }

    public ThreadFactoryProxyThread(Runnable target, String name) {
        this(null, target, name, 0);

    }

    public ThreadFactoryProxyThread(ThreadGroup group, Runnable target, String name) {
        this(group, target, "Thread-ProxyThread-"+name, 0);
    }

    public ThreadFactoryProxyThread(ThreadGroup group, Runnable target, String name,
                                    long stackSize) {
        super(group, target, TAG + "-" + name);
        this.target = target;
        trace();
    }


    @Override
    public synchronized void start() {
        super.start();
    }

    private void trace(){
        Throwable throwable = new Throwable();
        Log.e("heyan","ThreadFactoryProxyThread=="+target,throwable);
    }

}
