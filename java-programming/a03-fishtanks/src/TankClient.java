//=================================================================================
/* This is a main class that runs the client side of the program.
 * If no server is running, it will throw an exception and exit.
 */
//=================================================================================
import main.FishTankPanel;
import utils.Logger;

import javax.swing.*;

public class TankClient {

    //=================================================================================
    // Static Constants
    private final static int FRAME_WIDTH = 800;
    private final static int FRAME_HEIGHT = 600;


    //=================================================================================
    // Main Method
    public static void main(String[] args) {
        // Create New Frame
        JFrame frame = new JFrame("Tank Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        // Create Fish Tank Panel + Add to Frame
        try {
            FishTankPanel fishTankPanel = new FishTankPanel();
            frame.add(fishTankPanel);
        } catch (Exception e) {
            Logger.logError("Error upon creating fish tank panel", "TankClient", e);
            throw new RuntimeException(e);
        }

        // Run Client Instance
        frame.pack();
        frame.setVisible(true);
    }
    //=================================================================================
}
