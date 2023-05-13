import client.Client;

public class ClientTwo {

    private static final int PORT = 4444;

    public static void main(String[] args) {
        // Run and Start Client
        Client client = new Client("Mememo" ,PORT);
        client.login();
    }

}
