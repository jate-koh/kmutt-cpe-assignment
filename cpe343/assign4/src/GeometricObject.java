package src;
import java.lang.String;
import java.util.Date;

public class GeometricObject implements Comparable {
    private String color;
    private boolean filled = false;
    private Date dateCreated;

    public GeometricObject() {
        this.filled = false;
        this.dateCreated = new Date();
        this.dateCreated.getTime();
    }

    public GeometricObject(String color, boolean filled) {
        this.filled = filled;
        this.color = color;
        this.dateCreated = new Date();
        this.dateCreated.getTime();
    }

    public String getColor() {
        return this.color;
    }


    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return this.filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public static GeometricObject max(GeometricObject obj1, GeometricObject obj2) {
        switch(obj1.compareTo(obj2)) {
            case 1: return obj2;
            case -1: return obj1;
            case 0: return null;
            default: {
                System.out.println("max() error");
                return null;
            }
        }
    }

    public int compareTo(Object obj) {
        if(obj instanceof GeometricObject) {
            if(this.isFilled() == true && ((GeometricObject) obj).isFilled() == false) return -1;
            else if(this.isFilled() == false && ((GeometricObject) obj).isFilled() == true) return 1;
            else return 0;
        }
        else {
            System.out.println("compareTo() error, must be instance of GeometricObject");
            return -100;
        }
    }
}