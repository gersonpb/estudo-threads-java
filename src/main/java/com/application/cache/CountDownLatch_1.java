package com.application.cache;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountDownLatch_1 {
    public static volatile int i = 0;
    public static CountDownLatch latch = new CountDownLatch(3);

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        Runnable r1 = () -> {

            int j = new Random().nextInt(100);
            int x = i * j;
            System.out.println(i + " x " + j + " = " + x);
            latch.countDown();
        };
        executor.scheduleAtFixedRate(r1, 0, 1, TimeUnit.SECONDS);

        while (true) {
            await();
//            sleep();
            i = new Random().nextInt(100);
            latch = new CountDownLatch(3);
        }
    }

    private static void await() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    private static void sleep() {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
