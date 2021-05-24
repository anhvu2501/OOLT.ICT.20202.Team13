package oop.ict.project.tree.binary;

import oop.ict.project.shape.Circle;

//Node for Binary Tree
public class NodeBinary {
    public Circle rootCircle;
    public NodeBinary left;
    public NodeBinary right;
    public boolean highlightFlag;

    public NodeBinary(Circle rootCircle) {
        this.rootCircle = rootCircle;
        this.left = null;
        this.right = null;
    }

    public NodeBinary(int key) {
        this.rootCircle = new Circle(key);
        this.left = null;
        this.right = null;
    }
}
