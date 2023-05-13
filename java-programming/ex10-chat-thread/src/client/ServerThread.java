package client;

import utils.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {

    private Socket socket;
    private String name;
    private BufferedReader serverIn;
    private BufferedReader clientIn;
    private PrintWriter out;

    public ServerThread(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientIn = new BufferedReader(new InputStreamReader(System.in));

            // While socket is still open
            while (!socket.isClosed()) {
                if (serverIn.ready()) {
                    String message = serverIn.readLine();
                    if (message != null) {
                        Logger.logMessage("Message received: " + message, this);
                    }
                }

                if (clientIn.ready()) {
                    String message = clientIn.readLine();
                    if (message != null) {
                        out.write(name + ": " + message);
                        out.write("\n");
                        out.flush();
                        Logger.logMessage("Message sent: " + message, this);
                    }
                }
            }
        } catch (Exception err) {
            Logger.logError("Error in client thread: " + err.getMessage(), this, err);
        }
    }

}
