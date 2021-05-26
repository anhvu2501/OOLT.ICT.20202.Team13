package oop.ict.project.tree.generic;

import oop.ict.project.shape.Circle;

import java.util.ArrayList;
//import javafx.scene.shape.Circle;

public class Node {
	private int depth;
    public Circle rootCircle;
    public ArrayList<Node> children = new ArrayList<>();
    public boolean highlightFlag;

    public Node() {

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