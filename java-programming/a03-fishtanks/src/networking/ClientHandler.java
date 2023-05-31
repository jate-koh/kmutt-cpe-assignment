package networking;
//=================================================================================
/* Client Handler
 * This class is used to handle the client connection to the server. It is used to
 * receive and send packets between the server and the client.
 * It handles the collision of the object on the boundary of window
 *
 * When it collides, it will send a message to the server to remove the object and
 * spawn a new object in the other window.
 */
//=================================================================================
import main.FishTankPanel;
import element.Element;
import networking.message.*;
import utils.Logger;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {

    //=================================================================================
    // Client Handler Members
    private final ArrayList<ClientHandler> clients;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;

    //=================================================================================
    // Constructors
    public ClientHandler(ArrayList<ClientHandler> clients, Socket socket) throws IOException {
        this.clients = clients;

        // Create input and output streams from given socket
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    //=================================================================================
    // Running Thread Method
    @Override
    public void run() {
        while (true) {
            try {
                // Collider
                Collider collider = (Collider) inputStream.readObject();
                Element element = collider.getElement();
                Logger.logMessage("Collider received: " + collider.getElement().toString(), this);

                // If client list is empty, continue
                if (clients.isEmpty()) continue;

                // Get index of this client
                int clientIndex = clients.indexOf(this);

                // Check if element collides at what bound
                // If element collides left bound
                if (element.getX() < 0) {
                    Logger.logMessage("Element collides left bound", this);
                    // If this client is the first client, continue
                    if (clients.get(0) == this) {
                        Logger.logMessage("This client is the first client. Continuing...", this);
                        continue;
                    }

                    // Send remove element packet to client
                    outputStream.writeObject(new Remover(element.getId()));
                    outputStream.reset();

                    // Set element x to right bound
                    element.setX(FishTankPanel.PANEL_WIDTH - element.getSize());
                    Logger.logMessage("Element x set to: " + element.getX(), this);

                    // Get output stream of client before this client
                    ObjectOutputStream clientOut = clients.get(clientIndex - 1).outputStream;

                    // Send spawn element packet to client before this client
                    clientOut.writeObject(new Spawner(element));
                    clientOut.reset();

                }

                // If element collides right bound
                if ((element.getX() + element.getSize()) > FishTankPanel.PANEL_WIDTH) {
                    Logger.logMessage("Element collides right bound", this);
                    // If this client is the last client, continue
                    if (clients.get(clients.size() - 1) == this) {
                        Logger.logMessage("This client is the last client. Continuing...", this);
                        continue;
                    }

                    // Send remove element packet to client
                    outputStream.writeObject(new Remover(element.getId()));
                    outputStream.reset();

                    // Get output stream of client after this client
                    int nextClientIndex = clientIndex + 1;
                    ObjectOutputStream clientOut = clients.get(clientIndex + 1).outputStream;

                    // Set element x to left bound
                    element.setX(0);
                    Logger.logMessage("Element x set to: " + element.getX(), this);

                    // Send spawn element packet to client after this client
                    clientOut.writeObject(new Spawner(element));
                    clientOut.reset();
                }


            } catch (IOException | ClassNotFoundException e) {
                Logger.logError("Error in ClientHandler: " + e.getMessage(), this, e);
            }
        }
    }

    //=================================================================================
}
