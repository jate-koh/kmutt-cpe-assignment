package cpe327.Lab8.modified;

import cpe327.Lab8.modified.operation.*;
import java.lang.Double;

public class Calculator {
    private double operand1;
    private double operand2;
    private String operator;
    private boolean error = false;
    /* Set Decimal Places: Precision 
     * Precision is not available for Dividing */
    private int precision = 1; // Default is one

    public Calculator() {
        this.operand1 = 0.0;
        this.operand2 = 0.0;
        this.operator = null;
    }

    public Calculator(String stringOperand1, String stringOperand2, String operator) {
        try {
            this.operand1 = Double.parseDouble(stringOperand1);
            //System.out.println("Parse Operand 1: "+this.operand1);
            this.operand2 = Double.parseDouble(stringOperand2);
            //System.out.println("Parse Operand 2: "+this.operand2);
        } catch (Exception e) {
            System.out.println("> ERROR INVALID OPERAND");
            this.error = true;
        }
        this.operator = operator;
    }

    public double getOperand1() {
        return this.operand1;
    }

    public double getOperand2() {
        return this.operand2;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public void setOperand1(String operand1) {

        this.operand1 = Double.parseDouble(operand1);
    }

    public void setOperand2(String operand2) {
        this.operand2 = Double.parseDouble(operand2);
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Boolean getError() {
        return this.error;
    }

    public void resetError() {
        this.error = false;
    }

    public void setPrecision(int precision) {
        if(precision >= 0 ) this.precision =  precision;
        else System.out.println("> PRECISION MUST BE POSITIVE INT");
    }

    public double roundUp(double num, int precision) {
        return ( Math.round(num * Math.pow(10,precision)) )/ Math.pow(10,precision);
    }

    public double calculate() {
        this.resetError();
        if (this.operator != null) {
            switch (this.operator.replaceAll("\\s+", "")) {
                case "+": {
                    Add ops = new Add(this.operand1, this.operand2);
                    return this.roundUp(ops.getAnswer(), precision);
                }
                case "-": {
                    Subtract ops = new Subtract(this.operand1, this.operand2);
                    return this.roundUp(ops.getAnswer(), precision);
                }
                case "*": {
                    Multiply ops = new Multiply(this.operand1, this.operand2);
                    return this.roundUp(ops.getAnswer(), precision);
                }
                case "/": {
                    Divide ops = new Divide(this.operand1, this.operand2);
                    if (ops.getError()) {
                        this.error = true;
                        return 0;
                    } else
                        return ops.getAnswer();
                }
                default: {
                    System.out.println("> INVALID OPERATOR");
                    this.error = true;
                }
            }
            return 0;
        } else {
            System.out.println("> NULL OPERATOR");
            this.error = true;
            return 0;
        }
    }

}
