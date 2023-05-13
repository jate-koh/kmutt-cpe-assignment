package client.gui;

import javax.swing.*;
import java.awt.*;

public abstract class Frame extends JFrame {

    /** this constructor is used to create a panel.
     * The panel has no layout and no background color.
     * The layout and the background color can be set later.
     * Thus, it will set the layout to null and the background color to null.
     */
    public Frame() {
        this(null, null);
    }

    /** This constructor is used to create a panel with a layout and a background color.
     * @param layout The layout of the panel.
     * @param backgroundColor The background color of the panel.
     */
    public Frame(LayoutManager layout, Color backgroundColor) {
        if (backgroundColor != null) this.setBackground(backgroundColor);
        if (layout != null) this.setLayout(layout);
        this.initComponents();
    }

    /** This method is used to initialize the components of the panel.
     * This method is called in the constructor of the class that extends this class.
     * This method is abstract, so it must be implemented in the class that extends this class.
     */
    protected abstract void initComponents();

}
