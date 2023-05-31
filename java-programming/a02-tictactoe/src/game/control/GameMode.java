package game.control;
//==============================================================================
/* Game Mode
 * This enum is a state of all possible game modes.
 */
//==============================================================================
public enum GameMode {
    HUMAN_VS_HUMAN,
    HUMAN_VS_COMPUTER;

    //==========================================================================
    public String toString() {
        switch (this) {
            case HUMAN_VS_HUMAN:
                return "Human vs Human";
            case HUMAN_VS_COMPUTER:
                return "Human vs Computer";
            default:
                return "Unknown";
        }
    }
    //==========================================================================
}
