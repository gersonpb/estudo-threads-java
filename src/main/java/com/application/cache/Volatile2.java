package com.application.cache;

public class Volatile2 {
    private static volatile int numero = 0;
    private static volatile boolean preparado = false;

    private static class MyRunnable implements Runnable{
        @Override
        public void run() {
            while (!preparado) {
                Thread.yield();
            }

            if (numero != 42){
                System.out.println(numero);
                //throw new IllegalStateException("Erro devido numeto ser <> de 42");
            }
//            System.out.println(numero);
        }
    }
    public static void main(String[] args) {
        while (true) {
            Thread t0 = new Thread(new MyRunnable());
            t0.start();
            Thread t1 = new Thread(new MyRunnable());
            t1.start();
            Thread t2 = new Thread(new MyRunnable());
            t2.start();

            numero = 42;
            preparado = true;

            while (t0.getState() != Thread.State.TERMINATED
            || t1.getState() != Thread.State.TERMINATED
            || t2.getState() != Thread.State.TERMINATED) {
                //wait
            }

            numero = 0;
            preparado = false;

        }


    }
}
