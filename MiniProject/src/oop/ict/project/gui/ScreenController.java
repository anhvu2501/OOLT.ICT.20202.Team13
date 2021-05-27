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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScreenController implements Initializable {

    @FXML
    private TextField inputNodeKey;

    @FXML
    private Label currentTreeName;

    public static Label staticLabel;

    private GraphicTree graphicTree;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        staticLabel = currentTreeName;
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
        Parent root = FXMLLoader.load(getClass().getResource("screenmenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
