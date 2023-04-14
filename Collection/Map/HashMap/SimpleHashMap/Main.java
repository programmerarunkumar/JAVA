package Collection.Map.HashMap.SimpleHashMap;

public class Main {

    public static void main(String[] args) throws Exception {

        CustomHashMap<String, Integer> map = new CustomHashMap<>();

        map.put("Arun", 24);
        map.put("Suriya", 18);
        map.put("Pushpa", 45);
        map.put("Murugesan", 55);

        System.out.println("Arun" + " : " + map.get("Arun"));
        System.out.println("Suriya" + " : " + map.get("Suriya"));
        System.out.println("Pushpa" + " : " + map.get("Pushpa"));
        System.out.println("Murugesan" + " : " + map.get("Murugesan"));

        map.remove("Arun");


        System.out.println("Arun" + " : " + map.get("Arun"));
        System.out.println("Suriya" + " : " + map.get("Suriya"));
        System.out.println("Pushpa" + " : " + map.get("Pushpa"));
        System.out.println("Murugesan" + " : " + map.get("Murugesan"));

    }

}
