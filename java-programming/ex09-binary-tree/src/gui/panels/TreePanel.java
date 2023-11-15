package gui.panels;

import data.structure.tree.IntegerTree;
import utils.TreePrinter;

import javax.swing.*;
import java.awt.*;

public class TreePanel extends Panel {

    private IntegerTree tree;
    private JInternalFrame treeFrame;
    private JTextArea treeArea;

    public TreePanel() {
        super(new BorderLayout(), Color.WHITE);
        this.setVisible(true);
    }

    protected void initComponents() {
        this.treeFrame = new JInternalFrame();
        this.treeFrame.setVisible(true);
        this.treeFrame.setResizable(true);
        this.treeFrame.setMaximizable(true);

        this.treeArea = new JTextArea();
        this.treeArea.setEditable(false);
        this.treeArea.setVisible(true);

        this.treeFrame.add(this.treeArea);

        this.add(this.treeFrame, BorderLayout.CENTER);
    }

    public void setTree(IntegerTree tree) {
        this.tree = tree;
    }

    public IntegerTree getTree() {
        return this.tree;
    }

    public void clearTree() {
        this.treeArea.setText("");
    }

    public void drawTree() {
        this.clearTree();
        this.tree.printLog();
        this.treeArea.setText(TreePrinter.treeString.toString());
    }

    public void append(String output) {
        this.treeArea.append(output);
    }

    public void drawTree(String tree) {
        this.treeArea.setText(tree);
    }

}
