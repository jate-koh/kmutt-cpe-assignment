import data.structure.tree.*;
import utils.Logger;

import java.util.Random;

public class Main {

    // Constants
    private static final int LOOP_LIMIT = 20;
    private static final int MAX_RANDOM = 20;
    private static final int MIN_RANDOM = 0;


    public static void main(String[] args) {

        // Create Binary Tree with 10 nodes
        IntegerTree intTree = new IntegerTree(10);
        intTree.setLimit(LOOP_LIMIT);

        // Insert some numbers into the tree
        intTree.insert(9);
        intTree.insert(8);
        intTree.insert(5);
        intTree.insert(15);
        intTree.insert(3);
        intTree.insert(7);

        intTree.printLog();

        try {
            intTree.searchPath(5);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        intTree.remove(5);
//        intTree.printLog();
//        intTree.remove(15);
//        intTree.printLog();

//        // Insert random numbers into the tree
//        Random random = new Random();
//        int buffer = 0;
//        for (int i = 0; i < 6; i++) {
//            buffer = random.nextInt(MAX_RANDOM - MIN_RANDOM) + MIN_RANDOM;
//            Logger.logMessage("Inserting " + buffer + " into tree", intTree);
//            intTree.insert(buffer);
//            intTree.printLog();
//        }
//        intTree.printLog();
//
//        // Remove random numbers from the tree
//        for (int i = 0; i < 6; i++) {
//            buffer = random.nextInt(MAX_RANDOM - MIN_RANDOM) + MIN_RANDOM;
//            Logger.logMessage("Removing " + buffer + " from tree", intTree);
//            intTree.remove(buffer);
//            intTree.printLog();
//        }
//        intTree.printLog();

    }
}