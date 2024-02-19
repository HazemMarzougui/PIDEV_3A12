package controllers;

import entities.Panier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import services.ServicePanier;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.SQLException;



public class PanierController {
    ServicePanier servicePanier = new ServicePanier();
    @FXML
    private TextField tf_etat;
    @FXML
    private Button ajouterp;
    @FXML
    private TextField tf_idpr;

    @FXML
    private TextField tf_idu;

    //affiche
    @FXML
    private TableColumn<Panier, String> col_etat;

    @FXML
    private TableColumn<Panier, Integer> col_idp;

    @FXML
    private TableColumn<Panier, Integer> col_idpr;

    @FXML
    private TableColumn<Panier, Integer> col_idu;

    @FXML
    private TableView<Panier> tableview;


    int id = 0;


    @FXML
    void afficherpanier(ActionEvent event) {


        try {
            ObservableList<Panier> observableList = FXCollections.observableList(servicePanier.afficher());
            tableview.setItems(observableList);
            col_idp.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
            col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
            col_idu.setCellValueFactory(new PropertyValueFactory<>("id_user"));
            col_idpr.setCellValueFactory(new PropertyValueFactory<>("id_produit"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void ajouterpanier(ActionEvent event) {
        try {
            servicePanier.ajouter(new Panier(Integer.parseInt(tf_idu.getText()), Integer.parseInt(tf_idpr.getText()), tf_etat.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Panier ajoute");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


    }


    @FXML
    void getData(MouseEvent event) {

        if (event.getButton() == MouseButton.PRIMARY) {
            // Récupérer l'objet Panier sélectionné dans le TableView
            Panier panier = tableview.getSelectionModel().getSelectedItem();
            // Vérifi  er si un élément a été effectivement sélectionné
            if (panier != null) {
                // Récupérer les données du Panier et les afficher dans les champs de texte appropriés
                id = panier.getId_panier();
                tf_idpr.setText(String.valueOf(panier.getId_produit()));
                tf_idu.setText(String.valueOf(panier.getId_user()));
                tf_etat.setText(panier.getEtat());
                // ajouterp.setDisable(true);

            }
        }


    }

    @FXML
    void buttonmodifier(ActionEvent event) {

        // Récupérer l'objet Conseil sélectionné dans le TableView
        Panier panier = tableview.getSelectionModel().getSelectedItem();


        // Vérifier si un élément a été effectivement sélectionné
        if (panier != null) {
            // Mettre à jour les propriétés du Conseil avec les valeurs des champs de texte
            panier.setEtat(tf_etat.getText());
            panier.setId_produit(Integer.parseInt(tf_idpr.getText()));
            panier.setId_user(Integer.parseInt(tf_idu.getText()));


            // Appeler la méthode de mise à jour dans votre service ou gestionnaire de données
            try {
                servicePanier.modifier(panier, id); // Assuming you have an update method in your service
                // Rafraîchir la TableView après la modification
                refreshTableView();
            } catch (SQLException e) {
                System.out.println("Erreur lors de la mise à jour du panier: " + e.getMessage());
                // Vous pouvez également afficher une boîte de dialogue ou une alerte pour informer l'utilisateur de l'erreur.
            }
        }
        resetFields();
        refreshTableView();
    }


    private void refreshTableView() {
        try {
            ObservableList<Panier> observableList = FXCollections.observableList(servicePanier.afficher());
            tableview.setItems(observableList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // Gérer l'erreur selon vos besoins
        }
    }

    // Méthode pour réinitialiser les champs de texte
    private void resetFields() {
        tf_idu.clear();
        tf_idpr.clear();
        tf_etat.clear();

    }


    @FXML
    void buttonsupprimer(ActionEvent event) {

        Panier panier = tableview.getSelectionModel().getSelectedItem();
        if (panier != null) {
            try {
                servicePanier.supprimer(panier.getId_panier());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            resetFields();
            refreshTableView();
        }


    }


    @FXML
    void gocommande(ActionEvent event) throws IOException {


            Parent root = FXMLLoader.load(getClass().getResource("/Commande.fxml"));
            tf_idpr.getScene().setRoot(root);

        }
    }








