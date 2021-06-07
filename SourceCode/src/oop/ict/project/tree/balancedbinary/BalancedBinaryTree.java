package oop.ict.project.tree.balancedbinary;

import oop.ict.project.shape.Circle;
import oop.ict.project.tree.binary.BinaryTree;
import oop.ict.project.tree.exception.TreeException;
import oop.ict.project.tree.generic.Node;

import java.util.ArrayList;

public class BalancedBinaryTree extends BinaryTree {
    final int MAX_NB_CHILDREN = 2;
    private int limitDistance;
    private int minLeafDepth;
    private int maxLeafDepth;

    public BalancedBinaryTree() {
        super();
        this.limitDistance = 1;
        this.minLeafDepth = 1;
        this.maxLeafDepth = 1;
    }

    public BalancedBinaryTree(Node root) {
        super(root);
        this.limitDistance = 1;
        this.minLeafDepth = 1;
        this.maxLeafDepth = 1;
    }

    public BalancedBinaryTree(Circle newCircle) {
        super(newCircle);
        this.limitDistance = 1;
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
                    throw new TreeException("Only can input max 2 nodes");
                searchNodeList.add(newNode);
                this.updateMaxMin(this.root);
                this.updateDepth(this.root);
                if (this.maxLeafDepth - this.minLeafDepth > this.limitDistance) {
                    searchNodeList.get(searchNodeList.size() - 2).getChildren().remove(newNode);
                    this.updateMaxMin(this.root);
                    throw new TreeException("The node inserted makes the tree unbalanced");
                } else
                    return searchNodeList;
            } else
                throw new TreeException("Cannot find node with value " + parentValue);
        } else
            throw new TreeException("Node with value " + newNode.getRootCircle().getSearchKey()
                    + " already exists in the tree");
    }

    @Override
    public ArrayList<Node> deleteNode(Integer value) throws TreeException {
        boolean isInTree = isInTree(root, value);
        if (isInTree) {
            if (value == root.getRootCircle().getSearchKey()) {
                throw new TreeException("You cannot delete the root. You can create a new tree to clear the old tree.");
            } else {
                ArrayList<Node> foundDeleteNodeList = new ArrayList<>();
                foundDeleteNodeList.add(root);
                foundDeleteNodeList = searchNode(foundDeleteNodeList, value);
                Node foundNode = foundDeleteNodeList.get(foundDeleteNodeList.size() - 1);
                foundDeleteNodeList.remove(foundNode);
                Node parentNode = getParentNode(root, value);
                Integer indexOfDeleteNode = parentNode.getChildren().indexOf(foundNode);
                if (foundNode.getNbChildren()==0) {
                    parentNode.getChildren().remove(foundNode);
                    this.updateMaxMin(this.root);
                    if(this.maxLeafDepth-this.minLeafDepth>this.limitDistance) {
                        parentNode.getChildren().add(indexOfDeleteNode, foundNode);
                        this.updateMaxMin(this.root);
                        throw new TreeException("Cannot delete this node because it make the tree unbalanced");
                    }
                    return foundDeleteNodeList;
                } else {
                    parentNode.getChildren().remove(foundNode);
                    for (int i = 0; i < foundNode.getChildren().size(); i++) {
                        parentNode.getChildren().add(indexOfDeleteNode + i, foundNode.getChildren().get(i));
                        foundDeleteNodeList.add(foundNode.getChildren().get(i));
                    }
                    this.updateDepth(this.root);
                    this.updateMaxMin(this.root);
                    if(this.maxLeafDepth-this.minLeafDepth>this.limitDistance) {
                        for (int i = 0; i < foundNode.getChildren().size(); i++) {
                            parentNode.getChildren().remove(foundNode.getChildren().get(i));
                            foundDeleteNodeList.remove(foundNode.getChildren().get(i));
                        }
                        parentNode.getChildren().add(indexOfDeleteNode, foundNode);
                        this.updateDepth(this.root);
                        this.updateMaxMin(this.root);
                        throw new TreeException("Cannot delete this node because it make the tree unbalanced");
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
        for (Node child : root.getChildren())
            updateMaxMin(child);
    }
}
