package gui.panels;

import javax.swing.*;
import java.awt.*;

public class TreePanel extends Panel {

    private JInternalFrame treeFrame;

    public TreePanel() {
        super(new BorderLayout(), Color.WHITE);
        this.setVisible(true);
    }

    protected void initComponents() {
        this.treeFrame = new JInternalFrame();
        this.treeFrame.setVisible(true);
        this.treeFrame.setResizable(true);
        this.treeFrame.setMaximizable(true);

        this.add(this.treeFrame, BorderLayout.CENTER);
    }
}
