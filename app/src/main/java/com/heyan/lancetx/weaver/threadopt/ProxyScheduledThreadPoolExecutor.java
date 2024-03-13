package com.heyan.lancetx.weaver.threadopt;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ProxyScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {

    public ProxyScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
        settingCoreThreadTimeOut();
    }

    public ProxyScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory) {
        super(corePoolSize, threadFactory);
        settingCoreThreadTimeOut();

    }

    public ProxyScheduledThreadPoolExecutor(int corePoolSize, RejectedExecutionHandler handler) {
        super(corePoolSize, handler);
        settingCoreThreadTimeOut();
    }

    public ProxyScheduledThreadPoolExecutor(int corePoolSize, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, threadFactory, handler);
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


}
