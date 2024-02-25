package gui;

import entities.Categorie;
import entities.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceCategorie;
import services.ServiceProduit;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class itemProduitController implements Initializable {

    @FXML
    private Button btnModifierProd;

    @FXML
    private Button btnSupprimerProd;

    @FXML
    private AnchorPane itemProduitPane;

    @FXML
    private Label labelCategProd;

    @FXML
    private Label labelDescriptionProd;

    @FXML
    private Label labelImageProd;

    @FXML
    private Label labelNomProd;

    @FXML
    private Label labelPrixProd;

    @FXML
    private Label labelQteProd;


    Produit produit;
    public void setData (Produit prod){
        this.produit = prod ;

        labelNomProd.setText(prod.getNom_produit());
        labelPrixProd.setText(String.valueOf(prod.getPrix()));
        labelQteProd.setText(String.valueOf(prod.getQuantite()));
        labelDescriptionProd.setText(prod.getDescription());
        labelImageProd.setText(prod.getImage());
        labelCategProd.setText(String.valueOf(prod.getId_categorie()));

    }


    public Produit getData (Produit prod){
        this.produit = prod ;
        return this.produit;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void open_UpdateProd(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("updateProduit.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Update Produit");
        stage.setScene(new Scene(fxml));
        stage.showAndWait();
    }

    @FXML
    void supprimerProd(ActionEvent event) throws SQLException {
        ServiceProduit sc = new ServiceProduit();

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce produit ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID du produit sélectionnée
            int id = this.produit.getId_produit();
            System.out.println(id);
            // Supprimer le produit de la base de données
            sc.supprimer(id);
        }
    }
}
