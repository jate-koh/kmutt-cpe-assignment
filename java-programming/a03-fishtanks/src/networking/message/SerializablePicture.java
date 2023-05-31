package networking.message;
//=================================================================================
/* Serializable Picture
 * This class is used to serialize the picture object so that it can be sent over
 * the network.
 * Credits: riflowth (https://github.com/riflowth)
 */
//=================================================================================
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

public class SerializablePicture implements Serializable {

    //=================================================================================
    // Components
    private transient BufferedImage picture; // Picture to be serialized

    //=================================================================================
    // Constructors
    public SerializablePicture(String url) throws IOException {
        this(new URL(url));
    }

    public SerializablePicture(URL url) throws IOException {
        this.picture = ImageIO.read(url);
    }

    //================================================================================='
    // Serializer
    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(picture, "png", out);
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        picture = ImageIO.read(in);
    }

    //=================================================================================
    // Getters and Setters
    public BufferedImage getBuffer() {
        return picture;
    }
    //=================================================================================
}
