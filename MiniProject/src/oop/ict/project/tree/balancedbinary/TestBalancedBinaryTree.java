package oop.ict.project.tree.balancedbinary;

import oop.ict.project.tree.exception.TreeException;
import oop.ict.project.tree.generic.Node;

import java.util.ArrayList;

public class TestBalancedBinaryTree {
    public static void main(String[] args) {
        BalancedBinaryTree tempTree = new BalancedBinaryTree(new Node(4));
        tempTree.setLimitDistance(2);
        Integer rootValue = tempTree.root.rootCircle.getSearchKey();
//        try {
//            Node tempNode = tempTree.insertNode(rootValue, new Node(1));
//            tempNode = tempTree.insertNode(rootValue, new Node(2));
//            tempNode = tempTree.insertNode(1, new Node(14));
//            tempNode = tempTree.insertNode(1, new Node(6));
//            tempNode = tempTree.insertNode(2, new Node(7));
//            tempNode = tempTree.insertNode(2, new Node(5));
//        } catch (TreeException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }

        System.out.println("Preorder Traversal");
        ArrayList<Node> preorderList = tempTree.traversePreOrder();
        for (Node i : preorderList) {
            System.out.print(i.rootCircle.getSearchKey() + "  ");
        }
    }
}
