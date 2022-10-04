package cpe327.Lab8.source;
import java.util.Scanner;

public class index {
    public static void main(String[] args) {
        System.out.println("Hello world");
        Scanner input = new Scanner(System.in);
        Calculator cal = new Calculator();
        

        System.out.println("Enter fomula ?");
        cal.fomula = input.nextLine();
        cal.PrintString();
    }
}