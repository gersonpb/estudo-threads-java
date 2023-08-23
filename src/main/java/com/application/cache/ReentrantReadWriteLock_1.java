package com.application.cache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLock_1 {
    public static int i = -1;
    public static ReadWriteLock lock = new ReentrantReadWriteLock();
    public static void main(String[] args) {

        final ExecutorService executor = Executors.newCachedThreadPool();
        Runnable r1 = () -> {
            final Lock writeLock = lock.writeLock();
            writeLock.lock();
            String name = Thread.currentThread().getName();
            System.out.println(name + " - Escrevendo: " + i);
            i++;

            System.out.println(name + " - Escrito: " + i);
            writeLock.unlock();

        };

        Runnable r2 = () -> {
            final Lock readLock = lock.readLock();
            readLock.lock();
            System.out.println("Lendo: " + i);
            System.out.println("Lido: " + i);
            readLock.unlock();

        };

        for (int j = 0; j < 6; j++) {
            executor.execute(r1);
            executor.execute(r2);
        }

        executor.shutdown();
    }
}
