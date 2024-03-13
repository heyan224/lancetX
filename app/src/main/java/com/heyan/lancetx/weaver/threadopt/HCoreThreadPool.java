package com.heyan.lancetx.weaver.threadopt;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HCoreThreadPool extends ThreadPoolExecutor {
    public HCoreThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        settingCoreThreadTimeOut();
    }

    public HCoreThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        settingCoreThreadTimeOut();
    }

    public HCoreThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        settingCoreThreadTimeOut();
    }

    public HCoreThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        settingCoreThreadTimeOut();
    }

    private void settingCoreThreadTimeOut() {
        try {
            if (SuperThreadPoolManager.isProxyThreadEnable()) {
                if (!allowsCoreThreadTimeOut()) {
                    super.setKeepAliveTime(30L, TimeUnit.SECONDS);
                    allowCoreThreadTimeOut(true);
                }
            }
        } catch (Exception e) {

        }
    }

}
