package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel {
    private JInternalFrame gameFrame;
    public GamePanel() {
        this(0, 0, 200, 200);
    }

    public GamePanel(int x, int y, int width, int height) {
        this.gameFrame = new JInternalFrame();
        this.gameFrame.setResizable(false);
        this.gameFrame.setBackground(Color.BLACK);
        this.gameFrame.setVisible(true);
        this.gameFrame.setBounds(x, y, width, height);
    }

    public void addElement(JPanel element) {
        this.gameFrame.getContentPane().add(element);
    }

    public JInternalFrame getGameFrame() {
        return this.gameFrame;
    }

    public void addMouseAdapter(MouseAdapter listener) {
        this.gameFrame.getContentPane().addMouseListener(listener);
    }

    public void addKeyListener(KeyListener listener) {
        this.gameFrame.addKeyListener(listener);
    }



}
