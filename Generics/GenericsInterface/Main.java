package Generics.GenericsInterface;

public class Main {

    public static void main(String[] args) throws Exception {

        CustomList<String> items = new StringList();

        items.add("1");
        items.add("2");
        items.add("3");
        items.add("4");
        items.add("5");

        System.out.println("List Size : " + items.size());

        for (int i=0;i<items.size();i++){
            System.out.println("Value : " + items.get(i));
        }

    }

}
