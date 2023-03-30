import element.*;
import event.Spawner;
import panel.*;

public class Game {

    private FramePanel framePanel;
    private GamePanel gamePanel;

    public Game() {
        // Create a FramePanel object
        this.framePanel = new FramePanel("Bouncy Ball", 500, 500);

        // Create a GamePanel object
        this.gamePanel = new GamePanel(10,10, 480, 480);

        // Add the GamePanel object to the FramePanel object
        this.framePanel.addElement(this.gamePanel.getGameFrame());

        // Run Init
        this.init();
    }

    public Game(String title, int width, int height) {
        // Create a FramePanel object
        this.framePanel = new FramePanel(title, width, height);

        // Create a GamePanel object
        this.gamePanel = new GamePanel(0,0, width, height);

        // Add the GamePanel object to the FramePanel object
        this.framePanel.addElement(this.gamePanel.getGameFrame());

        // Run Init
        this.init();
    }

    public void init() {
        // Create a Spawner object
        Spawner spawner = new Spawner(this.gamePanel.getGameFrame());
    }

    public void setFrameSize(int width, int height) {
        this.framePanel.getFrame().setSize(width, height);
    }

    public void setGameFrameSize(int width, int height) {
        this.gamePanel.getGameFrame().setSize(width, height);
    }
}
