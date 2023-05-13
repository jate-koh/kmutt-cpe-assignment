package data.structure.tree.node;

/**
 * This interface is used to represent a node in an AVL tree.
 * It needs to be implemented by the class that represents a node in an AVL tree.
 * This interface should be used alongside the {@link data.structure.tree.AVLTree} interfaces.
 */
public interface AVLNode {

    /** This method is used to get height of the node.
     * @return The height of the node.
     */
    public int getHeight();

    /** This method is used to set height of the node.
     * @param height The height of the node.
     */
    public void setHeight(int height);

}
