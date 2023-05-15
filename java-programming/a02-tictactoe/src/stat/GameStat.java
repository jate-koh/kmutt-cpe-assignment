package stat;

public class GameStat {

    private int totalCompletedGames;
    private int totalBoardResets;
    private int totalGames;
    private int player1Wins;
    private int player2Wins;

    private int humanWins;
    private int computerWins;
    private int draws;

    public GameStat() {
        this.reset();
    }

    public void reset() {
        this.totalCompletedGames = 0;
        this.totalBoardResets = 0;
        this.player1Wins = 0;
        this.player2Wins = 0;
        this.humanWins = 0;
        this.computerWins = 0;
        this.draws = 0;
    }

    public void incrementTotalCompletedGames() {
        this.totalCompletedGames++;
    }

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

    public void incrementBoardResets() {
        this.totalBoardResets++;
    }

    public int getPlayer1Wins() {
        return player1Wins;
    }

    public int getPlayer2Wins() {
        return player2Wins;
    }

    public int getHumanWins() {
        return humanWins;
    }

    public int getComputerWins() {
        return computerWins;
    }

    public int getDraws() {
        return draws;
    }

    public int getTotalBoardResets() {
        return totalBoardResets;
    }

    public int getTotalCompletedGames() {
        return totalCompletedGames;
    }

    public int getTotalIncompleteGames() {
        return this.totalGames - this.totalCompletedGames;
    }

    public int getTotalGames() {
        this.totalGames = this.player1Wins + this.player2Wins + this.humanWins +
                this.computerWins + this.draws;
        return totalGames;
    }

    public int getPlayer1Losses() {
        return this.getTotalGames() - this.player1Wins - this.draws;
    }

    public int getPlayer2Losses() {
        return this.getTotalGames() - this.player2Wins - this.draws;
    }

    public int getHumanLosses() {
        return this.getTotalGames() - this.humanWins - this.draws;
    }

    public int getComputerLosses() {
        return this.getTotalGames() - this.computerWins - this.draws;
    }
}
