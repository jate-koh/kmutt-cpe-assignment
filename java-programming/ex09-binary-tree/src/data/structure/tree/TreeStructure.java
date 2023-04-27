package data.structure.tree;

import com.sun.source.tree.Tree;
import data.structure.tree.node.TreeNode;
import utils.Logger;

import javax.naming.LimitExceededException;

public abstract class TreeStructure<T> implements TraversableTree<T>, AVLTree<T> {

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
        this.limit = limit;
        this.root = new TreeNode<T>(root);
        this.size = 1;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    public TreeNode<T> getRoot() {
        return this.root;
    }

    /** Return size of tree (counted by number of nodes)
     * @return Size of tree
     * */
    public int getSize() {
        return this.size;
    }

    /** Return true if tree is empty
     * @return True if tree is empty, and false if tree is not empty
     * */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /** Insert data into tree as a new node.
     * @param data Data to insert into tree.
     * @return TreeNode<T> of inserted data if successful, and null if unsuccessful
     * @throws LimitExceededException If loop count exceeds limit
     * @throws IllegalArgumentException If data is not comparable
     * */
    public TreeNode<T> insert(T data) throws LimitExceededException, IllegalArgumentException {
        int loopCount = 0;

        // If data is not comparable, throw exception
        if (!(data instanceof Comparable)) {
            throw new IllegalArgumentException("Data is not comparable");
        }

        // If root is null, create new root
        if (root == null) {
            root = new TreeNode<T>(data);
            size++;
            return root;
        }

        // If root is not null, find the right place to insert
        TreeNode<T> current = this.root;
        while (true) {
            // Check if loop has exceeds specified limit
            this.hasExceedLimit(loopCount++);

            // If data is smaller than current node, go left
            if (((Comparable<T>) data).compareTo(current.getData()) == -1) {
                // If left child is null, insert new node
                if (current.getLeft() == null) {

                    // Set left child of current node
                    current.setLeft(new TreeNode<T>(data));

                    // Set parent of new node and increase size
                    current.getLeft().setParent(current);
                    size++;

                    // Update height of current node and its parents
                    if (current.getParent() != null) updateHeight(current.getParent());
                    updateHeight(current);

                    return current.getLeft();
                }

                // If left child is not null, move down and update height
                current = moveDownLeft(current);

            // If data is equal to current node, do not insert
            } else if( ((Comparable<T>) data).compareTo(current.getData()) == 0) {
                // Update height of current node
                updateHeight(current);

                // Return null, and end function
                return null;

            // If data is larger than current node, go right
            } else {
                // If right child is null, insert new node
                if (current.getRight() == null) {
                    // Set right child of current node
                    current.setRight(new TreeNode<T>(data));

                    // Set parent of new node and increase size
                    current.getRight().setParent(current);
                    size++;

                    // Update height of current node
                    if (current.getParent() != null) updateHeight(current.getParent());
                    updateHeight(current);

                    return current.getRight();
                }
                // If right child is not null, move down
                current = moveDownRight(current);
            }
        }
    }

    public TreeNode<T> remove(T data) throws IllegalArgumentException {

        if (!(data instanceof Comparable)) throw new IllegalArgumentException("Data is not comparable");

        // Start Recursive Remove Function
        return removeRec(root, data);
    }

    private TreeNode<T> removeRec(TreeNode<T> root, T data) {

        if (root == null) {
            return root;
        }

        if (((Comparable<T>)data).compareTo(root.getData()) == -1) {
            root.setLeft(removeRec(root.getLeft(), data));
        } else if (((Comparable<T>)data).compareTo(root.getData()) == 1) {
            root.setRight(removeRec(root.getRight(), data));
        } else {
            // If node has no child or one child
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }

            // If node has two children, get inorder successor
            // (smallest in the right subtree)
            root.setData(minValue(root.getRight()));

            // Delete the inorder successor
            root.setRight(removeRec(root.getRight(), root.getData()));
        }
        return root;
    }

    private T minValue(TreeNode<T> root) {
        T minValue = root.getData();
        while (root.getLeft() != null) {
            minValue = root.getLeft().getData();
            root = root.getLeft();
        }
        return minValue;
    }

    /** Search for data in tree.
     * @param data Data to search for in tree.
     * @return Node containing data, or null if data does not exist.
     * @throws LimitExceededException If loop count exceeds limit
     * @throws IllegalArgumentException If data is not comparable
     * */
    public TreeNode<T> search(T data) throws LimitExceededException {
        int loopCount = 0;
        StringBuilder path = new StringBuilder("Path: ");


        // If data is not comparable, throw exception
        if (!(data instanceof Comparable)) {
            throw new IllegalArgumentException("Data is not comparable");
        }

        // If root is null or data is null, end function
        if (root == null) return null;

        // If root is not null, find the node to remove
        TreeNode<T> current = root;
        while (true) {
            // Check if loop has exceeds specified limit
            this.hasExceedLimit(loopCount++);

            // If data is smaller than current node, go left
            if (((Comparable<T>)data).compareTo(current.getData()) == -1) {
                // If left child is null, data does not exist
                if (current.getLeft() == null) return null;

                // If left child is not null, move down
                current = moveDownLeft(current);

                // If data is larger than current node, go right
            } else if (((Comparable<T>)data).compareTo(current.getData()) == 1) {
                // If right child is null, data does not exist
                if (current.getRight() == null) return null;

                // If right child is not null, move down
                current = moveDownRight(current);

                // If data is equal to current node, return node
            } else {
                return current;
            }
        }
    }

    /** Clear tree of all nodes.
     * @return True if tree is cleared, false if tree is empty.
     * @throws Exception If an exception occurs.
     * */
    public boolean clear() {
        // If tree is empty, return false
        if (this.size == 0) return false;

        // Set root to null and size to 0
        root = null;
        this.size = 0;
        return true;
    }

    public abstract void printLog();

    /* Functions For Nodes Rotate + AVL Tree */
    public TreeNode<T> rebalance(TreeNode<T> node) {
        updateHeight(node);

        int balanceFactor = balanceFactor(node);
        Logger.logMessage("Balance factor of {" + node.getData() + "} is " + balanceFactor);

        // Left-heavy
        if (balanceFactor < -1) {
            if(balanceFactor(node.getLeft()) <= 0) {
                // Left-left case
                Logger.logMessage("Spinning right: Left-left case");
                return spinRight(node);
            } else {
                // Left-right case
                Logger.logMessage("Spinning left: Left-right case");
                node.setLeft(spinLeft(node.getLeft()));
                return spinRight(node);
            }
        }

        // Right-heavy
        if (balanceFactor > 1) {
            if(balanceFactor(node.getRight()) >= 0) {
                // Right-right case
                Logger.logMessage("Spinning left: Right-right case");
                return spinLeft(node);
            } else {
                // Right-left case
                Logger.logMessage("Spinning right: Right-left case");
                node.setRight(spinRight(node.getRight()));
                return spinLeft(node);
            }
        }
        return node;
    }

    public int balanceFactor(TreeNode<T> node) {
        if (node == null) return 0;
        return height(node.getRight()) - height(node.getLeft());
    }

    public void updateHeight(TreeNode<T> node) {
        if (node == null) return;
        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());
        node.setHeight(Math.max(leftHeight, rightHeight) + 1);
    }

    private int height(TreeNode<T> node) {
        if (node == null) return -1;
        return node.getHeight();
    }

    public TreeNode<T> spinLeft(TreeNode<T> node) throws NullPointerException {
        TreeNode<T> rightChild = node.getRight();

        node.setRight(rightChild.getLeft());
        rightChild.setLeft(node);

        updateHeight(node);
        updateHeight(rightChild);

        return rightChild;
    }

    public TreeNode<T> spinRight(TreeNode<T> node) throws NullPointerException {
        TreeNode<T> leftChild = node.getLeft();

        node.setLeft(leftChild.getRight());
        leftChild.setRight(node);

        updateHeight(node);
        updateHeight(leftChild);

        return leftChild;
    }

    public TreeNode<T> spinLeftRight(TreeNode<T> node) {
        node.setLeft(this.spinLeft(node.getLeft()));
        node = this.spinRight(node);
        return node;
    }

    public TreeNode<T> spinRightLeft(TreeNode<T> node) {
        node.setRight(this.spinRight(node.getRight()));
        node = this.spinLeft(node);
        return node;
    }

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
        return node.getRight();
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
