package networking.message;
//=================================================================================
/* Collider Message
 * This message (packets) object is send as a message to the server when an element
 * collide with the boundary.
 */
//=================================================================================
import element.Element;

import java.io.Serializable;

public class Collider implements Serializable {

    //=================================================================================
    // Message Members
    private Element element; // Send the element that collided with the boundary

    //=================================================================================
    // Constructors
    public Collider(Element element) {
        this.element = element;
    }

    //=================================================================================
    // Getters and Setters
    public Element getElement() {
        return element;
    }
    //=================================================================================
}
