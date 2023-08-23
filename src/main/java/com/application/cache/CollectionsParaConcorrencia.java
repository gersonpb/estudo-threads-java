package com.application.cache;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CollectionsParaConcorrencia {

    private static BlockingQueue<String> fila = new LinkedBlockingQueue<>();
//    private static Map<Integer, String> mapa = new ConcurrentHashMap<>();

    //private static List<String> lista = new CopyOnWriteArrayList<>();
        //CopyOnWriteArrayList é uma classe extremamente pesada
        // considere usar ela onde tem pouca add na lista ou quantidade grade de somente leitura
        //se for muita alteração e add não recomenda utilizar

    public static void main(String[] args) throws InterruptedException {
        //Coleções que são Thread-safe
        MyRunnable runnable = new MyRunnable();
        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        t0.start();
        t1.start();
        t2.start();
        Thread.sleep(500);
        System.out.println(fila);

    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
//            mapa.put(new Random().nextInt(), "gerando numero");
            fila.add("Se tiver tempo venha");
            fila.poll();
            String name = Thread.currentThread().getName();
            System.out.println(name + " inseriu no map!");
        }
    }
}
