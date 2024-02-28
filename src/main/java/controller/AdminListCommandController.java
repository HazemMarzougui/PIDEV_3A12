package controller;

import entities.commande;
import entities.panier;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import services.commandeService;
import services.panierService;
import test.MainFX;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AdminListCommandController implements Initializable {


    commandeService c =new commandeService();
    panierService p =new panierService();
    @FXML
    private HBox qrCodeImgModel;

    @FXML
    private GridPane commandDetailsContainer;

    public GridPane getCommandDetailsContainer() {
        return commandDetailsContainer;
    }

    @FXML
    public HBox commandModel;

    public HBox getCommandModel() {
        return commandModel;
    }

    @FXML
    private GridPane commandsListContainer;



    @FXML
    void close_commandDetailsModel(MouseEvent event) {

        commandModel.setVisible(false);

        MainFX.GlobalData.PanierProCom.clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        commandModel.setVisible(false);
        qrCodeImgModel.setVisible(false);
        afficherCommandeDansGridPane();

    }



    private void afficherCommandeDansGridPane() {

        int column = 0;
        int row = 1;
        try {
            List<commande> commandes = c.getAllCommand();
            for (commande commande : commandes) {




                    int Column = 0;
                    int Row = 1;

                      // Vous avez commenté cette ligne
                    commandModel.setVisible(true);
                AdminListCommandItem adminListCommandItem = new AdminListCommandItem();
                        List<panier> paniers = MainFX.GlobalData.PanierProCom; // Supposons que getId() retourne l'ID de la commande
                        for (int i = 0; i < paniers.size(); i++) {

                            commandModel.setVisible(true);

                            AdminProduitCommanderController adminProduitCommanderController = new AdminProduitCommanderController();
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AdminProduitCommander.fxml"));
                            fxmlLoader.setController(adminProduitCommanderController);
                            HBox commandee = fxmlLoader.load();
                            adminProduitCommanderController.setDatProduitt(paniers.get(i));
                            if (Column == 1) {
                                Column = 0;
                                ++Row;
                            }
                            commandDetailsContainer.add(commandee, Column++, Row);
                            GridPane.setMargin(commandee, new Insets(0, 5, 5, 5));
                        }



                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AdminListCommandItem.fxml"));
                fxmlLoader.setController(adminListCommandItem);
                HBox commandeitem = fxmlLoader.load();
                adminListCommandItem.setCommandeData(commande);
                if (column == 1) {
                    column = 0;
                    ++row;
                }
                commandsListContainer.add(commandeitem, column++, row);
                GridPane.setMargin(commandeitem, new Insets(0, 5, 5, 5));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Gérer l'exception correctement
        }
    }
    @FXML
    void close_QrCodeModel(MouseEvent event) {
        qrCodeImgModel.setVisible(false);

    }

    @FXML
    void pdf(MouseEvent event) throws SQLException {
/*
// Afficher la boîte de dialogue de sélection de fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le fichier PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers PDF", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            // Générer le fichier PDF avec l'emplacement de sauvegarde sélectionné
            // Récupérer la liste des produits
            int id= (int) MainFX.GlobalData.globalLong;
            commandeService achatsService = new commandeService();
            List<commande> achatList = (List<commande>) achatsService.getOneCommmande(id);

            try {
                // Créer le document PDF
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();

                // Créer une instance de l'image
                Image image = Image.getInstance(System.getProperty("user.dir") + "/src/assets/img/logo.png");

                // Positionner l'image en haut à gauche
                image.setAbsolutePosition(5, document.getPageSize().getHeight() - 120);

                // Modifier la taille de l'image
                image.scaleAbsolute(150, 150);

                // Ajouter l'image au document
                document.add(image);

                // Créer une police personnalisée pour la date
                Font fontDate = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                BaseColor color = new BaseColor(50, 187, 111); // Rouge: 50, Vert: 187, Bleu: 111
                fontDate.setColor(color); // Définir la couleur de la police

                // Créer un paragraphe avec le lieu
                Paragraph tunis = new Paragraph("Tunis", fontDate);
                tunis.setIndentationLeft(455); // Définir la position horizontale
                tunis.setSpacingBefore(-30); // Définir la position verticale
                // Ajouter le paragraphe au document
                document.add(tunis);

                // Obtenir la date d'aujourd'hui
                LocalDate today = LocalDate.now();

                // Créer un paragraphe avec la date
                Paragraph date = new Paragraph(today.toString(), fontDate);

                date.setIndentationLeft(437); // Définir la position horizontale
                date.setSpacingBefore(1); // Définir la position verticale
                // Ajouter le paragraphe au document
                document.add(date);

                // Créer une police personnalisée
                Font font = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD);
                BaseColor titleColor = new BaseColor(67, 136, 43); //
                font.setColor(titleColor);

                // Ajouter le contenu au document
                Paragraph title = new Paragraph("Liste des produits", font);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingBefore(50); // Ajouter une marge avant le titre pour l'éloigner de l'image
                title.setSpacingAfter(20);
                document.add(title);

                PdfPTable table = new PdfPTable(6); // 6 colonnes pour les 6 attributs des produits
                table.setWidthPercentage(100);
                table.setSpacingBefore(30f);
                table.setSpacingAfter(30f);

                // Ajouter les en-têtes de colonnes
                Font hrFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
                BaseColor hrColor = new BaseColor(50, 89, 74); //
                hrFont.setColor(hrColor);

                PdfPCell cell1 = new PdfPCell(new Paragraph("ID", hrFont));
                BaseColor bgColor = new BaseColor(222, 254, 230);
                cell1.setBackgroundColor(bgColor);
                cell1.setBorderColor(titleColor);
                cell1.setPaddingTop(20);
                cell1.setPaddingBottom(20);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell2 = new PdfPCell(new Paragraph("FullName", hrFont));
                cell2.setBackgroundColor(bgColor);
                cell2.setBorderColor(titleColor);
                cell2.setPaddingTop(20);
                cell2.setPaddingBottom(20);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell3 = new PdfPCell(new Paragraph("Email", hrFont));
                cell3.setBackgroundColor(bgColor);
                cell3.setBorderColor(titleColor);
                cell3.setPaddingTop(20);
                cell3.setPaddingBottom(20);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell4 = new PdfPCell(new Paragraph("Address", hrFont));
                cell4.setBackgroundColor(bgColor);
                cell4.setBorderColor(titleColor);
                cell4.setPaddingTop(20);
                cell4.setPaddingBottom(20);
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell5 = new PdfPCell(new Paragraph("Phone", hrFont));
                cell5.setBackgroundColor(bgColor);
                cell5.setBorderColor(titleColor);
                cell5.setPaddingTop(20);
                cell5.setPaddingBottom(20);
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell6 = new PdfPCell(new Paragraph("City", hrFont));
                cell6.setBackgroundColor(bgColor);
                cell6.setBorderColor(titleColor);
                cell6.setPaddingTop(20);
                cell6.setPaddingBottom(20);
                cell6.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);

                Font hdFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
                BaseColor hdColor = new BaseColor(50, 89, 74); //
                hrFont.setColor(hdColor);
                // Ajouter les données des produits
                for (Achats achat : achatList) {
                    PdfPCell cellR1 = new PdfPCell(new Paragraph(String.valueOf(commande.g), hdFont));
                    cellR1.setBorderColor(titleColor);
                    cellR1.setPaddingTop(10);
                    cellR1.setPaddingBottom(10);
                    cellR1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR1);

                    PdfPCell cellR2 = new PdfPCell(new Paragraph(achat.getFull_name(), hdFont));
                    cellR2.setBorderColor(titleColor);
                    cellR2.setPaddingTop(10);
                    cellR2.setPaddingBottom(10);
                    cellR2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR2);

                    PdfPCell cellR3 = new PdfPCell(new Paragraph(achat.getEmail(), hdFont));
                    cellR3.setBorderColor(titleColor);
                    cellR3.setPaddingTop(10);
                    cellR3.setPaddingBottom(10);
                    cellR3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR3);

                    PdfPCell cellR4 = new PdfPCell(new Paragraph(achat.getAddress(), hdFont));
                    cellR4.setBorderColor(titleColor);
                    cellR4.setPaddingTop(10);
                    cellR4.setPaddingBottom(10);
                    cellR4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR4);

                    PdfPCell cellR5 = new PdfPCell(
                            new Paragraph(String.valueOf(achat.getTel()), hdFont));
                    cellR5.setBorderColor(titleColor);
                    cellR5.setPaddingTop(10);
                    cellR5.setPaddingBottom(10);
                    cellR5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR5);

                    PdfPCell cellR6 = new PdfPCell(
                            new Paragraph(achat.getCity(), hdFont));
                    cellR6.setBorderColor(titleColor);
                    cellR6.setPaddingTop(10);
                    cellR6.setPaddingBottom(10);
                    cellR6.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR6);
                }
                table.setSpacingBefore(20);
                document.add(table);
                document.close();

                System.out.println("Le fichier PDF a été généré avec succès.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/

    }

}
