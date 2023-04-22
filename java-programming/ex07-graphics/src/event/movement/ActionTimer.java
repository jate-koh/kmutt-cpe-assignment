package event.movement;

import utils.Logger;

import javax.swing.*;
import java.awt.event.*;

public class ActionTimer<T> implements ActionListener {

    private T frame;
    private int delay = 50;
    private Timer timer;

    public ActionTimer(T frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent event) throws NullPointerException {
        if(this.frame == null) {
            Logger.logError("No frame set", this);
            throw new NullPointerException("Frame is null");
        }

        this.action();

        if (this.frame instanceof JFrame) {
            ((JFrame) this.frame).repaint();
        }
        else if (this.frame instanceof JPanel) {
            ((JPanel) this.frame).repaint();
        }
        else if (this.frame instanceof JInternalFrame) {
            ((JInternalFrame) this.frame).repaint();
        }
        else {
            Logger.logWarning("Frame is not a JFrame, JPanel, or JInternalFrame.", this);
        }
    }

    public void action() {

    }

    public Timer start() {
        this.timer = new Timer(this.delay, this);
        this.timer.start();
        return this.timer;
    }

    public void stop() {
        this.timer.stop();
    }

    public void setFrame(T frame) {
        this.frame = frame;
    }
}