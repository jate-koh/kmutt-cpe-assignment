package panel;

import element.GameElement;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GamePanel extends JInternalFrame {

    private ArrayList<GameElement> elements;

    public GamePanel() {
        this(0, 0, 200, 200);
    }

    public GamePanel(int x, int y, int width, int height) {
        // Initialize List
        this.elements = new ArrayList<GameElement>();

        // Set Properties for the Frame
        this.setTitle(title);
        this.setBounds(x, y, width, height);
        this.setBackground(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void changeTitle(String title) {
        this.setTitle(title);
    }

    public void changeSize(int width, int height) {
        this.setSize(width, height);
    }

    public void changeColor(Color color) {
        this.getContentPane().setBackground(color);
    }

    public void addElement(GameElement element) {
        this.elements.add(element);
        this.getContentPane().add((JPanel) element);
    }

    public void removeElement(GameElement element) {
        this.elements.remove(element);
        this.getContentPane().remove((JPanel) element);
    }

    public void addMouseAdapter(MouseAdapter listener) {
        this.getContentPane().addMouseListener(listener);
    }
}
