package data.structure.tree.node;

public class TreeNode<T> implements Comparable, AVLNode {

    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;
    private TreeNode<T> parent;
    private int height;

    public TreeNode(T data) {
        this(data, null, null);
    }

    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public TreeNode<T> getParent() {
        return this.parent;
    }

    public TreeNode<T> getLeft() {
        return this.left;
    }

    public TreeNode<T> getRight() {
        return this.right;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public boolean hasLeft() {
        return this.left != null;
    }

    public boolean hasRight() {
        return this.right != null;
    }

    @Override
    public int compareTo(Object o) throws ClassCastException {
        if (o instanceof TreeNode) {
            if (this.data instanceof Comparable) {
                return ((Comparable) this.data).compareTo(((TreeNode<?>) o).data);
            } else {
                throw new ClassCastException("Current node data type is not comparable");
            }
        }
        return 0;
    }

    /* Functions for AVL Nodes */
    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
