package element;

import utils.Logger;

import java.util.ArrayList;

public class ElementList {

    private ArrayList<GameElement> elements;

    public ElementList() {
        this.elements = new ArrayList<>();
    }

    public void addElement(GameElement element) {
        this.elements.add(element);
    }

    public void removeElement(GameElement element) {
        this.elements.remove(element);
    }

    public ArrayList<GameElement> getList() {
        return this.elements;
    }

    public GameElement get(int index) {
        return this.elements.get(index);
    }

    public void logElements() {
        StringBuilder message = new StringBuilder();
        String buffer = "";
        message.append("Elements: [ ");
        for (GameElement element : this.elements) {
            buffer = "{ Name: " + element.getClass().getSimpleName() + ", " +
                    "Color: R " + element.getColor().getRed() +
                    " G " + element.getColor().getGreen() +
                    " B " + element.getColor().getBlue() + ", " +
                    "Position: X " + element.getX() + " Y " + element.getY() + ", " +
                    "Size: " + element.getWidth() + "x" + element.getHeight() + " }, ";
            message.append(buffer);
        }
        message.append(" ]");
        Logger.logMessage(message.toString(), this);
    }

}
