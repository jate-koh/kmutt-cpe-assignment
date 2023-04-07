package data.structure.stack;

import utils.Logger;

public class IntegerStack extends Stack<Integer> {
    public IntegerStack() {
        super();
    }

    public IntegerStack(int size) {
        super(size);
    }

    @Override
    public void printLog() {
        StringBuilder message = new StringBuilder("Stack: {");
        for (Object itr: this.items) {
            message.append(itr).append(" ");
        }
        message.append("}");
        Logger.logMessage(message.toString(), "IntegerStack");
    }
}
