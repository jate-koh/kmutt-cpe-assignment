import event.spawner.InternalSpawner;
import panel.*;
import java.awt.*;

public class Game {

    private OuterPanel outerPanel;
    private GamePanel gamePanel;

    public Game(String title, int width, int height) {
        // Create a OuterPanel object
        this.outerPanel = new OuterPanel(title, width, height);

        // Create a GamePanel object
        this.gamePanel = new GamePanel(10,10, width - 20, height - 20);

        // Add the GamePanel object to the OuterPanel object
        this.outerPanel.addPanel(this.gamePanel);
    }

    public void setupBackgroud(Color outerBackground, Color gameBackground) {
        // Set the background color of the OuterPanel
        if (outerBackground != null) this.outerPanel.changeColor(outerBackground);

        // Set the background color of the GamePanel
        if (gameBackground != null ) this.gamePanel.changeColor(gameBackground);
    }

    public void setupSpawner(int maxDiameter) {

        // Create a Spawner object
        InternalSpawner spawner = new InternalSpawner();
        spawner.setMaxDiameter(maxDiameter);
        spawner.setFrame(this.gamePanel);

        // Add the Spawner object to the GamePanel object
        this.gamePanel.addMouseAdapter(spawner);
    }

}
