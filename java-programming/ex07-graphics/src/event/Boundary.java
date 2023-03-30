package event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Boundary {

    private Dimension dimension;
    private Insets insets;
    private int width, height;

    public Boundary(JFrame frame) {
        this.dimension = frame.getSize();
        this.insets = frame.getInsets();
        this.width = this.dimension.width - this.insets.left - this.insets.right;
        this.height = this.dimension.height - this.insets.top - this.insets.bottom;
    }

    public Boundary(JPanel panel) {
        this.dimension = panel.getSize();
        this.insets = panel.getInsets();
        this.width = this.dimension.width - this.insets.left - this.insets.right;
        this.height = this.dimension.height - this.insets.top - this.insets.bottom;
    }

    public Dimension getDimension() {
        return this.dimension;
    }

    public Insets getInsets() {
        return this.insets;
    }
}
