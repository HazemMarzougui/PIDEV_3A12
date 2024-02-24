package Controller;

import Entities.Categorie;
import Services.CategorieService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddCategorieController {

    CategorieService categorieService = new CategorieService();

    @FXML
    private TextField tf_nomConseil;

    @FXML
    void Ajouter_categorie(ActionEvent event) {
        try {
            categorieService.ajouterCategorie(new Categorie(
                    tf_nomConseil.getText()
            ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success !");
            alert.setContentText("Ajout Effectué");
            alert.showAndWait();


        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error !");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Annuler_cat(ActionEvent event) {
        Node source = (Node) event.getSource();
        // Get the Stage to which the source node belongs
        Stage stage = (Stage) source.getScene().getWindow();
        // Close the stage
        stage.close();
    }

}
