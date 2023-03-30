package src.Test1;

import src.Circle;

public class TestInstance {
    
    public static void main(String[] args) {
        /*Circle circle = new Circle(1);
        GeometricObject object = new GeometricObject();

        System.out.println("circle instance of GeometricObject: "+(circle instanceof GeometricObject));
        System.out.println("object instance of GeometricObject: "+(object instanceof GeometricObject));
        System.out.println("circle instance of Circle: "+(circle instanceof Circle));
        System.out.println("object instance of Circle: "+(object instanceof Circle)); */

        //Circle circle = new Circle(5);
        //GeometricObject object = circle;

        /*GeometricObject object = new GeometricObject();
        Circle circle = (Circle)object;
        System.out.println("If this print out, meaning it can be compiled!"); */

        Circle circle1 = new Circle();
        Circle circle2 = new Circle();
        System.out.println(circle1.equals(circle2));

    }
}
