package oop.ict.project.tree.balancedbinary;

import oop.ict.project.tree.generic.Node;

import java.util.ArrayList;

public class TestBalancedBinaryTree {
    public static void main(String[] args) {
        BalancedBinaryTree tempTree = new BalancedBinaryTree(new Node(4));
        tempTree.setLimitDistance(2);
        Integer rootValue = tempTree.root.rootCircle.getSearchKey();
        try {
            ArrayList<Node> listNodes = tempTree.insertNode(rootValue, new Node(2));
            listNodes = tempTree.insertNode(rootValue, new Node(1));
            listNodes = tempTree.insertNode(1, new Node(3));
            listNodes = tempTree.insertNode(1, new Node(7));
            listNodes = tempTree.insertNode(2, new Node(5));
            listNodes = tempTree.insertNode(2, new Node(6));

            ArrayList<Node> searchNode = new ArrayList<>();
            searchNode.add(tempTree.root);
            searchNode = tempTree.searchNode(searchNode, 5);
            System.out.println("Search list of 5:");
            for (Node index : searchNode) {
                System.out.print(index.rootCircle.getSearchKey() + "  ");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

        System.out.println("\nPreorder Traversal");
        ArrayList<Node> preOrderList = tempTree.traversePreOrder();
        for (Node i : preOrderList) {
            System.out.print(i.rootCircle.getSearchKey() + "  ");
        }
    }
}
