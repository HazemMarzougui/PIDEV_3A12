
package controller;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.commande;
import entities.panier;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import services.commandeService;
import services.panierService;
import test.MainFX;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.TrayNotificationAlert;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
public class AfficheItemController {


    @FXML
    private VBox Item;
    @FXML
    private HBox pdf;
     commandeService commandeService = new commandeService();

    @FXML
    private Text adresse;

    @FXML
    private HBox deleteAchat;

    @FXML
    private HBox editAddress;

    @FXML
    private Text id_commande;

    @FXML
    private Text nom;

    @FXML
    private Text prenom1;

    @FXML
    private Text telephone;
    private PdfPTable table;

    public HBox getEditAddress() {
        return editAddress;
    }


    public void setCommandeData(commande commande) {
        // Instantiate the produitService
        id_commande.setText(" " + commande.getId_commande());
        nom.setText(" " + commande.getNom());
        adresse.setText(" " + commande.getAdresse());
        telephone.setText(" " + commande.getTelephone());

        editAddress.setOnMouseClicked(event->{

            System.out.println("looooool"+commande.getId_commande());

            try {
                commande c = commandeService.getOneCommmande(commande.getId_commande());
                MainFX.GlobalData.idc = c;
                HBox checkoutModel1 = (HBox) ((Node) event.getSource()).getScene().lookup("#checkoutModel1");
                checkoutModel1.setVisible(true);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            //checkoutModel.setVisible(true);


        });

        deleteAchat.setOnMouseClicked(event -> {
            commandeService commandeService = new commandeService();
            panierService panierService = new panierService();

            // Supprimer l'élément de l'interface utilisateur
            commandeService.SupprimerProduitCommande(commande.getId_commande());
            panierService.AdminSupprimerProduitCommande(commande.getId_commande());






            // Afficher une notification pour informer l'utilisateur
            TrayNotificationAlert.notif("Address", "Address deleted successfully.",
                    NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));
        });
        pdf.setOnMouseClicked(event ->{

                    // Sélectionner l'emplacement de sauvegarde du fichier PDF
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Enregistrer le fichier PDF");
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers PDF", "*.pdf"));
                    File selectedFile = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());

                    if (selectedFile != null) {
                        // Récupérer les données de la commande
                        int commandeId = MainFX.GlobalData.globalLong;
                        commande c = null;
                        try {
                            c = commandeService.getOneCommmande(commandeId);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                        try {
                            // Créer le document PDF
                            Document document = new Document();
                            PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                            document.open();

                            // Créer une instance de l'image
                            Image image = Image.getInstance("C:\\Users\\Mon Pc\\Downloads\\wetransfer_pidev-sql_2024-03-01_0732\\fronte office\\fronte office\\src\\main\\resources\\assets\\img\\logo.png");


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
                            int id =commande.getId_commande();

                            addRows(table, panierService.getAllProductsForCommand(id));

                            // Ajouter la table au document
                            document.add(table);

                            // Calculer le total
                            double total = calculateTotal(panierService.getAllProductsForCommand(id));

                            // Ajouter le total
                            document.add(new Paragraph("\nTotal: " + total, FontFactory.getFont(FontFactory.TIMES_BOLD, 14)));

                            // Fermer le document
                            document.close();

                            System.out.println("Le fichier PDF de la facture a été généré avec succès.");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }



        });




    }


    private void addTableHeader(PdfPTable table, String... headers) {
        for (String header : headers) {
            PdfPCell cell = new PdfPCell();
            cell.setPadding(5);
            cell.setPhrase(new Phrase(header));
            table.addCell(cell);
        }
    }

    private void addRows(PdfPTable table, java.util.List<panier> paniers) {
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

    @FXML
    void editAdresse(MouseEvent event) {



    }

}
