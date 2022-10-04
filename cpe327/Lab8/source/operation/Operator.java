package cpe327.Lab8.source.operation;

public abstract class Operator {
    protected double operand1;
    protected double operand2;
    protected boolean error;
    protected double answer;

    public Operator(double operand1, double operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.error = false;
    }

    public double getOperand1() {
        return this.operand1;
    }

    public double getOperand2() {
        return this.operand2;
    }

    public boolean getError() {
        return this.error;
    }

    public double getAnswer() {
        return this.answer;
    }
    
    public abstract double process();

    public abstract int errorCase();

}
