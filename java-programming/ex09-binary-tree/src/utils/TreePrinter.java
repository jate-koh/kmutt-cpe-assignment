package utils;

import data.structure.tree.node.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreePrinter {

    public static StringBuilder treeString = new StringBuilder();

    public static <T extends Comparable<?>> void printNode(TreeNode<T> root) {
        // Clear previous tree in treeString
        treeString = new StringBuilder();

        int maxLevel = TreePrinter.maxLevel(root);
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<TreeNode<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || TreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        TreePrinter.printWhitespaces(firstSpaces);

        List<TreeNode<T>> newNodes = new ArrayList<TreeNode<T>>();
        for (TreeNode<T> node : nodes) {
            if (node != null) {
                System.out.print(node.getData());
                treeString.append(node.getData());
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
                treeString.append(" ");
            }

            TreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");
        treeString.append("\n");

        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                TreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    TreePrinter.printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).getLeft() != null) {
                    System.out.print("/");
                    treeString.append("/");
                }
                else
                    TreePrinter.printWhitespaces(1);

                TreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).getRight() != null) {
                    System.out.print("\\");
                    treeString.append("\\");
                }

                else
                    TreePrinter.printWhitespaces(1);

                TreePrinter.printWhitespaces(edgeLines + edgeLines - i);
            }

            System.out.println("");
            treeString.append("\n");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
            treeString.append(" ");
        }
    }

    private static <T extends Comparable<?>> int maxLevel(TreeNode<T> node) {
        if (node == null)
            return 0;

        return Math.max(TreePrinter.maxLevel(node.getLeft()), TreePrinter.maxLevel(node.getRight())) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}
