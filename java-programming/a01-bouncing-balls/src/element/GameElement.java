package element;

import javax.swing.*;
import java.awt.*;

public interface GameElement<T> {

    public void paintComponent(Graphics graphics);

    public void setX(int x);

    public void setY(int y);

    public int getX();

    public int getY();

    public int getWidth();

    public int getHeight();

    public Color getColor();

    public void setSize(int width, int height);

    public void setColor(Color color);

    public void setLocation(int x, int y);

}
