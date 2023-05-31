package stat;
//==============================================================================
/* Game Stat
 * This class is used to keep data on the game statistics.
 * Every time a game is completed, data can be update here.
 */
//==============================================================================
public class GameStat {

    //==========================================================================
    // General Data Members
    private int totalCompletedGames;
    private int totalBoardResets;
    private int totalGames;
    private int totalPlayersGames;
    private int player1Wins;
    private int player2Wins;

    //==========================================================================
    // Human vs Computer Data Members
    private int totalComputerGames;
    private int humanWins;
    private int computerWins;
    private int draws;

    //==========================================================================

    public GameStat() {
        this.reset();
    }

    //==========================================================================
    // Reset
    public void reset() {
        this.totalCompletedGames = 0;
        this.totalGames = 0;
        this.totalComputerGames = 0;
        this.totalPlayersGames = 0;
        this.totalBoardResets = 0;
        this.player1Wins = 0;
        this.player2Wins = 0;
        this.humanWins = 0;
        this.computerWins = 0;
        this.draws = 0;
    }

    //==========================================================================
    // Increase Total Games
    public void incrementTotalCompletedGames() {
        this.totalCompletedGames++;
    }
    public void incrementTotalGames() {
        this.totalGames++;
    }
    public void incrementTotalPlayersGames() {
        this.totalPlayersGames++;
    }
    public void incrementTotalComputerGames() {
        this.totalComputerGames++;
    }
    public void incrementBoardResets() {
        this.totalBoardResets++;
    }

    //==========================================================================
    // Increase Wins, Losses, and Draws
    public void incrementPlayer1Wins() {
        this.player1Wins++;
    }

    public void incrementPlayer2Wins() {
        this.player2Wins++;
    }
    public void incrementHumanWins() {
        this.humanWins++;
    }
    public void incrementComputerWins() {
        this.computerWins++;
    }

    public void incrementDraws() {
        this.draws++;
    }
    //==========================================================================
    // Getters
    // Total Games
    public int getTotalPlayersGames() {
        return totalPlayersGames;
    }
    public int getTotalComputerGames() {
        return totalComputerGames;
    }
    public int getTotalGames() {
        this.totalGames = this.totalPlayersGames + this.totalComputerGames;
        return totalGames;
    }

    // Individual Wins, Losses, and Draws
    // Human vs Human
    public int getPlayer1Wins() {
        return this.player1Wins;
    }
    public int getPlayer2Wins() {
        return this.player2Wins;
    }
    public int getPlayer1Losses() {
        return this.getTotalPlayersGames() - this.player1Wins;
    }
    public int getPlayer2Losses() {
        return this.getTotalPlayersGames() - this.player2Wins;
    }
    public int getHumanWins() {
        return humanWins;
    }
    // Human vs Computer
    public int getComputerWins() {
        return computerWins;
    }
    public int getHumanLosses() {
        return this.getTotalComputerGames() - this.humanWins;
    }
    public int getComputerLosses() {
        return this.getTotalComputerGames() - this.computerWins;
    }

    public int getDraws() {
        return this.draws;
    }

    // Total Completion and Resets
    public int getTotalBoardResets() {
        return this.totalBoardResets;
    }

    public int getTotalCompletedGames() {
        return this.totalCompletedGames;
    }

    public int getTotalIncompleteGames() {
        return this.totalGames - this.totalCompletedGames;
    }
    //==========================================================================
}
