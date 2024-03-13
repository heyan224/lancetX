package com.heyan.lancetx.weaver.threadopt;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class PThreadPoolExecutor  extends ThreadPoolExecutor {


    public PThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        settingCoreThreadTimeOut();
    }

    public PThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        settingCoreThreadTimeOut();

    }

    public PThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        settingCoreThreadTimeOut();
    }

    public PThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);

        settingCoreThreadTimeOut();

    }


    private void settingCoreThreadTimeOut() {
        try {
            if (SuperThreadPoolManager.isProxyThreadEnable()) {
                if (!allowsCoreThreadTimeOut()) {
                    super.setKeepAliveTime(Math.max(30L, getKeepAliveTime(TimeUnit.SECONDS)), TimeUnit.SECONDS);
                    allowCoreThreadTimeOut(true);
                }
            }
        } catch (Exception e) {

        }
    }


    @Override
    public void execute(Runnable command) {
        if (!SuperThreadPoolManager.isProxyThreadEnable()) {
            super.execute(command);
            return;
        }
        try {
            DefaultThreadPoolExecutor.getZyCoreThreadPool().execute(command);
        } catch (OutOfMemoryError error) {
            DefaultThreadPoolExecutor.getExtraThreadPool().execute(command);
        }
    }


    @Override
    public Future<?> submit(Runnable task) {
        if (!SuperThreadPoolManager.isProxyThreadEnable()) {
            return super.submit(task);
        }

        try {
            return DefaultThreadPoolExecutor.getZyCoreThreadPool().submit(task);
        } catch (OutOfMemoryError error) {
            return DefaultThreadPoolExecutor.getExtraThreadPool().submit(task);
        }
    }


    @Override
    public <T> Future<T> submit(Callable<T> task) {
        if (!SuperThreadPoolManager.isProxyThreadEnable()) {
            return super.submit(task);
        }
        try {
            return DefaultThreadPoolExecutor.getZyCoreThreadPool().submit(task);
        } catch (OutOfMemoryError error) {
            return DefaultThreadPoolExecutor.getExtraThreadPool().submit(task);
        }

    }
}
