package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class FramePanel {

    private JFrame frame ;

    public FramePanel() {
        this("Frame", 200, 200);
    }

    public FramePanel(String title, int width, int height) {
        this.frame = new JFrame();
        this.frame.setTitle(title);
        this.frame.setSize(width, height);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public Container getContentPane() {
        return this.frame.getContentPane();
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void addElement(JPanel element) {
        this.frame.getContentPane().add(element);
    }

    public void addElement(JInternalFrame element) {
        this.frame.getContentPane().add(element);
    }

}
