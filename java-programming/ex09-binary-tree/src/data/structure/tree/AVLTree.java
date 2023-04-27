package data.structure.tree;

import data.structure.tree.node.TreeNode;

public interface AVLTree<T> {

    public TreeNode<T> spinLeft(TreeNode<T> node);

    public TreeNode<T> spinRight(TreeNode<T> node);

    public TreeNode<T> spinLeftRight(TreeNode<T> node);

    public TreeNode<T> spinRightLeft(TreeNode<T> node);

    public TreeNode rebalance(TreeNode<T> node);

    public int balanceFactor(TreeNode<T> node);

    public void updateHeight(TreeNode<T> node);

}
