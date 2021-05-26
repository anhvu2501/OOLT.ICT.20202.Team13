package oop.ict.project.tree.binary;

import java.util.ArrayList;

import oop.ict.project.tree.balanced.BalancedTree;
import oop.ict.project.tree.exception.TreeException;
import oop.ict.project.tree.generic.Node;

public class TestBinaryTree {
	public static void main(String[] args) throws TreeException {
		BinaryTree tempTree = new BinaryTree(new Node(4));
		Integer rootValue = tempTree.root.rootCircle.getSearchKey();
		try {
			Node tempNode = tempTree.insertNode(rootValue, new Node(1));
			tempNode = tempTree.insertNode(rootValue, new Node(2));
			tempNode = tempTree.insertNode(1, new Node(14));
			tempNode = tempTree.insertNode(1, new Node(6));
			tempNode = tempTree.insertNode(2, new Node(7));
			tempNode = tempTree.insertNode(2, new Node(5));
//			dong duoi k insert dc do khi insert thi so children se >2
//			tempNode = tempTree.insertNode(2, new Node(3));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
		System.out.println("Preorder Traversal");
		ArrayList<Node> preOrderList = tempTree.traversePreOrder();
		for (Node i : preOrderList) {
			System.out.print(i.rootCircle.getSearchKey() + "  ");
		}

		tempTree.deleteNodeBinary(tempTree.root, 6);
		System.out.println("\nPreorder: ");
		ArrayList<Node> preorderList = tempTree.traversePreOrder();
		for (Node i : preorderList) {
			System.out.print(i.rootCircle.getSearchKey() + "  ");
		}
		System.out.println();

		tempTree.updateValueOfNode(2, 20);
		System.out.println("\nPreorder: ");
		preorderList = tempTree.traversePreOrder();
		for (Node i : preorderList) {
			System.out.print(i.rootCircle.getSearchKey() + "  ");
		}

		//Using methods of Generic tree
		System.out.println();
		Node searchNode = tempTree.searchNode(tempTree.root, 7);
		System.out.println(searchNode.rootCircle.getSearchKey());
	}

}
