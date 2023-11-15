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
        this.addActionListeners();
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

    private void addActionListeners() {
        this.getButtonPanel().getInsertButton().addActionListener(this);
        this.getButtonPanel().getDeleteButton().addActionListener(this);
        this.getButtonPanel().getSearchButton().addActionListener(this);
        this.getButtonPanel().getClearButton().addActionListener(this);
    }

    public TreePanel getTreePanel() {
        return this.treePanel;
    }

    public FormPanel getFormPanel() {
        return this.formPanel;
    }

    public ButtonPanel getButtonPanel() {
        return this.buttonPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.formPanel.getInputField() == null) {
            this.formPanel.getOutputArea().setText("Please enter a value in the input field.");
            this.treePanel.append("Please enter a value in the input field.");
            return;
        }

        if (e.getSource() == this.getButtonPanel().getInsertButton()) {

            // Get the data from the input field
            Integer data = Integer.parseInt(this.formPanel.getInputField().getText());
            this.getTreePanel().getTree().insert(data);

            // Re-draw the tree
            this.getTreePanel().drawTree();

            // Reset the input field
            this.formPanel.getInputField().setText("");

        } else if (e.getSource() == this.getButtonPanel().getDeleteButton()) {

            // Get the data from the input field
            Integer data = Integer.parseInt(this.formPanel.getInputField().getText());
            this.getTreePanel().getTree().remove(data);

            // Re-draw the tree
            this.getTreePanel().drawTree();

            // Reset the input field
            this.formPanel.getInputField().setText("");

        } else if (e.getSource() == this.getButtonPanel().getSearchButton()) {
            StringBuilder answer = new StringBuilder();

            // Get the data from the input field
            Integer data = Integer.parseInt(this.formPanel.getInputField().getText());
            try {
                answer.append(this.getTreePanel().getTree().searchPath(data));
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            // Set the answer to the output field
            this.formPanel.getOutputArea().setText(answer.toString());

            // Append new line to the tree panel
            this.getTreePanel().append("\n");

            // Append the answer to the tree panel
            this.getTreePanel().append("Path to " + data + ": ");
            this.getTreePanel().append(answer.toString());

            // Reset the input field
            this.formPanel.getInputField().setText("");

        } else if (e.getSource() == this.getButtonPanel().getClearButton()) {
            // Clear all the nodes from the tree
            this.treePanel.getTree().clear();

            // Clear the tree from the panel
            this.getTreePanel().clearTree();

            this.formPanel.getOutputArea().setText("");
        }
    }
}
