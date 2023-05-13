package data.structure.tree;

import data.structure.tree.node.TreeNode;

public interface AVLTree<T> {

    /**
     * Function for spinning the given node to the left.
     * @param node Node to spin to the left
     * @return New root node of the subtree
     */
    public TreeNode<T> spinLeft(TreeNode<T> node);

    /** Function for spinning the given node to the right.
     * @param node Node to spin to the right
     * @return New root node of the subtree
     */
    public TreeNode<T> spinRight(TreeNode<T> node);

    /** Function for spinning the given node to the left and then to the right. Used especially
     * for left-right case in unbalanced AVL tree.
     * @param node Node to spin to the left and then to the right
     * @return New root node of the subtree
     */
    public TreeNode<T> spinLeftRight(TreeNode<T> node);

    /** Function for spinning the given node to the right and then to the left. Used especially
     * for right-left case in unbalanced AVL tree.
     * @param node Node to spin to the right and then to the left
     * @return New root node of the subtree
     */
    public TreeNode<T> spinRightLeft(TreeNode<T> node);

    /** Function for re-balancing the given node.
     * @param node Node to re-balance
     * @return New root node of the subtree
     */
    public TreeNode rebalance(TreeNode<T> node);

    /** Function for calculating the balance factor of the given node.
     * @param node Node to calculate the balance factor of
     * @return Balance factor of the given node
     */
    public int balanceFactor(TreeNode<T> node);

    /** Function for updating the height of the given node.
     * @param node Node to update the height of
     */
    public void updateHeight(TreeNode<T> node);

}
