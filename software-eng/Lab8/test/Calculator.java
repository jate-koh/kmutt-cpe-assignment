package cpe327.Lab8.test;
import java.util.ArrayList;

public class Calculator {
    String fomula;
    public Calculator(){
    }
    public Calculator(String fomula){
        this.fomula = fomula;
    }

    public void PrintString(){
        System.out.println(fomula);
    }

    //Integer calculate
    public int IntCal (int Operand_1,char Operator,int Operand_2){
           
            switch(Operator){
                case '+':
                    return Operand_1 + Operand_2;
                case '-':
                    return Operand_1 - Operand_2;
                case '*':
                    return Operand_1 * Operand_2;
                case '/':
                    return Operand_1 / Operand_2;
                case '%':
                    return Operand_1 % Operand_2;
            default :
                    return -1;
            }
    }

    

    
}


/*
 * 5+6 
 */