package com.application.cache;

import java.util.concurrent.atomic.AtomicInteger;

public class ClassesAtomicas {
    static AtomicInteger i = new AtomicInteger(-1);
    public static void main(String[] args) {
        Synchronized_1.MyRunnable runnable = new Synchronized_1.MyRunnable();
        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        t0.start();
        t1.start();
        t2.start();
    }
    public static class MyRunnable implements Runnable {
        @Override
        public synchronized void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name +":"+ i.incrementAndGet());
        }
    }
}
