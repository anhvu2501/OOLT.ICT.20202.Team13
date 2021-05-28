package oop.ict.project.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oop.ict.project.tree.exception.TreeException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ScreenController {

    @FXML
    private TextField inputNodeKey;

    @FXML
    private Label currentTreeName;
    
    @FXML
    private BorderPane screenContainer;

    public static Label staticLabel;

    private GraphicTree graphicTree;

    public void initialize() {
        staticLabel = currentTreeName;
     	try {
			graphicTree = new GraphicTree();
		} catch (TreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	screenContainer.setCenter(graphicTree);
     	
     	graphicTree.widthProperty().bind(screenContainer.widthProperty());
     	graphicTree.heightProperty().bind(screenContainer.heightProperty());
    }

    public Label getCurrentTreeName() {
        return currentTreeName;
    }

    public void setCurrentTreeName(Label currentTreeName) {
        this.currentTreeName = currentTreeName;
    }

    private Stage stage;
    private Scene scene;


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

    @FXML
    void backPressed(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ScreenMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}