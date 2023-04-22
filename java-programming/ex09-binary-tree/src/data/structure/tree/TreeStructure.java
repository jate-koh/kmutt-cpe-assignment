package data.structure.tree;

import com.sun.source.tree.Tree;

public abstract class TreeStructure<T> {

    protected TreeNode<T> root;
    protected int size;

    public TreeStructure() {
        this.root = null;
        this.size = 0;
    }

    public TreeStructure(T root) {
        this.root = new TreeNode<T>(root);
        this.size = 1;
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

    public abstract void insert(T data);

    public abstract void remove(T data);

    public abstract boolean search(T data);

    public abstract void clear();

    public abstract void printLog();

}
