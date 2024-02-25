package controller;

import entities.panier;
import entities.produit;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import services.panierService;
import test.MainFX;

public class ListeCommandeController implements Initializable {

    panierService panierService =new panierService();
    @FXML
    private Label quantite;

    public Label getQuantite() {
        return quantite;
    }

    public Text getPrixp() {
        return prixp;
    }

    public HBox getMoin() {
        return moin;
    }

    public HBox getPlus() {
        return plus;
    }

    @FXML
    private HBox deletep;

    public HBox getDeletep() {
        return deletep;
    }

    @FXML
    private HBox moin;

    @FXML
    private Text nomp;

    @FXML
    private HBox plus;

    @FXML
    private Text pointp;

    @FXML
    private HBox priceAfterOfferHbox;

    @FXML
    private Text prixp;

    @FXML
    private Text quantitep;

    @FXML
    private Text quantitepp;
    @FXML
    private Pane content_area;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setProduit(produit produit){

        nomp.setText(" "+produit.getNom());
        prixp.setText("" + produit.getPrix());
        quantitep.setText("" + produit.getQuantite());

        // deleteCommand btn click
        deletep.setOnMouseClicked(event -> {
            // System.out.println("ID du produit à supprimer : " + produit.getId());
            panierService.SupprimerProduitCommande(produit.getId_produit());
            // TrayNotificationAlert.notif("Command", "produit deleted successfully.",
            //NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));
            // supprimer le contenu de la liste et afficher la nouvelle liste

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListePanier.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis ce controller
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // end

        } );


         }


}







