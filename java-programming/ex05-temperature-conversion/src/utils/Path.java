package utils;

import java.io.File;
public class Path {

    /**
     * Resolve relative path into absolute path of file.
     * @param filePath relative path of file.
     * @return absolute path of file in String.
     */
    public static String resolveAbsolutePath(String filePath) {
        return new File(filePath).getAbsolutePath();
    }
}
