package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConseilController implements Initializable {


    @FXML
    private Pane content_area;
    @FXML
    private Button cat_btn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void commande(MouseEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AdminListCommand.fxml")));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);


    }


}






















