package oop.ict.project.tree.balanced;

import java.util.ArrayList;

import oop.ict.project.shape.Circle;
import oop.ict.project.tree.exception.TreeException;
import oop.ict.project.tree.generic.GenericTree;
import oop.ict.project.tree.generic.Node;

public class BalancedTree extends GenericTree {

	private int limitDistance;
	private int minLeafDepth;
	private int maxLeafDepth;

	public BalancedTree() {
		super();
		this.limitDistance = 1; // Default value
		this.minLeafDepth = 1; // this is initialize for
		this.maxLeafDepth = 1; // a tree with only root
	}

	public BalancedTree(Node root) {
		super(root);
		this.limitDistance = 1; // Default value
		this.minLeafDepth = 1; // this is initialize for
		this.maxLeafDepth = 1; // a tree with only root
	}

	public BalancedTree(Circle rootValue) {
		super(rootValue);
		this.limitDistance = 1; // Default value
		this.minLeafDepth = 1;
		this.maxLeafDepth = 1;
	}

	public int getLimitDistance() {
		return limitDistance;
	}

	public void setLimitDistance(int limitDistance) {
		this.limitDistance = limitDistance;
	}

	public int getMinLeafDepth() {
		return minLeafDepth;
	}

	public int getMaxLeafDepth() {
		return maxLeafDepth;
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
				newNode.setDepth(searchNodeList.get(searchNodeList.size() - 1).getDepth() + 1);
				searchNodeList.get(searchNodeList.size() - 1).addChild(newNode);
				searchNodeList.add(newNode);
				this.updateMaxMin(this.root);
				if (this.maxLeafDepth - this.minLeafDepth > this.limitDistance) {
					searchNodeList.get(searchNodeList.size()-2).children.remove(newNode);
					this.updateMaxMin(this.root);
					throw new TreeException("Cannot insert because the new node make the tree imbalance");
				} else {
					return searchNodeList;
				}
			} else {
				throw new TreeException("Cannot find node with value " + parentValue);
			}
		} else {
			throw new TreeException(
					"Node with value " + newNode.rootCircle.getSearchKey() + "already exists in the tree.");
		}
	}

	@Override
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
				Node parentNode = getParentNode(root, value);
				Integer indexOfDeleteNode = parentNode.children.indexOf(foundNode);
				if (foundNode.getNbChildren()==0) {
					parentNode.children.remove(foundNode);
					this.updateMaxMin(this.root);
					if(this.maxLeafDepth-this.minLeafDepth>this.limitDistance) {
						parentNode.children.add(indexOfDeleteNode, foundNode);
						this.updateMaxMin(this.root);
						throw new TreeException("Cannot delete because it make the tree imbalance");
					}
					return foundDeleteNodeList;
				} else {
					parentNode.children.remove(foundNode);
					for (int i = 0; i < foundNode.children.size(); i++) {
						parentNode.children.add(indexOfDeleteNode + i, foundNode.children.get(i));
						foundDeleteNodeList.add(foundNode.children.get(i));
					}
					this.updateDepth(this.root);
					this.updateMaxMin(this.root);
					if(this.maxLeafDepth-this.minLeafDepth>this.limitDistance) {
						for (int i = 0; i < foundNode.children.size(); i++) {
							parentNode.children.remove(foundNode.children.get(i));
							foundDeleteNodeList.remove(foundNode.children.get(i));
						}
						parentNode.children.add(indexOfDeleteNode, foundNode);
						this.updateDepth(this.root);
						this.updateMaxMin(this.root);
						throw new TreeException("Cannot delete because it make the tree imbalance");
					}
					return foundDeleteNodeList;
				}
			}
		} else {
			throw new TreeException("Cannot find node with value " + value);
		}
	}

	public void updateMaxMin(Node root) {
		if (root == this.root) {
			if (root.getNbChildren() == 0) {
				this.minLeafDepth = 1;
				this.maxLeafDepth = 1;
				return;
			} else {
				this.minLeafDepth = 999999;
				this.maxLeafDepth = -1;
			}
		}
		if (root.getNbChildren() == 0 && root != this.root) {
			if (root.getDepth() < this.minLeafDepth)
				this.minLeafDepth = root.getDepth();
			if (root.getDepth() > this.maxLeafDepth)
				this.maxLeafDepth = root.getDepth();
		}
		for (Node child : root.children)
			updateMaxMin(child);
	}

}
