package event.movement;

import element.Circle;
import element.GameElement;
import event.Boundary;

public class Movement {

    private Boundary boundary;
    private GameElement gameElement;

    public void move() {
//        gameElement.setLocation(
//                (gameElement.getX() + 1) % boundary.getWidth(),
//                (gameElement.getY() + 1) % boundary.getHeight()
//        );
    }

    public void setCircle(Circle circle) {
        this.gameElement = circle;
    }

    public void setBoundary(Boundary boundary) {
        this.boundary = boundary;
    }

}
