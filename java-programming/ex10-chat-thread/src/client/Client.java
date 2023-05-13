package client;

import utils.Common;
import utils.Logger;

import java.net.Socket;

public class Client {

    private static final int DEFAULT_CLIENTPORT = 4444;
    private String name;
    private int port;
    private Socket socket = null;

    public Client() {
        this("Anonymous " + Common.generateUid(), DEFAULT_CLIENTPORT);
    }

    public Client(int port) {
        this("Anonymous " + Common.generateUid(), port);
    }

    public Client(String name, int port) {
        this.name = name;
        this.port = port;
    }

    public void login() {
        if (socket == null) {
            try {
                socket = new Socket("localhost", port);
                Thread.sleep(500);
                Thread server = new Thread(new ServerThread(socket, name));
                Logger.logMessage("Client socket created on port " + port, this);
                Logger.logMessage("Client logged in as " + name, this);
                server.start();
            } catch (Exception err) {
                Logger.logError("Error creating client socket: " + err.getMessage(), this, err);
            }
        }
        else {
            Logger.logMessage("Client already logged in", this);
        }
    }

    public void logout() {
        if (socket != null) {
            try {
                socket.close();
                socket = null;
            } catch (Exception err) {
                Logger.logError("Error closing client socket: " + err.getMessage(), this, err);
            }
        }
        else {
            Logger.logMessage("Client already logged out", this);
        }
    }

    public String getName() {
        return name;
    }

}
