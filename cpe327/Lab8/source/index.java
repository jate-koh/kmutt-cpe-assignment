package cpe327.Lab8.source;
import java.util.Scanner;


public class index {
    public static void main(String[] args) {
       //System.out.println("Hello world");
        try (Scanner input = new Scanner(System.in)) {
            System.out.printf("> OPERAND, ");
            String operand1 = input.nextLine();
            System.out.printf("> OPERATOR, ");
            String operator = input.nextLine();
            System.out.printf("> OPERAND, ");
            String operand2 = input.nextLine();
            
            Calculator cal = new Calculator(operand1,operand2,operator);
            double answer = cal.calculate();
            if(cal.getError()) System.out.println("> ERROR ");
            else System.out.println("> ANSWER, "+answer);
        }

    }
}