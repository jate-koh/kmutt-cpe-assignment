package event.spawner;

import element.GameElement;

import java.awt.event.*;

public interface MouseSpawner<FrameType> {

    public void setFrame(FrameType frame);

    public void setSpawnElement(GameElement element);

    public void spawnElement() throws NullPointerException;

    public void mouseClicked(MouseEvent event) throws NullPointerException;

}
