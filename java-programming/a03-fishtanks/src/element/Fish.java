package element;

import networking.message.SerializablePicture;
import utils.Logger;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.util.Random;

public class Fish extends Element implements Serializable {

    //=================================================================================
    // Static Attributes
    public static String[] FISH_URLS = {
            //"https://i.imgur.com/on0X7gw.png",
            "https://i.imgur.com/rElsP57.png",
            //"https://i.imgur.com/Ynw3bRj.png",
    };

    //=================================================================================
    // Components
    private SerializablePicture picture;
    private transient ImageObserver imageObserver;

    //=================================================================================
    public Fish(int x, int y, int vx, int vy, int size) {
        super(x, y, vx, vy, size);
        try {
            Random rand = new Random();
            String url = FISH_URLS[rand.nextInt(FISH_URLS.length)];
            this.picture = new SerializablePicture(url);
        } catch (Exception e) {
            Logger.logError("Error upon creating fish element", this, e);
            throw new RuntimeException(e);
        }
    }

    //=================================================================================
    // Methods
    @Override
    public void draw(Graphics graphic) {
        // Flip the image if the fish is moving left
        int width = 0;
        if (this.getVx() < 0)
            width = 100;
        else
            width = -100;

        graphic.drawImage(picture.getBuffer(), this.getX(), this.getY(), width, 50, imageObserver);
    }

    //=================================================================================
    // Getters and Setters
    public void setImageObserver(ImageObserver imageObserver) {
        this.imageObserver = imageObserver;
    }

    public ImageObserver getImageObserver() {
        return imageObserver;
    }
    //=================================================================================
}
