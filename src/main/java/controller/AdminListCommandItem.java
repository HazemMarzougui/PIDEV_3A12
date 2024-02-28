package controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import entities.commande;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.commandeService;
import services.panierService;
import test.MainFX;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class AdminListCommandItem implements Initializable {
    @FXML
    private HBox qrCodeCommand;

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
    private HBox deleteCommand;

    @FXML
    private Label email;

    @FXML
    private Label idcommande;

    @FXML
    private Label nom;


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

    @FXML
    private Pane content_areaa;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


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
            System.out.println("ID de la commande est affichée : " + commande.getId_commande());

            try {
                MainFX.GlobalData.PanierProCom = p.getAllProductsForCommand(commande.getId_commande());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Afficher le contenu de la liste et afficher la nouvelle liste
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminListCommand.fxml"));
            try {
                Parent root = loader.load();

                // Accéder à la pane content_area depuis ce controller
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de AdminListCommand.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        qrCodeCommand.setOnMouseClicked(event -> {
            System.out.println("ID du achat à générer qr Code : " + commande.getId_commande());
           // Achats.setAchatId(achat.getId());

            String text =  " commande id: " + commande.getId_commande()
                    + "\nPrenom: " +commande.getPrenom() + "\n Nom: "
                        +commande.getNom()+ "\nAdresse: " +commande.getAdresse()
                    + "\nTéléphone: " + commande.getTelephone() + "\nEmail: " +commande.getEmail();
            // Créer un objet QRCodeWriter pour générer le QR code
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            // Générer la matrice de bits du QR code à partir du texte saisi
            BitMatrix bitMatrix;
            try {
                bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
                // Convertir la matrice de bits en image BufferedImage
                BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
                // Enregistrer l'image en format PNG
                // File outputFile = new File("qrcode.png");
                // ImageIO.write(bufferedImage, "png", outputFile);
                // Afficher l'image dans l'interface utilisateur

                ImageView qrCodeImg = (ImageView) ((Node) event.getSource()).getScene().lookup("#qrCodeImg");


                qrCodeImg.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

                HBox qrCodeImgModel = (HBox) ((Node) event.getSource()).getScene().lookup("#qrCodeImgModel");
                qrCodeImgModel.setVisible(true);
            } catch (WriterException e) {
                e.printStackTrace();
            }

        });

        // END showCommand btn click
        deleteCommand.setOnMouseClicked(event -> {

            System.out.println("ID du commande est affichée : " + commande.getId_commande());
            commandeService commandeService =new commandeService();
            panierService panierService=new panierService();

            commandeService.SupprimerProduitCommande(commande.getId_commande());
            panierService.AdminSupprimerProduitCommande(commande.getId_commande());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminListCommand.fxml"));
            try {
                Parent root = loader.load();

                // Accéder à la pane content_area depuis ce controller
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);


        } catch (IOException e) {
                throw new RuntimeException(e);}
            });

    }


}
