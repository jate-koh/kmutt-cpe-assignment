package element;

import javax.swing.*;
import java.awt.*;

public class GameElement extends JPanel {

    private Color color;
    private int width, height;
    private int x, y;

    public GameElement() {
        this.width = 1;
        this.height = 1;
        this.x = 0;
        this.y = 0;
        this.color = Color.BLACK;
        this.setLocation(0, 0);
        this.setSize(1, 1);
    }
    public GameElement(Color color) {
        this.width = 1;
        this.height = 1;
        this.x = 0;
        this.y = 0;
        this.color = color;
        this.setLocation(0, 0);
        this.setSize(1, 1);
    }

    public GameElement(int x, int y) {
        this.color = Color.BLACK;
        this.setLocation(x, y);
        this.setSize(1, 1);
    }

    public GameElement(int x, int y, int width, int height) {
        this.color = Color.BLACK;
        this.setLocation(x, y);
        this.setSize(width, height);
    }

    public GameElement(Color color, int x, int y, int width, int height) {
        this.color = color;
        this.setLocation(x, y);
        this.setSize(width, height);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(this.color);
        graphics2D.fillRect(0, 0, this.width, this.height);
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        super.setLocation(x, y);
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        super.setSize(width, height);
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
