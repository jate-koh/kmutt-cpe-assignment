package src;
import java.lang.String;
import java.util.Date;
import java.util.UUID;

public class GeometricObject implements Comparable {
    private String color;
    private boolean filled = false;
    private Date dateCreated;
    private String uniqueId;
    protected double compareValue = 0;

    public GeometricObject() {
        this.uniqueId =  UUID.randomUUID().toString();
        this.filled = false;
        this.dateCreated = new Date();
        this.dateCreated.getTime();
    }

    public GeometricObject(String color, boolean filled) {
        this.uniqueId =  UUID.randomUUID().toString();
        this.filled = filled;
        this.color = color;
        this.dateCreated = new Date();
        this.dateCreated.getTime();
    }

    public String getUniqueId() {
        return this.uniqueId;
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

    public static String max(GeometricObject obj1, GeometricObject obj2) {
        if(obj1.getClass() == obj2.getClass() ) {
                switch(obj1.compareTo(obj2)) {
                case 1: return obj2.uniqueId;
                case -1: return obj1.uniqueId;
                case 0: return null;
                default: {
                    System.out.println("max() error");
                    return null;
                }
            }
        }
        else {
            System.out.println("max() error, object is of different shape");
            return null;
        }
    }

    public int compareTo(Object obj) {
        if(obj instanceof GeometricObject) {
            if(this.compareValue > ((GeometricObject) obj).compareValue) return -1;
            else if(this.compareValue < ((GeometricObject) obj).compareValue) return 1;
            else return 0;
        }
        else {
            System.out.println("compareTo() error, must be instance of GeometricObject");
            return -100;
        }
    }
}