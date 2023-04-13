package Generics.GenericsClass;

public class Main {

    public static void main(String[] args) throws Exception {

        Pair<Integer, String> idName = new Pair<Integer, String>(455, "ARUNKUMAR.MP");
        System.out.println("Key : " + idName.getKey() + " Value : " + idName.getValue());

        Pair<String, Integer> nameAge = new Pair<String, Integer>("ARUNKUMAR.MP", 24);
        System.out.println("Key : " + nameAge.getKey() + " Value : " + nameAge.getValue());

    }

}
