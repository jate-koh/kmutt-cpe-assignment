package server;

import utils.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends ChatServer implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public PrintWriter getWriter() {
        return out;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // While socket is still open
            while (!socket.isClosed()) {
                String message = in.readLine();
                if (message != null) {
                    Logger.logMessage("Message received: " + message, this);
                    // Send message to all clients
                    for (ClientThread client : getClientList()) {
                        client.getWriter().write(message);
                        client.getWriter().write("\n");
                        client.getWriter().flush();
                    }
                }
            }
        } catch (Exception err) {
            Logger.logError("Error in client thread: " + err.getMessage(), this, err);
        }
    }
}
