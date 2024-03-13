package com.heyan.lancetx.weaver.threadopt;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class DefaultThreadPoolExecutor {

    private static ThreadPoolExecutor extraThreadPool = new ThreadPoolExecutor(
            0,
            Integer.MAX_VALUE,
            10,
            TimeUnit.SECONDS,
            new SynchronousQueue(),
            new DefaultNamedThreadFactory(""));

    private static ScheduledExecutorService cacheWorkPool =
            Executors.newSingleThreadScheduledExecutor(new DefaultNamedThreadFactory("cacheWorkPool"));

    private static ThreadPoolExecutor hCoreThreadPool = new HCoreThreadPool(
            5,
            20,
            30L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue(20),
            new DefaultNamedThreadFactory("HCoreThreadPool"),
            new PRejectedExecutionHandler());

    public static void execute(Runnable runnable, int priority) {
        extraThreadPool.execute(runnable);
    }

    public static ThreadPoolExecutor getExtraThreadPool() {
        return extraThreadPool;
    }

    public static void executeCacheWork(Runnable runnable){
        cacheWorkPool.execute(runnable);
    }


    public static ThreadPoolExecutor getHCoreThreadPool(){
        return  hCoreThreadPool;
    }


}
