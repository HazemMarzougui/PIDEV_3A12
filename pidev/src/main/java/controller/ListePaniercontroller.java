package controller;

import entities.commande;
import entities.produit;
import entities.panier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.produitService;
import services.panierService;
import test.MainFX;
import  services.commandeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class ListePaniercontroller implements Initializable {
    panierService ps = new panierService();
  commandeService commandeService=new commandeService();


    @FXML
    private GridPane ListePanierContainer;

    @FXML
    private TextField adresse;

    @FXML
    private HBox checkoutModel;

    @FXML
    private Pane content_area;

    @FXML
    private TextField email;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField telephone;

    @FXML
    private HBox updateCheckoutBtn;

    @FXML
    void UpdateCheckoutBtn(MouseEvent event) {

    }

    @FXML
    void fermer(MouseEvent event) {
        checkoutModel.setVisible(false);
    }

    @FXML
    void formulaire(ActionEvent event) {

        checkoutModel.setVisible(true);

    }




    @FXML
    void switchToPaymentModel(MouseEvent event) {

    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        checkoutModel.setVisible(false);
        afficherProduitsDansGridPane( );
    }

    private void afficherProduitsDansGridPane() {

        int column = 0;
        int row = 2;
        try {
            List<produit> produits = ps.getAllProducts();

            for (int i = 0; i < produits.size(); i++) {
               AdminProduitCommanderController adminProduitCommanderController =new AdminProduitCommanderController();
               ListeCommandeController listeCommandeController = new ListeCommandeController();
               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ListeCommande.fxml"));
                fxmlLoader.setController(listeCommandeController);
                HBox productCard = fxmlLoader.load();
                listeCommandeController.setProduit(produits.get(i));
                String textFieldText = listeCommandeController.getPrixp().getText();
                float floatValue = Float.parseFloat(textFieldText);
               MainFX.GlobalData.prix.add(floatValue);
                String labelText = listeCommandeController.getQuantite().getText();
                int quantit = Integer.parseInt(labelText);
                MainFX.GlobalData.quantites.add(quantit);
                final int index = i;
                listeCommandeController.getPlus().setOnMouseClicked(event -> {
                    int quantitt = Integer.parseInt(listeCommandeController.getQuantite().getText());
                    quantitt = quantitt+1;
                    listeCommandeController.getQuantite().setText(String.valueOf(quantitt));
                    MainFX.GlobalData.quantites.set(index, quantitt);
                });
                listeCommandeController.getMoin().setOnMouseClicked(event -> {
                    int quantitt = Integer.parseInt(listeCommandeController.getQuantite().getText());
                    if(quantitt > 1) {
                        quantitt = quantitt - 1;
                    }
                    listeCommandeController.getQuantite().setText(String.valueOf(quantitt));
                    MainFX.GlobalData.quantites.set(index, quantitt);

                });




                if (column == 1) {
                    column = 0;
                    ++row;
                }
                ListePanierContainer.add(productCard, column++, row);
                GridPane.setMargin(productCard, new Insets(0, 10, 15, 10));



                // Dans la méthode afficherProduitsDansGridPane

                int finalI = i;
                listeCommandeController.getDeletep().setOnMouseClicked(event -> {
                    // Obtenez l'ID du produit associé à ce bouton de suppression
                    int idProduit = produits.get(finalI).getId_produit(); // Supposons que getId() retourne l'ID du produit

                    // Appelez la méthode pour supprimer ce produit
                    ps.SupprimerProduitCommande(idProduit);

                    // Supprimez le produit de l'interface utilisateur
                    ListePanierContainer.getChildren().remove(productCard);
                });   
            }
        } catch (IOException e) {
            e.printStackTrace(); // Consider more user-friendly error handling
        }
    }

    public static int generateID() {
        LocalDateTime now = LocalDateTime.now();
        int id = now.getYear() * 100000000 +
                now.getMonthValue() * 1000000 +
                now.getDayOfMonth() * 10000 +
                now.getHour() * 100 +
                now.getMinute();
        id = id * 100 + now.getSecond();
        return id;
    }



    @FXML
    void passe_commande(MouseEvent event) {

        int id = -generateID();
        Float tot=0f;
        for (int i = 0; i < MainFX.GlobalData.produits.size(); i++) {
            tot+=MainFX.GlobalData.prix.get(i)*MainFX.GlobalData.quantites.get(i);
        }
        try {
            commandeService.ajoutercommande(new commande(id,Integer.parseInt(telephone.getText()),prenom.getText(),nom.getText(),adresse.getText(),email.getText(),tot));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Commande ajoute");
            alert.showAndWait();
            for (int i = 0; i < MainFX.GlobalData.produits.size(); i++) {
                ps.ajouterProduitPanier(new panier(MainFX.GlobalData.produits.get(i),id,MainFX.GlobalData.quantites.get(i),MainFX.GlobalData.prix.get(i)));
            }
            MainFX.GlobalData.produits.clear();
            MainFX.GlobalData.prix.clear();
            MainFX.GlobalData.quantites.clear();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }



    }







}