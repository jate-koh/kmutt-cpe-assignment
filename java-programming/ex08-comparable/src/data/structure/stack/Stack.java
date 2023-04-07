package data.structure.stack;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Stack<T> {

    protected ArrayList<Object> items;
    private int top;
    private int limit;

    public Stack() {
        this(10);
    }

    public Stack(int size) {
        this.items = new ArrayList<>(size);
        this.limit = size;
        this.top = 0;
    }

    public void push(T item) {
        if(top <= limit) {
            this.items.add(item);
            this.top++;
        }
    }

    public T pop() {
        if(top >= 0) {
            if(top > 0) this.top--;
            return (T)this.items.remove(this.top);
        }
        return null;
    }

    public T get(int index) {
        return (T)this.items.get(index);
    }

    public int getSize() {
        return this.items.size();
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public boolean isFull() {
        return this.items.size() == this.limit;
    }

    public abstract void printLog();

}
