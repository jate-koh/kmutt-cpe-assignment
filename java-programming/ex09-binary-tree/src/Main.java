import com.sun.source.tree.Tree;
import data.structure.tree.*;
import gui.MainFrame;
import utils.Logger;
import utils.TreePrinter;

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

        // Print tree + Create and saved it in tree string in TreePrinter.treeString
        intTree.printLog();

        // Initialize GUI
        MainFrame mainFrame = new MainFrame("Binary Tree", 400, 800);

        // Set tree in GUI to be the one we created
        mainFrame.getTreePanel().setTree(intTree);

        // Draw tree using BST saved in TreePrinter.treeString
        mainFrame.getTreePanel().drawTree();

    }
}