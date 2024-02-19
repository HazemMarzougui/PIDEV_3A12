package Controllers;

import Entities.Categorie;
import Entities.Conseil;
import Services.CategorieService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;


import java.io.IOException;
import java.sql.SQLException;

public class CategorieController {

    CategorieService categorieService = new CategorieService();

    int idC = 0 ;

    @FXML
    private Button btn_afficherC;

    @FXML
    private Button btn_ajouterC;

    @FXML
    private Button btn_modifierC;

    @FXML
    private Button btn_supprimerC;

    @FXML
    private TableColumn<Categorie, Integer> col_idC;

    @FXML
    private TableColumn<Categorie,String> col_nomC;


    @FXML
    private TextField tf_nomCategorie;

    @FXML
    private TableView<Categorie> tv_categories;

    private void refreshTableView() {
        try {
            ObservableList<Categorie> observableList = FXCollections.observableList(categorieService.displayCategorie());
            tv_categories.setItems(observableList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // Gérer l'erreur selon vos besoins
        }
    }

    // Méthode pour réinitialiser les champs de texte
    private void resetFields() {
        tf_nomCategorie.clear();

    }

    @FXML
    void AfficherCategorie(ActionEvent event)
    {
        try {
            ObservableList<Categorie> observableList = FXCollections.observableList(categorieService.displayCategorie());
            tv_categories.setItems(observableList);

            col_idC.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
            col_nomC.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void AjouterCategorie(ActionEvent event) throws SQLException {
        try {
            categorieService.ajouterCategorie(new Categorie(
                    tf_nomCategorie.getText()
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
        resetFields();
    }

    @FXML
    void Click(MouseEvent event)
    {
        if (event.getButton()== MouseButton.PRIMARY) {
            // Récupérer l'objet Panier sélectionné dans le TableView
            Categorie categorie = tv_categories.getSelectionModel().getSelectedItem();
            // Vérifi  er si un élément a été effectivement sélectionné
            if (categorie != null) {
                // Récupérer les données du Panier et les afficher dans les champs de texte appropriés
                idC = categorie.getId_categorie();
                tf_nomCategorie.setText(categorie.getNom_categorie());
                btn_ajouterC.setDisable(true);
    }
        }
    }

    @FXML
    void ModifierCategorie(ActionEvent event)
    {
        Categorie categorie = tv_categories.getSelectionModel().getSelectedItem();

        if (categorie != null) {
            categorie.setNom_categorie(tf_nomCategorie.getText());
            try {
                categorieService.modifierCategorie(categorie, idC); // Assuming you have an update method in your service
                // Rafraîchir la TableView après la modification

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                // Gérer l'erreur selon vos besoins
            }
        }
        resetFields();
    }

    @FXML
    void SupprimerCategorie(ActionEvent event)
    {
        Categorie categorie = tv_categories.getSelectionModel().getSelectedItem();
        if (categorie != null) {
            try {
                categorieService.deleteCategorie(idC);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            resetFields();
            refreshTableView();
        }
    }

    @FXML
    void Go_conseils(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GestionConseils.fxml"));
        tf_nomCategorie.getScene().setRoot(root);
    }
}
