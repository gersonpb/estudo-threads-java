package com.application.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Executors_MultThread_1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = null;

        try {
            executor = Executors.newCachedThreadPool();
            List<Tarefa> lista = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                lista.add(new Tarefa());
            }

            List<Future<String>> list = executor.invokeAll(lista);
            for (Future<String> future: list) {
                System.out.println(future.get());
            }

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
