package main;
//==============================================================================
/* Fish Tank Panel
 * This is core and main class of this project.
 * It is used to create the panel and the game loop.
 * It's also listening to for action and handle them.
 */
//==============================================================================
import element.*;
import networking.message.*;
import utils.Logger;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class FishTankPanel extends JPanel implements ActionListener {

    //==========================================================================
    // Static Attributes
    private static final int DEFAULT_CLIENTPORT = 3000;
    public static int PANEL_WIDTH = 800;
    public static int PANEL_HEIGHT = 600;

    //==========================================================================
    // Components
    private final Socket clientSocket;
    private final ObjectOutputStream outputStream;
    private final Map<String, Element> elements = new HashMap<>();

    //==========================================================================
    // Constructors
    public FishTankPanel() throws IOException {
        this(DEFAULT_CLIENTPORT);
    }

    public FishTankPanel(int port) throws IOException {
        // Connect to server
        clientSocket = new Socket("127.0.0.1", 3000);

        // Create output stream
        outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

        // Create reader thread
        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());

                    // Read message from server
                    while (true) handleMessage(inputStream.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    Logger.logError("Error reading message from server", this, e);
                    throw new RuntimeException(e);
                }
            }
        });
        reader.start();

        // Initialize panel
        initComponents();
        initListeners();

        // Start Timer
        Timer timer = new Timer(1/2, this);
        timer.start();

    }
    //==========================================================================
    // Initializer
    private void initComponents() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setVisible(true);
    }

    private void initListeners() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                handleClick(e);
            }
        });
    }

    //==========================================================================
    // Creator Methods
    public void createElement(int coordX, int coordY) {
        // Randomize Element Size
        Random rand = new Random();
        int size = rand.nextInt(20,50);

        // Make sure is spawn at the center of the mouse click
        int x = coordX - size/2;
        int y = coordY - size/2;

        // Randomize Element Velocity
        int vx = rand.nextInt(-4,5);
        int vy = rand.nextInt(-4,5);

        // Create Element
        Logger.logMessage("Creating fish at (" + x + ", " + y + ")", this);
        Logger.logMessage(" { Fish size: " + size + ", Velocity: ( " + vx + ", " + vy + " ) }", this);
        Fish fish = new Fish(x, y, vx, vy, size);
        fish.setImageObserver(this);

        // Put fish in Map
        elements.put(fish.getId(), fish);
    }

    public void removeElement() {
        // Get keyset of elements
        Set<String> keys = elements.keySet();

        // If it is empty then return
        if (keys.isEmpty()) return;

        // Remove last element
        String lastKey = keys.iterator().next();
        elements.remove(lastKey);
    }
    //==========================================================================
    // Handler and Listener Methods
    private void handleClick(MouseEvent e) {
        if( e.getButton() == MouseEvent.BUTTON1 ) {
            // Create Element
            createElement(e.getX(), e.getY());
        } else if( e.getButton() == MouseEvent.BUTTON3 ) {
            // If element is not empty, remove last element
            if (!elements.isEmpty())
                removeElement();
            else
                Logger.logMessage("No element to remove", this);
        }
    }

    public void handleMessage(Object message) {
        if (message instanceof Remover)
            elements.remove(((Remover) message).getId());
        else if (message instanceof Spawner) {
            Fish fish = (Fish) ((Spawner) message).getElement();
            fish.setImageObserver(this);
            elements.put(fish.getId(), fish);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            for (Element element : elements.values()) {
                // Update element position
                element.move();

                // If element collide vertical boundary
                if (element.getX() < 0 || element.getX() + element.getSize() > PANEL_WIDTH) {

                    // Send collider message to server
                    outputStream.writeObject(new Collider(element));
                    outputStream.reset();

                    // Reverse element direction
                    element.setVx(-1 * element.getVx());
                }

                // If element collide horizontal boundary
                if (element.getY() < 0 || element.getY() + element.getSize() > PANEL_HEIGHT) {

                    // Reverse element direction
                    element.setVy(-1 * element.getVy());
                }

            }
            // Repaint panel
            repaint();
        } catch (IOException err) {
            Logger.logError("Error in updating element position", this, err);
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (Element element : elements.values()) {
            element.draw(graphics);
        }
    }
    //==========================================================================
}
