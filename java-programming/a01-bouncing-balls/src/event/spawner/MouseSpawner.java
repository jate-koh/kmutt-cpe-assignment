package event.spawner;

import element.*;

import java.awt.event.MouseEvent;

public interface MouseSpawner<FrameType> {

    public void setFrame(FrameType frame);

    public void spawnElement(int x, int y);

    public void mouseClicked(MouseEvent event);

}
