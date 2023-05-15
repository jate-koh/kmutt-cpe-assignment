package game.control;

import utils.Logger;

public class XOBoard {

    private Shape[] shapeBoard = new Shape[9];

    public XOBoard() {
        this.initBoard();
    }

    public void initBoard() {
        for (int i = 0; i < 9; i++) {
            shapeBoard[i] = Shape.EMPTY;
        }
    }

    public void setShape(int index, Shape shape) {
        shapeBoard[index] = shape;
    }

    public Shape[] getShapeBoard() {
        return shapeBoard;
    }

    public Shape getShape(int index) {
        return shapeBoard[index];
    }

    public void printBoard() {
        StringBuilder boardMessage = new StringBuilder();
        boardMessage.append("\n" + "-------------" + "\n");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
               boardMessage.append("| ");
            }
            boardMessage.append(shapeBoard[i] + " | ");
            if (i % 3 == 2) {
                boardMessage.append("\n");
               boardMessage.append("-------------" + "\n");
            }
        }
        Logger.logMessage(boardMessage.toString(), this);
    }

}
