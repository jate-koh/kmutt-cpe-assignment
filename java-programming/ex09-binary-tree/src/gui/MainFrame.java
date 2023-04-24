package gui;

import gui.panels.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private TreePanel treePanel;
    private ButtonPanel buttonPanel;

    public MainFrame() {
        this("Binary Tree", 800, 600);
    }

    public MainFrame(String title, int width, int height) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        this.init();
    }

    public void init() {
        this.initTextPanel();
        this.initTreePanel();
        this.initButtonPanel();
    }

    private void initTextPanel() {
        this.textPanel = new TextPanel();
        this.add(this.textPanel, BorderLayout.NORTH);
    }

    private void initTreePanel() {
        this.treePanel = new TreePanel();
        this.textPanel.add(this.treePanel, BorderLayout.CENTER);
    }

    private void initButtonPanel() {
        this.buttonPanel = new ButtonPanel();
        this.textPanel.add(this.buttonPanel, BorderLayout.SOUTH);
    }
}
