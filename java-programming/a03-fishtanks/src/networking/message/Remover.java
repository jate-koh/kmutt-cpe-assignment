package networking.message;
//=================================================================================
/* Remover Message
 * This message (packets) object is send as a message to the server when an element
 * need to be removed from the certain window. In order for it to be sent to other
 * windows.
 */
//=================================================================================
import java.io.Serializable;

public class Remover implements Serializable {

    //=================================================================================
    // Message Members
    private String id;

    //=================================================================================
    // Constructors
    public Remover(String id) {
        this.id = id;
    }

    //=================================================================================
    // Getters and Setters
    public String getId() {
        return id;
    }
    //=================================================================================
}
