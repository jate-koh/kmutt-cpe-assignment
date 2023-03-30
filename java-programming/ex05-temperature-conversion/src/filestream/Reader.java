package filestream;

import utils.*;
import java.io.*;
import java.util.ArrayList;
import java.lang.Double.*;

public class Reader {

    private BufferedReader bufferedReader;
    private String filePath = null;
    private final ArrayList<Double> buffer = new ArrayList<Double>();

    /**
     * Default constructor
     * If no file path is provided, the default file path is "input.txt"
     */
    public Reader() {
        try {
            this.filePath = Path.resolveAbsolutePath("input.txt");
            this.initializeBufferedReader();
        } catch (FileNotFoundException error) {
            error.printStackTrace();
            Logger.logMessage(this.getClass().getSimpleName(),"'input.txt' not found or not readable", true);
            Logger.logMessage(
                    this.getClass().getSimpleName(),
                    "Please provide a file path as parameter or create a file named 'input.txt'",
                    true
            );
        }
    }

    /**
     * Constructor with file path as parameter
     * If file path is provided, the file path is set to the provided file path
     * @param filePath the file path to set to.
     */
    public Reader(String filePath) {
        this.filePath = Path.resolveAbsolutePath(filePath);
        try {
            this.initializeBufferedReader();
        } catch (FileNotFoundException error) {
            error.printStackTrace();
            Logger.logMessage(this.getClass().getSimpleName(),"File not found or not readable", true);
        }
    }

    /**
     * Initialize bufferedReader with filePath from attribute
     * @return bufferedReader return attribute bufferedReader upon successful initialization.
     * @throws FileNotFoundException throw exception when file specified in
     * file path that set in class attribute is not found or not readable.
     */
    public BufferedReader initializeBufferedReader() throws FileNotFoundException {
        this.bufferedReader = new BufferedReader(new FileReader(this.filePath));
        return this.bufferedReader;
    }

    /**
     * Initialize bufferedReader with filePath from parameter.
     * @param filePath the file path to set to.
     * @return bufferedReader return attribute bufferedReader upon successful initialization.
     * @throws FileNotFoundException throw exception when file specified in
     * file path that set in class attribute is not found or not readable.
     */
    public BufferedReader initializeBufferedReader(String filePath) throws FileNotFoundException {
        this.bufferedReader = new BufferedReader(new FileReader(filePath));
        return this.bufferedReader;
    }

    /**
     * Read file and store each line in buffer
     * @return buffer return buffer attribute which is the output of this method
     * upon successful reading of file. But return null if reading failed.
     */
    public ArrayList<Double> readFile() {
        String line;
        // StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        try {
            while ((line = this.bufferedReader.readLine()) != null) {
                this.buffer.add(Double.parseDouble(line));
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error reading file!");
            return null;
        }
        return this.buffer;
    }

    /**
     * Close bufferedReader
     * @throws IOException throw exception when bufferedReader is not initialized or
     * when bufferedReader is already closed.
     */
    public void closeBufferedReader() throws IOException {
        this.bufferedReader.close();
    }

    /**
     * Get bufferedReader attribute. bufferedReader is initialized in initializeBufferedReader() method.
     * @return bufferedReader
     */
    public BufferedReader getBufferedReader() {
        return this.bufferedReader;
    }

    /**
     * Get buffer attribute. This is the output of readFile() method.
     * @return buffer output of readFile() method stored in buffer attribute.
     */
    public ArrayList<Double> getBuffer() {
        return this.buffer;
    }

    /**
     * Manually set filePath attribute.
     * @param filePath the file path to set to.
     */
    public void setFilePath(String filePath) {
        this.filePath = Path.resolveAbsolutePath(filePath);
    }

}
