package oop.ict.project.tree.balancedbinary;

import oop.ict.project.shape.Circle;
import oop.ict.project.tree.binary.BinaryTree;
import oop.ict.project.tree.exception.TreeException;
import oop.ict.project.tree.generic.Node;

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

    public BalancedBinaryTree (Circle newCircle) {
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
    public Node insertNode(Integer parentValue, Node newNode) throws TreeException {
        boolean isParentInTree = isInTree(root, parentValue);
        if (isParentInTree) {
            Node foundParentNode = searchNode(root, parentValue);
            if (foundParentNode.getDepth() + 1 - this.minLeafDepth <= this.limitDistance && foundParentNode
                    .getNbChildren() < this.MAX_NB_CHILDREN) {
                newNode.setDepth(foundParentNode.getDepth() + 1);
                foundParentNode.children.add(newNode);
                updateMaxMin(this.root);
                return newNode;
            } else {
                throw new TreeException("Cannot insert! There only has 2 nodes");
            }
        } else {
            throw new TreeException("Cannot find node with value " + parentValue);
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
