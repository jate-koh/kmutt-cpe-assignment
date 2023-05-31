package game.control;
//==============================================================================
/* Turns
 * This enum is used to keep track of the turns of the game.
 */
//==============================================================================
public enum Turns {

    //==============================================================================
    PLAYER1, PLAYER2, COMPUTER;

    //==========================================================================
    public String toString() {
        switch (this) {
            case PLAYER1:
                return "Player 1";
            case PLAYER2:
                return "Player 2";
            case COMPUTER:
                return "Computer";
            default:
                return "Unknown";
        }
    }
    //==========================================================================
}
