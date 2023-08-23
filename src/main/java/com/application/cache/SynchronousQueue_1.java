package com.application.cache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueue_1 {
    public static final SynchronousQueue<String> FILA =
            new SynchronousQueue<>();

    public static void main(String[] args) {
        final ExecutorService executor = Executors.newCachedThreadPool();
        Runnable r1 = () -> {
            put();
            System.out.println("Fez a matrícula!");
        };

        Runnable r2 = () -> {
            String msg = take();
            System.out.println("Pegou dados da fila! " + msg);
        };

        executor.execute(r1);
        executor.execute(r2);
        executor.shutdown();
    }

    private static String take() {
        try {
            return FILA.take();
//            return FILA.poll(timeout, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return "Deu erro";
        }
    }

    private static void put() {
        try {
            FILA.put("Faça sua matrícula");
//            FILA.offer(e, timeout, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
