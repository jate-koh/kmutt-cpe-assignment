package gui;

import gui.panels.*;
import gui.panels.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener {

    private FormPanel formPanel;
    private TreePanel treePanel;
    private ButtonPanel buttonPanel;

    /** This constructor is used to create a JFrame with a title, width and height.
     * If params are not provided, the default values are used.
     * The title is set to "null".
     * The width is set to 800.
     * The height is set to 600.
     */
    public MainFrame() {
        this(null, 800, 600);
    }

    /** This constructor is used to create a JFrame with a title, width and height.
     * If only title is provided, the default values are set for width and height.
     * The width is set to 800.
     * The height is set to 600.
     * @param title The title of the JFrame.
     */
    public MainFrame(String title) {
        this(title, 800, 600);
    }

    /** This constructor is used to create a JFrame with a title, width and height.
     * @param title The title of the JFrame.
     * @param width The width of the JFrame.
     * @param height The height of the JFrame.
     */
    public MainFrame(String title, int width, int height) {

        // Set the JFrame properties
        super();
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        // Initialize the components
        this.init();
    }

    private void init() {
        this.drawFormPanel();
        this.drawTreePanel();
        this.drawButtonPanel();
    }

    private void drawFormPanel() {
        this.formPanel = new FormPanel(2,2);
        this.add(this.formPanel, BorderLayout.NORTH);
    }

    private void drawTreePanel() {
        this.treePanel = new TreePanel();
        this.add(this.treePanel, BorderLayout.CENTER);
    }

    private void drawButtonPanel() {
        this.buttonPanel = new ButtonPanel(2,2);
        this.add(this.buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
