package src;

public class Square extends GeometricObject implements Colorable {
    private double side;
    private double area;
    private double perimeter;

    public String howToColor() {
        return "Color all four sides";
    }
    
    public Square() {
        super();
        this.side = 0;
    }

    public Square(double side) {
        super("white",false);
        this.side= side;
        this.area = this.getArea();
        this.perimeter = this.getPerimeter();
        this.compareValue = this.area;
    }

    public Square(double side, String color, boolean filled) {
        super(color,filled);
        this.side = side;
        this.area = this.getArea();
        this.perimeter = this.getPerimeter();
        this.compareValue = this.area;
    }

    public double getside() {
        return this.side;
    }

    public void setside(double side) {
        this.side = side;
    }

    public double getArea() {
        this.area = this.side*this.side;
        return this.area;
    }

    public double getPerimeter() {
        this.perimeter = 4*this.side;
        return this.perimeter;
    }

    public String toString() {
        return "Square: create on "+this.getDateCreated();
    }
}
