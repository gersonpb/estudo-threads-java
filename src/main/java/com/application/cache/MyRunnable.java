package com.application.cache;

public class MyRunnable implements Runnable{

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name);
    }
}
