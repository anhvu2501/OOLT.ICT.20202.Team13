
package oop.ict.project.tree.generic;

import oop.ict.project.shape.Circle;

import java.util.ArrayList;
//import javafx.scene.shape.Circle;

public class Node {
	private int depth;
    private Circle rootCircle;
    private ArrayList<Node> children = new ArrayList<>();
    private boolean highlightFlag;

    public Circle getRootCircle() {
		return rootCircle;
	}

	public void setRootCircle(Circle rootCircle) {
		this.rootCircle = rootCircle;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public Node(Integer key) {
        this.rootCircle = new Circle(key);
    }

    public Node(Circle rootCircle) {
        this.rootCircle = rootCircle;
    }

    public void setValue(Integer value) {
        this.rootCircle.setSearchKey(value);
    }

    public void addChild(Node child) {
        this.children.add(child);
    }
    
    public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public int getNbChildren() {
		return this.children.size();
	}
}