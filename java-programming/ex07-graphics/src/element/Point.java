package element;

import java.awt.*;

public class Point extends GameElement {

    public Point() {
        super();
    }

    public Point(Color color) {
        super(color);
    }

    public Point(int x, int y) {
        super(x, y, 1, 1);
    }

    public Point(Color color, int x, int y) {
        super(color, x, y, 1, 1);
    }
}
