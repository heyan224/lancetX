package com.heyan.lancetx.weaver.threadopt;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class PRejectedExecutionHandler  implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        DefaultThreadPoolExecutor.getExtraThreadPool().execute(r);
    }
}
