//=================================================================================
/* This is the main class of the server. It will start the server and listen for
 * incoming connections. It will also handle the messages that are sent from the
 * clients.
 */
//=================================================================================
import networking.FishTankServer;
import utils.Logger;

public class TankServer {
    //=================================================================================
    // Main Method
    public static void main(String[] args) {
        // Run Server Instance
        try {
            FishTankServer server = new FishTankServer();
            server.start();
        } catch (Exception e) {
            Logger.logError("Error upon creating fish tank server", TankServer.class, e);
            throw new RuntimeException(e);
        }
        Logger.logMessage("Server started", "TankServer");
    }
    //=================================================================================

}