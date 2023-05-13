package Lamda.Math;

public class Main {

    public static void main(String[] args) throws Exception {

        int a =1;
        int b = 2;
        Add add = (x, y) -> {
            return x + y;
        };
        System.out.println(add.perform(a, b));

    }
    
}
