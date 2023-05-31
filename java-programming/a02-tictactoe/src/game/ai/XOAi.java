package game.ai;
//==============================================================================
/* XOAi
 * This class is used to control the AI of the game.
 * It processes the AI's turn in HUMAN vs COMPUTER mode.
 */
//==============================================================================
import game.XOGame;
import game.control.Shape;
import game.control.Turns;
import game.control.XOBoard;
import gui.panels.GamePanel;
import utils.Logger;

public class XOAi {

    //==========================================================================
    // Debug Variables
    public static boolean AI_DEBUG = false; // Set to true to enable debug messages

    //==========================================================================
    // Components
    private XOBoard board;
    private GamePanel gamePanelInstance;
    private XOGame gameInstance;
    //==========================================================================
    // AI Variables
    private Shape shape;
    private Turns currentTurn;
    private int prevPlayerMove;

    //==========================================================================
    public XOAi(GamePanel gamePanel, XOGame gameInstance) {
        Logger.logMessage("Initializing AI...", this);
        this.gameInstance = gameInstance;
        this.gamePanelInstance = gamePanel;
        this.board = this.gameInstance.getBoard();
    }

    //==========================================================================
    // Turn Processing
    public void computerProcessTurn() {
        // Do Computer's move
        if ( currentTurn == Turns.COMPUTER ) {

            // If the board is empty (first move)
            if ( board.isEmpty() ) {
                // Randomize the first move
                if ( XOGame.DEBUG || AI_DEBUG) Logger.logMessage("DEBUG: First move. Randomizing Move", this);
                randomMove();
                gameInstance.nextTurn();
                return;
            }

            // Get Player's previous move
            this.prevPlayerMove = board.getPrevPlayerMove();

            // If the player's previous move is in the center
            if ( this.prevPlayerMove == 4 ) {
                // Randomize the move
                if ( XOGame.DEBUG || AI_DEBUG) Logger.logMessage("DEBUG: Player's previous move is in the center. Randomizing Move", this);
                randomMove();
            }
            // If the player's previous move is in the corner
            else if ( this.prevPlayerMove == 0 || this.prevPlayerMove == 2 || this.prevPlayerMove == 6 || this.prevPlayerMove == 8 ) {
                // Randomize the move
                if ( XOGame.DEBUG || AI_DEBUG ) Logger.logMessage("DEBUG: Player's previous move is in the corner. Randomizing Move", this);
                randomMove();
            }
            // If the player's previous move is in the side
            else if ( this.prevPlayerMove == 1 || this.prevPlayerMove == 3 || this.prevPlayerMove == 5 || this.prevPlayerMove == 7 ) {
                // Randomize the move
                if ( XOGame.DEBUG || AI_DEBUG ) Logger.logMessage("DEBUG: Player's previous move is in the side. Randomizing Move", this);
                randomMove();
            }

            gameInstance.nextTurn();
        }
    }

    private void randomMove() {
        int randomIndex = (int) (Math.random() * 9);
        // Check if the button is vacant
        while ( !buttonIsVacant(randomIndex) ) {
            randomIndex = (int) (Math.random() * 9);
        }
        pressButton(randomIndex);
    }

    private boolean buttonIsVacant(int index) {
        return board.getShape(index) == Shape.EMPTY;
    }

    private void pressButton(int index) {
        board.setShape(index, this.shape);
        if (this.shape == Shape.X) {
            gamePanelInstance.setButtonX(index);
        }
        else {
            gamePanelInstance.setButtonO(index);
        }
    }

    public void reset() {
        this.shape = null;
        this.currentTurn = null;
        this.prevPlayerMove = -1;
    }

    //==========================================================================
    // Getter and Setters
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setCurrentTurn(Turns currentTurn) {
        this.currentTurn = currentTurn;
    }
    //==========================================================================

}
