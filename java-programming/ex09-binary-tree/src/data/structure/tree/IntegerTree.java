package data.structure.tree;

import com.sun.source.tree.Tree;
import data.structure.tree.node.TreeNode;
import utils.Logger;
import utils.TreePrinter;

import javax.naming.LimitExceededException;

public class IntegerTree extends TreeStructure<Integer> {

    public IntegerTree() {
        super();
    }

    public IntegerTree(Integer root) {
        super(root);
    }

    public TreeNode<Integer> insert(Integer data) {
        TreeNode<Integer> node = null;
        try {
            node = super.insert(data);
            super.updateHeight(node);
            node = super.rebalance(root);
            this.setRoot(node);
        } catch (Exception exception) {
            Logger.logError(
                    "Error inserting data into tree: " + exception.getMessage(),
                    this,
                    exception
            );
            return null;
        }
        Logger.logMessage("Inserted " + data + " into tree", this);
        return node;
    }

    public TreeNode<Integer> remove(Integer data) {
        TreeNode<Integer> node = null;
        try {
            node = super.remove(data);
            super.updateHeight(node);
            node = super.rebalance(root);
            this.setRoot(node);
        } catch (Exception exception) {
            Logger.logError(
                    "Error removing data from tree: " + exception.getMessage(),
                    this,
                    exception
            );
            return null;
        }
        return node;
    }

    public StringBuilder searchPath(Integer data) throws IllegalArgumentException, LimitExceededException {
        if ( root == null ) {
            Logger.logMessage("Tree is empty", this);
            return null;
        }

        if ( !(data instanceof Comparable<?>) )
            throw new IllegalArgumentException("Data must be comparable");

        Logger.logMessage("Searching for " + data + " in tree", this);
        StringBuilder path = new StringBuilder("[Path] ");
        int loopCount = 0;

        TreeNode<Integer> current = root;
        path.append( "Root: " + current.getData()).append(" ");

        while ( current != null ) {
            this.hasExceedLimit(loopCount++);
            // If data is found, return the path
            if ( current.getData().compareTo(data) == 0 ) {
                path.append("-> (Found " + data + ")");
                Logger.logMessage("Found " + data + " in tree", this);
                Logger.logMessage(path.toString(), this);
                return path;
            }

            // If data is not found, move down the tree
            // If data is less than current node, move down left. Only if left node exists
            if (current.hasLeft() && current.getData().compareTo(data) == 1) {
                current = moveDownLeft(current);
                path.append("-> Left: " + current.getData() + " ");
            }
            // If data is greater than current node, move down right. Only if right node exists
            else if (current.hasRight() && current.getData().compareTo(data) == -1) {
                current = moveDownRight(current);
                path.append("-> Right: " + current.getData() + " ");
            }
            // If data no node exists, that data is not in the tree
            else {
                path.append("-> (Not found)");
                current = null;
                Logger.logMessage("Could not find " + data + " in tree", this);
                Logger.logMessage(path.toString(), this);
                return path;
            }
        }
        return null;
    }

    public StringBuilder traverseInorder() {
        Logger.logMessage("Traversing tree inorder", this);
        StringBuilder path = new StringBuilder(300);
        path.append("[Path] ");
        path.append(traverseInorderRec(root));

        Logger.logMessage("Inorder traversal: " + path.toString(), this);
        return path;
    }

    private StringBuilder traverseInorderRec(TreeNode<Integer> node) {
        StringBuilder path = new StringBuilder(300);

        path.append(node.getData() + " ");

        if ( node.hasLeft() )
            path.append(traverseInorderRec(node.getLeft()));
        if ( node.hasRight() )
            path.append(traverseInorderRec(node.getRight()));

        return path;
    }

    public void printLog() {
        // Traverse Tree
        this.traverseInorder();

        // Draw Tree
        Logger.logMessage("Tree: ", this);
        TreePrinter.printNode(this.root);
        Logger.logMessage("*** END ***", this);
    }
}
