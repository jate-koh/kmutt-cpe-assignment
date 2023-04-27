package data.structure.tree;

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

    public void printLog() {
        Logger.logMessage("Tree: ", this);
        TreePrinter.printNode(this.root);
        Logger.logMessage("*** END ***", this);
    }
}
