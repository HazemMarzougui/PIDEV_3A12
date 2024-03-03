package controller.produit;

import entities.Categorie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import services.ServiceCategorie;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class listCategorieController implements Initializable {

    @FXML
    private TableColumn<Categorie, String> NomCategorie;

    @FXML
    private Button bntAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnLoad;

    @FXML
    private AnchorPane listCategoriePane;

    @FXML
    private TableView<Categorie> tableCategories;

    @FXML
    private TextField txtSearch;

    ObservableList<Categorie> dataCategorie = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AfficherCategorie();
    }

    @FXML
    void AfficherCategorie() {
        ServiceCategorie sc = new ServiceCategorie();
        sc.Show().stream().forEach((p) -> {
            dataCategorie.add(p);
        });
        NomCategorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        NomCategorie.setCellFactory(TextFieldTableCell.forTableColumn());
        NomCategorie.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Categorie, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Categorie, String> event) {
                Categorie p = event.getRowValue();
                p.setNom_categorie(event.getNewValue());
                ServiceCategorie ps = new ServiceCategorie();
                ps.modifier(p);
            }
        });
        tableCategories.setItems(dataCategorie);
    }

    @FXML
    void open_addCategorie(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("addCategorie.fxml"));
        listCategoriePane.getChildren().removeAll();
        listCategoriePane.getChildren().setAll(fxml);
    }

    @FXML
    void supprimerCategorie(ActionEvent event) throws SQLException {
        ServiceCategorie ps = new ServiceCategorie();

        if (tableCategories.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner la categorie à supprimer");
            alert.showAndWait();
            return;
        }

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette categorie ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de la categorie sélectionnée dans la vue de la table
            int id = tableCategories.getSelectionModel().getSelectedItem().getId_categorie();

            // Supprimer la categorie de la base de données
            ps.supprimer(id);
            // Rafraîchir la liste de données
            dataCategorie.clear();
            AfficherCategorie();
            // Rafraîchir la vue de la table
            tableCategories.refresh();
        }
        tableCategories.setItems(dataCategorie);
        try {
            searchCategories();
        } catch (SQLException ex) {
            Logger.getLogger(listCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ServiceCategorie sc = new ServiceCategorie();
    public void searchCategories() throws SQLException {
        FilteredList<Categorie> filteredData = new FilteredList<>(FXCollections.observableArrayList(sc.Show()), p -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(categories -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String nom = String.valueOf(categories.getNom_categorie());

                String lowerCaseFilter = newValue.toLowerCase();

                if (nom.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Categorie> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableCategories.comparatorProperty());
        tableCategories.setItems(sortedData);
    }


}
