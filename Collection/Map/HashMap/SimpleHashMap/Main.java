package Collection.Map.HashMap.SimpleHashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);
        map.put("E", 5);
        map.put("F", 6);
        map.put("G", 7);
        map.put("H", 8);
        map.put("I", 9);
        map.put("J", 10);

        printMap(map);

        map.remove("A");

        printMap(map);

    }

    private static void printMap(CustomHashMap map){
        System.out.println("A" + " : " + map.get("A"));
        System.out.println("B" + " : " + map.get("B"));
        System.out.println("C" + " : " + map.get("C"));
        System.out.println("D" + " : " + map.get("D"));
        System.out.println("E" + " : " + map.get("E"));
        System.out.println("F" + " : " + map.get("F"));
        System.out.println("G" + " : " + map.get("G"));
        System.out.println("H" + " : " + map.get("H"));
        System.out.println("I" + " : " + map.get("I"));
        System.out.println("J" + " : " + map.get("J"));
    }

}
