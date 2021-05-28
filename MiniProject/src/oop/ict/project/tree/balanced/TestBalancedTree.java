package oop.ict.project.tree.balanced;

import java.util.ArrayList;

import oop.ict.project.tree.generic.Node;

public class TestBalancedTree {
		public static void main(String args[]) {

			BalancedTree tempTree = new BalancedTree(new Node(4));
			tempTree.setLimitDistance(2);
			Integer rootValue = tempTree.root.rootCircle.getSearchKey();
			try {
//				Node tempNode = tempTree.insertNode(rootValue, new Node(8));
//				tempNode = tempTree.insertNode(rootValue, new Node(1));
//				tempNode = tempTree.insertNode(rootValue, new Node(2));
//				tempNode = tempTree.insertNode(rootValue, new Node(10));
//				tempNode = tempTree.insertNode(8, new Node(9));
//				tempNode = tempTree.insertNode(8, new Node(16));
//				tempNode = tempTree.insertNode(1, new Node(14));
//				tempNode = tempTree.insertNode(1, new Node(6));
//				tempNode = tempTree.insertNode(2, new Node(7));
//				tempNode = tempTree.insertNode(2, new Node(5));
//				tempNode = tempTree.insertNode(2, new Node(3));
//				tempNode = tempTree.insertNode(9, new Node(22));
				//Insertion below make the tree imbalance
//				tempNode = tempTree.insertNode(22, new Node(33));

				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(e.getStackTrace());
			}
			
			System.out.println("Preorder Traversal");
			ArrayList<Node> preOrderList = tempTree.traversePreOrder();
			for (Node i : preOrderList) {
				System.out.print(i.rootCircle.getSearchKey() + "  ");
			}

		}

}
