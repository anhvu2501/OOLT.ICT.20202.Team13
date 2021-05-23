package oop.ict.project.tree;

import java.util.ArrayList;
import java.util.Stack;

public class GenericTree {
	public Node root;

	public GenericTree() {
		this.root = null;
	}

	public GenericTree(Node root) {
		this.root = root;
	}
	
	public GenericTree(int rootValue) {
		this.root = new Node(rootValue);
	}

	public GenericTree createGenericTree() {
		return new GenericTree();
	}

	public boolean insertNode(int parentValue, Node newNode) {
		boolean isParentInTree = isInTree(root, parentValue);
		if (isParentInTree) {
			Node foundParentNode = searchNode(root, parentValue);
			foundParentNode.children.add(newNode);
			return true;
		} else {
			System.out.println("Cannot find node with value " + parentValue);
			return false;
		}
	}

//	public boolean deleteNode(int value) {  
//		Node foundDeleteNode = root;
//		boolean foundNode = searchNode(root, value, foundDeleteNode);
//		if (foundNode) {
//			
//			return true;
//		} else {
//			System.out.println("Cannot find node with value " + value);
//			return false;
//		}
//	}
//	
	
	public boolean updateValueOfNode(int currentValue, int newValue) {
		//Node foundUpdateNode = new Node();
		boolean isUpdateInTree = isInTree(root, currentValue);
		if (isUpdateInTree) {
			Node foundUpdateNode = searchNode(root, currentValue);
			foundUpdateNode.setValue(newValue);
			System.out.println("Node with value " + currentValue + 
								" is updated to " + newValue);
			return true;
		} else {
			System.out.println("Cannot find node with value " + currentValue);
			return false;
		}
	}
	
	private class Pair{
		private Node node;
		private int state;
		
		private Pair(Node node, int state){
			this.node = node;
			this.state = state;
		}
	}
	
	public ArrayList<Node> traversePreOrder() {
		Stack<Pair> stack = new Stack<>();
		stack.push(new Pair(root, -1));
		
		ArrayList<Node> preOrderList = new ArrayList<>();
		while(stack.size() > 0) {
			Pair top = stack.peek();
			if(top.state == -1) {
				preOrderList.add(top.node);
				top.state ++;
			}
			else if(top.state == top.node.children.size()) {
				stack.pop();
			}
			else {
				Pair cp = new Pair(top.node.children.get(top.state), -1);
				stack.push(cp);
				top.state++;
			}
		}
		return preOrderList;
	}
	
	public ArrayList<Node> traversePostOrder() {
		Stack<Pair> stack = new Stack<>();
		stack.push(new Pair(root, -1));
		
		ArrayList<Node> postOrderList = new ArrayList<>();
		while(stack.size() > 0) {
			Pair top = stack.peek();
			if(top.state == -1) {
				top.state ++;
			}
			else if(top.state == top.node.children.size()) {
				postOrderList.add(top.node);
				stack.pop();
			}
			else {
				Pair cp = new Pair(top.node.children.get(top.state), -1);
				stack.push(cp);
				top.state++;
			}
		}
		return postOrderList;
	}
	
	public void traverseTree(Node rootNode) {
		System.out.println("Node Pre " + rootNode.value);
		for(Node child: rootNode.children) {
			System.out.println("Edge Pre " + rootNode.value + "--" + child.value);
			traverseTree(child);
			System.out.println("Edge Post " + rootNode.value + "--" + child.value);
			
		}
		System.out.println("Node Post " + rootNode.value);
	}
	

	public boolean isInTree(Node node, int key) {
		if (node.value == key) {
			return true;
		}
		for (Node child : node.children) {
			boolean found = isInTree(child, key);
			if (found) {
				return true;
			}
		}
		return false;
	}
	
	public Node searchNode(Node node, int key) {
		if (node.value == key) {
			return node;
		}

		for (Node child : node.children) {
			Node foundNode = searchNode(child, key);
			if (foundNode != null) {
				return foundNode;
			}
		}

		return null;
	}

	

}
