package Generics.GenericClass;

public class Pair<keyType, valueType> {

    private keyType key;
    private valueType value;

    Pair(keyType key, valueType value){
        this.key = key;
        this.value = value;
    }

    public keyType getKey(){
        return this.key;
    }

    public valueType getValue(){
        return this.value;
    }

}
