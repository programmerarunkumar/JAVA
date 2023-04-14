package Generics.GenericMethod;

public class Main {

    private static <dataType> void printData(dataType[] array){
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args){

        Integer[] intArray = {1, 2, 3, 4, 5};
        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4, 5.5};
        String[] stringArray = {"apple", "papaya", "orange", "pineapple", "watermelon"};

        printData(intArray);
        printData(doubleArray);
        printData(stringArray);

    }

}
