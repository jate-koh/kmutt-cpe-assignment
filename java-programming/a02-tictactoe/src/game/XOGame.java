package game;

import game.control.*;
import stat.GameStat;
import utils.Logger;

import gui.*;
import java.util.Random;

public class XOGame {

    public static XOGame createGame() {
        if (XO_instance == null) {
            Logger.logMessage("Creating game instance...", XOGame.class, true);
            XO_instance = new XOGame();
        }
        return XO_instance;
    }

    // Singleton instance
    private static XOGame XO_instance = null;

    // Game Components
    private GameMode gameMode = GameMode.HUMAN_VS_HUMAN;
    private GameStat gameStat;
    private XOBoard board;
    private Turns turns = null;
    private int turn = 0;

    // GUI Components
    private MainFrame mainFrame = null;
    private StatFrame statFrame = null;

    private XOGame() {
        Logger.logMessage("Initializing game...", this, true);
        this.board = new XOBoard();
        this.gameStat = new GameStat();
    }

    // Game Controls
    public void startGame() {
        Logger.logMessage("Starting game...", this, true);

        // Set Initial GameMode to HUMAN_VS_HUMAN
        this.gameMode = GameMode.HUMAN_VS_HUMAN;

        // Initialize the GUI
        this.mainFrame = new MainFrame("Tic Tac Toe", 800, 600);
        this.statFrame = new StatFrame(this.gameStat);

        // Set the game instance for the GUI
        Logger.logMessage("Setting game instance for Game Frame...", this, true);
        this.mainFrame.setGameInstance(this);

        // Set the game stat instance for the GUI
        Logger.logMessage("Setting game stat instance for Stat Frame...", this, true);
        this.statFrame.setGameStat(this.gameStat);

        // Randomize the first turn
        this.randomTurn();
    }

    public void resetGame() {
        Logger.logMessage("Resetting game...", this, true);

        // Reset the board
        this.board.initBoard();

        // Reset the turns and randomize the first turn
        this.turns = null;
        this.turn = 0;
        this.randomTurn();
    }

    public void clearBoard() {
        Logger.logMessage("Clearing board...", this, true);
        this.gameStat.incrementBoardResets();
        this.board.initBoard();
    }

    // Turn Controls
    public void nextTurn() {
        Logger.logMessage("Processing next turn...", this, true);
        Logger.logMessage("Checking conditions...", this, true);

        // Check if the game is done, result will be null if the game is not done
        GameResult result = this.checkConditions();

        // If the game is not done, proceed to the next turn
        if (result != null) {
            Logger.logMessage("Game is done!", this, true);
            this.gameStat.incrementTotalCompletedGames();
            StringBuilder message = new StringBuilder("Result: ");
            switch (result) {
                case P1WIN:
                    message.append("Player 1 wins!");
                    this.gameStat.incrementPlayer1Wins();
                    break;
                case P2WIN:
                    message.append("Player 2 wins!");
                    this.gameStat.incrementPlayer2Wins();
                    break;
                case WIN:
                    message.append("You win!");
                    this.gameStat.incrementHumanWins();
                    break;
                case LOSE:
                    message.append("You lose!");
                    this.gameStat.incrementComputerWins();
                    break;
                case DRAW:
                    message.append("It's a draw!");
                    this.gameStat.incrementDraws();
                    break;
            }
            Logger.logMessage(message.toString(), this, true);
            this.statFrame.getStatPanel().updateStatField();
            return;
        }

        Logger.logMessage("Turns: " + this.turns.toString(), this, true);
        this.turn++;
        this.changeTurn();
    }


    private Turns randomTurn() {
        Random random = new Random();
        int randomTurn = random.nextInt(2);
        // For Computer vs Human, randomize the first turn
        if (this.gameMode == GameMode.HUMAN_VS_COMPUTER) {
            switch (randomTurn) {
                case 0:
                    this.turns = Turns.PLAYER1;
                    break;
                case 1:
                    this.turns = Turns.COMPUTER;
                    break;
            }
        // For Human vs Human, randomize the first turn
        } else if (this.gameMode == GameMode.HUMAN_VS_HUMAN) {
            switch (randomTurn) {
                case 0:
                    this.turns = Turns.PLAYER1;
                    break;
                case 1:
                    this.turns = Turns.PLAYER2;
                    break;
            }
        }
        Logger.logMessage(this.turns.toString() + " will go first!", this, true);
        return this.turns;
    }

    private Turns changeTurn() {
        if ( this.gameMode == GameMode.HUMAN_VS_COMPUTER) {
            if (this.turns == Turns.PLAYER1) {
                this.turns = Turns.COMPUTER;
            } else if (this.turns == Turns.COMPUTER) {
                this.turns = Turns.PLAYER1;
            }
        } else if (this.gameMode == GameMode.HUMAN_VS_HUMAN) {
            if (this.turns == Turns.PLAYER1) {
                this.turns = Turns.PLAYER2;
            } else if (this.turns == Turns.PLAYER2) {
                this.turns = Turns.PLAYER1;
            }
        }
        return this.turns;
    }

    // Win-Lose-Draw Controls
    public GameResult checkConditions() {
        Shape[] shapeBoard = this.board.getShapeBoard();
        if (GameRule.isWin(shapeBoard, Shape.X)) {
            if (this.gameMode == GameMode.HUMAN_VS_COMPUTER) {
                if (this.turns == Turns.PLAYER1) {
                    return GameResult.WIN;
                } else if (this.turns == Turns.COMPUTER) {
                    return GameResult.LOSE;
                }
            } else if (this.gameMode == GameMode.HUMAN_VS_HUMAN) {
                if (this.turns == Turns.PLAYER1) {
                    return GameResult.P1WIN;
                } else if (this.turns == Turns.PLAYER2) {
                    return GameResult.P2WIN;
                }
            }
        }
        if (GameRule.isWin(shapeBoard, Shape.O)) {
            if (this.gameMode == GameMode.HUMAN_VS_COMPUTER) {
                if (this.turns == Turns.PLAYER1) {
                    this.gameStat.incrementPlayer1Wins();
                    return GameResult.WIN;
                } else if (this.turns == Turns.COMPUTER) {
                    this.gameStat.incrementComputerWins();
                    return GameResult.LOSE;
                }
            } else if (this.gameMode == GameMode.HUMAN_VS_HUMAN) {
                if (this.turns == Turns.PLAYER1) {
                    this.gameStat.incrementPlayer1Wins();
                    return GameResult.P1WIN;
                } else if (this.turns == Turns.PLAYER2) {
                    this.gameStat.incrementPlayer2Wins();
                    return GameResult.P2WIN;
                }
            }
        }
        if (GameRule.isDraw(shapeBoard)) {
            this.gameStat.incrementDraws();
            return GameResult.DRAW;
        }
        return null;
    }

    // Getters and Setters
    public XOBoard getBoard() {
        return board;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
        this.resetGame();
    }

    public int getTurnNumber() {
        return this.turn;
    }

    public Turns getTurns() {
        return turns;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

}
