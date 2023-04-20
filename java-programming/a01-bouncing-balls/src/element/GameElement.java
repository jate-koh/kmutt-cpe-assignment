package element;

import javax.swing.*;
import java.awt.*;

public interface GameElement<T> {

    public T cloneElement();

    public T getGameElement();

    public void paintComponent(Graphics graphics);

    public int getX();

    public int getY();

    public Color getColor();

    public void setSize(int width, int height);

    public void setColor(Color color);

    public void setLocation(int x, int y);

}
