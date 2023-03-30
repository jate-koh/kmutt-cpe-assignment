package event;

import element.Circle;
import element.GameElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Spawner extends MouseAdapter {

    private JInternalFrame frame;
    private JPanel panel;
    private GameElement element;

    public Spawner() {
        this.frame = null;
        this.panel = null;
    }

    public Spawner(JInternalFrame frame) {
        this(frame, new Circle(Color.WHITE));
    }

    public Spawner(JPanel panel) {
        this(panel, new Circle(Color.WHITE));
    }

    public Spawner(JInternalFrame frame, GameElement element) {
        this.frame = frame;
        this.frame.addMouseListener(this);
        this.element = element;
        this.frame.getContentPane().addMouseListener(this);
    }

    public Spawner(JPanel panel, GameElement element) {
        this.panel = panel;
        this.panel.addMouseListener(this);
        this.element = element;
        this.frame.getContentPane().addMouseListener(this);
    }

    public void setSpawnElement(GameElement element) {
        this.element = element;
    }

    public void spawnElement() {
        if (this.frame != null) {
            this.frame.add(this.element);
        } else if (this.panel != null) {
            this.panel.add(this.element);
        }
    }

    public void mouseClicked(MouseEvent event) {
        if (this.frame != null) {
            System.out.println("Frame: " + event.getX() + ", " + event.getY());
        } else if (this.panel != null) {
            System.out.println("Panel: " + event.getX() + ", " + event.getY());
        }
    }

}
