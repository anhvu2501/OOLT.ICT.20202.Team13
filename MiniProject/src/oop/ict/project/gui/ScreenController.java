package oop.ict.project.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oop.ict.project.tree.exception.TreeException;

import static oop.ict.project.gui.ScreenController.staticLabel;

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

	public void transferData(GraphicTree tree) {
		this.graphicTree = tree;
	}

	public void switchTree(Integer type) {
		this.graphicTree.switchTree(type);
		this.graphicTree.drawTree();
	}

	private Stage stage;
	private Scene scene;

	@FXML
	void createPressed(ActionEvent event) {
		Alert success = new Alert(AlertType.INFORMATION, "Successfully create a new empty tree!",
				ButtonType.OK);
		success.setTitle("");
		success.setHeaderText("");
		Alert fail = new Alert(AlertType.INFORMATION, "Please input a POSITIVE INTEGER!",
				ButtonType.OK);
		fail.setTitle("Error");
		fail.setHeaderText("");
		if (staticLabel.getText() == "Generic Tree") {
			this.graphicTree.createEmptyTree(1);
			this.graphicTree.drawTree();
			success.show();
		} else if (staticLabel.getText() == "Binary Tree") {
			this.graphicTree.createEmptyTree(2);
			this.graphicTree.drawTree();
			success.show();
		} else if (staticLabel.getText() == "Balanced Tree") {
			TextInputDialog td = new TextInputDialog();
			td.setHeaderText("Input the distance limit");
			Button okButton = (Button) td.getDialogPane().lookupButton(ButtonType.OK);
			EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent evt) {
					try {
						Integer temp = Integer.parseInt(td.getEditor().getText());
						if (temp > 0) {
							graphicTree.createEmptyTree(3);
							graphicTree.setLimit(temp, 3);
							graphicTree.drawTree();
							success.show();
						} else {
							throw new NumberFormatException();
						}
					} catch (Exception e) {
						fail.show();
					}
				}
			};
			okButton.setOnAction(event1);
			td.showAndWait();
		} else {
			TextInputDialog td = new TextInputDialog();
			td.setHeaderText("Input the distance limit");
			Button okButton = (Button) td.getDialogPane().lookupButton(ButtonType.OK);
			EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent evt) {
					try {
						Integer temp = Integer.parseInt(td.getEditor().getText());
						if (temp > 0) {
							graphicTree.createEmptyTree(4);
							graphicTree.setLimit(temp, 4);
							graphicTree.drawTree();
							success.show();
						} else {
							throw new NumberFormatException();
						}
					} catch (Exception e) {
						fail.show();
					}
				}
			};
			okButton.setOnAction(event1);
			td.showAndWait();
		}
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
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ScreenMenu.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		ScreenMenuController controller = loader.getController();
		controller.transferData(this.graphicTree);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void redoPressed(ActionEvent event) {

	}

	@FXML
	void undoPressed(ActionEvent event) {

	}

}
