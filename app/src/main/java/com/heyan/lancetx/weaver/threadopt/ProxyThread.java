package com.heyan.lancetx.weaver.threadopt;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProxyThread extends Thread {
    private static String THREAD_POOL_WORK = "ThreadPoolExecutor$Worker";
    private Runnable target;
    public ProxyThread() {
        this(null, null, "Thread-ProxyThread", 0);
    }

    public ProxyThread(@Nullable Runnable target) {
        this(null, target, "Thread-ProxyThread", 0);
    }

    public ProxyThread(ThreadGroup group, Runnable target) {
        this(group, target, "Thread-ProxyThread", 0);
    }

    public ProxyThread(@NonNull String name) {
        this(null, null, "Thread-ProxyThread-"+name, 0);
    }

    public ProxyThread(ThreadGroup group, String name) {
        this(group, null, "Thread-ProxyThread-"+name, 0);
    }

    public ProxyThread(Runnable target, String name) {
        this(null, target, "Thread-ProxyThread-"+name, 0);

    }

    public ProxyThread(ThreadGroup group, Runnable target, String name) {
        this(group, target, "Thread-ProxyThread-"+name, 0);
    }

    public ProxyThread(ThreadGroup group, Runnable target, String name,
                       long stackSize) {
        super(group, target, name, SuperThreadPoolManager.getStackSize());
        this.target = target;
        trace();
    }

    @Override
    public synchronized void start() {
        try {

            if (target == null || target.getClass().getName().contains(THREAD_POOL_WORK)) {

                super.start();
                return;
            }

            if (!SuperThreadPoolManager.isProxyThreadEnable()) {
                super.start();
                return;
            }
            DefaultThreadPoolExecutor.getHCoreThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread currentThread = currentThread();
                        String name = currentThread.getName();
                        String originName = ProxyThread.this.getName();
                        currentThread.setName(originName);
                        if (currentThread.getPriority() != ProxyThread.this.getPriority()) {
                            currentThread.setPriority(ProxyThread.this.getPriority());
                        }
                    } catch (Exception e) {

                    }
                    ProxyThread.this.run();
                }
            });


        } catch (OutOfMemoryError error) {
            DefaultThreadPoolExecutor.executeCacheWork(new Runnable() {
                @Override
                public void run() {
                    ProxyThread.this.run();
                }
            });

        }
    }


    private void trace(){

    }
}