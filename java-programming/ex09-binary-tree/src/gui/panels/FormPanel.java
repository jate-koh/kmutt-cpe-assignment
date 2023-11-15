package gui.panels;

import javax.swing.*;
import java.awt.*;

public class FormPanel extends Panel {

    private JLabel inputLabel;
    private JLabel outputLabel;
    private JTextField inputField;
    private JTextField outputArea;

    /** This constructor is used to create a panel with a layout and a background color.
     * @param row The number of rows for grid layout of the panel.
     * @param column The number of columns for grid layout of the panel.
     */
    public FormPanel(int row, int column) {
        this.setLayout(new GridLayout(row, column));
        this.setVisible(true);
    }

    protected void initComponents() {
        this.inputLabel = new JLabel("Input:");
        this.outputLabel = new JLabel("Output:");

        this.inputField = new JTextField();
        this.inputField.setEditable(true);
        this.outputArea = new JTextField();
        this.outputArea.setBounds(0, 0, 100, 100);
        this.outputArea.setEditable(false);

        this.add(this.inputLabel);
        this.add(this.inputField);
        this.add(this.outputLabel);
        this.add(this.outputArea);
    }

    public JTextField getOutputArea() {
        return this.outputArea;
    }

    public JTextField getInputField() {
        return this.inputField;
    }
}
