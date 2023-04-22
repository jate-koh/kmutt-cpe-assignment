package panel;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

public interface Panel<FrameType> {

    public void setCoordinates(int x, int y);

    public void setFrameSize(int width, int height);

    public void addElement(FrameType element);

    public Container getContentPane();

    public FrameType getFrame();

    public void addMouseAdapter(MouseAdapter listener);

    public void addKeyListener(KeyListener listener);

}
