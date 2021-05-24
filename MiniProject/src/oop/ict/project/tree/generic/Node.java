package oop.ict.project.tree.generic;

import oop.ict.project.shape.Circle;

import java.util.ArrayList;
//import javafx.scene.shape.Circle;

public class Node {
    public Circle rootCircle;
    public ArrayList<Node> children = new ArrayList<>();
    public boolean highlightFlag;

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