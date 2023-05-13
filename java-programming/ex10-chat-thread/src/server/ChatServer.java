package server;

import utils.Logger;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

    private static final int DEFAULT_SERVERPORT = 4444;
    private static ServerSocket serverSocket = null;
    private static ArrayList<ClientThread> clients = null;

    public static ArrayList<ClientThread> getClientList() {
        return clients;
    }

    private ServerSocket createServerSocket(int port) {
        if (serverSocket == null) {
            try {
                if (port == 0) port = DEFAULT_SERVERPORT;
                serverSocket = new ServerSocket(port);
                Logger.logMessage("Server socket created on port " + port, this);
                return serverSocket;
            } catch (Exception err) {
                Logger.logError("Error creating server socket: " + err.getMessage(), this, err);
            }
        }
        return null;
    }

    public void startServer(int port) {
        ServerSocket serverSocket = createServerSocket(port);
        Logger.logMessage("Listening for clients on port " + port, this);
        if (serverSocket != null) {
            acceptClients();
        }
    }

    public void stopServer() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
                serverSocket = null;
                Logger.logMessage("Server socket closed successfully", this);
            } catch (Exception err) {
                Logger.logError("Error closing server socket: " + err.getMessage(), this, err);
            }
        }
        else {
            Logger.logMessage("Server socket already closed", this);
        }
    }

    public void acceptClients() {
        clients = new ArrayList<ClientThread>();
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ClientThread client = new ClientThread(socket);
                clients.add(client);
                Thread thread = new Thread(client);
                thread.start();
                Logger.logMessage("Client accepted", this);
            } catch (Exception err) {
                Logger.logError("Error in client acceptation: " + err.getMessage(), this, err);
            }
        }
    }

}
