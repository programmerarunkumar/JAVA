package Collection.Map.HashMap.SimpleHashMap;

import java.util.LinkedList;
import java.util.Map;

public class CustomHashMap<keyType, valueType> {

    private static final int INITIAL_CAPACITY = 10;
    private static final float LOAD_FACTOR = 0.75f;
    private int size;

    private LinkedList<Pair>[] data;

    CustomHashMap(){
        data = new LinkedList[INITIAL_CAPACITY];
        for (int i=0; i<data.length; i++){
            data[i] = new LinkedList<Pair>();
        }
        System.out.println("INITIAL_CAPACITY : " + data.length);
    }

    private int getIndex(keyType key){
        int hashCode = key.hashCode();
        int index = hashCode % data.length;
        return Math.abs(index);
    }

    public void put(keyType key, valueType value){

        if(size >= (LOAD_FACTOR * data.length)){
            System.out.println("Going to resize the map. while inserting the key : " + key + " Size : " + size);
            resize();
        }

        int index = getIndex(key);
        LinkedList<Pair> bucket = data[index];

        for(Pair pair : bucket){
            if(pair.key.equals(key)){
                pair.value = value;
                return;
            }
        }

        bucket.add(new Pair(key, value));

        size++;

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

    public void resize(){

        int newCapacity = data.length * 2;
        System.out.println("NEW_CAPACITY : " + newCapacity);

        LinkedList<Pair>[] newData = new LinkedList[newCapacity];

        for(int i=0; i<newCapacity; i++){
            newData[i] = new LinkedList<Pair>();
        }

        for(LinkedList<Pair> bucket : data){
            for (Pair pair : bucket){
                int index = pair.key.hashCode() % newCapacity;
                index = Math.abs(index);
                newData[index].add(pair);
            }
        }

        data = newData;

    }

}
