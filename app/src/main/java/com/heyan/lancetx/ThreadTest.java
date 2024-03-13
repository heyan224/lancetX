package com.heyan.lancetx;

public class ThreadTest {
    public static void ttt(){
        Thread thread = new Thread(new Runnable(){

            @Override
            public void run() {

            }
        });

        thread.start();
    }
}
