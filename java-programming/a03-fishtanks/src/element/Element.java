package element;
//==============================================================================
/* Element
 * This abstract class is used to keep data on the elements.
 * Every element in the game will extend this class.
 */
//==============================================================================
import utils.Common;

import java.awt.*;
import java.io.Serializable;

public abstract class Element implements Serializable {

    //=================================================================================
    // Attributes
    private String id;
    private int x, y, vx, vy;
    private int size;

    //=================================================================================
    // Constructors
    public Element(int x, int y, int vx, int vy, int size) {
        this.id = Common.generateUid();
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.size = size;
    }

    //=================================================================================
    // Methods
    public void move() {
        this.x += this.vx;
        this.y += this.vy;
    }

    //=================================================================================
    // Getters and Setters
    public String getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVx() {
        return vx;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    //=================================================================================
    // Abstract Methods
    public abstract void draw(Graphics graphic);

    //=================================================================================
}
