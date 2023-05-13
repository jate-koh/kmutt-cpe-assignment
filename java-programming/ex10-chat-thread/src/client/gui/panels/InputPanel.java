package client.gui.panels;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends Panel {

    private JTextField textField;
    private JButton sendButton;

    public InputPanel() {
        super(new FlowLayout(), Color.WHITE);
    }

    @Override
    protected void initComponents() {
        this.textField = new JTextField(30);
        this.sendButton = new JButton("Send");
        this.add(this.textField);
        this.add(this.sendButton);
    }
}
