package Future.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class Main {

    public static void main(String[] args) throws Exception {
        create();
        transform();
        chaining();
        combine();
        logging();
    }

    private static void create() throws Exception {
        supplyAsync();
        runAsync();
    }

    private static void transform() throws Exception {
        thenApply();
        applyToEither();
        handle();
    }

    private static void chaining() throws Exception {
        thenCompose();
    }

    private static void combine() throws Exception {
        thenCombine();
        allOf();
        anyOf();
    }

    private static void logging() throws Exception {
        thenAccept();
        whenComplete();
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

    //handle -> transformation on the CompletableFuture result. Also, it handles the error from the CompletableFuture
    private static void handle() throws Exception {

        int n = 10;
        CompletableFuture<Integer> add = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for(int i=0; i<n; i++){
                sum = sum + i;
            }
            return sum;
        });

        add.handle((result, error) -> {
            if(error != null){
                System.out.println("Exception : " + error.getMessage());
                return null;
            }else {
                return result/n;
            }
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
    //TODO - How to combine more than two CompletableFuture and perform operation on their results ?
    private static void thenCombine() throws Exception {

        int a = 10;
        int b = 20;
        CompletableFuture<Integer> addFuture = CompletableFuture.supplyAsync(() -> {
            return a + b;
        });

        CompletableFuture<Integer> multiplyFuture = CompletableFuture.supplyAsync(() -> {
          return a * b;
        });

        BiFunction<Integer, Integer, Integer> combineFunction = (sum, product) -> sum + product;

        CompletableFuture<Integer> combinedFuture = addFuture.thenCombine(multiplyFuture, combineFunction);
        combinedFuture.thenAccept(result -> {
            System.out.println("Add : " + addFuture.join());
            System.out.println("Multiply : " + multiplyFuture.join());
            System.out.println("Result : " + result);
        });

    }

    //allOf -> used to combine all the CompletableFuture to know its completion status
    private static void allOf() throws Exception {

        int a = 10;
        int b = 20;

        CompletableFuture<Integer> addFuture = CompletableFuture.supplyAsync(() -> {
            return a + b;
        });

        CompletableFuture<Integer> multiplyFuture = CompletableFuture.supplyAsync(() -> {
            return a * b;
        });

        CompletableFuture<Integer> divisionFuture = CompletableFuture.supplyAsync(() -> {
            return a / b;
        });

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(addFuture, multiplyFuture, divisionFuture);
        combinedFuture.thenAccept(v -> {
            System.out.println("Add : " + addFuture.join());
            System.out.println("Multiply : " + multiplyFuture.join());
            System.out.println("Division : " + divisionFuture.join());
            System.out.println("Result : "+ (addFuture.join() + multiplyFuture.join() + divisionFuture.join()));
        });

    }

    //anyOf -> used to combine multiple CompletableFutures and return the result of the first completed CompletableFuture.
    private static void anyOf() throws Exception {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "Service1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
           return "Service2";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            return "Service3";
        });

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future1, future2, future3);
        anyOf.thenApply(result -> {
           return "Response from " + result;
        }).thenAccept(result -> {
            System.out.println("Result : " + result);
        });

    }

    //thenAccept -> to handle the logging only when the CompletableFuture is completed successfully.
    private static void thenAccept() throws Exception {

        int n = 10;
        CompletableFuture<Integer> add = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i=0; i<n; i++){
                sum = sum + i;
            }
            return sum;
        });

        add.thenAccept(result -> {
            System.out.println("Result : " + result);
        });

    }

    //whenComplete -> to handle the logging in both success and failure cases when the  CompletableFuture is completed.
    private static void whenComplete() throws Exception {

        int n = 10;
        CompletableFuture<Integer> add = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for(int i=0; i<n; i++){
                sum = sum + i;
            }
            return sum;
        });

        add.whenComplete((result, exception) -> {
            if(exception != null){
                System.out.println("Exception : " + exception.getMessage());
            }else {
                System.out.println("Result : " + result);
            }
        });

    }

}