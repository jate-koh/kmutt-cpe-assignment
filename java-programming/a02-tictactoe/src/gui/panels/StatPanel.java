package gui.panels;

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

        Logger.logMessage(this.statMessage().toString(), this);

        this.statField.setText(this.statMessage().toString());
        Logger.logMessage("Stat message updated.", this);
        this.add(this.statField);

    }

    public void clearStatField() {
        this.statField.setText("");
    }

    private StringBuilder statMessage() {
        Logger.logMessage("Updating stat message...", this);
        StringBuilder message = new StringBuilder("Player 1 Stat:\n");
        message.append("Win: ").append(this.gameStatInstance.getPlayer1Wins()).append("\n");
        message.append("Lose: ").append(this.gameStatInstance.getPlayer1Losses()).append("\n");

        message.append("\nPlayer 2 Stat:\n");
        message.append("Win: ").append(this.gameStatInstance.getPlayer2Wins()).append("\n");
        message.append("Lose: ").append(this.gameStatInstance.getPlayer2Losses()).append("\n");

        message.append("\nTotal Games: ").append(this.gameStatInstance.getTotalGames()).append("\n");
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
