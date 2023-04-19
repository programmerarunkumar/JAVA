package Future.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) throws Exception {

        thenAppyly();

    }

    //Both SupplyAsync and thenApply will execute in Asynchronously
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

        CompletableFuture<Integer> div = add.thenApply(sum -> sum/n);
        div.thenAccept(result -> {
            System.out.println("Divide : " + result);
        });

        CompletableFuture<Integer> mod = add.thenApply(sum -> sum%n);
        mod.thenAccept(result -> {
            System.out.println("Mod " + result);
        });

    }

}
