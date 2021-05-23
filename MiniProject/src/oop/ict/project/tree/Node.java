package oop.ict.project.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
//import javafx.scene.shape.Circle;

public class Node {
	public int value;
	public ArrayList<Node> children = new ArrayList<>();
	public boolean highlightFlag;
	
	/**
	 * A binary tree using circle objects.
	 * @param rootCircle a root tree circle
	 * @param children : array of children
	 */
	
	public Node() {
		this.value = 0;
	}
	
	public Node(int value) {
		this.value = value;
	}
	
	public Node(int value, ArrayList<Node> children) {
		this.value = value;
		this.children = children;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	public void addChild(Node child) {
		this.children.add(child);
	}
	
}