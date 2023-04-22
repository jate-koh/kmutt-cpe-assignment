package element;
public interface MovingElement<T> extends GameElement<T> {

    public void setXVelocity(int vx);

    public void setYVelocity(int vy);

    public int getXVelocity();

    public int getYVelocity();

    public void move();

    public void reverseX();

    public void reverseY();

}
