package com.heyan.lancetx.weaver;

import com.heyan.lancetx.weaver.threadopt.PThreadPoolExecutor;
import com.heyan.lancetx.weaver.threadopt.ProxyScheduledThreadPoolExecutor;
import com.heyan.lancetx.weaver.threadopt.ProxyThread;
import com.knightboost.lancet.api.Scope;
import com.knightboost.lancet.api.annotations.Group;
import com.knightboost.lancet.api.annotations.ReplaceInvoke;
import com.knightboost.lancet.api.annotations.ReplaceNewInvoke;
import com.knightboost.lancet.api.annotations.TargetClass;
import com.knightboost.lancet.api.annotations.TargetMethod;
import com.knightboost.lancet.api.annotations.Weaver;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Weaver
@Group("threadOptimize")
public class ThreadOptimize {
    // Executors.newSingleThreadExecutor() 暂不做处理
    // handlerthread 暂不做处理
    @ReplaceNewInvoke()
    public static void replaceNewThread(Thread t, ProxyThread proxyThread, ThreadFactoryProxyThread extra) {
    }

    @ReplaceNewInvoke()
    public static void replaceNewScheduledThreadPoolExecutor(ScheduledThreadPoolExecutor t,
                                                             ProxyScheduledThreadPoolExecutor proxyThreadPool) {
    }

    @ReplaceNewInvoke()
    public static void replaceNewThreadPoolExecutor(ThreadPoolExecutor t,
                                                    PThreadPoolExecutor proxyThreadPool) {
    }


    @ReplaceInvoke(isStatic = true)
    @TargetClass(value = "java.util.concurrent.Executors", scope = Scope.SELF)
    @TargetMethod(methodName = "newFixedThreadPool")
    public static ExecutorService replaceNewFixedThreadPool(int nThreads) {
        return new PThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    @ReplaceInvoke(isStatic = true)
    @TargetClass(value = "java.util.concurrent.Executors", scope = Scope.SELF)
    @TargetMethod(methodName = "newCachedThreadPool")
    public static ExecutorService replaceNewCachedThreadPool() {
        return new PThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }


    @ReplaceInvoke(isStatic = true)
    @TargetClass(value = "java.util.concurrent.Executors", scope = Scope.SELF)
    @TargetMethod(methodName = "newCachedThreadPool")
    public static ExecutorService replaceNewCachedThreadPool(ThreadFactory threadFactory) {
        return new PThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                threadFactory);
    }


}
