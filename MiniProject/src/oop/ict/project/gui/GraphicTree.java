package oop.ict.project.gui;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import oop.ict.project.tree.balanced.BalancedTree;
import oop.ict.project.tree.binary.BinaryTree;
import oop.ict.project.tree.exception.TreeException;
import oop.ict.project.tree.generic.GenericTree;
import oop.ict.project.tree.generic.Node;
import oop.ict.project.shape.Line;

public class GraphicTree extends Canvas {

	private GenericTree genericTree; // type1
	private BinaryTree binaryTree; // type2
	private BalancedTree balancedTree; // type3
//	private BalancedBinaryTree balancedBinaryTree; // type4
	private GenericTree mainTree;
	private static final int numberLayer = 8;
	private ArrayList<Node> highlightList = new ArrayList<Node>();

	public void switchTree(Integer type) {
		if (type == 1) {
			this.mainTree = this.genericTree;
		} else if (type == 2) {
			this.mainTree = this.binaryTree;
		} else if (type == 3) {
			this.mainTree = this.balancedTree;
		}
	}

	public GraphicTree() throws TreeException {
		this.genericTree = new GenericTree();
		this.binaryTree = new BinaryTree();
		this.balancedTree = new BalancedTree();
		this.mainTree = this.genericTree;
		widthProperty().addListener(evt -> drawTree());
		heightProperty().addListener(evt -> drawTree());
	}

	public void drawTree() {
		GraphicsContext gc = this.getGraphicsContext2D();
		clear();
		if (this.mainTree.root != null) {
			drawLines(gc, this.mainTree.root, 0, this.getWidth(), 0, this.getHeight() / this.numberLayer);
			drawCircles(gc, this.mainTree.root, 0, this.getWidth(), 0, this.getHeight() / this.numberLayer);
		}
	}

	public void drawCircles(GraphicsContext gc, Node root, double minWidth, double maxWidth, double minHeight,
			double maxHeight) {
		Point2D point = new Point2D((minWidth + maxWidth) / 2, (minHeight + maxHeight) / 2);
		root.rootCircle.setPoint(point);
		root.rootCircle.draw(gc);
		for (int i = 0; i < root.getNbChildren(); i++) {
			drawCircles(gc, root.children.get(i), minWidth + i * (maxWidth - minWidth) / root.getNbChildren(),
					minWidth + (i + 1) * (maxWidth - minWidth) / root.getNbChildren(), maxHeight,
					2 * maxHeight - minHeight);
		}
	}

	public void drawLines(GraphicsContext gc, Node root, double minWidth, double maxWidth, double minHeight,
			double maxHeight) {
		Point2D linePoint1; // Point_1
		Point2D linePoint2; // Point_2
		Line newLine = new Line(); // Blank line
		linePoint1 = new Point2D((minWidth + maxWidth) / 2, (minHeight + maxHeight) / 2);
		if (root.getNbChildren() != 0) {
			for (int i = 0; i < root.getNbChildren(); i++) {
				linePoint2 = new Point2D(
						(2 * minWidth + (2 * i + 1) * (maxWidth - minWidth) / root.getNbChildren()) / 2,
						(3 * maxHeight - minHeight) / 2);
				newLine.setPoint(linePoint1, linePoint2);
				newLine.draw(gc);// Draw the line

				drawLines(gc, root.children.get(i), minWidth + i * (maxWidth - minWidth) / root.getNbChildren(),
						minWidth + (i + 1) * (maxWidth - minWidth) / root.getNbChildren(), maxHeight,
						2 * maxHeight - minHeight);
			}
		}
	}

	public void drawHighlightSequence(ArrayList<Node> ar) {
		GraphicsContext gc = this.getGraphicsContext2D();
		Timeline timeline = new Timeline();
		KeyFrame kf = new KeyFrame(Duration.ZERO, evt -> {
			ar.get(0).rootCircle.setHighlighter(true);
			drawTree();
		});
		timeline.getKeyFrames().add(kf);
		for (Integer index = 1; index < ar.size(); index++) {
			final Integer i = index;
			KeyFrame kf1 = new KeyFrame(Duration.seconds(i), evt -> {
				ar.get(i - 1).rootCircle.setHighlighter(false);
				ar.get(i).rootCircle.setHighlighter(true);
				drawTree();
			});
			timeline.getKeyFrames().add(kf1);
		}

		KeyFrame kf2 = new KeyFrame(Duration.seconds(ar.size()), evt -> {
			ar.get(ar.size() - 1).rootCircle.setHighlighter(false);
			drawTree();
		});
		timeline.getKeyFrames().add(kf2);
		timeline.play();

	}

	public void clear() {
		getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
	}

}
