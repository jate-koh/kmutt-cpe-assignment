package networking.message;
//=================================================================================
/* Spawner Message
 * This message (packets) object is send as a message to the server when an element
 * need to be spawned in the certain window. In order for it to be sent to other
 * windows.
 */
//=================================================================================
import element.Element;

import java.io.Serializable;

public class Spawner implements Serializable {

    private Element element;

    public Spawner(Element element) {
        this.element = element;
    }

    public Element getElement() {
        return element;
    }

}
