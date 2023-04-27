package data.structure.tree;

import javax.naming.LimitExceededException;

public class IntegerTree extends TreeStructure<Integer> {


    public IntegerTree() {
        super();
    }

    public IntegerTree(Integer root) {
        super(root);
    }

    public void insert(Integer data) throws LimitExceededException {
        int loopCount = 0;

        // If root is null, create new root
        if (root == null) {
            root = new TreeNode<Integer>(data);
            size++;
            return;
        }

        // If root is not null, find the right place to insert
        TreeNode<Integer> current = root;
        while (true) {
            // Check if loop has exceeds specified limit
            this.hasExceedLimit(loopCount++);

            // If data is smaller than current node, go left
            if (data < current.getData()) {
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

    public void remove(Integer data) throws LimitExceededException {
        int loopCount = 0;

        // If root is null or data is null, end function
        if (root == null) return;

        // If root is not null, find the node to remove
        TreeNode<Integer> current = root;
        while (true) {
            // Check if loop has exceeds specified limit
            this.hasExceedLimit(loopCount++);

            // If data is smaller than current node, go left
            if (data < current.getData()) {
                // If left child is null, data does not exist
                if (current.getLeft() == null) return;

                // If left child is not null, move down
                moveDownLeft(current);

            // If data is larger than current node, go right
            } else if (data > current.getData()) {
                // If right child is null, data does not exist
                if (current.getRight() == null) return;

                // If right child is not null, move down
                moveDownRight(current);

            // If data is equal to current node, remove node
            } else {
                // If node has no children, remove node right away and decrease size
                if (current.getLeft() == null && current.getRight() == null) {

                        // If node is root, set root to null
                        if (current == root) {
                            // Set root to null and decrease size
                            root = null;
                            size--;
                            return;
                        }

                        // If node is not root, remove node
                        if (current.getParent().getLeft() == current) {
                            // Set left child of parent to null and decrease size
                            current.getParent().setLeft(null);
                            size--;
                            return;

                        } else {
                            // Set right child of parent to null and decrease size
                            current.getParent().setRight(null);
                            size--;
                            return;
                        }
                }

                // If node has one child, remove node, decrease size, and replace node with child
                if (current.getLeft() == null || current.getRight() == null) {
                    // If node is root, set root to child
                    if (current == root) {
                        // Set root to child and decrease size
                        if (current.getLeft() == null) {
                            root = current.getRight();
                            size--;
                            return;
                        } else {
                            root = current.getLeft();
                            size--;
                            return;
                        }
                    }

                    // If node is not root, remove node
                    if (current.getParent().getLeft() == current) {
                        // Set left child of parent to child and decrease size
                        if (current.getLeft() == null) {
                            current.getParent().setLeft(current.getRight());
                            size--;
                            return;
                        } else {
                            current.getParent().setLeft(current.getLeft());
                            size--;
                            return;
                        }

                    } else {
                        // Set right child of parent to child and decrease size
                        if (current.getLeft() == null) {
                            current.getParent().setRight(current.getRight());
                            size--;
                            return;
                        } else {
                            current.getParent().setRight(current.getLeft());
                            size--;
                            return;
                        }
                    }
                }


            }
        }


    }

    public Integer search(Integer data) {
        return 0;
    }

    public void clear() {

    }

    public void printLog() {
        StringBuilder message = new StringBuilder("Tree Elements: [ ");
        String buffer = "";


    }
}
