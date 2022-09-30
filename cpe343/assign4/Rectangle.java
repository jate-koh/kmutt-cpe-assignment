package cpe343.assign4;

import java.lang.String;

public class Rectangle extends GeometricObject {
    private double width;
    private double height;
    private double area;
    private double perimeter;
    
    public Rectangle() {
        super();
        this.width = 0;
        this.height = 0;
    }

    public Rectangle(double width, double height) {
        super("white",false);
        this.width = width;
        this.height = height;
    }

    public Rectangle(double width, double height, String color, boolean filled) {
        super(color,filled);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        this.area = this.width*this.height;
        return this.area;
    }

    public double getPerimeter() {
        this.perimeter = 2*this.width + 2*this.height;
        return this.perimeter;
    }

    public String toString() {
        return "Rectangle: create on "+this.getDateCreated();
    }
}
