package data.structure.tree;

import javax.naming.LimitExceededException;

public abstract class TreeStructure<T> implements TraversableTree<T> {

    private int limit; // Limit of loops for insert, remove, search, clear
    protected TreeNode<T> root;
    protected int size;

    public TreeStructure() {
        this(null, 1000);
    }

    public TreeStructure(T root) {
        this(root, 1000);
    }

    public TreeStructure(T root, int limit) {
        this.root = new TreeNode<T>(root);
        this.size = 1;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public TreeNode<T> getRoot() {
        return this.root;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void insert(T data) throws LimitExceededException, IllegalArgumentException {
        int loopCount = 0;

        // If data is comparable, throw exception
        if (data instanceof Comparable) {
            throw new IllegalArgumentException("Data is not comparable");
        }

        // If root is null, create new root
        if (root == null) {
            root = new TreeNode<T>(data);
            size++;
            return;
        }

        // If root is not null, find the right place to insert
        TreeNode<T> current = root;
        while (true) {
            // Check if loop has exceeds specified limit
            this.hasExceedLimit(loopCount++);

            // If data is smaller than current node, go left
            if ( ((Comparable<T>) data).compareTo(current.getData()) == -1) {
                // If left child is null, insert new node
                if (current.getLeft() == null) {

                    // Set left child of current node
                    current.setLeft(new TreeNode<Integer>(data));

                    // Set parent of new node and increase size
                    current.getLeft().setParent(current);
                    size++;
                    return;
                }

                // If left child is not null, move down
                moveDownLeft(current);

                // If data is larger than current node, go right
            } else {
                // If right child is null, insert new node
                if (current.getRight() == null) {
                    // Set right child of current node
                    current.setRight(new TreeNode<Integer>(data));

                    // Set parent of new node and increase size
                    current.getRight().setParent(current);
                    size++;
                    return;
                }
                // If right child is not null, move down
                moveDownRight(current);
            }
        }
    }

    public abstract void remove(T data) throws LimitExceededException;

    public abstract T search(T data) throws LimitExceededException;

    public abstract void clear() throws LimitExceededException;

    public abstract void printLog();

    /* Functions For Moving Up and Down the Tree */
    public TreeNode<T> moveUp(TreeNode<T> node) throws NullPointerException {
        if(node.getParent() == null)
            throw new NullPointerException("No parent node");
        return node.getParent();
    }

    public TreeNode<T> moveDownLeft(TreeNode<T> node) throws NullPointerException {
        if (node.getLeft() == null)
            throw new NullPointerException("No left child");
        return node.getLeft();
    }

    public TreeNode<T> moveDownRight(TreeNode<T> node) throws NullPointerException {
        if (node.getRight() == null)
            throw new NullPointerException("No right child");
        return node.getLeft();
    }

    /* Error Check Functions */
    public boolean hasExceedLimit(int loopCount) throws LimitExceededException {
        // If loopCount exceeds limit, return false
        if (loopCount >= this.limit) {
            throw new LimitExceededException("Loop count exceeds specified limit");
        }
        else return false;
    }

}
