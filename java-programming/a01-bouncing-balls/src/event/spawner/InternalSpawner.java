package event.spawner;

import element.Circle;
import element.GameElement;
import panel.GamePanel;
import utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InternalSpawner extends MouseAdapter implements MouseSpawner<GamePanel> {

    private GamePanel frame;
    private GameElement element;
    private int maxDiameter;

    public InternalSpawner() {
        this.frame = null;
        this.element = null;
    }

    public void setFrame(GamePanel frame) {
        this.frame = frame;
    }

    public void setMaxDiameter(int maxDiameter) {
        this.maxDiameter = maxDiameter;
    }

    @Override
    public void spawnElement(int x, int y) {
        if (this.frame == null) {
            Logger.logError(
                    "Frame is null, please setFrame() before spawning elements",
                    this,
                    new NullPointerException("Frame is null"));
            return;
        }
        Logger.logMessage("Spawning element at " + x + ", " + y, this);
        this.frame.addElement(Circle.createCircle(x, y, this.maxDiameter));
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        Logger.logMessage("Mouse clicked at " + event.getX() + ", " + event.getY(), this);
        this.spawnElement(event.getX(), event.getY());
    }
}
