package Generics.GenericInterface;

public interface CustomList<valueType> {

    public void add(valueType value);

    public valueType get(int index);

    public int size();

}
