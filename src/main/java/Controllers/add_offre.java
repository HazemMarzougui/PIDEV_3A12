package Controllers;

import entities.Evenement;
import entities.Offre;
import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import services.Service_evenement;
import services.Service_offre;
import services.Service_produit;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class add_offre {

    @FXML
    private Button id_add_offre;

    @FXML
    private DatePicker id_date_deb_offre;

    @FXML
    private DatePicker id_date_fin_offre;

    @FXML
    private Button id_delete_offre;

    @FXML
    private TextArea id_desc_offre;

    @FXML
    private TextField id_prix_remise_offre;

    @FXML
    private Button id_update_offre;



    @FXML
    private ChoiceBox<String> pick_produit;
    Service_evenement se = new Service_evenement();
    Service_offre so =new Service_offre();
    Service_produit sp =new Service_produit();

    private Evenement evenement;

    @FXML
    void add_and_return_to_prod(ActionEvent event) {
        try {
            List<Produit> produits = sp.afficher();
            // Convert LocalDate objects to java.util.Date objects
            Date dateDebut = java.sql.Date.valueOf(id_date_deb_offre.getValue());
            Date dateFin = java.sql.Date.valueOf(id_date_fin_offre.getValue());

            // Get the selected product name from the ChoiceBox
            String selectedProductName = pick_produit.getValue();

            // Find the corresponding product with the selected name
            Produit selectedProduct = produits.stream()
                    .filter(produit -> produit.getNom_produit().equals(selectedProductName))
                    .findFirst()
                    .orElse(null);

            // Ensure a product is selected
            if (selectedProduct != null) {
                // Create Evenement object with converted dates
                so.ajouter(new Offre(id_desc_offre.getText(), dateDebut, dateFin, Integer.parseInt(id_prix_remise_offre.getText()), evenement.getId_evenement(), selectedProduct.getId_produit()));

                // Other code here...

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Événement ajouté avec succès");
                alert.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("/admin/event/admin_event.fxml"));
                id_add_offre.getScene().setRoot(root);
            } else {
                // Handle case when no product is selected
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Veuillez sélectionner un produit.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void delete_and_return_to_prod(ActionEvent event) {

    }

    @FXML
    void update_and_return_to_prod(ActionEvent event) {

    }




    @FXML
    void initialize() {
        try {
            // Retrieve all products
            List<Produit> produits = sp.afficher();

            // Collect all product names
            List<String> productNames = produits.stream()
                    .map(Produit::getNom_produit)
                    .collect(Collectors.toList());

            // Populate the ChoiceBox with all product names
            ObservableList<String> items = FXCollections.observableArrayList(productNames);
            pick_produit.setItems(items);

            // Ajouter un écouteur de changement pour le choix de produit
            pick_produit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    // Obtenez le produit sélectionné en fonction de son nom
                    Produit selectedProduct = produits.stream()
                            .filter(produit -> produit.getNom_produit().equals(newValue))
                            .findFirst()
                            .orElse(null);

                    // Afficher les données du produit sélectionné
                    if (selectedProduct != null) {
                        setDatas(selectedProduct);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode setData() que vous avez fournie précédemment
    void setData(Evenement e) throws SQLException {
        this.evenement=e;

    }
    void setDatas(Produit produit) throws SQLException {

    }



}