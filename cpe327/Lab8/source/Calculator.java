package cpe327.Lab8.source;

import java.lang.Double;
import cpe327.Lab8.source.operation.*;

public class Calculator {
    private double operand1;
    private double operand2;
    private String operator;
    public boolean error = false;
    
    public Calculator(String stringOperand1, String stringOperand2, String operator) {
        this.operand1 = Double.parseDouble(stringOperand1);
        this.operand2 = Double.parseDouble(stringOperand2);
        this.operator = operator;
    }

    public Calculator(double operand1, double operand2, String operator) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
    }

    public String getFormula() {
        return this.operand1 + " " + this.operator + " " + this.operand2;
    }

    public double calculate() {
        switch(this.operator.replaceAll("\\s+","")) {
            case "+": {
                Add ops = new Add(this.operand1,this.operand2);
                return ops.getAnswer();
            }
            case "-": {
                Subtract ops = new Subtract(this.operand1,this.operand2);
                return ops.getAnswer();
            }
            case "*": {
                Multiply ops = new Multiply(this.operand1,this.operand2);
                return ops.getAnswer();
            }
            case "/": {
                Divide ops = new Divide(this.operand1,this.operand2);
                if(ops.getError()) { this.error = true; return 0; }
                else return ops.getAnswer();
            }
        }
        return 0;

    }


}
