package src;

public class Triangle extends GeometricObject implements Colorable {
    private double side1;
    private double side2;
    private double side3;
    private double area;
    private double perimeter;

    public String howToColor() {
        return "Color all three sides";
    }

    public Triangle() {
        super();
        this.side1 = 1.0;
        this.side2 = 1.0;
        this.side3 = 1.0;
    }

    public Triangle(double side1, double side2, double side3) {
        super("white",false);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.area = this.getArea();
        this.perimeter = this.getPerimeter();
        this.compareValue = this.area;
    }

    public Triangle(double side1, double side2, double side3, String color, boolean filled) {
        super(color, filled);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.area = this.getArea();
        this.perimeter = this.getPerimeter();
        this.compareValue = this.area;
    }

    public double getArea() {
        double s = (this.side1 + this.side2 + this.side3)/2;
        this.area = Math.sqrt(s*(s-this.side1)*(s-this.side2)*(s-this.side3));
        return this.area;
    }

    public double getPerimeter() {
        this.perimeter = this.side1 + this.side2 + this.side3;
        return this.perimeter;
    }

    public String toString() {
        return "Triangle: side1 = "+this.side1+" side2 = "+this.side2+" side3 = "+this.side3;
    }
}
