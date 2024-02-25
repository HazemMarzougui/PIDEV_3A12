package controller;

import entities.commande;
import entities.produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.panierService;
import test.MainFX;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.ResourceBundle;

public class AdminListCommandItem implements Initializable {

    @FXML
    private HBox HBoxx;

    @FXML
    private Label adresse;

    @FXML
    private Button btn_del;

    @FXML
    private Button btn_mod1;

    @FXML
    private HBox detailsCommand;



    @FXML
    private Label email;

    @FXML
    private Label idcommande;

    @FXML
    private Label nom;
    public AdminListCommandItem() {
        // Initialisez detailsCommand dans le constructeur
        detailsCommand = new HBox();
        // Autres initialisations si nécessaires
    }

    // Getter pour detailsCommand
    public HBox getDetailsCommand() {
        return detailsCommand;
    }
    @FXML
    private Label prixtotale;

    @FXML
    private Label telephone;

    @FXML
    void delete_conseil(ActionEvent event) {

    }

    @FXML
    void update_conseil(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*AdminListCommandController adminListCommandController =new AdminListCommandController();
        adminListCommandController.commandModel.setVisible(false);*/
    }
    public void setCommandeData(commande commande) {
        // Instantiate the produitService

        idcommande.setText(" "+ commande.getId_commande());
        nom.setText("" + commande.getNom());
        adresse.setText("" + commande.getAdresse());
        telephone.setText("" + commande.getTelephone());
        email.setText("" + commande.getEmail());
        prixtotale.setText("" + commande.getPrix_totale());


        panierService p = new panierService();
       detailsCommand.setOnMouseClicked(event -> {
            System.out.println("ID du commande est affichée : " + commande.getId_commande());
           try {
               MainFX.GlobalData.PanierProCom = p.getAllProductsForCommand(commande.getId_commande());
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
           //MainFX.GlobalData.achat= Collections.  (1);


            // afficher le contenu de la liste et afficher la nouvelle liste
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminListCommand.fxml"));
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
        });
        // END showCommand btn click




    }


}
