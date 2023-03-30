package cpe327.Lab8.source.operation;

public class Divide extends Operator {

    public Divide(double operand1, double operand2) {
        super(operand1,operand2);
        if(this.errorCase() == 1) { 
            this.error = true;
            this.answer = 0;
            System.out.println("> DIVIDE BY ZERO");
        } 
        else this.answer = this.process();
    }

    public double process() {
        return operand1 / operand2;
    }

    public int errorCase() {
        if(this.operand2 == 0) return 1;
        else return 0;
    }
    
}
