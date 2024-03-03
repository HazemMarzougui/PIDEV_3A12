package controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import entities.Produit;
import entities.commande;
import entities.panier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import services.commandeService;
import services.panierService;
import test.MainFX;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.TrayNotificationAlert;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class ListePaniercontroller implements Initializable {
    panierService ps = new panierService();
    commandeService commandeService = new commandeService();


    public HBox getCommandModel() {
        return commandModel;
    }


    // Method to refresh the commandModel HBox
    public void refreshCommandModel() {

            // Clear existing content if needed
            commandModel.getChildren().clear();

            // Add new content or refresh content as required
            // Example: Add new content to commandModel

    }

    @FXML
    private ScrollPane scroll;



    @FXML
    private Text totalPrice;
    @FXML
    private GridPane ListePanierContainer;

    @FXML
    private TextField adresse;

    @FXML
    private Text adresseE;

    @FXML
    private HBox totale;

    @FXML
    private HBox checkoutModel;
    @FXML
    private HBox checkoutModel2;



    @FXML
    private Pane content_area;
    @FXML
    private Pane content_areaa;

    @FXML
    private TextField email;

    @FXML
    private Text emailE;

    @FXML
    private TextField nom;

    @FXML
    private Text nomE;

    @FXML
    private HBox nomHbox;

    @FXML
    private HBox emailHbox;

    @FXML
    private TextField prenom;

    @FXML
    private Text prenomE;

    @FXML
    private HBox prenomHbox;

    @FXML
    private TextField telephone;

    @FXML
    private Text telephoneE;

    @FXML
    private HBox telephoneHbox;

    @FXML
    private HBox adresseHbox;

    @FXML
    private HBox updateCheckoutBtn;


    @FXML
    private HBox telephoneHbox1;
    @FXML
    private Text telephoneE1;
    @FXML
    private TextField telephone1;










    @FXML
    private HBox checkoutModel1;

    @FXML
    private HBox adresseHbox1;
    @FXML
    private Text adresseE1;

    @FXML
    private TextField adresse1;


    @FXML
    void fermer(MouseEvent event) {
        checkoutModel.setVisible(false);
    }

    @FXML
    void formulaire(ActionEvent event) {

        checkoutModel.setVisible(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String nomm = MainFX.connecteduser.getNom();
        nom.setText(nomm);

        String prenomm = MainFX.connecteduser.getPrenom();
        prenom.setText(prenomm);

        String adressee = MainFX.connecteduser.getAddresse();
        adresse.setText(adressee);

        int tell = MainFX.connecteduser.getTel();
        telephone.setText(""+tell);

        String mail = MainFX.connecteduser.getEmail();
        email.setText(mail);
        commandModel.setVisible(false);
        checkoutModel.setVisible(false);
        nomHbox.setVisible(false);
        prenomHbox.setVisible(false);
        adresseHbox.setVisible(false);
        telephoneHbox.setVisible(false);
        emailHbox.setVisible(false);
        checkoutModel1.setVisible(false);
        adresseHbox1.setVisible(false);
        telephoneHbox1.setVisible(false);
        checkoutModel2.setVisible(false);

        afficherProduitsDansGridPane();


    }
    @FXML
    void fermer1(MouseEvent event) {
        checkoutModel2.setVisible(false);

    }

  private void afficherProduitsDansGridPane() {
        Float tot = 0f;
        int column = 0;
        int row = 2;
        try {
            List<Produit> produits = ps.getAllProducts();

            for (int i = 0; i < produits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ListeCommande.fxml"));
                ListeCommandeController listeCommandeController = new ListeCommandeController();
                fxmlLoader.setController(listeCommandeController);
                HBox productCard = fxmlLoader.load();

               listeCommandeController.setProduit(produits.get(i));
                String textFieldText = listeCommandeController.getPrixp().getText();
                float prix = Float.parseFloat(textFieldText);
                MainFX.GlobalData.prix.add(prix);
                String Quantite = listeCommandeController.getQuantite().getText();
                int quantit = Integer.parseInt(Quantite);
                MainFX.GlobalData.quantites.add(quantit);

                tot += prix * quantit;

                // Plus button click event
                final int index = i;
                listeCommandeController.getPlus().setOnMouseClicked(event -> {
                    // Increase quantity
                    int quantitt = Integer.parseInt(listeCommandeController.getQuantite().getText());
                    quantitt++;
                    listeCommandeController.getQuantite().setText(String.valueOf(quantitt));
                    MainFX.GlobalData.quantites.set(index, quantitt);
                    updateTotalPrice();
                });

                // Minus button click event
                listeCommandeController.getMoin().setOnMouseClicked(event -> {
                    // Decrease quantity
                    int quantitt = Integer.parseInt(listeCommandeController.getQuantite().getText());
                    if (quantitt > 1) {
                        quantitt--;
                        listeCommandeController.getQuantite().setText(String.valueOf(quantitt));
                        MainFX.GlobalData.quantites.set(index, quantitt);
                        updateTotalPrice();
                    }
                });

                // Delete button click event
                listeCommandeController.getDeletep().setOnMouseClicked(event -> {
                    // Remove product
                    MainFX.GlobalData.prix.remove(index);
                    MainFX.GlobalData.quantites.remove(index);
                    ListePanierContainer.getChildren().remove(productCard);
                    updateTotalPrice();
                });

                // Add product card to container
                if (column == 1) {
                    column = 0;
                    ++row;
                }
                ListePanierContainer.add(productCard, column++, row);
                GridPane.setMargin(productCard, new Insets(0, 5, 5, 5));

            }

            // Set total price
            totalPrice.setText(String.valueOf(tot));

        } catch (IOException e) {
            e.printStackTrace(); // Consider more user-friendly error handling
        }
    }

    // Method to update total price
    private void updateTotalPrice() {
        Float totale = 0f;
        for (int i = 0; i < MainFX.GlobalData.prix.size(); i++) {
            totale += MainFX.GlobalData.prix.get(i) * MainFX.GlobalData.quantites.get(i);
        }
        totalPrice.setText(String.valueOf(totale));
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

        nomHbox.setVisible(false);
        prenomHbox.setVisible(false);
        adresseHbox.setVisible(false);
        telephoneHbox.setVisible(false);
        emailHbox.setVisible(false);
        // Vérifier si tous les champs sont remplis
        if (prenom.getText().isEmpty()) {
            prenomHbox.setVisible(true);
            return;
        }
        if (adresse.getText().isEmpty()) {
            adresseHbox.setVisible(true);
            return;
        }

        if (nom.getText().isEmpty()) {
            nomHbox.setVisible(true);
            return;
        }
        if (email.getText().isEmpty()) {
            emailHbox.setVisible(true);
            return;
        }
        if (telephone.getText().isEmpty()) {
            telephoneHbox.setVisible(true);
            return;
        }

        String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        if (!email.getText().matches(emailPattern)) {
            emailE.setText("email invalid");
            emailHbox.setVisible(true);
            return;
        }

        // Vérifier le format du numéro de téléphone

        if (!telephone.getText().matches("\\d{8}")) {
            telephoneE.setText("Le téléphone doit contenir 8 chiffres");
            telephoneHbox.setVisible(true);
            return;
        }

        checkoutModel.setVisible(false);
        checkoutModel2.setVisible(true);


    }


    @FXML
    void close(MouseEvent event) {

        checkoutModel1.setVisible(false);
    }




    public void setdata(commande c) {

        adresse1.setText(c.getAdresse());
        telephone1.setText("" + c.getTelephone());
    }


    @FXML
    void modifier(MouseEvent event) {



        adresseHbox1.setVisible(false);
        telephoneHbox1.setVisible(false);

        // Vérifier si tous les champs sont remplis

        if (adresse1.getText().isEmpty()) {
            adresseHbox1.setVisible(true);
            return;
        }



        if (telephone1.getText().isEmpty()) {
            telephoneHbox1.setVisible(true);
            return;
        }



        // Vérifier le format du numéro de téléphone

        if (!telephone1.getText().matches("\\d{8}")) {
            telephoneE1.setText("Le téléphone doit contenir 8 chiffres");
            telephoneHbox1.setVisible(true);
            return;
        }


        int id = MainFX.GlobalData.idc.getId_commande();

        String adresse = adresse1.getText();
        int telephone = Integer.parseInt(telephone1.getText());

        commande commande = new commande(id, telephone,  adresse);


        // Vérifier si la commande existe
        if (commande != null) {
            try {
                // Appeler la méthode de mise à jour dans votre service ou gestionnaire de données
                commandeService.modifierCommande(commande, id);
                System.out.println(" commande modifier");

                // Afficher une notification pour informer l'utilisateur
                TrayNotificationAlert.notif("Commande", "Commande Modifier  avec Success .",
                        NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));

            } catch (SQLException e) {
                System.out.println("Erreur lors de la mise à jour de la commande : " + e.getMessage());
                // Vous pouvez également afficher une boîte de dialogue ou une alerte pour informer l'utilisateur de l'erreur.
            }
        } else {
            System.out.println("Impossible de modifier la commande car elle est null.");
        }
    }









    // Méthode pour calculer le total
    private double calculateTotal(List<panier> paniers) {
        double total = 0;
        for (panier p : paniers) {
            total += p.getPrix_u() * p.getQuantite();
        }
        return total;
    }


    @FXML
    private HBox commandModel;

    @FXML
    void close_commandDetailsModel(MouseEvent event) {
        commandModel.setVisible(false);

    }

    @FXML
    private GridPane commandDetailsContainer;
    List<AfficheItemController> afficheItemControllerList = new ArrayList<>();








    @FXML
    void liste_commande(MouseEvent event) throws IOException, SQLException {


        int Column = 0;
        int Row = 1;

        // Vous avez commenté cette ligne
        //commandModel.setVisible(true);
        //AdminListCommandItem adminListCommandItem = new AdminListCommandItem();
        List<commande> commandes = commandeService.getCommandUser(MainFX.connecteduser.getId()); // Supposons que getId() retourne l'ID de la commande
        for (int i = 0; i < commandes.size(); i++) {
            commandModel.setVisible(true);
            adresse1.setText(commandes.get(i).getAdresse());
            telephone1.setText(""+commandes.get(i).getTelephone());

            AfficheItemController afficheItemController = new AfficheItemController();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AfficheItem.fxml"));
            fxmlLoader.setController(afficheItemController);
            VBox commandee = fxmlLoader.load();
            afficheItemController.setCommandeData(commandes.get(i));
            if (Column == 1) {
                Column = 0;
                ++Row;
            }
            commandDetailsContainer.add(commandee, Column++, Row);
            GridPane.setMargin(commandee, new Insets(0, 5, 5, 5));
        }

    }










    @FXML
    void mmInputTyped(KeyEvent event) {

    }
    @FXML
    void cvcInputTyped(KeyEvent event) {

    }

    @FXML
    void yyInputTyped(KeyEvent event) {

    }

    @FXML
    void zipInputTyped(KeyEvent event) {

    }
    @FXML
    void cardNumberInputTyped(KeyEvent event) {

    }


    @FXML
    private TextField yyInput;

    @FXML
    private Text yyInputError;

    @FXML
    private HBox yyInputErrorHbox;

    @FXML
    private TextField zipInput;

    @FXML
    private Text zipInputError;

    @FXML
    private HBox zipInputErrorHbox;

    @FXML
    private TextField cardNumberInput;

    @FXML
    private Text cardNumberInputError;

    @FXML
    private HBox cardNumberInputErrorHbox;


    @FXML
    private TextField cvcInput;

    @FXML
    private Text cvcInputError;

    @FXML
    private HBox cvcInputErrorHbox;


    @FXML
    private TextField mmInput;

    @FXML
    private Text mmInputError;

    @FXML
    private HBox mmInputErrorHbox;


    private void StripeFunction() {
        String STRIPE_SECRET_KEY = "sk_test_51MgYOOFXYK38vFYwOPGPKxftWYpStBWSuhx2ltC4jYfuyWkTxrXbpuVAGx6VrBBehZQtX5uJFFA7os4WQTVCFORz00pGTPG1FH";
        // Set up the Stripe API key
        Stripe.apiKey = STRIPE_SECRET_KEY;
        // Get the credit card details from the text fields
        String cardNumber = cardNumberInput.getText();// "4242 4242 4242 4242"
        int expMonth = Integer.parseInt(mmInput.getText());// 03
        int expYear = Integer.parseInt(yyInput.getText());// 45
        String cvc = cvcInput.getText();// "678"
        String zip = zipInput.getText();// "12345"

        // Create a map of the credit card details
        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", cardNumber);
        cardParams.put("exp_month", expMonth);
        cardParams.put("exp_year", expYear);
        cardParams.put("cvc", cvc);
        cardParams.put("address_zip", zip); // Add the zip code to the cardParams map

            cardParams.put("name", "Hleli Khairidinne");
        cardParams.put("address_line1", "123 Main St");
        cardParams.put("address_line2", "Apt 4");
        cardParams.put("address_city", "Anytown");
        cardParams.put("address_state", "CA");
        cardParams.put("address_country", "US");

        // Create a Stripe token for the credit card details
        Token token = null;
        try {
            Map<String, Object> tokenParams = new HashMap<>();
            tokenParams.put("card", cardParams);
            token = Token.create(tokenParams);
            System.out.println("Stripe token ID: " + token.getId());
        } catch (StripeException e) {
            e.printStackTrace();
            // handle the error appropriately
        }

        // Create a charge for the payment
        Charge charge = null;
        try {
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount",1000);
            chargeParams.put("currency", "usd");
            chargeParams.put("source", token.getId()); // Use the token ID as the source for the charge
            charge = Charge.create(chargeParams);
        } catch (StripeException e) {
            System.out.println("Error creating charge: " + e.getMessage());
            e.printStackTrace();
        }

        if (charge == null || charge.getFailureMessage() != null) {
            System.out.println("Charge failed: " + charge.getFailureMessage());
        }
    }


    @FXML
    void payemant(ActionEvent event) throws StripeException {
       StripeFunction();

        // Continuer avec le processus de commande si tous les champs sont remplis

        int id = -generateID();
        MainFX.GlobalData.globalLong = id;
        Float tot = 0f;

        for (int i = 0; i < MainFX.GlobalData.produits.size(); i++) {
            tot += MainFX.GlobalData.prix.get(i) * MainFX.GlobalData.quantites.get(i);
        }

        try {
            commandeService.ajoutercommande(new commande(id, Integer.parseInt(telephone.getText()), prenom.getText(), nom.getText(), adresse.getText(), email.getText(), tot ,MainFX.connecteduser.getId()));

            //Effacer les champs après la commande
            prenom.clear();
            nom.clear();
            adresse.clear();
            telephone.clear();
            email.clear();

            TrayNotificationAlert.notif("Commande", "Commande Ajouter avec Success .",
                    NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));


            for (int i = 0; i < MainFX.GlobalData.produits.size(); i++) {
                ps.ajouterProduitPanier(new panier(MainFX.GlobalData.produits.get(i), id, MainFX.GlobalData.quantites.get(i), MainFX.GlobalData.prix.get(i),MainFX.connecteduser.getId()));
            }

            MainFX.GlobalData.produits.clear();
            MainFX.GlobalData.prix.clear();
            MainFX.GlobalData.quantites.clear();
        } catch (SQLException e) {
            TrayNotificationAlert.notif("Commande", "error .",
                    NotificationType.ERROR, AnimationType.POPUP, Duration.millis(2500));
        }
    }







}

