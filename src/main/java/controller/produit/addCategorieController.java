package controller.produit;

import entities.Categorie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.ServiceCategorie;
import utils.MyDB;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class addCategorieController implements Initializable {

    @FXML
    private AnchorPane addCategoriePane;

    @FXML
    private Button btnAddCateg;

    @FXML
    private Button btnClearCateg;

    @FXML
    private Button btnReturnCateg;

    @FXML
    private TextField textNomCateg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void AjoutCategorie(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddCateg){
            if (textNomCateg.getText().isEmpty() )
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre categorie.");
                Optional<ButtonType> option = alert.showAndWait();

            } else {
                ajouterCategorie();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajouté avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre categorie a été ajoutée avec succès.");
                Optional<ButtonType> option = alert.showAndWait();

                clearFieldsCategorie();
            }
        }
        if(event.getSource() == btnClearCateg){
            clearFieldsCategorie();
        }
    }

    @FXML
    void clearFieldsCategorie() {
        textNomCateg.clear();
    }

    @FXML
    void return_ListCategorie(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("listCategorie.fxml"));
        addCategoriePane.getChildren().removeAll();
        addCategoriePane.getChildren().setAll(fxml);
    }


    private void ajouterCategorie() {

        // From Formulaire
        String nomCateg = textNomCateg.getText();


        MyDB db = MyDB.getInstance();
        Categorie categ = new Categorie(nomCateg);
        ServiceCategorie sc = new ServiceCategorie();
        sc.ajouter(categ);
    }


}
