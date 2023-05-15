package gui.panels;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends Panel {

    private JLabel modeLabel;
    private JComboBox<String> mode;
    private JButton clear;
    private JButton stat;
    private JTextArea textArea;

    public MenuPanel() {
        super(new FlowLayout(FlowLayout.LEFT, 5, 5), Color.WHITE);
    }

    public void setTextAreaMessage(String message) {
        this.textArea.setText(message);
    }

    @Override
    protected void initComponents() {
        this.modeLabel = new JLabel("Mode: ");
        this.mode = new JComboBox<>(new String[]{"Player vs Player", "Player vs Computer"});

        this.clear = new JButton("Clear Board");
        this.stat = new JButton("View Stats");

        this.textArea = new JTextArea();
        this.textArea.setEditable(false);
        this.textArea.setFont(new Font("Arial", Font.PLAIN, 12));
        this.textArea.setText(
                "Welcome to Tic Tac Toe Game!\n" +
                "Please select the mode and click on the button to start the game."
        );

        this.add(this.modeLabel);
        this.add(this.mode);
        this.add(this.clear);
        // this.add(this.stat);
        this.add(this.textArea);
    }

    // Getters and Setters
    public JButton getClear() {
        return clear;
    }

    public JButton getStat() {
        return stat;
    }

    public String getModeString() {
        return (String) this.mode.getSelectedItem();
    }

    public JComboBox<String> getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode.setSelectedItem(mode);
    }

}
