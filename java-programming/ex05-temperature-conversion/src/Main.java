// Import Libraries
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Import Packages
import converter.TemperatureConverter;
import filestream.Reader;
import filestream.Writer;
import random.RandomNumber;
import utils.Logger;

public class Main {

    // This is the main method
    public static void main(String[] args) {

        // Declare variables
        ArrayList<Double> randomBuffer;
        int X = 0;

        // Declare ArrayList object

        // Create a new Reader and Writer object
        Reader farenheitReader = new Reader("f.txt");
        Reader celsiusReader = new Reader("c.txt");
        Writer farenheitWriter = new Writer("f.txt");
        Writer celsiusWriter = new Writer("c.txt");

        // Create Scanner object
        Scanner scanner = new Scanner(System.in);

        Logger.logMessage("Main", "Enter number of random numbers to generate: ", false);
        boolean success = false;

        // Scan Input
        do {
            try {
                X = scanner.nextInt();
                success = true;
            } catch (InputMismatchException error) {
                scanner.nextLine();
                Logger.logMessage("Main", "Invalid input. Please enter a number.", true);
                Logger.logMessage("Main", "Re-enter number of random numbers to generate: ", false);
            }
        } while(!success);

        // Create a Random Number Object
        RandomNumber randomNumber = new RandomNumber(32, 212, X);

        // Get random numbers
        randomBuffer = randomNumber.getBuffer();
        Logger.logArray("Random Buffer", randomBuffer,6);

        // Clear text file and
        try {
            celsiusWriter.clearFile();
            farenheitWriter.clearFile();
        } catch (Exception error) {
            error.printStackTrace();
            Logger.logMessage("Main","Error in initializing text files", true);
        }

        // Write random numbers to Fahrenheit text file
        try {
            farenheitWriter.writeToFile(randomBuffer);
        } catch (Exception error) {
            error.printStackTrace();
            Logger.logMessage("Main","Error in writing to Fahrenheit text file", true);
        }

        // Read Fahrenheit text file and store in ArrayList
        ArrayList<Double> fahrenheitList = farenheitReader.readFile();
        Logger.logArray("Fahrenheit Buffer", fahrenheitList, 6);

        // Create Temperature Converter Object
        // and convert Fahrenheit List to Celsius List
        TemperatureConverter temperatureConverter = new TemperatureConverter(fahrenheitList, "F");
        ArrayList<Double> celsiusList = temperatureConverter.getCelsiusArray();
        Logger.logArray("Celsius Buffer", celsiusList, 6);

        //Write Celsius List to Celsius text file
        try {
            celsiusWriter.writeToFile(celsiusList);
        } catch (Exception error) {
            error.printStackTrace();
            Logger.logMessage("Main","Error in writing to Celsius text file", true);
        }

        // Find the average of the Celsius List and Fahrenheit List
        double celsiusAverage = temperatureConverter.getAverageFahrenheit();
        double fahrenheitAverage = temperatureConverter.getAverageCelsius();
        Logger.logMessage("Main", "Average of Celsius List: " + celsiusAverage, true);
        Logger.logMessage("Main", "Average of Fahrenheit List: " + fahrenheitAverage, true);

        // Close Scanner
        scanner.close();

    }
}