package element;

import javax.swing.*;
import java.awt.*;

public class Point extends JPanel implements GameElement {

    private Color color;
    private int x, y, diameter;

    public Point() {
        this(Color.WHITE, 0, 0);
    }

    public Point(Color color) {
        this(color, 0, 0);
    }

    public Point(int x, int y) {
        this(Color.WHITE, x, y);
    }

    public Point(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.setLocation(x, y);
        this.setBounds(x, y, 1, 1);
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

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(this.color);
        graphics2D.fillOval(0, 0, this.diameter, this.diameter);
    }

    public GameElement cloneElement() {
        return new Point(this.color, this.x, this.y);
    }
}
