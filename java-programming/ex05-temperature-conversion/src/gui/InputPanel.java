package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import converter.TemperatureConverter;

public class InputPanel extends JFrame implements ActionListener {

    private JLabel inputLabel;
    private JLabel outputLabel;
    private JLabel unitLabel;
    private JTextField inputField;
    private JTextField outputField;
    private JTextField unitField;
    private JButton button;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;

    public InputPanel() {
        Container contentPane = this.getContentPane();
        this.setLayout(new FlowLayout(FlowLayout.LEFT , 2, 2));

        // Set Frame Properties
        this.setTitle("Temperature Converter");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // Create Element Objects
        this.inputLabel = new JLabel("Enter a temperature value to convert:");
        this.outputLabel = new JLabel("Converted temperature:");
        this.unitLabel = new JLabel("Unit:");
        this.inputField = new JTextField(30);
        this.outputField = new JTextField(30);
        this.unitField = new JTextField(10);
        this.checkBox1 = new JCheckBox("Fahrenheit");
        this.checkBox2 = new JCheckBox("Celsius");
        this.button = new JButton("Convert");

        // Set Properties of Element Objects
        this.inputField.setEditable(true);
        this.inputField.setText("0");
        this.inputField.setBounds(10, 10, 100, 20);

        this.outputField.setEditable(false);
        this.outputField.setText("0");
        this.outputField.setBounds(10, 10, 100, 20);

        this.unitField.setEditable(false);
        this.unitField.setText("None");
        this.unitField.setBounds(10, 10, 100, 20);

        // Add Element Objects to Panel
        this.add(inputLabel);
        this.add(inputField);
        this.add(checkBox1);
        this.add(checkBox2);
        this.add(button);

        this.add(outputLabel);
        this.add(outputField);
        this.add(unitLabel);
        this.add(unitField);

        // Add Action Listener to Button
        this.button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.button) {
            String input = this.inputField.getText();
            double value = Double.parseDouble(input);
            String unit = "";
            if (this.checkBox1.isSelected()) {
                unit = "F";
            } else if (this.checkBox2.isSelected()) {
                unit = "C";
            }
            TemperatureConverter converter = new TemperatureConverter(value, unit);

            double output;
            if (unit.equals("F")) {
                output = converter.getCelsius();
            } else {
                output = converter.getFahrenheit();
            }
            this.outputField.setText(Double.toString(output));
            this.unitField.setText(unit);
        }
    }
}
