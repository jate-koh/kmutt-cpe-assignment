package utils;

import java.util.ArrayList;

public class IntegerRandomizer extends Randomizer<Integer> {

    public IntegerRandomizer(ArrayList<Integer> list) {
        super(list);
    }

    public IntegerRandomizer(Integer[] array) {
        super(array);
    }

    public IntegerRandomizer(Integer buffer) {
        super(buffer);
    }

    @Override
    public Integer randomSingle() {
        this.buffer = this.rand.nextInt();
        return this.buffer;
    }

    @Override
    public Integer[] randomArray(int n) {
        this.bufferArray = new Integer[n];
        for (int i = 0; i < n; i++) {
            this.bufferArray[i] = this.rand.nextInt();
        }
        return this.bufferArray;
    }

    @Override
    public ArrayList<Integer> randomList(int n) {
        this.bufferList = new ArrayList<Integer>(n);
        for (int i = 0; i < n; i++) {
            this.bufferList.add(this.rand.nextInt());
        }
        return this.bufferList;
    }
}
