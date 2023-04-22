package Future.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class Main {

    public static void main(String[] args) throws Exception {

        thenApply();
        thenCompose();
        thenCombine();

    }

    //SupplyAsync -> to run the task asynchronously.


    //thenApply -> transformation of the previous result.
    private static void thenApply() throws Exception {

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

    // thenCompose -> used  when we need to chain dependent asynchronous tasks.
    private static void thenCompose() throws Exception {

        int n = 10;
        CompletableFuture<Void> sumDivide = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i=0; i<n; i++){
                sum = sum + i;
            }
            return sum;
        }).thenCompose(sum -> CompletableFuture.supplyAsync(() -> {
            return sum/n;
        })).thenAccept(result -> {
            System.out.println("Result : " + result);
        });

    }

    //thenCombine -> used to combine the results of two independent CompletableFutures.
    private static void thenCombine() throws Exception {

        int a = 10;
        int b = 20;
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            return a + b;
        });

        CompletableFuture<Integer> productFuture = CompletableFuture.supplyAsync(() -> {
          return a * b;
        });

        BiFunction<Integer, Integer, Integer> combineFunction = (sum, product) -> sum + product;

        CompletableFuture<Integer> combinedFuture = sumFuture.thenCombine(productFuture, combineFunction);
        combinedFuture.thenAccept(result -> {
            System.out.println("Result : " + result);
        });

    }

}