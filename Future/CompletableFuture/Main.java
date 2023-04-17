package Future.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) throws Exception {

        CompletableFuture<String> response = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Result";
        });

        response.thenAccept(s -> System.out.println(s));

        CompletableFuture<Integer> responseLen = response.thenApply(s -> s.length());

        responseLen.thenAccept(s -> System.out.println(s));

        System.out.println("Response : " + response.get() + " Length : " + responseLen.get());

    }

}
