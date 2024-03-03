package controller.produit;


import entities.Produit;
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
import javafx.util.converter.IntegerStringConverter;
import services.ServiceProduit;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class listProduitsController implements Initializable {
    @FXML
    private TableColumn<Produit, Integer> Categorie;

    @FXML
    private TableColumn<Produit, String> DescriptionProduit;

    @FXML
    private TableColumn<Produit, String> ImageProduit;

    @FXML
    private TableColumn<Produit, String> NomProduit;

    @FXML
    private TableColumn<Produit, Integer> Offre;

    @FXML
    private TableColumn<Produit, Integer> PrixProduit;

    @FXML
    private TableColumn<Produit, Integer> QuantiteProduit;

    @FXML
    private Button bntAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnLoad;

    @FXML
    private TableView<Produit> tableProduits;

    @FXML
    private TextField txtSearch;

    @FXML
    private AnchorPane listProduitPane;
    @FXML
    private ComboBox<String> comboBoxTriProduits;

    ObservableList<Produit> dataProduit = FXCollections.observableArrayList();


    @FXML
    void open_addProduit(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("/produit/addProduit.fxml"));
        listProduitPane.getChildren().removeAll();
        listProduitPane.getChildren().setAll(fxml);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherProduit();
    }

    public void AfficherProduit() {
        ServiceProduit sp = new ServiceProduit();
        sp.Show().stream().forEach((c) -> {
            dataProduit.add(c);
        });

        NomProduit.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        NomProduit.setCellFactory(TextFieldTableCell.forTableColumn());
        NomProduit.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produit, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Produit, String> event) {
                Produit p = event.getRowValue();
                p.setNom_produit(event.getNewValue());
                ServiceProduit ps = new ServiceProduit();
                ps.modifier(p);
            }
        });
        PrixProduit.setCellValueFactory(new PropertyValueFactory<>("prix"));
        PrixProduit.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        PrixProduit.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produit, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Produit, Integer> event) {
                Produit p = event.getRowValue();
                p.setPrix(event.getNewValue());
                ServiceProduit ps = new ServiceProduit();
                ps.modifier(p);
            }
        });

        QuantiteProduit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        QuantiteProduit.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        QuantiteProduit.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produit, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Produit, Integer> event) {
                Produit p = event.getRowValue();
                p.setQuantite(event.getNewValue());
                ServiceProduit ps = new ServiceProduit();
                ps.modifier(p);
            }
        });
        DescriptionProduit.setCellValueFactory(new PropertyValueFactory<>("description"));
        DescriptionProduit.setCellFactory(TextFieldTableCell.forTableColumn());
        DescriptionProduit.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produit, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Produit, String> event) {
                Produit p = event.getRowValue();
                p.setDescription(event.getNewValue());
                ServiceProduit ps = new ServiceProduit();
                ps.modifier(p);
            }
        });
        ImageProduit.setCellValueFactory(new PropertyValueFactory<>("image"));
        ImageProduit.setCellFactory(TextFieldTableCell.forTableColumn());
        ImageProduit.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produit, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Produit, String> event) {
                Produit p = event.getRowValue();
                p.setImage(event.getNewValue());
                ServiceProduit ps = new ServiceProduit();
                ps.modifier(p);
            }
        });
        Categorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        Categorie.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Categorie.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produit, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Produit, Integer> event) {
                Produit p = event.getRowValue();
                p.setId_categorie(event.getNewValue());
                ServiceProduit ps = new ServiceProduit();
                ps.modifier(p);
            }
        });
        Offre.setCellValueFactory(new PropertyValueFactory<>("id_offre"));
        Offre.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Offre.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produit, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Produit, Integer> event) {
                Produit p = event.getRowValue();
                p.setId_offre(event.getNewValue());
                ServiceProduit ps = new ServiceProduit();
                ps.modifier(p);
            }
        });
        tableProduits.setItems(dataProduit);
        comboBoxTriProduits.getItems().addAll("Trier Selon",  "Nom ", "Prix" );

        try {
            searchProduits();
        } catch (SQLException ex) {
            Logger.getLogger(listProduitsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    void supprimerProduit(ActionEvent event) throws SQLException {
        ServiceProduit ps = new ServiceProduit();

        if (tableProduits.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner le produit à supprimer");
            alert.showAndWait();
            return;
        }

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce produit ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID du produit sélectionnée dans la vue de la table
            int id = tableProduits.getSelectionModel().getSelectedItem().getId_produit();

            // Supprimer le produit de la base de données
            ps.supprimer(id);
            // Rafraîchir la liste de données
            dataProduit.clear();
            AfficherProduit();
            // Rafraîchir la vue de la table
            tableProduits.refresh();
        }

    }
    public ServiceProduit sp = new ServiceProduit();
    public void searchProduits() throws SQLException {
        FilteredList<Produit> filteredData = new FilteredList<>(FXCollections.observableArrayList(sp.Show()), p -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(produits -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String nom = String.valueOf(produits.getNom_produit());
                String prix = String.valueOf(produits.getPrix());
                String quantite = String.valueOf(produits.getQuantite());
                String description = String.valueOf(produits.getDescription());
                String image = String.valueOf(produits.getImage());
                String categorie = String.valueOf(produits.getId_categorie());
                String lowerCaseFilter = newValue.toLowerCase();

                if (nom.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (prix.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (quantite.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (description.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (image.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (categorie.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Produit> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableProduits.comparatorProperty());
        tableProduits.setItems(sortedData);
    }
    private void TriNom() {
        ServiceProduit sp = new ServiceProduit();
        List<Produit> a = sp.TriNom();
        NomProduit.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        PrixProduit.setCellValueFactory(new PropertyValueFactory<>("prix"));
        QuantiteProduit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        DescriptionProduit.setCellValueFactory(new PropertyValueFactory<>("description"));
        ImageProduit.setCellValueFactory(new PropertyValueFactory<>("image"));
        Categorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));


        tableProduits.setItems(FXCollections.observableList(a));
    }





    private void TriPrix() {
        ServiceProduit sp = new ServiceProduit();
        List<Produit> a = sp.TriPrix();
        NomProduit.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        PrixProduit.setCellValueFactory(new PropertyValueFactory<>("prix"));
        QuantiteProduit.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        DescriptionProduit.setCellValueFactory(new PropertyValueFactory<>("description"));
        ImageProduit.setCellValueFactory(new PropertyValueFactory<>("image"));
        Categorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));


        tableProduits.setItems(FXCollections.observableList(a));
    }
    @FXML
    private void TriChoice(ActionEvent event) throws IOException {
        if (comboBoxTriProduits.getValue().equals("Nom ")) {
            TriNom();
        } else if (comboBoxTriProduits.getValue().equals("Prix")) {
            TriPrix();


        }


    }}


