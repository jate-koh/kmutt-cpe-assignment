package cpe343.assign4;
import java.lang.String;

import java.util.Date;

public class GeometricObject {
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

}