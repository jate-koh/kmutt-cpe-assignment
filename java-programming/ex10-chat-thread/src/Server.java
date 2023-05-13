import server.ChatServer;

public class Server {

    private static final int PORT = 4444;

    public static void main(String[] args) {
        // Run and Start Server
        ChatServer server = new ChatServer();
        server.startServer(PORT);
    }
}