package converter;

import java.util.ArrayList;

public class TemperatureConverter {

    private double celsius;
    private double fahrenheit;
    private ArrayList<Double> fahrenheitArray = new ArrayList<Double>();
    private ArrayList<Double> celsiusArray = new ArrayList<Double>();

    /**
     * Default constructor.
     * Initializes the all class attributes to 0.
     */
    public TemperatureConverter() {
        this.celsius = 0;
        this.fahrenheit = 0;
    }

    /**
     * C for Celsius, F for Fahrenheit.
     * If the unit is F, the value is converted to Celsius.
     * If the unit is C, the value is converted to Fahrenheit.
     * If the unit is invalid, the unit will be defaulted to C.
     * @param value the value to be converted.
     * @param unit the unit of the value to be converted.
     */
    public TemperatureConverter(double value, String unit) {
        if (unit.equals("C")) {
            this.celsius = value;
            this.fahrenheit = this.celsiusToFahrenheit();
        } else if (unit.equals("F")) {
            this.fahrenheit = value;
            this.celsius = this.farenheitToCelsius();
        } else {
            this.celsius = value;
            this.fahrenheit = this.celsiusToFahrenheit();
        }
    }

    /**
     * C for Celsius, F for Fahrenheit.
     * If the unit is F, the value is converted to Celsius.
     * If the unit is C, the value is converted to Fahrenheit.
     * If the unit is invalid, the unit will be defaulted to C.
     * @param array the array of values to be converted.
     * @param unit the unit of the value to be converted.
     */
    public TemperatureConverter(ArrayList<Double> array, String unit) {
        if (unit.equals("C")) {
            this.celsiusArray = array;
            this.fahrenheitArray = this.convertTemperatureArray(array, "C");
        } else if (unit.equals("F")) {
            this.fahrenheitArray = array;
            this.celsiusArray = this.convertTemperatureArray(array, "F");
        } else {
            this.celsiusArray = array;
            this.fahrenheitArray = this.convertTemperatureArray(array, "C");
        }
    }

    /**
     * Get the value of Celsius from class attribute.
     * If the unit of the array is assumed to be Celsius, the array will be converted to Fahrenheit.
     * If the unit of the array is assumed to be Fahrenheit, the array will be converted to Celsius.
     * @param array the array of values to be converted.
     * @param unit the unit of the value to be converted.
     * @return the value of Celsius.
     */
    public ArrayList<Double> convertTemperatureArray(ArrayList<Double> array, String unit) {
        ArrayList<Double> convertedArray = new ArrayList<Double>();
        if (unit.equals("C")) {
            for (Double itr : array) {
                convertedArray.add(this.celsiusToFahrenheit(itr));
            }
        } else if (unit.equals("F")) {
            for (Double itr : array) {
                convertedArray.add(this.farenheitToCelsius(itr));
            }
        }
        return convertedArray;
    }

    /**
     * Get the value of Celsius from class attribute.
     * @return the value of Celsius.
     */
    private double celsiusToFahrenheit() {
        return (this.celsius * 9/5) + 32;
    }

    /**
     * Get the value of Fahrenheit from class attribute.
     * @return the value of Fahrenheit.
     */
    private double farenheitToCelsius() {
        return (this.fahrenheit - 32) * 5/9;
    }

    /**
     * Function to convert a value from Celsius to Fahrenheit.
     * @param celsius the Celsius value to be converted.
     * @return the value of the Celsius parameter when converted to Fahrenheit.
     */
    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    /**
     * Function to convert a value from Fahrenheit to Celsius.
     * @param fahrenheit the Farenheit value to be converted.
     * @return the value of the Fahrenheit parameter when converted to Celsius.
     */
    public static double farenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }

    /**
     * Get the value of Celsius from class attribute.
     * @return the value of Celsius.
     */
    public double getCelsius() {
        return this.celsius;
    }

    /**
     * Get the value of Fahrenheit from class attribute.
     * @return the value of Fahrenheit.
     */
    public double getFahrenheit() {
        return this.fahrenheit;
    }

    /**
     * Get the value of Celsius from class attribute.
     * @return the value of Celsius.
     */
    public ArrayList<Double> getCelsiusArray() {
        return this.celsiusArray;
    }

    /**
     * Get the value of Fahrenheit from class attribute.
     * @return the value of Fahrenheit.
     */
    public ArrayList<Double> getFahrenheitArray() {
        return this.fahrenheitArray;
    }

    /**
     * Find average Celsius from celsiusArray.
     * @return average value of Celsius calculated from all Celsius in celsiusArray.
     */
    public double getAverageCelsius() {
        double sum = 0;
        for (Double itr : this.celsiusArray) {
            sum += itr;
        }
        return sum / this.celsiusArray.size();
    }

    /**
     * Find average Fahrenheit from fahrenheitArray attribute.
     * @return average value of Fahrenheit calculated from all Fahrenheit in fahrenheitArray.
     */
    public double getAverageFahrenheit() {
        double sum = 0;
        for (Double itr : this.fahrenheitArray) {
            sum += itr;
        }
        return sum / this.fahrenheitArray.size();
    }

}
