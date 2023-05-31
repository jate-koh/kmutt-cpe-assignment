package networking;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class FishTankServer extends Thread {
    //=================================================================================
    // Static Constants
    private static final int DEFAULT_SERVERPORT = 3000;


    //=================================================================================
    private final ServerSocket serverSocket; // Server socket
    private final ArrayList<ClientHandler> clientHandlers = new ArrayList<>(); // List of client handlers threads

    //=================================================================================
    // Constructors
    public FishTankServer() throws IOException {
        this(DEFAULT_SERVERPORT);
    }

    public FishTankServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }
    //=================================================================================
    // Running Thread Method
    @Override
    public void run() {
        try {
            while (true) {
                // Accept client connection
                Socket clientSocket = serverSocket.accept();

                // Create client handler thread
                ClientHandler clientHandler = new ClientHandler(clientHandlers, clientSocket);

                // Start client handler thread
                clientHandler.start();

                // Add client handler to list
                clientHandlers.add(clientHandler);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //=================================================================================
}
