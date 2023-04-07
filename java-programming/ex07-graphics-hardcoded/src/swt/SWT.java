package swt;

import element.Circle;
import utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SWT extends JFrame {

    protected int dx = 1;
    protected int dy = 1;
    private JTextField textField;
    private JFrame outerFrame;
    private JInternalFrame innerFrame;
    private JPanel labelPanel;
    private JLabel label;
    private ActionListener actionListener;
    private Timer timer;

    public SWT() {

        // Initialize the frame
        this.initFrame();

        // Initialize element
        this.initElement();

        // Initialize the timer
        this.initTimer();

    }

    private void initFrame() {

        // Init the outer frame
        outerFrame = new JFrame();

        // Set Properties for the outer frame
        outerFrame.setTitle("Bouncy Ball");
        outerFrame.setBounds(100, 100, 450, 300);
        outerFrame.setVisible(true);
        outerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        outerFrame.getContentPane().setLayout(null);

        // Initialize the inner frame
        innerFrame = new JInternalFrame();

        // Set Properties for the inner frame
        innerFrame.setBounds(10, 55, 420, 200);
        innerFrame.setVisible(true);
        innerFrame.getContentPane().setLayout(null);

        // Add the inner frame to the outer frame
        outerFrame.getContentPane().add(innerFrame);

        // Initialize label panel
        labelPanel = new JPanel();
        labelPanel.setVisible(true);

        // Add label panel to the outer frame
        outerFrame.getContentPane().add(labelPanel);

        // Initialize label and text field
        label = new JLabel("Bouncing Ball!");
        textField = new JTextField("Wow, nice text field...");

        // Add label and text field to the label panel
        labelPanel.add(label);
        labelPanel.add(textField);

    }

    private void initElement() {

        // Create Circle
        final Circle circle = new Circle(Color.RED, 0, 0, 15);

        // Initialize Action Listener
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Get width and height
                int x = circle.getX();
                int y = circle.getY();
                Dimension size = circle.getSize();
                Insets insets = circle.getInsets();

                // Calculate width and height
                int width = size.width - insets.left - insets.right;
                int height = size.height - insets.top - insets.bottom;

                // Check if the ball has hit the right or left edge of internal frame
                if (x + dx > width - circle.getDiameter() || x + dx < 0) {

                    // Reverse the direction of the ball horizontally
                    dx = -dx;

                }

                // Check if the ball has hit the top or bottom edge of internal frame
                if (y + dy > height - circle.getDiameter()/2 || y + dy < 0) {

                    // Reverse the direction of the ball vertically
                    dy = -dy;

                }

                // Set the new location of the ball
                circle.setLocation(x + dx, y + dy);

                // Update the label
                label.setText("X: " + (x + dx) + " Y: " + (y + dy) );
                labelPanel.add(label);

                // Log the location of the ball
                Logger.logMessage("[X] " + (x + dx) + " [Y] " + (y + dy), this);

            }
        };

    }

    public void initTimer() {

        // Create timer
        timer = new Timer(50, actionListener);

        // Start timer
        timer.start();

    }

}
