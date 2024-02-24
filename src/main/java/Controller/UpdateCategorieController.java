package Controller;

import Entities.Categorie;
import Entities.Conseil;
import Services.CategorieService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class UpdateCategorieController {

    CategorieService categorieService = new CategorieService();

    private  CategorieController categorieController;


    @FXML
    private Button btn_modifier;

    @FXML
    private TextField tf_nomCategorie;

    private Categorie selectedCategorie;

    public void setCategorie(Categorie categorie) {
        this.selectedCategorie = categorie;
        tf_nomCategorie.setText(categorie.getNom_categorie());

    }

    public void setCategorieControllerController(CategorieController categorieController) {
        this.categorieController = categorieController;
    }

    int idCategorie = 0;



    @FXML
    void Annuler_conseil(ActionEvent event) {
        Node source = (Node) event.getSource();
        // Get the Stage to which the source node belongs
        Stage stage = (Stage) source.getScene().getWindow();
        // Close the stage
        stage.close();
    }

    @FXML
    void Modifier_categorie(ActionEvent event) {
        Categorie categorie = categorieController.getTv_categories().getSelectionModel().getSelectedItem();
        if (categorie != null) {
            categorie.setNom_categorie(tf_nomCategorie.getText());
            try {
                categorieService.modifierCategorie(categorie, categorie.getId_categorie());
                refreshTableView();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success !");
                alert.setContentText("update Effectué");
                alert.showAndWait();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    void Supprimer_categorie(ActionEvent event) {
        Categorie categorie = categorieController.getTv_categories().getSelectionModel().getSelectedItem();
        if (categorie != null) {
            try {
                int idCategorie = categorie.getId_categorie();
                categorieService.deleteCategorie(idCategorie);
                categorieController.observableList.remove(categorie);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Art deleted successfully ");
                alert.showAndWait();
            } catch (SQLException e) {
                refreshTableView();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    private void refreshTableView() {
        try {
            ObservableList<Categorie> observableList = FXCollections.observableList(categorieService.displayCategorie());
            categorieController.getTv_categories().setItems(observableList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // Gérer l'erreur selon vos besoins
        }
    }

}
