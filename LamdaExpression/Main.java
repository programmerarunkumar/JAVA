package LamdaExpression;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {

    public static void main(String[] agrs) throws Exception {

        singleStatement();
        multiStatement();

    }

    private static void singleStatement(){

        Function<Integer, Integer> square = (x) -> (x * x);

        int n = 3;
        int result = square.apply(n);
        System.out.println("Square of " + n + " is " + result);

    }

    private static void multiStatement(){

        BiFunction<Integer, Integer, Integer> func = (x, y) -> {
            int sum = x + y;
            int product = x * y;
            return sum * product;
        };

        int result = func.apply(1,2);
        System.out.println("Product : " + result);

    }

}