package com.zhangyue.lancetx;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProxyThread extends Thread{

    public ProxyThread(){
        System.out.println("ProxyThread created");
    }

    public ProxyThread(@Nullable Runnable target) {
        super(target);
    }

    public ProxyThread(@Nullable Runnable target, @NonNull String name) {
        super(target,name);

    }
    public ProxyThread(@NonNull String name) {
        super(name);
        Log.i("Activity","ProxyThread"+name);
    }
}
