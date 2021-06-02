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
		boolean isNodeInTree = isInTree(root, newNode.rootCircle.getSearchKey());
		if (!isNodeInTree) {
			boolean isParentInTree = isInTree(root, parentValue);
			if (isParentInTree) {
				ArrayList<Node> searchParentNodeList = new ArrayList<>();
				searchParentNodeList.add(root);
				ArrayList<Node> searchNodeList = searchNode(searchParentNodeList, parentValue);
				if (searchNodeList.get(searchNodeList.size() - 1).children.size() < this.MAX_NB_CHILDREN) {
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
					"Node with value " + newNode.rootCircle.getSearchKey() + " already exists in the tree");
	}

	public Node getDeepestNode() {
		Node temp = root;
		while (temp.children.get(temp.children.size() - 1) != null) {
			temp = temp.children.get(temp.children.size() - 1);
		}
		if (temp.children.get(0) != null) {
			temp.children.remove(0);
			return temp.children.get(0);
		} else {
			temp.children.remove(temp.children.size() - 1);
			return temp;
		}
	}

//    public ArrayList<Node> deleteNodeBinary(Integer value) throws TreeException {
//        boolean isInTree = isInTree(root, value);
//        if (isInTree) {
//            if (value == root.rootCircle.getSearchKey())
//                throw new TreeException("Cannot delete root node. You can clear the old tree to create a new one");
//            else {
//                ArrayList<Node> foundDeleteNodeList = new ArrayList<>();
//                foundDeleteNodeList.add(root);
//                foundDeleteNodeList = searchNode(foundDeleteNodeList, value);
//                Node foundNode = foundDeleteNodeList.get(foundDeleteNodeList.size() - 1);
//                foundDeleteNodeList.remove(foundNode);
//                foundDeleteNodeList.add(getDeepestNode());
//                return foundDeleteNodeList;
//            }
//        } else
//            throw new TreeException("Cannot find node with value " + value);
//    }

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
				Node parentNode = getParentNode(root, value);
				foundDeleteNodeList.remove(foundNode);
				if(foundNode.children.size() != 0) {
					Integer indexOfDeteleNode = parentNode.children.indexOf(foundNode);
					Node firstChildOfDeleteNode = foundNode.children.get(0);
					parentNode.children.add(indexOfDeteleNode, firstChildOfDeleteNode);
					foundDeleteNodeList.add(firstChildOfDeleteNode);
					if(foundNode.children.size() == 2) {
						firstChildOfDeleteNode.children.add(foundNode.children.get(1));
					}
				} 
				parentNode.children.remove(foundNode);
				return foundDeleteNodeList;
			}
		} else {
			throw new TreeException("Cannot find node with value " + value);
		}
	}
	
}
