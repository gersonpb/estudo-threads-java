package com.application.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Executors_MultThread {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = null;
        try {
////            executor = Executors.newFixedThreadPool(4);
            executor = Executors.newCachedThreadPool();

            final Future<String> f1 = executor.submit(new Tarefa());
            System.out.println(f1.get());
            final Future<String> f2 = executor.submit(new Tarefa());
            final Future<String> f3 = executor.submit(new Tarefa());

            System.out.println(f2.get());
            System.out.println(f3.get());
            executor.shutdown();
        } catch (Exception e) {
            throw e;

        } finally {
            if (executor != null) {
                executor.shutdownNow();
            }
        }
    }
    public static class Tarefa implements Callable<String> {
        @Override
        public String call() throws Exception {
            String name = Thread.currentThread().getName();
            final int nextInt = new Random().nextInt(1000);
            return name +" : Executors Thread " + nextInt;
        }
    }

}
