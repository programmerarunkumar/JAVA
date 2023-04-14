package Collection.Map.HashMap.SimpleHashMap;

import java.util.LinkedList;

public class CustomHashMap<keyType, valueType> {

    private static final int INITIAL_CAPACITY = 20;

    private LinkedList<Pair>[] data;

    CustomHashMap(){
        data = new LinkedList[INITIAL_CAPACITY];
        for (int i=0; i<data.length; i++){
            data[i] = new LinkedList<Pair>();
        }
    }

    private int getIndex(keyType key){
        int hashCode = key.hashCode();
        int index = hashCode % data.length;
        return Math.abs(index);
    }

    public void put(keyType key, valueType value){

        int index = getIndex(key);
        LinkedList<Pair> bucket = data[index];

        for(Pair pair : bucket){
            if(pair.key.equals(key)){
                pair.value = value;
                return;
            }
        }

        bucket.add(new Pair(key, value));

    }

    public valueType get(keyType key){

        int index = getIndex(key);
        LinkedList<Pair> bucket = data[index];

        for(Pair pair : bucket){
            if(pair.key.equals(key)){
                return (valueType) pair.value;
            }
        }

        return null;

    }

    public void remove(keyType key){

        int index = getIndex(key);
        LinkedList<Pair> bucket = data[index];

        for (Pair pair : bucket){
            if(pair.key.equals(key)){
                bucket.remove(pair);
                return;
            }
        }

    }

}
