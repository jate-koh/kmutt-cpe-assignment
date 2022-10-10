package cpe327.Lab8.source;

import java.lang.Double;

import cpe327.Lab8.source.operation.*;

public class Calculator {
    private double operand1;
    private double operand2;
    private String operator;
    private boolean error = false;

    public Calculator() {
        this.operand1 = 0.0;
        this.operand2 = 0.0;
        this.operator = null;
    }
    
    public Calculator(String stringOperand1, String stringOperand2, String operator) {
        try {
            this.operand1 = Double.parseDouble(stringOperand1);
            this.operand2 = Double.parseDouble(stringOperand2);
         }
         catch(Exception e) {
            System.out.println("> ERROR INVALID OPERAND");
            this.error = true;
         }
        this.operator = operator;
    }

    public Calculator(double operand1, double operand2, String operator) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
    }
    
    public double getOperand1() {
        return this.operand1;
    }

    public double getOperand2() {
        return this.operand2;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public String getFormula() {
        return this.operand1 + " " + this.operator + " " + this.operand2;
    }

    public Boolean getError() {
        return this.error;
    }
    
    public void resetError() {
        this.error = false;
    }

    public double calculate() {
        this.resetError();
        if(this.operator != null)     
            {
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
                default: {
                    System.out.println("> INVALID OPERATOR");
                    this.error = true;
                }
            }
            return 0;
        }
        else {
            System.out.println("> NULL OPERATOR");
            this.error = true;
            return 0;
        }
    }


}
