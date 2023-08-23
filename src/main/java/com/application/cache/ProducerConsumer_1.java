package com.application.cache;

import com.application.cache.client.Janelas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ProducerConsumer_1 {

    public static final List<Integer> LISTA = new ArrayList<>(5);

    private static boolean produzed = true;
    private static boolean consumed = true;

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            while (true) {
                try {
                    simulationProcess();
                    if (produzed) {
                        System.out.println("Produzindo");
                        int numero = new Random().nextInt(10000);
                        LISTA.add(numero);
                        if (LISTA.size() == 5){
                            System.out.println("Pausa no producer");
                            produzed = false;
                        }
                        if (LISTA.size() == 1) {
                            System.out.println("Iniciando consumer");
                            consumed = true;
                        }
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
                    System.out.println("Consumindo");
                    Optional<Integer> numero = LISTA.stream().findFirst();
                    numero.ifPresent(n -> {
                        LISTA.remove(n);
                    });
                    if (LISTA.size() == 0) {
                        consumed = false;
                    }
                    if (LISTA.size() == 4) {
                        produzed = true;
                    }
                } else {
                    System.out.println("??? Consumidor está dormindo!");
                }
            }
        });

        Janelas.monitore(() -> String.valueOf(LISTA.size()));
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
