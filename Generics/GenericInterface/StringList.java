package Generics.GenericInterface;

import java.util.ArrayList;
import java.util.List;

public class StringList implements CustomList<String> {

    private List<String> items = new ArrayList<String>();

    public void add(String value) {
        items.add(value);
    }

    public String get(int index) {
        return items.get(index);
    }

    public int size() {
        return items.size();
    }

}
