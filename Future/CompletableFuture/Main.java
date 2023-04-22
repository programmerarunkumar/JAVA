package Future.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class Main {

    public static void main(String[] args) throws Exception {
        create();
        transform();
        chaining();
        combine();
    }

    private static void create() throws Exception {
        supplyAsync();
        runAsync();
    }

    private static void transform() throws Exception {
        thenApply();
        applyToEither();
    }

    private static void chaining() throws Exception {
        thenCompose();
    }

    private static void combine() throws Exception {
        thenCombine();
    }

    //SupplyAsync -> to run the task asynchronously.
    private static void supplyAsync() throws Exception{

        int n = 10;
        CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for(int i=0; i<n; i++){
                sum = sum + i;
            }
            return sum;
        }).thenAccept(result -> {
            System.out.println("Result : " + result);
        });

    }

    //runAsync -> to run the task asynchronously without getting the result
    private static void runAsync() throws Exception {

        int n = 10;
        CompletableFuture<Void> addFuture = CompletableFuture.runAsync(() -> {
            int sum = 0;
            for(int i=0; i<n; i++){
                sum = sum + i;
            }
            System.out.println("Sum : " + sum);
        });

    }

    //thenApply -> transformation on the CompletableFuture result.
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

    //applyToEither -> transformation on the first completed CompletableFuture result.
    //TODO -  How to applyToEither() for more than two completableFuture ?
    private static void applyToEither() throws Exception {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "Service1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "Service2";
        });

        future1.applyToEither(future2, input -> {
            return "Response From " + input;
        }).thenAccept(result -> {
            System.out.println("Result : " + result);
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