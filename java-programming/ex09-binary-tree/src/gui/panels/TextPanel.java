package gui.panels;

import javax.swing.*;
import java.awt.*;

public class TextPanel extends Panel {

    private JLabel inputLabel;
    private JLabel outputLabel;
    private JTextField inputField;
    private JTextArea outputArea;

    public TextPanel() {
        super(new BorderLayout(), Color.WHITE);
        this.setVisible(true);
    }

    public void initComponents() {
        this.inputField = new JTextField();
        this.outputArea = new JTextArea();
        this.outputArea.setEditable(false);
        this.outputArea.setLineWrap(true);
        this.outputArea.setWrapStyleWord(true);

        this.inputLabel = new JLabel("Input:");
        this.outputLabel = new JLabel("Output:");

        this.add(this.inputLabel, BorderLayout.NORTH);
        this.add(this.inputField, BorderLayout.CENTER);
        this.add(this.outputLabel, BorderLayout.SOUTH);
        this.add(this.outputArea, BorderLayout.EAST);
    }
}
