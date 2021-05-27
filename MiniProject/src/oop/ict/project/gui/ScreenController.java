package oop.ict.project.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ScreenController {


    @FXML
    private TextField inputNodeKey;

    @FXML
    private MenuButton selectTreeMenu;

    @FXML
    private Label currentTreeName;
    
    @FXML
    private BorderPane rootContainer;
    
    private GraphicTree graphicTree;

    
    public void initialize() {
    	currentTreeName.setText("GENERIC TREE");
    	// The center panel for drawing the tree
    	this.graphicTree = new GraphicTree();
    	rootContainer.setCenter(graphicTree);
    	
    	this.graphicTree.widthProperty().bind(rootContainer.widthProperty());
    	this.graphicTree.heightProperty().bind(rootContainer.heightProperty());
    	this.graphicTree.switchTree(1);
    }
    @FXML
    void genericTreePressed(ActionEvent event) {
    	currentTreeName.setText("GENERIC TREE");
    	this.graphicTree.switchTree(1);;
    }

    @FXML
    void binaryTreePressed(ActionEvent event) {
    	currentTreeName.setText("BINARY TREE");
    	this.graphicTree.switchTree(2);
    }

    @FXML
    void balancedTreePressed(ActionEvent event) {
    	currentTreeName.setText("BALANCED TREE");
    	this.graphicTree.switchTree(3);
    }

    @FXML
    void balancedBinaryTreePressed(ActionEvent event) {
    	currentTreeName.setText("BALANCED BINARY TREE");
    }

    @FXML
    void createPressed(ActionEvent event) {
    	GraphicsContext gc = graphicTree.getGraphicsContext2D();
		Point2D point = new Point2D(0, 0);
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
    }

    @FXML
    void insertPressed(ActionEvent event) {

    }

    @FXML
    void deletePressed(ActionEvent event) {

    }

    @FXML
    void updatePressed(ActionEvent event) {

    }

    @FXML
    void searchPressed(ActionEvent event) {

    }

    @FXML
    void preorderPressed(ActionEvent event) {

    }

    @FXML
    void postorderPressed(ActionEvent event) {

    }

}
