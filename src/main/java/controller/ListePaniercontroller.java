package controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.commande;
import entities.panier;
import entities.produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.commandeService;
import services.panierService;
import test.MainFX;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
public class ListePaniercontroller implements Initializable {
    panierService ps = new panierService();
    commandeService commandeService = new commandeService();
    int addressTest = -1;
    int phoneTest = -1;

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
    private Pane content_area;

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
    private HBox prenomHbox1;
    @FXML
    private Text prenomE1;
    @FXML
    private TextField prenom1;

    @FXML
    private HBox nomHbox1;
    @FXML
    private Text nomE1;
    @FXML
    private TextField nom1;

    @FXML
    private HBox emailHbox1;
    @FXML
    private Text emailE1;
    @FXML
    private TextField email1;
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


        checkoutModel.setVisible(false);
        nomHbox.setVisible(false);
        prenomHbox.setVisible(false);
        adresseHbox.setVisible(false);
        telephoneHbox.setVisible(false);
        emailHbox.setVisible(false);
        checkoutModel1.setVisible(false);
        prenomHbox1.setVisible(false);
        nomHbox1.setVisible(false);
        adresseHbox1.setVisible(false);
        telephoneHbox1.setVisible(false);
        emailHbox1.setVisible(false);
        afficherProduitsDansGridPane();
    }

    private void afficherProduitsDansGridPane() {
        Float tot = 0f;
        int column = 0;
        int row = 2;
        try {
            List<produit> produits = ps.getAllProducts();

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


        // Continuer avec le processus de commande si tous les champs sont remplis

        int id = -generateID();
        MainFX.GlobalData.globalLong = id;
        Float tot = 0f;

        for (int i = 0; i < MainFX.GlobalData.produits.size(); i++) {
            tot += MainFX.GlobalData.prix.get(i) * MainFX.GlobalData.quantites.get(i);
        }

        try {
            commandeService.ajoutercommande(new commande(id, Integer.parseInt(telephone.getText()), prenom.getText(), nom.getText(), adresse.getText(), email.getText(), tot));

            // Effacer les champs après la commande
            prenom.clear();
            nom.clear();
            adresse.clear();
            telephone.clear();
            email.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Commande ajoutée");
            alert.showAndWait();

            for (int i = 0; i < MainFX.GlobalData.produits.size(); i++) {
                ps.ajouterProduitPanier(new panier(MainFX.GlobalData.produits.get(i), id, MainFX.GlobalData.quantites.get(i), MainFX.GlobalData.prix.get(i)));
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


    @FXML
    void close(MouseEvent event) {
        checkoutModel1.setVisible(false);
    }

    @FXML
    void modifie_commande(MouseEvent event) throws SQLException {
        // Récupérer l'ID de la commande
        int id = (int) MainFX.GlobalData.globalLong;

        // Récupérer la commande correspondant à cet ID
        commande c = commandeService.getOneCommmande(id);

        if (c != null) {
            // Afficher le modèle de modification de commande
            checkoutModel1.setVisible(true);
            setdata(c);
        } else {
            // Afficher un message d'erreur si la commande n'est pas trouvée
            System.out.println("Commande non trouvée pour l'ID : " + id);
        }
    }

    public void setdata(commande c) {
        // Mettre à jour les champs de l'interface utilisateur avec les données de la commande
        prenom1.setText(c.getPrenom());
        nom1.setText(c.getNom());
        adresse1.setText(c.getAdresse());
        telephone1.setText("" + c.getTelephone());
        email1.setText(c.getEmail());


    }


    @FXML
    void modifier(MouseEvent event) {


        // Récupérer l'ID de la commande
        int id = (int) MainFX.GlobalData.globalLong;

        // Récupérer la commande à partir des champs de l'interface utilisateur
        String prenom = prenom1.getText();
        String nom = nom1.getText();
        String adresse = adresse1.getText();
        int telephone = Integer.parseInt(telephone1.getText());
        String email = email1.getText();

        // Créer un objet commande avec les nouvelles valeurs
        commande commande = new commande(id, telephone, prenom, nom, adresse, email);

        nomHbox1.setVisible(false);
        prenomHbox1.setVisible(false);
        adresseHbox1.setVisible(false);
        telephoneHbox1.setVisible(false);
        emailHbox1.setVisible(false);
        // Vérifier si tous les champs sont remplis
        if (prenom1.getText().isEmpty()) {
            prenomHbox1.setVisible(true);
            return;
        }
        if (adresse1.getText().isEmpty()) {
            adresseHbox1.setVisible(true);
            return;
        }

        if (nom1.getText().isEmpty()) {
            nomHbox1.setVisible(true);
            return;
        }
        if (email1.getText().isEmpty()) {
            emailHbox1.setVisible(true);
            return;
        }
        if (telephone1.getText().isEmpty()) {
            telephoneHbox1.setVisible(true);
            return;
        }

        String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        if (!email1.getText().matches(emailPattern)) {
            emailE1.setText("email invalid");
            emailHbox1.setVisible(true);
            return;
        }

        // Vérifier le format du numéro de téléphone

        if (!telephone1.getText().matches("\\d{8}")) {
            telephoneE1.setText("Le téléphone doit contenir 8 chiffres");
            telephoneHbox1.setVisible(true);
            return;
        }

        // Vérifier si la commande existe
        if (commande != null) {
            try {
                // Appeler la méthode de mise à jour dans votre service ou gestionnaire de données
                commandeService.modifierCommande(commande, id);
                System.out.println(" commande modifier");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Commande modifiée");
                alert.showAndWait();

            } catch (SQLException e) {
                System.out.println("Erreur lors de la mise à jour de la commande : " + e.getMessage());
                // Vous pouvez également afficher une boîte de dialogue ou une alerte pour informer l'utilisateur de l'erreur.
            }
        } else {
            System.out.println("Impossible de modifier la commande car elle est null.");
        }
    }


    @FXML
    void imprimer(MouseEvent event) throws SQLException {

        // Sélectionner l'emplacement de sauvegarde du fichier PDF
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le fichier PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers PDF", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            // Récupérer les données de la commande
            int commandeId = MainFX.GlobalData.globalLong;
            commande c = commandeService.getOneCommmande(commandeId);

            try {
                // Créer le document PDF
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();

                // Créer une instance de l'image
                Image image = Image.getInstance("C:\\Users\\Mon Pc\\IdeaProjects\\fronte office\\src\\main\\resources\\assets\\img\\logo.png");


                // Positionner l'image en haut à gauche
                image.setAbsolutePosition(450, document.getPageSize().getHeight() -120);

                // Modifier la taille de l'image
                image.scaleAbsolute(100, 100);

                // Ajouter l'image au document
                document.add(image);

                Paragraph title = new Paragraph("FACTURE",  FontFactory.getFont(FontFactory.TIMES_BOLD, 20));
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingBefore(50); // Ajouter une marge avant le titre pour l'éloigner de l'image
                title.setSpacingAfter(20);
                document.add(title);


                // Ajouter le logo en haut à gauche
                //Image logo = Image.getInstance("chemin/vers/votre/logo.png");
                //logo.scaleAbsolute(150, 150);
               // document.add(logo);

                // Ajouter les informations de la société (lieu, date)
                Paragraph companyInfo = new Paragraph();
                companyInfo.add(new Chunk("Votre société\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 16)));
                companyInfo.add("Pole Technologie , Ghazela\n");
                companyInfo.add("Ariana,Tunisie\n\n");
                companyInfo.add("Tél : +70 800 000\n");
                companyInfo.add("Email :TapNeat@gmail.con\n\n");
                companyInfo.add("Date : " + LocalDate.now().toString() + "\n\n");
                document.add(companyInfo);

                // Ajouter les informations du client
                Paragraph clientInfo = new Paragraph();
                clientInfo.add(new Chunk("Client:\n", FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));
                clientInfo.add("Prénom Nom: " + c.getPrenom() + " " + c.getNom() + "\n");
                clientInfo.add("Adresse: " + c.getAdresse() + "\n");
                clientInfo.add("Tél: " + c.getTelephone() + "\n");
                clientInfo.add("Email: " + c.getEmail() + "\n\n");
                document.add(clientInfo);



                // Créer la table des produits
                PdfPTable table = new PdfPTable(4);
                table.setWidthPercentage(100);
                table.setWidths(new float[] { 2, 4, 2, 2 }); // Définir la largeur des colonnes

                // En-têtes de colonnes
                    addTableHeader(table, "ID_Commande", "ID_Produit", "Prix unitaire", "Quantité");
               panierService panierService =new panierService();
                // Ajouter les produits à la table
                addRows(table, panierService.getAllProductsForCommand(commandeId));

                // Ajouter la table au document
                document.add(table);

                // Calculer le total
                double total = calculateTotal(panierService.getAllProductsForCommand(commandeId));

                // Ajouter le total
                document.add(new Paragraph("\nTotal: " + total, FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));

                // Fermer le document
                document.close();

                System.out.println("Le fichier PDF de la facture a été généré avec succès.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Méthode pour ajouter les en-têtes de colonnes à la table
    private void addTableHeader(PdfPTable table, String... headers) {
        for (String header : headers) {
            PdfPCell cell = new PdfPCell();
            cell.setPadding(5);
            cell.setPhrase(new Phrase(header));
            table.addCell(cell);
        }
    }

    // Méthode pour ajouter les lignes de produits à la table

    // Méthode pour ajouter les lignes de produits à la table
    private void addRows(PdfPTable table, List<panier> paniers) {
        for (panier p : paniers) {
            table.addCell(String.valueOf(p.getId_commande()));
            table.addCell(String.valueOf(p.getId_produit())); // ID du produit
            table.addCell(String.valueOf(p.getPrix_u())); // Prix unitaire
            table.addCell(String.valueOf(p.getQuantite())); // Quantité
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

}

