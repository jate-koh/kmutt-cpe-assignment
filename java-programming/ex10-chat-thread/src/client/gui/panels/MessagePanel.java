package client.gui.panels;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends Panel {

    private JTextArea textArea;

    public MessagePanel() {
        super(null, Color.WHITE);
    }

    protected void initComponents() {
        this.textArea = new JTextArea();
        this.textArea.setEditable(false);
        this.textArea.setBounds(0, 0, 400, 400);
        this.add(new JScrollPane(this.textArea));
    }
}
