package data.structure.tree;

import data.structure.tree.node.TreeNode;

public interface TraversableTree<T> {

    /* Functions for moving up and down the tree */

    /**
     * Function for moving up the tree.
     * @param node Node to move up from
     * @return Parent node of the given node
     * @throws NullPointerException if the given node has no parent
     */
    public TreeNode<T> moveUp(TreeNode<T> node) throws NullPointerException;

    /**
     * Function for moving down the tree via left child of the given node.
     * @param node Node to move down from
     * @return Left child node of the given node
     * @throws NullPointerException if the given node has no left child
     */
    public TreeNode<T> moveDownLeft(TreeNode<T> node) throws NullPointerException;

    /**
     * Function for moving down the tree via right child of the given node.
     * @param node Node to move down from
     * @return Right child node of the given node
     * @throws NullPointerException if the given node has no right child
     */
    public TreeNode<T> moveDownRight(TreeNode<T> node) throws NullPointerException;
}
