package com.heyan.lancetx.weaver.threadopt;

public class SuperThreadPoolManager {



    public static long getStackSize() {
      return -(512*1024);
    }
    public static boolean isProxyThreadEnable() {
        return true;
    }

}
