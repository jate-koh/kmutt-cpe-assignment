package element;

import java.awt.*;

public interface GameElement {

    public void setLocation(int x, int y);

    public void setSize(int width, int height);

    public int getWidth();

    public int getHeight();

    public int getX();

    public int getY();

    public Graphics getGraphics();

    public void setBounds(int x, int y, int width, int height);

    public void paintComponent(Graphics graphics);

    public GameElement cloneElement();
}
