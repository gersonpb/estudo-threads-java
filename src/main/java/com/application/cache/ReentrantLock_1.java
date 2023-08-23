package com.application.cache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock_1 {
    public static int i = -1;
    public static Lock lock = new ReentrantLock();
    public static void main(String[] args) {

        final ExecutorService executor = Executors.newCachedThreadPool();
        Runnable r1 = () -> {
            lock.lock();
            String name = Thread.currentThread().getName();
            i++;
            System.out.println(name + ": " + i);
            lock.unlock();

        };

        for (int j = 0; j < 6; j++) {
            executor.execute(r1);
        }

        executor.shutdown();
    }
}
