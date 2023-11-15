package gui.panels;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends Panel {

    private JButton insertButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton clearButton;

    public ButtonPanel(int row, int column) {
        super(new GridLayout(row, column), Color.WHITE);
        this.setVisible(true);
    }

    protected void initComponents() {
        this.clearButton = new JButton("Clear");
        this.clearButton.setVisible(true);

        this.insertButton = new JButton("Insert");
        this.insertButton.setVisible(true);

        this.deleteButton = new JButton("Delete");
        this.deleteButton.setVisible(true);

        this.searchButton = new JButton("Search");
        this.searchButton.setVisible(true);

        this.add(this.insertButton);
        this.add(this.deleteButton);
        this.add(this.searchButton);
        this.add(this.clearButton);
    }

    public JButton getInsertButton() {
        return this.insertButton;
    }

    public JButton getDeleteButton() {
        return this.deleteButton;
    }

    public JButton getSearchButton() {
        return this.searchButton;
    }

    public JButton getClearButton() {
        return this.clearButton;
    }

}
