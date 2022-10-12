package src.Test1;

import src.Circle;
import src.Rectangle;
public class TestCircleRectangle {
    public static void main(String[] args) {
        Circle circle = new Circle(1);
        System.out.println(circle.toString());
        System.out.println("color is "+circle.getColor()+" and filled: "+circle.isFilled());
        System.out.println("radius is "+circle.getRadius());
        System.out.println("area is "+circle.getArea());
        System.out.println("diameter is "+circle.getDiameter());

        Rectangle rectangle = new Rectangle(2,4);
        System.out.println("\n"+rectangle.toString());
        System.out.println("color is "+circle.getColor()+" and filled: "+circle.isFilled());
        System.out.println("area is "+rectangle.getArea());
        System.out.println("perimeter is "+rectangle.getPerimeter());
    }
}
