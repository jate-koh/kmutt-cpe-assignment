package src;
import java.lang.String;

public class Circle extends GeometricObject implements Colorable {
    private double radius;
    private double area;
    private double perimeter;
    private double diameter;

    public void howToColor() {
        System.out.println("Color from center of circle to the round side of circle");
    }

    public Circle() {
        super();
        this.radius = 0;
    }

    public Circle(double radius) {
        super("white",false);
        this.radius = radius;
        this.area = this.getArea();
        this.perimeter = this.getPerimeter();
        this.diameter = this.getDiameter();
    }

    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
        this.area = this.getArea();
    }

    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        this.area = Math.PI*Math.pow(this.radius, 2);
        return this.area;
    }

    public double getPerimeter() {
        this.perimeter = 2*Math.PI*this.radius;
        return this.perimeter;
    }

    public double getDiameter() {
        this.diameter = 2*this.radius;
        return this.diameter;
    }

    public String toString() {
        return "Circle: created on "+this.getDateCreated();
    }

    /*
    public boolean equals(Object o) {
        System.out.println("Note: this is overridden method!");
        if(o instanceof Circle) {
            return this.radius == ((Circle)o).radius;
        }
        else return false;
    } */
    public boolean equals(Circle circle) {
        System.out.println("Note: this is overloaded method!");
        return this.radius == circle.radius;
    }
}
