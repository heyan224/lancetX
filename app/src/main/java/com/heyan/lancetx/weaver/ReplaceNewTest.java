package com.heyan.lancetx.weaver;

import com.heyan.lancetx.weaver.threadopt.ProxyThread;
import com.knightboost.lancet.api.annotations.Group;
import com.knightboost.lancet.api.annotations.ReplaceNewInvoke;
import com.knightboost.lancet.api.annotations.Weaver;

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
