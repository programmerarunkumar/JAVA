package Future.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) throws Exception {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Result";
        });

        future1.thenAccept(input -> {
            System.out.println("future1 : " + input);
        });

        CompletableFuture<Integer> future2 = future1.thenApply(input -> input.length() * 2);

        future2.thenAccept(input -> {
            System.out.println("future2 : " + input);
        });

        System.out.println("future1 : " + future1.get() + " future2 : " + future2.get());

    }

}
