package filestream;

import utils.*;
import java.io.*;
import java.util.ArrayList;

public class Writer {

    private PrintWriter printWriter;
    private FileWriter fileWriter;
    private String filePath = null;

    /**
     * Default constructor
     * If no file path is provided, the default file path is "output.txt"
     */
    public Writer() {
        try {
            this.filePath = Path.resolveAbsolutePath("output.txt");
            this.initializePrintWriter(this.filePath);
        } catch (IOException error) {
            error.printStackTrace();
            System.out.println(this.getClass().getSimpleName()+" File not found or not writable!");
        }
    }

    /**
     * Constructor with file path as parameter
     * If file path is provided, the file path is set to the provided file path
     * @param filePath the file path to set to.
     */
    public Writer(String filePath) {
        this.filePath = Path.resolveAbsolutePath(filePath);
        try {
            this.initializeFileWriter(this.filePath);
            this.initializePrintWriter();
        } catch (IOException error) {
            error.printStackTrace();
            System.out.println(this.getClass().getSimpleName()+" File not found or not writable!");
        }
    }

    /**
     * Initialize fileWriter with filePath from parameter.
     * @param filePath the file path to set to.
     * @throws IOException throw exception when file specified in file path in parameters
     * is not found or not readable.
     * @return fileWriter return attribute fileWriter upon successful initialization.
     */
    public FileWriter initializeFileWriter(String filePath) throws IOException {
        try {
            this.fileWriter = new FileWriter(filePath);
        } catch (IOException e) {
            this.fileWriter = null;
            throw new IOException("Error writing to file!");
        }
        return this.fileWriter;
    }

    /**
     * Initialize printWriter with filePath from attribute
     * @return printWriter return attribute printWriter upon successful initialization.
     * @throws IOException throw exception when file in file path from class attributes
     * is not found or not readable.
     */
    public PrintWriter initializePrintWriter() throws IOException, NullPointerException {
        if(this.filePath == null) throw new NullPointerException("File path is not set!");

        // If fileWriter is not initialized,
        // attempt to initialize it with filePath from attribute
        if(this.fileWriter == null) this.initializeFileWriter(this.filePath);

        // Initialize printWriter with fileWriter
        this.printWriter = new PrintWriter(this.fileWriter, true);

        return this.printWriter;
    }

    /**
     * Initialize printWriter with filePath from manually provided parameter
     * @param filePath the file path to set to.
     * @return printWriter return attribute printWriter upon successful initialization.
     * @throws IOException throw exception when file specified in file path in parameters
     * is not found or not readable.
     */
    public PrintWriter initializePrintWriter(String filePath) throws IOException {
        this.printWriter = new PrintWriter(new FileWriter(filePath), true);
        return this.printWriter;
    }

    /**
     * Write to file specified in filePath class attribute
     * @param data one piece of string to write to file
     */
    public void writeToFile(double data) {
        this.printWriter.write(Double.toString(data));
    }

    /**
     * Write to file specified in filePath class attribute
     * @param data the data array to write to file
     */
    public void writeToFile(ArrayList<Double> data) throws IOException {
        this.openStream();
        for( Double value : data) {
            this.printWriter.write(Double.toString(value));
            this.printWriter.write("\n");
        }
        this.closeStream();
    }

    /**
     * Clear a file specified in filePath class attribute by writing empty string to it.
     * @throws IOException throw exception when file specified in file path in parameters
     * is not found or not readable or has trouble writing to file.
     */
    public void clearFile() throws IOException {
        this.openStream();
        this.printWriter.write("");
        this.closeStream();
    }

    /**
     * Open the printWriter stream by initializing printWriter with filePath from attribute
     * @throws IOException throw exception when file specified in file path in parameters
     * is not found or not readable or has trouble writing to file.
     */
    private void openStream() throws IOException {
        this.initializePrintWriter(this.filePath);
    }

    /**
     * Close the printWriter stream by using method close() from printWriter class
     */
    private void closeStream() {
        this.printWriter.close();
    }

    /**
     * Set file path to write to
     * @param filePath the file path to set to.
     */
    public void setFilePath(String filePath) {
        this.filePath = Path.resolveAbsolutePath(filePath);
    }

}
