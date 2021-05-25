package oop.ict.project.tree.balanced;

import oop.ict.project.shape.Circle;
import oop.ict.project.tree.exception.TreeException;
import oop.ict.project.tree.generic.GenericTree;
import oop.ict.project.tree.generic.Node;

public class BalancedTree extends GenericTree {

	private int limitDistance;
	private int minLeafDepth;
	private int MaxLeafDepth;
	public BalancedTree() {
		super();
		this.limitDistance=1;  //Default value
		this.minLeafDepth= 1000000; // this is initialize for
		this.MaxLeafDepth= -1;		// a tree with only root
	}
	
	public BalancedTree(Node root) {
		super(root);
		this.limitDistance=1;  //Default value
		this.minLeafDepth= 1000000; // this is initialize for 
		this.MaxLeafDepth= -1;		// a tree with only root
	}
	
	public BalancedTree(Circle rootValue) {
		super(rootValue);
		this.limitDistance=1; //Default value
		this.minLeafDepth=1;
		this.MaxLeafDepth=1;
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
		return MaxLeafDepth;
	}

	@Override
	public Node insertNode(Integer parentValue, Node newNode) throws TreeException {
        boolean isParentInTree = isInTree(root, parentValue);
        if (isParentInTree) {
            Node foundParentNode = searchNode(root, parentValue);
            if(foundParentNode.getDepth()+1-this.minLeafDepth<=this.limitDistance) {
	            newNode.setDepth(foundParentNode.getDepth()+1);
	            foundParentNode.children.add(newNode);
	            updateMaxMin(this.root);
	            return newNode;
            }else {
            	throw new TreeException("Cannot insert! The node make the tree imbalanced");
            }
        } else {
            throw new TreeException("Cannot find node with value " + parentValue);
        }
    }
	
	public void updateMaxMin(Node root) {
		if(root.getNbChildren()==0 && root!=this.root) {
			if(root.getDepth()<this.minLeafDepth)
				this.minLeafDepth=root.getDepth();
			if(root.getDepth()>this.MaxLeafDepth)
				this.MaxLeafDepth=root.getDepth();
		}
		for(Node child: root.children)
			updateMaxMin(child);
	}

}
