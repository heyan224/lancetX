package com.zhangyue.lancetx.weaver;

import android.content.Intent;

import com.knightboost.lancet.api.annotations.Group;
import com.knightboost.lancet.api.annotations.ReplaceNewInvoke;
import com.knightboost.lancet.api.annotations.Weaver;
import com.zhangyue.lancetx.ProxyThread;

@Weaver
@Group("threadOptimize")
public class ReplaceNewTest {

//    @ReplaceNewInvoke()
//    public static void replaceNewThread(Thread t, ProxyThread proxyThread){
//    }
//
//    @ReplaceNewInvoke()
//    public static void replaceIntent(Intent intent, WrappedIntent newIntent){
//    }

    @ReplaceNewInvoke()
    public static void replaceNewThread(Thread t, ProxyThread proxyThread, ThreadFactoryProxyThread extra) {
    }

}
