package element;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import utils.*;

public class Circle extends JPanel implements GameElement<Circle> {

    private Color color;
    private int diameter;

    public static Circle createCircle(int x, int y) {
        return createCircle(x, y, 29);
    }

    public static Circle createCircle(int x, int y, int maxDiameter) {
        Random random = new Random();
        int diameter = random.nextInt(maxDiameter) + 1;
        Color color = new Color(
                random.nextInt(255),
                random.nextInt(255),
                random.nextInt(255)
        );
        Logger.logMessage("Created Circle with diameter " + diameter + " and color " + color.toString(), "Circle");
        return new Circle(x, y, diameter, color);
    }

    public Circle() {
        this(0, 0, 2, Color.RED);
    }

    public Circle(Color color) {
        this(0, 0, 2, color);
    }

    public Circle(int x, int y, int diameter, Color color) {
        this.color = color;
        this.diameter = diameter;
        this.setBackground(new Color(0, 0, 0, 0));
        this.setLocation(x, y);
        this.setBounds(x, y, diameter ,diameter);
    }

    public Circle getGameElement() {
        return this;
    }

    public Circle cloneElement() {
        return new Circle(this.getX(), this.getY(), this.getWidth(), this.getColor());
    }

    @Override
    public void paintComponent(Graphics graphics) throws NullPointerException {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(this.getColor());
        graphics2D.fillOval(0, 0, this.getWidth(), this.getHeight());
    }

    public void setColor(Color color) {
        this.setBackground(color);
    }

    public Color getColor() {
        return this.color;
    }

}
