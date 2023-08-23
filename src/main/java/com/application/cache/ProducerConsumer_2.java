package com.application.cache;

import com.application.cache.client.Janelas;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JAVA MULTITHREAD - producer - consumer
 * 2) Região crítica e Exclusão mútua.
 * @author Gerson
 */
public class ProducerConsumer_2 {

    public static final BlockingQueue<Integer> FILA = new LinkedBlockingQueue<>(5);

    private static volatile boolean produzed = true;
    private static volatile boolean consumed = true;
    public static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            while (true) {
                try {
                    simulationProcess();
                    if (produzed) {
                        LOCK.lock();
                        System.out.println("Produzindo");
                        int numero = new Random().nextInt(10000);
                        FILA.add(numero);
                        if (FILA.size() == 5){
                            System.out.println("Pausa no producer");
                            produzed = false;
                        }
                        if (FILA.size() == 1) {
                            System.out.println("Iniciando consumer");
                            consumed = true;
                        }
                        LOCK.unlock();
                    } else {
                        System.out.println("??? Produtor está dormindo!");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        Thread consumer = new Thread(() -> {
            while (true) {
                simulationProcess();
                if (consumed) {
                    LOCK.lock();
                    System.out.println("Consumindo");
                    Optional<Integer> numero = FILA.stream().findFirst();
                    numero.ifPresent(n -> {
                        FILA.remove(n);
                    });
                    if (FILA.size() == 0) {
                        consumed = false;
                    }
                    if (FILA.size() == 4) {
                        produzed = true;
                    }
                    LOCK.unlock();
                } else {
                    System.out.println("??? Consumidor está dormindo!");
                }
            }
        });

        Janelas.monitore(() -> String.valueOf(FILA.size()));
        producer.start();
        consumer.start();

    }

    public static final void simulationProcess() {
        int tempo = new Random().nextInt(10);
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
