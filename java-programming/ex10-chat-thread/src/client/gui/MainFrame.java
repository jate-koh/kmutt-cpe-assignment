package client.gui;

import client.gui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends Frame implements ActionListener {

    private MainPanel mainPanel;

    public MainFrame() {
        this(null, 800, 600);
    }

    public MainFrame(String title) {
        this(title, 800, 600);
    }

    public MainFrame(String title, int width, int height) {
        super(new BorderLayout(), Color.WHITE);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        this.initComponents();
    }

    public void initComponents() {
        this.mainPanel = new MainPanel();
        this.add(this.mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
