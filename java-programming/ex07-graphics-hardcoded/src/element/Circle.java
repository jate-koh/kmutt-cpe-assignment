package element;

import javax.swing.*;
import java.awt.*;

public class Circle extends JPanel implements GameElement {

    private Color color;
    private int x, y, diameter;

    public Circle() {
        this(Color.RED, 0, 0, 10);
    }

    public Circle(Color color) {
        this(color, 0, 0, 10);
    }

    public Circle(int x, int y, int diameter) {
        this(Color.RED, x, y, diameter);
    }

    public Circle(Color color, int x, int y, int diameter) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.setBackground(new Color(0, 0, 0, 0));
        this.setLocation(x, y);
        this.setBounds(x, y, diameter, diameter);
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        this.setBounds(x, y, this.diameter, this.diameter);
        super.setLocation(x, y);
    }

    public int getDiameter() {
        return this.diameter;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getWidth() {
        return this.diameter;
    }

    @Override
    public int getHeight() {
        return this.diameter;
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.setColor(this.color);
        graphics.fillOval(0, 0, this.diameter, this.diameter);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(this.color);
        graphics2D.fillOval(0, 0, this.diameter, this.diameter);
    }

    public GameElement cloneElement() {
        return new Circle(this.color, this.x, this.y, this.diameter);
    }

}
