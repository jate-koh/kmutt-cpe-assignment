package cpe327.Lab8.source.operation;

public class Multiply extends Operator {
    
    public Multiply(double operand1, double operand2) {
        super(operand1,operand2);
        this.answer = this.process();
    }

    public double process() {
        return operand1 * operand2;
    }

    public int errorCase() {
        return 0;
    }
}
