package gui;

import gui.panels.StatPanel;
import stat.GameStat;
import utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatFrame extends Frame implements ActionListener {

    private StatPanel statPanel;
    private GameStat gameStat;
    private JButton clearStat;

    public StatFrame(GameStat gameStat) {
        this("Statistics", 600, 900, gameStat);
    }

    public StatFrame(String title, GameStat gameStat) {
        this(title, 600, 900, gameStat);
    }

    public StatFrame(String title, int width, int height, GameStat gameStat) {
        super(new BorderLayout(), Color.WHITE);
        this.gameStat = gameStat;
        this.setTitle(title);
        this.setBounds(0, 0, width, height);
        this.setVisible(true);
    }

    public void setGameStat(GameStat gameStat) {
        this.statPanel.setGameStatInstance(gameStat);
        this.gameStat = gameStat;
    }

    public void view() {
        this.setVisible(true);
    }

    @Override
    protected void initComponents() {
        this.statPanel = new StatPanel();
        this.clearStat = new JButton("Clear Stat");

        this.add(this.clearStat, BorderLayout.NORTH);
        this.add(this.statPanel, BorderLayout.CENTER);
    }

    @Override
    protected void initListeners() {
        this.clearStat.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.clearStat) {
            Logger.logMessage("Clearing game stat...", this);
            this.gameStat.reset();
            this.statPanel.updateStatField();
        }
    }

    // Getters and Setters
    public StatPanel getStatPanel() {
        return statPanel;
    }
}
