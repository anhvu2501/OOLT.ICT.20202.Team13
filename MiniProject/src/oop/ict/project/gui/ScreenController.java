package oop.ict.project.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class ScreenController {

    @FXML
    private TextField parentNodeKey;

    @FXML
    private TextField inputNodeKey;

    @FXML
    private MenuButton selectTreeMenu;

    @FXML
    private Label currentTreeName;

    
    public void initialize() {
    	currentTreeName.setText("GENERIC TREE");
    }
    @FXML
    void genericTreePressed(ActionEvent event) {
    	currentTreeName.setText("GENERIC TREE");
    }

    @FXML
    void binaryTreePressed(ActionEvent event) {
    	currentTreeName.setText("BINARY TREE");
    }

    @FXML
    void balancedTreePressed(ActionEvent event) {
    	currentTreeName.setText("BALANCED TREE");
    }

    @FXML
    void balancedBinaryTreePressed(ActionEvent event) {
    	currentTreeName.setText("BALANCED BINARY TREE");
    }

    @FXML
    void createPressed(ActionEvent event) {

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
