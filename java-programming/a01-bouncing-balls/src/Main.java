import java.awt.*;

public class Main {

    // Frame properties
    private static final String TITLE = "Bouncy Balls";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    // Frame Color
    private static final Color OUTER_BACKGROUND = Color.BLACK;
    private static final Color GAME_BACKGROUND = Color.WHITE;

    // Element Spawn Properties
    public static final int MAX_DIAMETER = 50;
    public static final int MIN_DIAMETER = 20;
    public static final int MAX_SPEED = 5;

    public static void main(String[] args) {
        // Instantiate a Game object
        Game game = new Game(TITLE, WIDTH, HEIGHT);

        // Setup Game
        game.setupBackgroud(OUTER_BACKGROUND, GAME_BACKGROUND);
        game.setupSpawner(MAX_DIAMETER, MIN_DIAMETER, MAX_SPEED);
    }
}