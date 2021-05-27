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
import oop.ict.project.tree.generic.GenericTree;

public class GraphicTree extends Canvas {

	
	private GenericTree genericTree; //type1
	private BinaryTree binaryTree; //type2
	private BalancedTree balancedTree; //type3
//	private BalancedBinaryTree balancedBinaryTree; // type4
	private GenericTree mainTree;
	
	public void switchTree(Integer type) {
		if(type==1) {
			this.mainTree = this.genericTree;
		}else if(type==2) {
			this.mainTree = this.binaryTree;
		}else if(type==3){
			this.mainTree = this.balancedTree;
		}
	}

	public GraphicTree() {
		this.genericTree = new GenericTree();
		this.binaryTree = new BinaryTree();
		this.balancedTree = new BalancedTree();
		widthProperty().addListener(evt -> draw());
		heightProperty().addListener(evt -> draw());
	}
	
	public void draw() {
		GraphicsContext gc = this.getGraphicsContext2D();
		Timeline timeline = new Timeline(
			    new KeyFrame(Duration.seconds(2), evt -> {
					Point2D point = new Point2D(700, 500);
					gc.setLineWidth(3); // Sets the width of the lines
					int RADIUS = 20;
					gc.setFill(Color.rgb(99, 99, 99));
					gc.fillOval(point.getX() - RADIUS, point.getY() - RADIUS, 2 * RADIUS, 2 * RADIUS);
					
					// Outline the circle border
					gc.setStroke(Color.rgb(99, 99, 99));
					gc.strokeOval(point.getX() - RADIUS, point.getY() - RADIUS, 2 * RADIUS, 2 * RADIUS);

					// Draw the id number inside the circle
					gc.setFont(Font.font("Cooper Black", FontWeight.BOLD, 16));
					gc.setFill(Color.web("#FCFCFC"));
					gc.fillText("2",
							 point.getX() - (16 / 2),
							 point.getY() + (16 / 4));
//					i=i+1;
			    }),
			    new KeyFrame(Duration.seconds(4), evt -> {
					Point2D point = new Point2D(200, 300);
					gc.setLineWidth(3); // Sets the width of the lines
					int RADIUS = 20;
					gc.setFill(Color.rgb(99, 99, 99));
					gc.fillOval(point.getX() - RADIUS, point.getY() - RADIUS, 2 * RADIUS, 2 * RADIUS);
					
					// Outline the circle border
					gc.setStroke(Color.rgb(99, 99, 99));
					gc.strokeOval(point.getX() - RADIUS, point.getY() - RADIUS, 2 * RADIUS, 2 * RADIUS);

					// Draw the id number inside the circle
					gc.setFont(Font.font("Cooper Black", FontWeight.BOLD, 16));
					gc.setFill(Color.web("#FCFCFC"));
					gc.fillText("3",
							 point.getX() - (16 / 2),
							 point.getY() + (16 / 4));
//					i=i+1;
			    }),
			    new KeyFrame(Duration.seconds(6), evt -> {
					Point2D point = new Point2D(700, 500);
					gc.setLineWidth(3); // Sets the width of the lines
					int RADIUS = 20;
					gc.setFill(Color.rgb(99, 99, 99));
					gc.fillOval(point.getX() - RADIUS, point.getY() - RADIUS, 2 * RADIUS, 2 * RADIUS);
					
					// Outline the circle border
					gc.setStroke(Color.rgb(99, 99, 99));
					gc.strokeOval(point.getX() - RADIUS, point.getY() - RADIUS, 2 * RADIUS, 2 * RADIUS);

					// Draw the id number inside the circle
					gc.setFont(Font.font("Cooper Black", FontWeight.BOLD, 16));
					gc.setFill(Color.web("#FCFCFC"));
					gc.fillText("4",
							 point.getX() - (16 / 2),
							 point.getY() + (16 / 4));
			    })
			);
		Timeline timeline1 = new Timeline(
			    new KeyFrame(Duration.seconds(2), evt -> {
					Point2D point = new Point2D(700, 500);
					gc.setLineWidth(3); // Sets the width of the lines
					int RADIUS = 20;
					gc.setFill(Color.rgb(99, 99, 99));
					gc.fillOval(point.getX() - RADIUS, point.getY() - RADIUS, 2 * RADIUS, 2 * RADIUS);
					
					// Outline the circle border
					gc.setStroke(Color.rgb(2, 2, 2));
					gc.strokeOval(point.getX() - RADIUS, point.getY() - RADIUS, 2 * RADIUS, 2 * RADIUS);

					// Draw the id number inside the circle
					gc.setFont(Font.font("Cooper Black", FontWeight.BOLD, 16));
					gc.setFill(Color.web("#FCFCFC"));
					gc.fillText("9",
							 point.getX() - (16 / 2),
							 point.getY() + (16 / 4));
//					i=i+1;
			    }));
		timeline.play();
		timeline1.play();
	}

}
