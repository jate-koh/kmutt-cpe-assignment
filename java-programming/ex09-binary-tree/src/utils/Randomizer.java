package utils;

import java.util.ArrayList;
import java.util.Random;

public abstract class Randomizer<T> {

    private int min, max = 0;
    protected ArrayList<T> bufferList;
    protected T[] bufferArray;
    protected T buffer;
    protected Random rand;

    public Randomizer(ArrayList<T> list) {
        this.bufferList = list;
        this.min = 0;
        this.max = 100;
        this.rand = new Random();
    }

    public Randomizer(T[] array) {

        this.bufferArray = array;
        this.min = 0;
        this.max = 100;
        this.rand = new Random();
    }

    public Randomizer(T buffer) {
        this.buffer = buffer;
        this.min = 0;
        this.max = 100;
        this.rand = new Random();
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public T getBuffer() {
        if (this.buffer != null) return this.buffer;
        return null;
    }

    public T[] getBufferArray() {
        if (this.bufferArray != null) return this.bufferArray;
        return null;
    }

    public ArrayList<T> getBufferList() {
        if (this.bufferList != null) return this.bufferList;
        return null;
    }

    public abstract T randomSingle();

    public abstract T[] randomArray(int n);

    public abstract ArrayList<T> randomList(int n);

}
