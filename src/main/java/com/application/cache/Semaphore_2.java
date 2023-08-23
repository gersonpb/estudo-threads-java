package com.application.cache;

import com.application.cache.client.Janelas;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Semaphore_2 {

    public static final Semaphore SEMAPHORE = new Semaphore(100);
    public static final AtomicInteger QTD = new AtomicInteger(0);


    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(501);

        Runnable r1 = () -> {
            String name = Thread.currentThread().getName();
            int usuario = new Random().nextInt(10000);

            boolean tryAcquire = false;
            QTD.incrementAndGet();
            while (!tryAcquire) {
                tryAcquire = tryAcquire();
            }
            QTD.decrementAndGet();

            System.out.println("Usuário " + usuario
                + " se inscreveu na vaga usando a thread " + name +"\n");
            sleep();
            SEMAPHORE.release();
        };


        Janelas.Mensagem janela = Janelas.criaJanela("QDTY");

        Runnable r2 = () -> {
            int qtd = QTD.get();
            janela.setText(qtd + " usu&#225;rios esperando para se candidatarem a vaga!");
        };

        for (int i = 0; i < 500; i++) {
            executor.execute(r1);
        }
        executor.scheduleWithFixedDelay(r2, 0, 100, TimeUnit.MILLISECONDS);

    }

    private static void sleep() {
        // espera de 1 a 6 segundos
        try {
            int tempoEspera = new Random().nextInt(6);
            tempoEspera++;
            Thread.sleep(1000 * tempoEspera);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private static boolean tryAcquire() {
        try {
           return SEMAPHORE.tryAcquire(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return false;
        }

    }
}
