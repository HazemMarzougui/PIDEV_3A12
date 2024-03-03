package controller;

import entities.Produit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.panierService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListeCommandeController implements Initializable {

    panierService panierService =new panierService();


    @FXML
    private ImageView imageP;
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

    public void setProduit(Produit produit){


        try {
            File file = new File("src/main/java/uploads/" + produit.getImage());
            String localUrl = file.toURI().toURL().toString();
            Image image = new Image(localUrl);
            imageP.setImage(image);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        nomp.setText(" "+produit.getNom_produit());
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







