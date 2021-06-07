package oop.ict.project.tree.binary;

import oop.ict.project.shape.Circle;
import oop.ict.project.tree.exception.TreeException;
import oop.ict.project.tree.generic.GenericTree;
import oop.ict.project.tree.generic.Node;

import java.util.ArrayList;

public class BinaryTree extends GenericTree {
	final int MAX_NB_CHILDREN = 2;

	public BinaryTree() {
		super();
	}

	public BinaryTree(Node root) {
		super(root);
	}

	public BinaryTree(Circle rootValue) {
		super(rootValue);
	}

	public BinaryTree createBinaryTree() {
		return new BinaryTree();
	}

	@Override
	public ArrayList<Node> insertNode(Integer parentValue, Node newNode) throws TreeException {
		boolean isNodeInTree = isInTree(root, newNode.getRootCircle().getSearchKey());
		if (!isNodeInTree) {
			boolean isParentInTree = isInTree(root, parentValue);
			if (isParentInTree) {
				ArrayList<Node> searchParentNodeList = new ArrayList<>();
				searchParentNodeList.add(root);
				ArrayList<Node> searchNodeList = searchNode(searchParentNodeList, parentValue);
				if (searchNodeList.get(searchNodeList.size() - 1).getChildren().size() < this.MAX_NB_CHILDREN) {
					newNode.setDepth(searchNodeList.get(searchNodeList.size() - 1).getDepth() + 1);
					searchNodeList.get(searchNodeList.size() - 1).addChild(newNode);
				} else
					throw new TreeException("Only can input max 2 nodes!!");
				searchNodeList.add(newNode);
				this.updateDepth(this.root);
				return searchNodeList;
			} else
				throw new TreeException("Cannot find node with value " + parentValue);
		} else
			throw new TreeException(
					"Node with value " + newNode.getRootCircle().getSearchKey() + " already exists in the tree");
	}

	public Node getDeepestNode() {
		Node temp = root;
		while (temp.getChildren().get(temp.getChildren().size() - 1) != null) {
			temp = temp.getChildren().get(temp.getChildren().size() - 1);
		}
		if (temp.getChildren().get(0) != null) {
			temp.getChildren().remove(0);
			return temp.getChildren().get(0);
		} else {
			temp.getChildren().remove(temp.getChildren().size() - 1);
			return temp;
		}
	}

	public ArrayList<Node> deleteNode(Integer value) throws TreeException {
		boolean isInTree = isInTree(root, value);
		if (isInTree) {
			if (value == root.getRootCircle().getSearchKey()) {
				throw new TreeException("Cannot delete root node. You can create new tree to clear old tree.");
			} else {
				ArrayList<Node> foundDeleteNodeList = new ArrayList<>();
				foundDeleteNodeList.add(root);
				foundDeleteNodeList = searchNode(foundDeleteNodeList, value);
				Node foundNode = foundDeleteNodeList.get(foundDeleteNodeList.size() - 1);
				Node parentNode = getParentNode(root, value);
				foundDeleteNodeList.remove(foundNode);
				if(foundNode.getChildren().size() != 0) {
					Integer indexOfDeteleNode = parentNode.getChildren().indexOf(foundNode);
					Node firstChildOfDeleteNode = foundNode.getChildren().get(0);
					parentNode.getChildren().add(indexOfDeteleNode, firstChildOfDeleteNode);
					foundDeleteNodeList.add(firstChildOfDeleteNode);
					if(foundNode.getChildren().size() == 2) {
						firstChildOfDeleteNode.getChildren().add(foundNode.getChildren().get(1));
					}
				} 
				parentNode.getChildren().remove(foundNode);
				this.updateDepth(this.root);
				return foundDeleteNodeList;
			}
		} else {
			throw new TreeException("Cannot find node with value " + value);
		}
	}
	
	public BinaryTree cloneTree() {
		Node newRootNode = root.cloneNode(root);
		BinaryTree newTree = new BinaryTree(newRootNode);
		return newTree;
	}
	
}
