package element;

import java.awt.*;

public class Circle extends GameElement {

    public Circle() {
        super();
    }

    public Circle(Color color) {
        super(color);
    }

    public Circle(int x, int y) {
        super(x, y, 1, 1);
    }

    public Circle(Color color, int x, int y) {
        super(color, x, y, 1, 1);
    }

    public Circle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Circle(Color color, int x, int y, int width, int height) {
        super(color, x, y, width, height);
    }
}
