package com.application.cache;

import java.util.Random;
import java.util.concurrent.*;

public class Executors_SingleThread_Callable {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = null;
        try {
            executor = Executors.newSingleThreadExecutor();
            final Future<String> submit = executor.submit(new Tarefa());

            System.out.println(submit.isDone());
//            System.out.println(submit.get());
            System.out.println(submit.get(1, TimeUnit.SECONDS));

            //executor.shutdown();;
            //executor.awaitTermination(10, TimeUnit.SECONDS);
            //System.out.println(submit.isDone());
        } catch (Exception e) {
            throw e;
        }finally {
            if (executor != null) {
//                executor.shutdown();
                executor.shutdownNow();
            }
        }


    }

    public static class Tarefa implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(1000);
            String name = Thread.currentThread().getName();
            final int nextInt = new Random().nextInt(1000);
            return name +" : Executors Thread " + nextInt;
        }
    }
}
