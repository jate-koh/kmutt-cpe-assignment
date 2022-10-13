package src;
import java.lang.String;

public class Rectangle extends GeometricObject implements Colorable, Comparable {
    private double width;
    private double height;
    private double area;
    private double perimeter;

    public String howToColor() {
        return "Color all four sides";
    }
    
    public Rectangle() {
        super();
        this.width = 0;
        this.height = 0;
        this.area = this.getArea();
        this.perimeter = this.getPerimeter();
        this.area = this.compareValue;
    }

    public Rectangle(double width, double height) {
        super("white",false);
        this.width = width;
        this.height = height;
        this.area = this.getArea();
        this.perimeter = this.getPerimeter();
        this.area = this.compareValue;
    }

    public Rectangle(double width, double height, String color, boolean filled) {
        super(color,filled);
        this.width = width;
        this.height = height;
        this.area = this.getArea();
        this.perimeter = this.getPerimeter();
        this.area = this.compareValue;
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

    public int compareTo(Object obj) {
        if(obj instanceof Rectangle) {
            if(this.getArea() > ((Rectangle)obj).getArea()) return -1;
            else if(this.getArea() < ((Rectangle)obj).getArea()) return 1;
            else return 0;
        }
        else {
            System.out.println("compareTo() error, must be instance of Rectangle");
            return -100;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Rectangle) {
            if(this.compareTo(obj) ==  0) return true;
            else return false;
        }
        else {
            System.out.println("compareTo() error, must be instance of Rectangle");
            return false;
        }
    }
}
