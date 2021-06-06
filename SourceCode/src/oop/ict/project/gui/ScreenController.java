package oop.ict.project.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oop.ict.project.tree.exception.TreeException;

import java.io.IOException;

public class ScreenController {
    @FXML
    private TextField inputNodeKey;

    @FXML
    private Label currentTreeName;

    @FXML
    private BorderPane screenContainer;
    
    @FXML
    private Button pauseBtn;

    @FXML
    private Button resumeBtn;

    public static Label staticLabel;

    private GraphicTree graphicTree;
    
    private Stage stage;
    private Scene scene;

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

    @FXML
    public void createPressed(ActionEvent event) {
        screenContainer.setCenter(graphicTree);

        graphicTree.widthProperty().bind(screenContainer.widthProperty());
        graphicTree.heightProperty().bind(screenContainer.heightProperty());

        if (!graphicTree.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to create new tree? This will delete the old tree.", ButtonType.OK, ButtonType.CANCEL);
            alert.setTitle("Exit");
            alert.setHeaderText("");
            if (alert.showAndWait().get() != ButtonType.OK) {
                return;
            }
        }
        Alert success = new Alert(AlertType.INFORMATION, "Successfully create a new empty tree!", ButtonType.OK);
        success.setTitle("");
        success.setHeaderText("");
        Alert fail = new Alert(AlertType.INFORMATION, "Please input a POSITIVE INTEGER!", ButtonType.OK);
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
                        Integer temp = Integer.parseInt(td.getEditor().getText().trim());
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
                        Integer temp = Integer.parseInt(td.getEditor().getText().trim());
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
    public void insertPressed(ActionEvent event) {
        screenContainer.setCenter(graphicTree);

        graphicTree.widthProperty().bind(screenContainer.widthProperty());
        graphicTree.heightProperty().bind(screenContainer.heightProperty());
        if (graphicTree.isEmptyForBalanced()) {
            Alert er1 = new Alert(AlertType.INFORMATION, "You must create a tree and set the limit first",
                    ButtonType.OK);
            er1.setTitle("Error");
            er1.setHeaderText("");
            er1.show();
        } else {
            try {
                Integer num = Integer.parseInt(this.inputNodeKey.getText().trim());
                if (graphicTree.isEmpty()) {
                    graphicTree.setRoot(num);
                    return;
                }
                TextInputDialog td = new TextInputDialog();
                td.setHeaderText("Input the parent node's key you want to insert into");
                Button okButton = (Button) td.getDialogPane().lookupButton(ButtonType.OK);
                EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent evt) {
                        try {
                            Integer temp = Integer.parseInt(td.getEditor().getText().trim());
                            graphicTree.insert(temp, num);
                        } catch (TreeException e) {
                            Alert er2 = new Alert(AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
                            er2.setTitle("Error");
                            er2.setHeaderText("");
                            er2.show();
                        } catch (NumberFormatException e2) {
                            Alert er1 = new Alert(AlertType.INFORMATION, "Key must be an INTEGER!", ButtonType.OK);
                            er1.setTitle("Error");
                            er1.setHeaderText("");
                            er1.show();
                        }
                    }
                };
                okButton.setOnAction(event1);
                td.showAndWait();
            } catch (NumberFormatException e1) {
                Alert er1 = new Alert(AlertType.INFORMATION, "Key must be an INTEGER!", ButtonType.OK);
                er1.setTitle("Error");
                er1.setHeaderText("");
                er1.show();
            }
        }
    }

    public void restoreDraw() {
        screenContainer.setCenter(graphicTree);

        graphicTree.widthProperty().bind(screenContainer.widthProperty());
        graphicTree.heightProperty().bind(screenContainer.heightProperty());
        graphicTree.drawTree();
    }

    @FXML
    public void deletePressed(ActionEvent event) {
        screenContainer.setCenter(graphicTree);

        graphicTree.widthProperty().bind(screenContainer.widthProperty());
        graphicTree.heightProperty().bind(screenContainer.heightProperty());
        if (graphicTree.isEmptyForBalanced()) {
            Alert er1 = new Alert(AlertType.INFORMATION, "You must create a tree and set the limit first",
                    ButtonType.OK);
            er1.setTitle("Error");
            er1.setHeaderText("");
            er1.show();
        } else {
            if (graphicTree.isEmpty()) {
                Alert er1 = new Alert(AlertType.INFORMATION, "The tree is empty now!", ButtonType.OK);
                er1.setTitle("Error");
                er1.setHeaderText("");
                er1.show();
            } else {
                try {
                    Integer num = Integer.parseInt(this.inputNodeKey.getText().trim());
                    graphicTree.delete(num);
                } catch (NumberFormatException e1) {
                    Alert er1 = new Alert(AlertType.INFORMATION, "Key must be an INTEGER!", ButtonType.OK);
                    er1.setTitle("Error");
                    er1.setHeaderText("");
                    er1.show();
                }
            }
        }
    }

    @FXML
    public void updatePressed(ActionEvent event) {
        screenContainer.setCenter(graphicTree);

        graphicTree.widthProperty().bind(screenContainer.widthProperty());
        graphicTree.heightProperty().bind(screenContainer.heightProperty());
        if (graphicTree.isEmptyForBalanced()) {
            Alert er1 = new Alert(AlertType.INFORMATION, "You must create a tree and set the limit first",
                    ButtonType.OK);
            er1.setTitle("Error");
            er1.setHeaderText("");
            er1.show();
        } else {
            try {
                Integer num = Integer.parseInt(this.inputNodeKey.getText().trim());
                if (graphicTree.isEmpty()) {
                    graphicTree.setRoot(num);
                    return;
                }
                TextInputDialog td = new TextInputDialog();
                td.setHeaderText("Input the value you want to update to node with value " + num);
                Button okButton = (Button) td.getDialogPane().lookupButton(ButtonType.OK);
                EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent evt) {
                        try {
                            Integer temp = Integer.parseInt(td.getEditor().getText().trim());
                            graphicTree.update(num, temp);
                        } catch (TreeException e) {
                            Alert er2 = new Alert(AlertType.INFORMATION, e.getMessage(), ButtonType.OK);
                            er2.setTitle("Error");
                            er2.setHeaderText("");
                            er2.show();
                        } catch (NumberFormatException e2) {
                            Alert er1 = new Alert(AlertType.INFORMATION, "Key must be an INTEGER!", ButtonType.OK);
                            er1.setTitle("Error");
                            er1.setHeaderText("");
                            er1.show();
                        }
                    }
                };
                okButton.setOnAction(event1);
                td.showAndWait();
            } catch (NumberFormatException e1) {
                Alert er1 = new Alert(AlertType.INFORMATION, "Key must be an INTEGER!", ButtonType.OK);
                er1.setTitle("Error");
                er1.setHeaderText("");
                er1.show();
            }
        }
    }

    @FXML
    public void searchPressed(ActionEvent event) {
        screenContainer.setCenter(graphicTree);

        graphicTree.widthProperty().bind(screenContainer.widthProperty());
        graphicTree.heightProperty().bind(screenContainer.heightProperty());
        if (graphicTree.isEmptyForBalanced()) {
            Alert er1 = new Alert(AlertType.INFORMATION, "You must create a tree and set the limit first",
                    ButtonType.OK);
            er1.setTitle("Error");
            er1.setHeaderText("");
            er1.show();
        } else {
            if (graphicTree.isEmpty()) {
                Alert er1 = new Alert(AlertType.INFORMATION, "The tree is empty now!", ButtonType.OK);
                er1.setTitle("Error");
                er1.setHeaderText("");
                er1.show();
            } else {
                try {
                    Integer num = Integer.parseInt(this.inputNodeKey.getText().trim());
                    graphicTree.search(num);
                } catch (NumberFormatException e1) {
                    Alert er1 = new Alert(AlertType.INFORMATION, "Key must be an INTEGER!", ButtonType.OK);
                    er1.setTitle("Error");
                    er1.setHeaderText("");
                    er1.show();
                }
            }
        }
    }

    @FXML
    public void preorderPressed(ActionEvent event) {
        screenContainer.setCenter(graphicTree);

        graphicTree.widthProperty().bind(screenContainer.widthProperty());
        graphicTree.heightProperty().bind(screenContainer.heightProperty());

        try {
            graphicTree.preorderList();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!!");
            alert.setHeaderText("");
            alert.setContentText("The tree is empty");
            alert.showAndWait();
        }
    }

    @FXML
    public void postorderPressed(ActionEvent event) {
        screenContainer.setCenter(graphicTree);

        graphicTree.widthProperty().bind(screenContainer.widthProperty());
        graphicTree.heightProperty().bind(screenContainer.heightProperty());

        try {
            graphicTree.postorderList();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!!");
            alert.setHeaderText("");
            alert.setContentText("The tree is empty");
            alert.showAndWait();
        }

    }

    @FXML
    public void backPressed(ActionEvent event) throws IOException {
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
    void resumePressed(ActionEvent event) {
    	graphicTree.timeline.play();
    }

    @FXML
    void pausePressed(ActionEvent event) {
    	graphicTree.timeline.pause();
    }
}
