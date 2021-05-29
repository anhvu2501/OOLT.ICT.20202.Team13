package oop.ict.project.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import oop.ict.project.tree.exception.TreeException;

import java.io.IOException;

import static oop.ict.project.gui.ScreenController.staticLabel;

public class ScreenMenuController {
    @FXML
    private Button helpMenuButton;

    @FXML
    private Button exitButton;

    private Stage stage;
    private Scene scene;

    private GraphicTree graphicTree;

    public void initialize() {
    	try {
			graphicTree = new GraphicTree();
		} catch (TreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void transferData(GraphicTree tree) {
		this.graphicTree=tree;
	}
    
    @FXML
    void selectBalancedBinaryTree(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("FXMLGraphicTree.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ScreenController controller = loader.getController();
        controller.transferData(this.graphicTree);
        controller.switchTree(4);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        staticLabel.setText("Balanced Binary Tree");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void selectBalancedTree(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("FXMLGraphicTree.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ScreenController controller = loader.getController();
        controller.transferData(this.graphicTree);
        controller.switchTree(3);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        staticLabel.setText("Balanced Tree");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void selectBinaryTree(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("FXMLGraphicTree.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ScreenController controller = loader.getController();
        controller.transferData(this.graphicTree);
        controller.switchTree(2);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        staticLabel.setText("Binary Tree");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void selectGenericTree(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("FXMLGraphicTree.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        ScreenController controller = loader.getController();
        controller.transferData(this.graphicTree);
        controller.switchTree(1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        staticLabel.setText("Generic Tree");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void selectHelpMenu(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Aims: Visualization of Tree Data Structures" +
                        "\n\nHow to visualize a tree:\n " +
                        "Step 1: Choose your type of tree\n" +
                        "Step 2: Choose operations\n" +
                        "Step 2: Wait and enjoy your work\n", ButtonType.OK);
        alert.setTitle("Help Menu");
        alert.setHeaderText("Orientation: ");
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> alert.close());
    }

    @FXML
    void pressExitButton(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to quit?", ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("Exit");
        alert.setHeaderText("");
//        alert.showAndWait()
//                .filter(response -> response == ButtonType.OK)
//                .ifPresent(response -> alert.close());
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        } else {
        }
    }
}

