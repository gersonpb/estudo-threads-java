package com.application.cache;

import java.util.concurrent.CompletableFuture;

/**
 * JAVA MULTITHREAD - CompletableFuture (simples)
 */
public class CompletableFuture_1 {
    public static void main(String[] args) {
        CompletableFuture<String> processe = processe();
        CompletableFuture<String> thenApply =
                processe.thenApply(s -> s + " e Curta nossa pagina!");
        CompletableFuture<Void> thenAccept =
                thenApply.thenAccept(s -> System.out.println(s));

        System.out.println("Apoie o curso e curta nossa página");


        sleep();
        sleep();

    }

    public static CompletableFuture<String> processe () {
        return CompletableFuture.supplyAsync(() -> {
            sleep();
            return "Inscreva-se no curso";
        });
    }

    private static void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
