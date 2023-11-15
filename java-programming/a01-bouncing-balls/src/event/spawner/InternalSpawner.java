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
    private int maxDiameter = 0;
    private int minDiameter = 0;
    private int maxVelocity = 0;

    public InternalSpawner() {
        this.frame = null;
        this.element = null;
    }

    public void setFrame(GamePanel frame) {
        this.frame = frame;
    }

    public void setMinDiameter(int minDiameter) {
        this.minDiameter = minDiameter;
    }

    public void setMaxDiameter(int maxDiameter) {
        this.maxDiameter = maxDiameter;
    }

    public void setMaxVelocity(int maxVelocity) {
        this.maxVelocity = maxVelocity;
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
        if (this.maxDiameter == 0 || this.maxVelocity == 0) {
            Logger.logError(
                    "Max diameter or max velocity is 0, please setMaxDiameter() and setMaxVelocity() before spawning elements",
                    this,
                    new NullPointerException("Max diameter or max velocity is 0")
            );
            return;
        }
        this.frame.addElement(Circle.createCircle(x, y, this.maxDiameter, this.maxVelocity));
        this.frame.getElements().logElements();
    }

    public void destroyElement() {
        if (this.frame.getElements().getList().size() == 0) {
            Logger.logError(
                    "No elements to destroy",
                    this,
                    new NullPointerException("No elements to destroy")
            );
            return;
        }

        Logger.logMessage(
                "Destroying element: { Name: " +
                        this.frame.getElements().get(0).getClass().getSimpleName() +
                        "Color: R " + this.frame.getElements().get(0).getColor().getRed() +
                        " G " + this.frame.getElements().get(0).getColor().getGreen() +
                        " B " + this.frame.getElements().get(0).getColor().getBlue() + " }",
                this
        );
        this.frame.getElements().removeElement(this.frame.getElements().get(0));
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getButton() == MouseEvent.BUTTON1) {
            Logger.logMessage("Mouse LEFT clicked at " + event.getX() + ", " + event.getY(), this);
            this.spawnElement(event.getX(), event.getY());
        }
        if (event.getButton() == MouseEvent.BUTTON3) {
            Logger.logMessage("Mouse RIGHT clicked at " + event.getX() + ", " + event.getY(), this);
            this.destroyElement();
        }
    }
}
