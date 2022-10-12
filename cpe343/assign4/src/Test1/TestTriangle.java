package src.Test1;


import src.Triangle;
import java.util.Scanner;
import java.lang.String;

public class TestTriangle {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            Triangle triangle = new Triangle(1,1,1);
            System.out.println("Triangle sides: "+triangle.toString());
            System.out.println("color is "+triangle.getColor()+" and filled: "+triangle.isFilled());
            System.out.println("area is "+triangle.getArea());
            System.out.println("perimeter is "+triangle.getPerimeter());

            System.out.printf("\n");
            System.out.println("Changing Triangle Properties");
            triangle = new Triangle(1,2,2,"red",true);
            System.out.println("Triangle sides: "+triangle.toString());
            System.out.println("color is "+triangle.getColor()+" and filled: "+triangle.isFilled());
            System.out.println("area is "+triangle.getArea());
            System.out.println("perimeter is "+triangle.getPerimeter());

            System.out.printf("\n");
            System.out.println("User's own properties");
            System.out.printf("Input side1: ");
            double side1 = input.nextDouble();
            System.out.printf("Input side2: ");
            double side2 = input.nextDouble();
            System.out.printf("Input side3: ");
            double side3 = input.nextDouble();
            System.out.printf("Input fill: ");
            
            String fill;
            do {
                fill = input.next();
      } while(!(fill.replaceAll("\\s+", "").toLowerCase().equals("true")) && !(fill.replaceAll("\\s+", "").toLowerCase().equals("false")));

      String color = "white";
            if(fill.replaceAll("\\s+", "").toLowerCase().equals("true")) { 
                System.out.printf("Input color: ");
                color = input.next();
            }
            boolean filled = Boolean.parseBoolean(fill);

            if(fill.replaceAll("\\s+", "").toLowerCase().equals("true")) triangle = new Triangle(side1,side2,side3,color,filled);
            else triangle = new Triangle(side1,side2,side3);
            System.out.println("Triangle sides: "+triangle.toString());
            System.out.println("color is "+triangle.getColor()+" and filled: "+triangle.isFilled());
            System.out.println("area is "+triangle.getArea());
            System.out.println("perimeter is "+triangle.getPerimeter());
        }
    }
}
