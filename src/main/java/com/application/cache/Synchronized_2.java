package com.application.cache;

public class Synchronized_2 {
    static int i = 0;
    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread t0 = new Thread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        Thread t4 = new Thread(runnable);

        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    //mais proximo do que será usado em produção
    public static class MyRunnable implements Runnable {
        @Override
        public synchronized void run() {
            int j;
            synchronized (this) {
                i++;
                j = i * 2;
            }

            double jElevadoA100 = Math.pow(j, 100);
            double sqrt = Math.sqrt(jElevadoA100);
            System.out.println(sqrt);
        }
    }
}
