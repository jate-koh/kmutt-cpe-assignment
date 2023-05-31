package game.control;
//==============================================================================
/* XO Board
 * This class is the game board of object (backend) of the game.
 * Useds to keep track of / set what shape is in each slot.
 * Also keeps track of the previous player move.
 */
//==============================================================================
import utils.Logger;

public class XOBoard {

    //==========================================================================
    // Components
    private Shape[] shapeBoard = new Shape[9];
    private int prevPlayerMove = -1;

    //==========================================================================
    public XOBoard() {
        this.initBoard();
    }

    public void initBoard() {
        for (int i = 0; i < 9; i++) {
            shapeBoard[i] = Shape.EMPTY;
        }
    }

    //==========================================================================
    // Methods
    public boolean isEmpty() {
        for (int i = 0; i < 9; i++) {
            if (shapeBoard[i] != Shape.EMPTY) {
                return false;
            }
        }
        return true;
    }

    //==========================================================================
    // Getters and Setters
    public void setShape(int index, Shape shape) {
        shapeBoard[index] = shape;
        prevPlayerMove = index;
    }

    public int getPrevPlayerMove() {
        return prevPlayerMove;
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

    //==========================================================================
}
