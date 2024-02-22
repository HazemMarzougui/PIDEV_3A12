package gui;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class gestionProduitController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnListe;

    @FXML
    private AnchorPane gestionProduits;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void open_listProduit()throws IOException{
        Parent fxml= FXMLLoader.load(getClass().getResource("listProduit.fxml"));
        gestionProduits.getChildren().removeAll();
        gestionProduits.getChildren().setAll(fxml);
    }


    @FXML
    void open_addProduit()throws IOException{
        Parent fxml= FXMLLoader.load(getClass().getResource("addProduit.fxml"));
        gestionProduits.getChildren().removeAll();
        gestionProduits.getChildren().setAll(fxml);
    }



}
