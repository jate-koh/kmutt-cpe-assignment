package gui.panels;

import game.control.Shape;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends Panel {

    private JButton[] buttons;
    private JOptionPane optionPane;

    public GamePanel() {
        super(new GridLayout(3, 3), Color.WHITE);
    }

    public void displayInfoMessage(String message) {
        this.optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        this.optionPane.setMessage(message);
        JDialog dialog = this.optionPane.createDialog(this, "Info");
        dialog.setVisible(true);
    }

    public void displayErrorMessage(String message) {
        this.optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
        this.optionPane.setMessage(message);
        JDialog dialog = this.optionPane.createDialog(this, "Error!");
        dialog.setVisible(true);
    }

    @Override
    protected void initComponents() {
        this.buttons = new JButton[9];
        for (int i = 0; i < this.buttons.length; i++) {
            this.buttons[i] = new JButton();
            this.buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            this.add(this.buttons[i]);
        }

        // Setup OptionPane
        this.optionPane = new JOptionPane();
        this.optionPane.setFont(new Font("Arial", Font.PLAIN, 20));
        this.optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);

        // Set all buttons to empty
        for (int i = 0; i < this.buttons.length; i++) {
            this.buttons[i].setText("");
        }

    }

    public void clearButtons() {
        for (int i = 0; i < this.buttons.length; i++) {
            this.buttons[i].setText("");
        }
    }

    // Getters and Setters
    public void setButtonX(int index) {
        this.buttons[index].setText("X");
    }

    public void setButtonO(int index) {
        this.buttons[index].setText("O");
    }

    public JButton[] getButtons() {
        return buttons;
    }

    public JButton getButton(int index) {
        return this.buttons[index];
    }
}
