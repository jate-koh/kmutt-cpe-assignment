package panel;

import element.ElementList;
import element.GameElement;
import element.MovingElement;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {

    private ElementList elements;
    private Timer timer;

    public GamePanel() {
        this(0, 0, 200, 200);
    }

    public GamePanel(int x, int y, int width, int height) {
        // Initialize List
        this.elements = new ElementList();

        // Set Properties for the Frame
        this.setBounds(x, y, width, height);
        this.setBackground(null);
        this.setVisible(true);

        // Initialize Timer and Start Timer
        this.timer = new Timer(50, this);
        timer.start();
    }

    public void changeSize(int width, int height) {
        this.setSize(width, height);
    }

    public void changeColor(Color color) {
        this.setBackground(color);
    }

    public void addElement(GameElement element) {
        this.elements.addElement(element);
        this.add((JPanel) element);
    }

    public void removeElement(GameElement element) {
        this.elements.removeElement(element);
        this.add((JPanel) element);
    }

    public void addMouseAdapter(MouseAdapter listener) {
        this.addMouseListener(listener);
    }

    public ElementList getElements() {
        return this.elements;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (GameElement element : this.elements.getList()) {
            if(element instanceof MovingElement<?>) {
                ((MovingElement<?>) element).move();

                // Calculate insets
                Insets insets = this.getInsets();
                int frameWidth = this.getWidth() - insets.left - insets.right;
                int frameHeight = this.getHeight() - insets.top - insets.bottom;

                // Check if element is out of bounds
                if (element.getX() < 0 || element.getX() > frameWidth - element.getWidth() )
                    ((MovingElement<?>) element).reverseX();

                if (element.getY() < 0 || element.getY() > frameHeight - element.getHeight() )
                    ((MovingElement<?>) element).reverseY();

            }
        }
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (GameElement element : this.elements.getList()) {
            element.paintComponent(g);
        }
    }
}
