package oop.ict.project.tree;

import java.util.ArrayList;

public class TestGenericTree {
	public static void main(String args[]) {
		GenericTree tempTree = new GenericTree(4);
		int rootValue = tempTree.root.value;
		tempTree.insertNode(rootValue, new Node(8));
		tempTree.insertNode(rootValue, new Node(1));
		tempTree.insertNode(rootValue, new Node(2));
		tempTree.insertNode(8, new Node(9));
		tempTree.insertNode(1, new Node(14));
		tempTree.insertNode(1, new Node(6));
		tempTree.insertNode(2, new Node(7));
		tempTree.insertNode(2, new Node(5));
		tempTree.insertNode(2, new Node(3));
		
		tempTree.insertNode(rootValue, new Node(10));
		tempTree.insertNode(8, new Node(16));
		
		for(Node child: tempTree.root.children) {
			System.out.println(child.value);
		}
		
		tempTree.traverseTree(tempTree.root);
		
		System.out.println("Preorder Traversal");
		ArrayList<Node> preOrderList = tempTree.traversePreOrder();
		for(Node i: preOrderList) {
			System.out.print(i.value + "  ");
		}
		
		System.out.println("\nPostorder Traversal");
		ArrayList<Node> postOrderList = tempTree.traversePostOrder();
		for(Node i: postOrderList) {
			System.out.print(i.value + "  ");
		}

		System.out.println("\n");
		tempTree.updateValueOfNode(8, 80);
		//Update node 8 -> 80
		System.out.println("Preorder Traversal");
		preOrderList = tempTree.traversePreOrder();
		for(Node i: preOrderList) {
			System.out.print(i.value + "  ");
		}
		
		System.out.println("\nPostorder Traversal");
		postOrderList = tempTree.traversePostOrder();
		for(Node i: postOrderList) {
			System.out.print(i.value + "  ");
		}
	}
}
