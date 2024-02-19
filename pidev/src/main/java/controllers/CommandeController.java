package controllers;



import entities.Panier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import services.ServiceCommande;
import entities.commande;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;

public class CommandeController {
    ServiceCommande serviceCommande =new ServiceCommande();
    @FXML
    private Button buttonafficher;

    @FXML
    private Button buttonajouter;

    @FXML
    private Button buttonmodifier;

    @FXML
    private Button buttonsupprime;


    @FXML
    private TextField tf_adresse;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_nom;

    @FXML
    private TextField tf_prenom;

    @FXML
    private TextField tf_telephone;
    @FXML
    private TableColumn<commande, String> col_adresse;

    @FXML
    private TableColumn<commande, String> col_email;

    @FXML
    private TableColumn<commande,Integer> col_id;

    @FXML
    private TableColumn<commande, String> col_nom;

    @FXML
    private TableColumn<commande,String> col_prenom;

    @FXML
    private TableColumn<commande,Integer> col_telephone;


    @FXML
    private TableView<commande> tv_commande;
    int id=0;

    @FXML
    void AfficherCommande(ActionEvent event) {


        try {
            ObservableList<commande> observableList = FXCollections.observableList(serviceCommande.afficher());
            tv_commande.setItems(observableList);
            col_id.setCellValueFactory(new PropertyValueFactory<>("idcommande"));
            col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            col_telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            col_email.setCellValueFactory(new PropertyValueFactory<>("email"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void AjouterCommande(ActionEvent event) {
        try {
            serviceCommande.ajouter(new commande(tf_prenom.getText(),tf_nom.getText(),tf_adresse.getText(), Integer.parseInt(tf_telephone.getText()),tf_email.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Commande ajoute");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }



    }

    @FXML
    void ModifierCommande(ActionEvent event) {

        // Récupérer l'objet Conseil sélectionné dans le TableView
        commande commande = tv_commande.getSelectionModel().getSelectedItem();


        // Vérifier si un élément a été effectivement sélectionné
        if (commande != null) {
            // Mettre à jour les propriétés du Conseil avec les valeurs des champs de texte
            commande.setPrenom(tf_prenom.getText());
            commande.setNom(tf_nom.getText());
            commande.setAdresse(tf_adresse.getText());
            commande.setTelephone(Integer.parseInt(tf_telephone.getText()));
            commande.setEmail(tf_email.getText());



            // Appeler la méthode de mise à jour dans votre service ou gestionnaire de données
            try {
                serviceCommande.modifier(commande, id); // Assuming you have an update method in your service
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
            ObservableList<commande> observableList = FXCollections.observableList(serviceCommande.afficher());
            tv_commande.setItems(observableList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // Gérer l'erreur selon vos besoins
        }
    }

    // Méthode pour réinitialiser les champs de texte
    private void resetFields() {
        tf_prenom.clear();
        tf_nom.clear();
        tf_adresse.clear();
        tf_telephone.clear();
        tf_email.clear();

    }


    @FXML
    void SupprimerCommande(ActionEvent event) {
        commande c = tv_commande.getSelectionModel().getSelectedItem();
        if (c != null) {
            try {
                serviceCommande.supprimer(c.getIdcommande());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            resetFields();
            refreshTableView();
        }

    }

    @FXML
    void getData(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            // Récupérer l'objet Panier sélectionné dans le TableView
            commande c = tv_commande.getSelectionModel().getSelectedItem();
            // Vérifi  er si un élément a été effectivement sélectionné
            if (c != null) {
                // Récupérer les données du Panier et les afficher dans les champs de texte appropriés
                id = c.getIdcommande();
                tf_prenom.setText(c.getPrenom());
                tf_nom.setText(c.getNom());
                tf_adresse.setText(c.getAdresse());
                tf_telephone.setText(String.valueOf(c.getTelephone()));
                tf_email.setText(c.getEmail());
                // ajouterp.setDisable(true);

            }
        }


    }
    @FXML
    void retour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Panier.fxml"));
        tf_prenom.getScene().setRoot(root);

    }

}
