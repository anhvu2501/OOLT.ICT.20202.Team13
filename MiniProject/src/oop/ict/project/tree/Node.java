package oop.ict.project.tree;

import java.util.ArrayList;

import oop.ict.project.shape.Circle;
//import javafx.scene.shape.Circle;

public class Node {
	public Circle rootCircle;
	public ArrayList<Node> children = new ArrayList<>();
	public boolean highlightFlag;

	/**
	 * A binary tree using circle objects.
	 * 
	 * @param rootCircle a root tree circle
	 * @param children   : array of children
	 */

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

}