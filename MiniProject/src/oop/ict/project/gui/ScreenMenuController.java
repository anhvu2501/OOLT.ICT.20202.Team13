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

    @FXML
    void selectBalancedBinaryTree(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGraphicTree.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        staticLabel.setText("Balanced Binary Tree");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void selectBalancedTree(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGraphicTree.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        staticLabel.setText("Balanced Tree");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void selectBinaryTree(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGraphicTree.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        staticLabel.setText("Binary Tree");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void selectGenericTree(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGraphicTree.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        staticLabel.setText("Generic Tree");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void selectHelpMenu(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "How to visualize a tree:\n " +
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

