package gui;

import entities.Categorie;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class itemCategorieController implements Initializable {

    @FXML
    private Button btnModifierCateg;

    @FXML
    private Button btnSupprimerCateg;

    @FXML
    private AnchorPane itemCategPane;

    @FXML
    private Label labelNomCateg;

    Categorie categ;
    public void setData (Categorie categorie){
        this.categ = categorie ;

        labelNomCateg.setText(categorie.getNom_categorie());

    }


    public Categorie getData (Categorie categorie){
        this.categ = categorie ;
        return this.categ;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void open_UpdateCateg(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("updateCategorie.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Update Category");
        stage.setScene(new Scene(fxml));
        stage.showAndWait();
    }

    @FXML
    void supprimerCateg(ActionEvent event) throws SQLException {
        ServiceCategorie sc = new ServiceCategorie();

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette categorie ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de la categorie sélectionnée
            int id = this.categ.getId_categorie();

            // Supprimer la categorie de la base de données
            sc.supprimer(id);
        }
    }
}
