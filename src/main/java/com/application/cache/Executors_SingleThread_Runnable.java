package com.application.cache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Executors_SingleThread_Runnable {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();
            executor.execute(new Tarefa());
            executor.execute(new Tarefa());
            executor.execute(new Tarefa());
            final Future<?> submit = executor.submit(new Tarefa());

            System.out.println(submit.isDone());
            executor.shutdown();;
            executor.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println(submit.isDone());
        } catch (Exception e) {
            throw e;
        }finally {
            if (executor != null) {
//                executor.shutdown();
                executor.shutdownNow();
            }
        }


    }

    public static class Tarefa implements Runnable{
        @Override
        public void run() {

            String name = Thread.currentThread().getName();
            System.out.println(name +" Executors Thread");
        }
    }
}
