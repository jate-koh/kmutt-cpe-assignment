package client.gui.panels;

import java.awt.*;
public class MainPanel extends Panel {

    private MessagePanel messagePanel;
    private InputPanel inputPanel;

    public MainPanel() {
        super(new BorderLayout(), null);
    }

    protected void initComponents() {
        this.messagePanel = new MessagePanel();
        this.inputPanel = new InputPanel();
        this.add(this.messagePanel, BorderLayout.CENTER);
        this.add(this.inputPanel, BorderLayout.SOUTH);
    }

}
