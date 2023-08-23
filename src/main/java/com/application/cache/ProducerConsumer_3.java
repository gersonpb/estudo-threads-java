package com.application.cache;

import com.application.cache.client.Janelas;

import java.util.Random;
import java.util.concurrent.*;

/**
 * JAVA MULTITHREAD - producer - consumer
 * 2) Região crítica e Exclusão mútua.
 * @author Gerson
 */
public class ProducerConsumer_3 {
    public static final BlockingQueue<Integer> FILA =
            new LinkedBlockingQueue<>(5);

    public static void main(String[] args) {
        Runnable producer = () -> {
            while (true) {
                simulationProcessLento();
                System.out.println("Produzindo");
                int numero = new Random().nextInt(10000);
                try {
                    FILA.put(numero);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        };

        Runnable consumer = () -> {
            while (true) {
                simulationProcess();
                System.out.println("Consumindo");
                try {
                    FILA.take();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        };

        Janelas.monitore(() -> String.valueOf(FILA.size()));

        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleWithFixedDelay(producer, 0, 10, TimeUnit.MILLISECONDS);
        executor.scheduleWithFixedDelay(consumer, 0, 10, TimeUnit.MILLISECONDS);


    }

    public static final void simulationProcess() {
        int tempo = new Random().nextInt(400);
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static final void simulationProcessLento() {
        int tempo = new Random().nextInt(40);
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
