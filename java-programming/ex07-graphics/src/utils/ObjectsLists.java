package utils;

import java.util.ArrayList;

public class ObjectsLists<T> {

    private ArrayList<T> objects;

    public ObjectsLists() {
        this.objects = new ArrayList<T>();
    }

    public void add(T object) {
        this.objects.add(object);
    }

    public void remove(T object) {
        this.objects.remove(object);
    }

}
