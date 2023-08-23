package com.application.cache;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.*;

public class Executors_Scheduled {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

        //Callable
//        ScheduledFuture<String> future = executor.schedule(new Tarefa(), 1, TimeUnit.SECONDS);
//        System.out.println(future.get());

        //Runnable
//        executor.schedule(new Tarefa(), 2, TimeUnit.SECONDS);
//        executor.scheduleAtFixedRate(new Tarefa(), 0, 1, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(new Tarefa(), 0, 1, TimeUnit.SECONDS);

//        executor.shutdown();
    }

    public static class Tarefa implements Runnable {
        @Override
        public void run(){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(LocalTime.now());

            String name = Thread.currentThread().getName();
            final int nextInt = new Random().nextInt(1000);
            System.out.println(name +" : Executors Thread " + nextInt);
        }
    }

//    public static class Tarefa implements Callable<String> {
//        @Override
//        public String call() throws Exception {
//            String name = Thread.currentThread().getName();
//            final int nextInt = new Random().nextInt(1000);
//            return name +" : Executors Thread " + nextInt;
//        }
//    }
}
