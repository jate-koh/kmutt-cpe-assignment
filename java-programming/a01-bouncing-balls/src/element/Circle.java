package element;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import utils.*;

public class Circle extends JPanel implements MovingElement<Circle> {

    // Color and Size
    private Color color;
    private int diameter;

    // Position and Velocity
    private int x, y;
    private int vx, vy;


    /* Circle Init and Render Functions */
    public static Circle createCircle(int x, int y) {
        return createCircle(x, y, 20, 50);
    }

    public static Circle createCircle(int x, int y, int maxDiameter, int maxVelocity) {
        Random random = new Random();

        // Randomize diameter and color
        int diameter = random.nextInt(maxDiameter) + 1;
        Color color = new Color(
                random.nextInt(255),
                random.nextInt(255),
                random.nextInt(255)
        );

        // Randomize velocity
        int vx = random.nextInt(maxVelocity * 2) - maxVelocity;
        int vy = random.nextInt(maxVelocity * 2) - maxVelocity;

        Logger.logMessage(
                "Created Circle with diameter " + diameter + " and color R " + color.getRed() +
                        " G " + color.getGreen() + " B " + color.getBlue() + " at " + x + ", " + y +
                        " with initial velocity of " + vx + ", " + vy,
                "Circle");

        // Calculate position to center circle
        x = x - diameter / 2;
        y = y - diameter / 2;

        return new Circle(x, y, diameter, color, vx, vy);
    }

    public Circle() {
        this(0, 0, 2, Color.RED, 1, 1);
    }

    public Circle(Color color) {
        this(0, 0, 2, color, 1, 1);
    }

    public Circle(int x, int y, int diameter, Color color, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.color = color;
        this.diameter = diameter;
        this.setBackground(new Color(0, 0, 0, 0));
        this.setBounds(this.x, this.y, this.diameter, this.diameter);
    }

    @Override
    public void paintComponent(Graphics graphics) throws NullPointerException {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(this.getColor());
        graphics2D.fillOval(this.x, this.y, this.diameter, this.diameter);
    }

    /* Movement Functions */
    public void move() {
        this.x += this.vx;
        this.y += this.vy;
        this.setLocation(this.x, this.y);
    }

    public void reverseX() {
        this.vx = -this.vx;
    }

    public void reverseY() {
        this.vy = -this.vy;
    }

    /* Getter and Setter Functions */
    public void setXVelocity(int vx) {
        this.vx = vx;
    }

    public int getXVelocity() {
        return this.vx;
    }

    public void setYVelocity(int vy) {
        this.vy = vy;
    }

    public int getYVelocity() {
        return this.vy;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getWidth() {
        return this.diameter;
    }

    public int getHeight() {
        return this.diameter;
    }

    public void setColor(Color color) {
        this.setBackground(color);
    }

    public Color getColor() {
        return this.color;
    }

}
