package com.application.cache;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t = Thread.currentThread();
        System.out.println(t.getName());

        Thread t1 = new Thread(new MyRunnable());


        Thread t2 = new Thread(() -> System.out.println(Thread.currentThread().getName()));
//        t2.start(); não faça duas vezes, vai lançar exceção

        Thread t3 = new Thread(new MyRunnable());

        t1.start();
        t2.start();
        t3.start();
    }
}