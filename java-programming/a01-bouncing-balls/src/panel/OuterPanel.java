package panel;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class OuterPanel extends JFrame {


    public OuterPanel() {
        this("", 500, 500);
    }

    public OuterPanel(String title) {
        this(title, 500, 500);
    }

    public OuterPanel(String title, int width, int height) {
        // Set Properties for the Frame
        this.setTitle(title);
        this.setBounds(0, 0, width, height);
        this.setBackground(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void changeTitle(String title) {
        this.setTitle(title);
    }

    public void changeSize(int width, int height) {
        this.setSize(width, height);
    }

    public void changeColor(Color color) {
        this.setBackground(color);
    }

    public void addPanel(JPanel panel) {
        this.add(panel);
    }

    public void addPanel(JInternalFrame panel) {
        this.add(panel);
    }

    public void removePanel(JPanel panel) {
        this.remove(panel);
    }

    public void removePanel(JInternalFrame panel) {
        this.remove(panel);
    }

}
