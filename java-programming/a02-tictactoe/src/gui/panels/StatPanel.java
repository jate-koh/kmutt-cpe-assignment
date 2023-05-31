package gui.panels;

import game.XOGame;
import stat.GameStat;
import utils.Logger;

import javax.swing.*;
import java.awt.*;

public class StatPanel extends Panel {

    private JTextArea statField;
    private JScrollPane scrollPane;
    private GameStat gameStatInstance = null;

    public StatPanel() {
        super(new BorderLayout(), Color.WHITE);
    }

    @Override
    protected void initComponents() {
        this.statField = new JTextArea();
        this.statField.setEditable(false);
        this.statField.setFont(new Font("Arial", Font.PLAIN, 20));
        this.statField.setText("");

        this.scrollPane = new JScrollPane(this.statField);

        this.add(this.statField);
    }

    public void updateStatField() {
        Logger.logMessage("Updating stat field...", this);
        this.remove(this.statField);
        this.clearStatField();

        if ( XOGame.DEBUG ) Logger.logMessage(this.statMessage().toString(), this);

        this.statField.setText(this.statMessage().toString());
        Logger.logMessage("Stat message updated.", this);
        this.add(this.statField);

    }

    public void clearStatField() {
        this.statField.setText("");
    }

    private StringBuilder statMessage() {
        Logger.logMessage("Updating stat message...", this);
        StringBuilder message = new StringBuilder();
        message.append("\n======== PLAYER vs PLAYER ========\n");
        message.append("Total 2-Players Games: ").append(this.gameStatInstance.getTotalPlayersGames()).append("\n");

        message.append("\nPlayer 1's Stat:\n");
        message.append("Win: ").append(this.gameStatInstance.getPlayer1Wins()).append("\n");
        message.append("Lose: ").append(this.gameStatInstance.getPlayer1Losses()).append("\n");

        message.append("\nPlayer 2's Stat:\n");
        message.append("Win: ").append(this.gameStatInstance.getPlayer2Wins()).append("\n");
        message.append("Lose: ").append(this.gameStatInstance.getPlayer2Losses()).append("\n");

        message.append("\n======== PLAYER vs COMPUTER ========\n");
        message.append("Total AI Games: ").append(this.gameStatInstance.getTotalComputerGames()).append("\n");

        message.append("\nHuman's Stat:\n");
        message.append("Win: ").append(this.gameStatInstance.getHumanWins()).append("\n");
        message.append("Lose: ").append(this.gameStatInstance.getHumanLosses()).append("\n");

        message.append("\nComputer's Stat:\n");
        message.append("Win: ").append(this.gameStatInstance.getComputerWins()).append("\n");
        message.append("Lose: ").append(this.gameStatInstance.getComputerLosses()).append("\n");

        message.append("\nTotal Games: ").append(this.gameStatInstance.getTotalGames()).append("\n");
        message.append("Total Completed Games: ").append(this.gameStatInstance.getTotalCompletedGames()).append("\n");
        message.append("Total Incomplete Games: ").append(this.gameStatInstance.getTotalIncompleteGames()).append("\n");
        message.append("Total Draw: ").append(this.gameStatInstance.getDraws()).append("\n");
        message.append("Total Board Reset: ").append(this.gameStatInstance.getTotalBoardResets()).append("\n");

        return message;
    }

    // Getters and Setters
    public void setGameStatInstance(GameStat gameStatInstance) {
        this.gameStatInstance = gameStatInstance;
        this.updateStatField();
    }
}
