package oop.ict.project.tree;

import java.util.ArrayList;
import oop.ict.project.shape.Circle;

/*								Generic Tree
 * 									4
 * 			/				/				\			\
 * 			8				1				2			10
 * 		/		\		/		\		/	|	\	
 * 		9		16		14		6		7	5	3
 */

public class TestGenericTree {
	public static void main(String args[]) {

		GenericTree tempTree = new GenericTree(new Node(4));
		Integer rootValue = tempTree.root.rootCircle.getSearchKey();
		try {
			Node tempNode = tempTree.insertNode(rootValue, new Node(8));
			tempNode = tempTree.insertNode(rootValue, new Node(1));
			tempNode = tempTree.insertNode(rootValue, new Node(2));
			tempNode = tempTree.insertNode(8, new Node(9));
			tempNode = tempTree.insertNode(1, new Node(14));
			tempNode = tempTree.insertNode(1, new Node(6));
			tempNode = tempTree.insertNode(2, new Node(7));
			tempNode = tempTree.insertNode(2, new Node(5));
			tempNode = tempTree.insertNode(2, new Node(3));

			tempNode = tempTree.insertNode(rootValue, new Node(10));
			tempNode = tempTree.insertNode(8, new Node(16));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		

		for (Node child : tempTree.root.children) {
			System.out.println(child.rootCircle.getSearchKey());
		}

		tempTree.traverseTree(tempTree.root);

		System.out.println("Preorder Traversal");
		ArrayList<Node> preOrderList = tempTree.traversePreOrder();
		for (Node i : preOrderList) {
			System.out.print(i.rootCircle.getSearchKey() + "  ");
		}

		System.out.println("\nPostorder Traversal");
		ArrayList<Node> postOrderList = tempTree.traversePostOrder();
		for (Node i : postOrderList) {
			System.out.print(i.rootCircle.getSearchKey() + "  ");
		}

		System.out.println("\n");
		
		try { // Update node 8 -> 80
			Node tempNode = tempTree.updateValueOfNode(8, 80); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
		System.out.println("Preorder Traversal");
		preOrderList = tempTree.traversePreOrder();
		for (Node i : preOrderList) {
			System.out.print(i.rootCircle.getSearchKey() + "  ");
		}

		System.out.println("\nPostorder Traversal");
		postOrderList = tempTree.traversePostOrder();
		for (Node i : postOrderList) {
			System.out.print(i.rootCircle.getSearchKey() + "  ");
		}
	}
}
