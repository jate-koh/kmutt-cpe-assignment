package random;

import java.util.ArrayList;
import java.util.Random;
public class RandomNumber {

    private double min;
    private double max;
    private ArrayList<Double> buffer = new ArrayList<Double>();

    /**
     * Default constructor.
     * If no min and max value is provided, the default min and max value is 0 and 100 respectively.
     * Class attribute min and max value is not set.
     * */
    public RandomNumber() {
        this.getRandomNumber(0, 100);
    }

    /**
     * Constructor with min and max value as parameter.
     * If min and max value is provided, the min and max value is set to the provided min and max value.
     * @param max maximum value that random number can be.
     * @param min minimum value that random number can be.
     * */
    public RandomNumber(double min, double max) {
        this.min = min;
        this.max = max;
        this.getRandomNumber(min, max);
    }

    /**
     * Constructor with min and max value as parameter. With an additional parameter
     * to specify the number of random number to be generated. Generated Random number
     * will be stored in buffer.
     * @param max maximum value that random number can be.
     * @param min minimum value that random number can be.
     * @param numberOfRandomNumber number of random number to be generated.
     * */
    public RandomNumber(double min, double max, int numberOfRandomNumber) {
        this.min = min;
        this.max = max;
        this.getRandomNumbers(min, max, numberOfRandomNumber);
    }

    /**
     * Generate specified amount of random number and store it in buffer.
     * Use minimum and maximum value
     * from class attribute.
     * @param numberOfRandomNumber number of random number to be generated.
     * @return buffer return buffer attribute upon successful generation.
     */
    public ArrayList<Double> getRandomNumbers(int numberOfRandomNumber) {
        for (int i = 0; i < numberOfRandomNumber; i++) {
            this.buffer.add(this.getRandomNumber(this.min, this.max));
        }
        return this.buffer;
    }

    /**
     * Generate specified amount of random number and store it in buffer.
     * Use minimum and maximum value
     * from parameter.
     * @param min minimum value that random number can be.
     * @param max maximum value that random number can be.
     * @param numberOfRandomNumber number of random number to be generated.
     * @return buffer return buffer attribute upon successful generation.
     */
    public ArrayList<Double> getRandomNumbers(double min, double max, int numberOfRandomNumber) {
        for (int i = 0; i < numberOfRandomNumber; i++) {
            this.buffer.add(this.getRandomNumber(min, max));
        }
        return this.buffer;
    }

    /** Generate one random number between min and max value.
     * @param min minimum value that random number can be.
     * @param max maximum value that random number can be.
     * @return random number between min and max value.
     */
    public double getRandomNumber(double min, double max) {
        Random random = new Random();
        return random.nextDouble((max - min) + 1) + min;
    }

    /** Set min attribute.
     * @param min minimum value that random number can be.
     */
    public void setMin(double min) {
        this.min = min;
    }

    /** Set max attribute.
     * @param max maximum value that random number can be.
     */
    public void setMax(double max) {
        this.max = max;
    }

    /** Get buffer attribute.
     * @return buffer return buffer attribute.
     * Buffer will be empty if no random number is generated.
     */
    public ArrayList<Double> getBuffer() {
        return this.buffer;
    }

}
