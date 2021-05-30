
package oop.ict.project.tree.generic;

import oop.ict.project.shape.Circle;
import oop.ict.project.tree.exception.TreeException;

import java.util.ArrayList;
import java.util.Stack;

public class GenericTree {
	public Node root;

	public GenericTree() {
		this.root = null;
	}

	public GenericTree(Node root) {
		this.root = root;
		this.root.setDepth(1);
	}

	public GenericTree(Circle rootValue) {
		this.root.rootCircle = rootValue;
		this.root.setDepth(1);
	}

	public GenericTree createTree() {
		return new GenericTree();
	}

	public ArrayList<Node> insertNode(Integer parentValue, Node newNode) throws TreeException {
		boolean isNodeInTree = isInTree(root, newNode.rootCircle.getSearchKey());
		if (!isNodeInTree) {
			boolean isParentInTree = isInTree(root, parentValue);
			if (isParentInTree) {
				ArrayList<Node> searchParentNodeList = new ArrayList<>();
				searchParentNodeList.add(root);
				ArrayList<Node> searchNodeList = searchNode(searchParentNodeList, parentValue);
				searchNodeList.get(searchNodeList.size() - 1).addChild(newNode);
				searchNodeList.add(newNode);
				this.updateDepth(this.root);
				return searchNodeList;
			} else {
				throw new TreeException("Cannot find node with value " + parentValue);
			}
		} else {
			throw new TreeException(
					"Node with value " + newNode.rootCircle.getSearchKey() + " already exists in the tree.");
		}
	}

	public Node getParentNode(Node node, Integer key) {
		ArrayList<Node> foundNodeList = new ArrayList<>();
		foundNodeList.add(root);
		if (node.children.contains(searchNode(foundNodeList, key).get(foundNodeList.size() - 1))) {
			return node;
		}

		for (Node child : node.children) {
			Node foundNode = getParentNode(child, key);
			if (foundNode != null) {
				return foundNode;
			}
		}

		return null;
	}

	public ArrayList<Node> deleteNode(Integer value) throws TreeException {
		boolean isInTree = isInTree(root, value);
		if (isInTree) {
			if (value == root.rootCircle.getSearchKey()) {
				throw new TreeException("Cannot delete root node. You can create new tree to clear old tree.");
			} else {
				ArrayList<Node> foundDeleteNodeList = new ArrayList<>();
				foundDeleteNodeList.add(root);
				foundDeleteNodeList = searchNode(foundDeleteNodeList, value);
				Node foundNode = foundDeleteNodeList.get(foundDeleteNodeList.size() - 1);
				foundDeleteNodeList.remove(foundNode);
				if (foundNode.children == null) {
					foundNode = null;
					return foundDeleteNodeList;
				} else {
					Node parentNode = getParentNode(root, value);
					Integer indexOfDeteleNode = parentNode.children.indexOf(foundNode);
					for (int i = 0; i < foundNode.children.size(); i++) {
						parentNode.children.add(indexOfDeteleNode + i, foundNode.children.get(i));
						foundDeleteNodeList.add(foundNode.children.get(i));
					}
					parentNode.children.remove(foundNode);
					foundNode = null;
					return foundDeleteNodeList;
				}
			}
		} else {
			throw new TreeException("Cannot find node with value " + value);
		}
	}

	public ArrayList<Node> updateValueOfNode(Integer currentValue, Integer newValue) throws TreeException {
		boolean isUpdateInTree = isInTree(root, currentValue);
		if (isUpdateInTree) {
			ArrayList<Node> foundUpdateNodeList = new ArrayList<>();
			foundUpdateNodeList.add(root);
			foundUpdateNodeList = searchNode(foundUpdateNodeList, currentValue);
			foundUpdateNodeList.get(foundUpdateNodeList.size() - 1).rootCircle.setSearchKey(newValue);

			return foundUpdateNodeList;
		} else {
			throw new TreeException("Cannot find node with value " + currentValue);
		}
	}

	private class Pair {
		private Node node;
		private int state;

		private Pair(Node node, Integer state) {
			this.node = node;
			this.state = state;
		}
	}

	public ArrayList<Node> traversePreOrder() {
		Stack<Pair> stack = new Stack<>();
		stack.push(new Pair(root, -1));

		ArrayList<Node> preOrderList = new ArrayList<>();
		while (stack.size() > 0) {
			Pair top = stack.peek();
			if (top.state == -1) {
				preOrderList.add(top.node);
				top.state++;
			} else if (top.state == top.node.children.size()) {
				stack.pop();
			} else {
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
		while (stack.size() > 0) {
			Pair top = stack.peek();
			if (top.state == -1) {
				top.state++;
			} else if (top.state == top.node.children.size()) {
				postOrderList.add(top.node);
				stack.pop();
			} else {
				Pair cp = new Pair(top.node.children.get(top.state), -1);
				stack.push(cp);
				top.state++;
			}
		}
		return postOrderList;
	}

	// for testing only
	public void traverseTree(Node rootNode) {
		System.out.println("Node Pre " + rootNode.rootCircle.getSearchKey());
		for (Node child : rootNode.children) {
			System.out
					.println("Edge Pre " + rootNode.rootCircle.getSearchKey() + "--" + child.rootCircle.getSearchKey());
			traverseTree(child);
			System.out.println(
					"Edge Post " + rootNode.rootCircle.getSearchKey() + "--" + child.rootCircle.getSearchKey());

		}
		System.out.println("Node Post " + rootNode.rootCircle.getSearchKey());
	}

	public boolean isInTree(Node node, Integer key) {
		if (node.rootCircle.getSearchKey() == key) {
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

	public ArrayList<Node> searchNode(ArrayList<Node> listNodes, Integer key) { // listNodes contains 1 Node: root
		if (listNodes.get(listNodes.size() - 1).rootCircle.getSearchKey() == key) {
			return listNodes;
		}

		for (Node child : listNodes.get(listNodes.size() - 1).children) {
			listNodes.add(child);
			ArrayList<Node> foundListNodes = searchNode(listNodes, key);
			if (foundListNodes.get(listNodes.size() - 1).rootCircle.getSearchKey() == key) {
				return foundListNodes;
			}
		}

		return listNodes; // ????
	}
	
	public void updateDepth(Node root) {
		if(root==this.root) {
			this.root.setDepth(1);
		}
		for (Node child : root.children) {
			child.setDepth(root.getDepth()+1);
			updateDepth(child);
		}
	}

}
