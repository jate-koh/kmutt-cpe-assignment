package cpe327.Lab8.modified.operation;

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

    public boolean getError() {
        return this.error;
    }

    public double getAnswer() {
        return this.answer;
    }
    
    public abstract double process();

    public abstract int errorCase();

}
