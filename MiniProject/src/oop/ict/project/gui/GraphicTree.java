package oop.ict.project.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import oop.ict.project.shape.Line;
import oop.ict.project.tree.balanced.BalancedTree;
import oop.ict.project.tree.balancedbinary.BalancedBinaryTree;
import oop.ict.project.tree.binary.BinaryTree;
import oop.ict.project.tree.exception.TreeException;
import oop.ict.project.tree.generic.GenericTree;
import oop.ict.project.tree.generic.Node;

import java.util.ArrayList;

public class GraphicTree extends Canvas {

	private GenericTree genericTree; // type1
	private BinaryTree binaryTree; // type2
	private BalancedTree balancedTree; // type3
	private BalancedBinaryTree balancedBinaryTree; // type4
	private GenericTree mainTree;
	private static final Integer numberLayer = 8;
	private ArrayList<Node> nodeList = new ArrayList<Node>();
	public void switchTree(Integer type) {
		if (type == 1) {
			this.mainTree = this.genericTree;
		} else if (type == 2) {
			this.mainTree = this.binaryTree;
		} else if (type == 3) {
			this.mainTree = this.balancedTree;
		} else {
			this.mainTree = this.balancedBinaryTree;
		}
	}

	public void setLimit(Integer limit, Integer type) {
		if (type == 3) {
			this.balancedTree.setLimitDistance(limit);
		} else {
			this.balancedBinaryTree.setLimitDistance(limit);
		}
	}

	public void createEmptyTree(Integer type) {
		if (type == 1) {
			this.genericTree = new GenericTree();
			this.mainTree = this.genericTree;
		} else if (type == 2) {
			this.binaryTree = new BinaryTree();
			this.mainTree = this.binaryTree;
		} else if (type == 3) {
			this.balancedTree = new BalancedTree();
			this.mainTree = this.balancedTree;
		} else {
			this.balancedBinaryTree = new BalancedBinaryTree();
			this.mainTree = this.balancedBinaryTree;
		}
	}

	public GraphicTree() throws TreeException {
		this.genericTree = new GenericTree();
		this.binaryTree = new BinaryTree();
		this.balancedTree = new BalancedTree();
		this.balancedBinaryTree = new BalancedBinaryTree();
		this.mainTree = new GenericTree();
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
			KeyFrame kf1 = new KeyFrame(Duration.millis(500 * i), evt -> {
				ar.get(i - 1).rootCircle.setHighlighter(false);
				ar.get(i).rootCircle.setHighlighter(true);
				drawTree();
			});
			timeline.getKeyFrames().add(kf1);
		}

		KeyFrame kf2 = new KeyFrame(Duration.millis(ar.size() * 500), evt -> {
			ar.get(ar.size() - 1).rootCircle.setHighlighter(false);
			drawTree();
		});
		timeline.getKeyFrames().add(kf2);
		timeline.play();
	}

	public void insert(Integer parentValue, Integer keyValue) throws TreeException {
		nodeList.clear();
		nodeList.add(this.mainTree.root);
		nodeList = this.mainTree.searchNode(nodeList, parentValue);
		this.drawHighlightSequence(nodeList);
		final int numTime = nodeList.size(); // so lan de chay xong search
		System.out.println(nodeList.get(nodeList.size() - 1).getDepth());
		if (nodeList.get(nodeList.size() - 1).getDepth() == 8) {
			Alert er2 = new Alert(AlertType.INFORMATION, "The maximum tree's depth of this program is "
					+ this.numberLayer.toString() +"! You cannot insert more", ButtonType.OK);
			er2.setTitle("Error");
			er2.setHeaderText("");
			er2.show();
		} else {
			GraphicsContext gc = this.getGraphicsContext2D();
			Timeline timeline = new Timeline();
			KeyFrame kf = new KeyFrame(Duration.millis(500 * numTime), evt -> {
				try {
					nodeList = this.mainTree.insertNode(parentValue, new Node(keyValue));
				} catch (TreeException e) {
					Alert er2 = new Alert(AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
					er2.setTitle("Error");
					er2.setHeaderText("");
					er2.show();
				}
				nodeList.get(nodeList.size() - 1).rootCircle.setHighlighter(true);
				drawTree();
			});
			timeline.getKeyFrames().add(kf);
			KeyFrame kf1 = new KeyFrame(Duration.millis(500 * (numTime + 1)), evt -> {
				nodeList.get(nodeList.size() - 1).rootCircle.setHighlighter(false);
				drawTree();
			});
			timeline.getKeyFrames().add(kf1);
			timeline.play();
		}
	}

	public void search(Integer searchValue) {
		nodeList.clear();
		nodeList.add(this.mainTree.root);
		nodeList = this.mainTree.searchNode(nodeList, searchValue);
		final int numTime = nodeList.size(); // so lan de chay xong search
		this.drawHighlightSequence(nodeList);
		GraphicsContext gc = this.getGraphicsContext2D();
		Timeline timeline = new Timeline();
		KeyFrame kf = new KeyFrame(Duration.millis(500 * numTime), evt -> {
			if (nodeList.get(nodeList.size() - 1).rootCircle.getSearchKey() != searchValue) {
				Alert er2 = new Alert(AlertType.INFORMATION, "Cannot find the node with inserted key", ButtonType.OK);
				er2.setTitle("Error");
				er2.setHeaderText("");
				er2.show();
			}
		});
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	public void delete(Integer deleteValue) {
		nodeList.clear();
		nodeList.add(this.mainTree.root);
		nodeList = this.mainTree.searchNode(nodeList, deleteValue);
		this.drawHighlightSequence(nodeList);
		final int numTime = nodeList.size(); // so lan de chay xong search
		Node deleteNode = nodeList.get(nodeList.size()-1);
		Node parentNode = mainTree.getParentNode(mainTree.root, deleteValue);
		
		GraphicsContext gc = this.getGraphicsContext2D();
		Timeline timeline = new Timeline();
		KeyFrame kf = new KeyFrame(Duration.millis(500 * numTime), evt -> {
			try {
				nodeList = this.mainTree.deleteNode(deleteValue);
			} catch (TreeException e) {
				Alert er2 = new Alert(AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
				er2.setTitle("Error");
				er2.setHeaderText("");
				er2.show();
			}
			drawTree();
		});
		timeline.getKeyFrames().add(kf);
		if(deleteNode.children.size() > 0  && deleteNode != mainTree.root) {
			ArrayList<Node> childrenList = deleteNode.children;
			KeyFrame kf1 = new KeyFrame(Duration.millis(500 * (numTime + childrenList.size())), evt -> {
				this.drawHighlightSequence(childrenList);
				drawTree();
			});
			timeline.getKeyFrames().add(kf1);
		}
		timeline.play();
	}
	
	public void update(Integer oldValue, Integer newValue) throws TreeException{
		nodeList.clear();
		nodeList.add(this.mainTree.root);
		nodeList = this.mainTree.searchNode(nodeList, oldValue);
		final int numTime = nodeList.size(); // so lan de chay xong search
		this.drawHighlightSequence(nodeList);
		
		GraphicsContext gc = this.getGraphicsContext2D();
		Timeline timeline = new Timeline();
		KeyFrame kf = new KeyFrame(Duration.millis(500 * numTime), evt -> {
			try {
				nodeList = this.mainTree.updateValueOfNode(oldValue, newValue);
			} catch (TreeException e) {
				Alert er2 = new Alert(AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
				er2.setTitle("Error");
				er2.setHeaderText("");
				er2.show();
			}
			nodeList.get(nodeList.size() - 1).rootCircle.setHighlighter(true);
			drawTree();
		});
		timeline.getKeyFrames().add(kf);
		KeyFrame kf1 = new KeyFrame(Duration.millis(500 * (numTime + 1)), evt -> {
			nodeList.get(nodeList.size() - 1).rootCircle.setHighlighter(false);
			drawTree();
		});
		timeline.getKeyFrames().add(kf1);
		timeline.play();
	}

	public boolean isEmpty() {
		if (this.mainTree.root == null) {
			return true;
		}
		return false;
	}

	public boolean isEmptyForBalanced() {
		if (this.mainTree.root == null && ((this.balancedTree.getLimitDistance() == 1
				&& this.mainTree == this.balancedTree)
				|| (this.balancedBinaryTree.getLimitDistance() == 1 && this.mainTree == this.balancedBinaryTree)))
			return true;
		return false;
	}

	public void setRoot(Integer key) {
		Node temp = new Node(key);
		temp.setDepth(1);
		this.mainTree.root = temp;
		this.drawTree();
	}

	public void clear() {
		getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
	}
    public void preorderList() {
        nodeList.clear();
        nodeList = this.mainTree.traversePreOrder();
        this.drawHighlightSequence(nodeList);
    }

    public void postorderList() {
        nodeList.clear();
        nodeList = this.mainTree.traversePostOrder();
        this.drawHighlightSequence(nodeList);
    }
}
