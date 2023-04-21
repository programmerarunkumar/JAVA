package Future.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) throws Exception {

        thenAppyly();
        thenCompose();

    }

    //SupplyAsync -> to run the task asynchronously.
    //thenApply -> transformation of the previous result.

    private static void thenAppyly() throws Exception {

        int n = 10;
        CompletableFuture<Integer> add = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i=0; i<n; i++){
               sum = sum + i;
            }
            return sum;
        });
        add.thenAccept(sum -> {
            System.out.println("Sum : " + sum);
        });

        CompletableFuture<Integer> divide = add.thenApply(sum -> sum/n);
        divide.thenAccept(result -> {
            System.out.println("Divide : " + result);
        });

        CompletableFuture<Integer> mod = add.thenApply(sum -> sum%n);
        mod.thenAccept(result -> {
            System.out.println("Mod " + result);
        });

    }

    // thenCompose -> used  when we need to chain dependent asynchronous tasks

    private static void thenCompose() throws Exception {

        int n = 10;
        CompletableFuture<Integer> sumDivide = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i=0; i<n; i++){
                sum = sum + i;
            }
            return sum;
        }).thenCompose(sum -> CompletableFuture.supplyAsync(() -> {
            return sum/n;
        }));

        sumDivide.thenAccept(result -> {
            System.out.println("Result : " + result);
        });

    }

}