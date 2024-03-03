package controller.produit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class gestionCategorieController implements Initializable {
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnListe;

    @FXML
    private AnchorPane gestionCategories;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void open_addCategorie()throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("/produit/addCategorie.fxml"));
        gestionCategories.getChildren().removeAll();
        gestionCategories.getChildren().setAll(fxml);
    }

    @FXML
    void open_listCategorie()throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("/produit/listCategorie.fxml"));
        gestionCategories.getChildren().removeAll();
        gestionCategories.getChildren().setAll(fxml);
    }


}
