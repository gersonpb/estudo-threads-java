package com.application.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollections {
    private static List<String> lista = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        //resolve o problema de não preecher os dados corretamento na chamada do run
        lista = Collections.synchronizedList(lista);
        // utilize de acordo com o tipo de lista
//        lista = Collections.synchronizedCollection(lista);
//        lista = Collections.synchronizedSet(lista);
//        lista = Collections.synchronizedMap(lista);


        MyRunnable runnable = new MyRunnable();
        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        t0.start();
        t1.start();
        t2.start();
        Thread.sleep(1);
        System.out.println(lista);

    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            lista.add("Inclusão dados na lista");
            String name = Thread.currentThread().getName();
            System.out.println(name + " incseriu na lista!");
        }
    }
}
