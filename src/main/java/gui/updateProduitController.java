package gui;

import entities.Categorie;
import entities.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import services.ServiceCategorie;
import services.ServiceProduit;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class updateProduitController implements Initializable {

    @FXML
    private Button btnClearProd;

    @FXML
    private Button btnImportProd;

    @FXML
    private Button btnUpdateProd;

    @FXML
    private ImageView imgProduitInput;

    @FXML
    private TextArea textUpdateDescriptionProd;

    @FXML
    private TextField textUpdateNomProd;

    @FXML
    private TextField textUpdatePrixProd;

    @FXML
    private TextField textUpdateQuantiteProd;

    @FXML
    private ComboBox<String> txtUpdateCategorieProd;

    @FXML
    private ComboBox<?> txtUpdateOffreProd;

    @FXML
    private AnchorPane updateProduitPane;

    private int id;
    ServiceCategorie cs = new ServiceCategorie();
    List<Categorie> categs = cs.Show();
    private int categId=-1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Map<String, Integer> valuesMap = new HashMap<>();
        for(Categorie c : categs){
            txtUpdateCategorieProd.getItems().add(c.getNom_categorie());
            valuesMap.put(c.getNom_categorie(),c.getId_categorie());
        }

        txtUpdateCategorieProd.setOnAction(event ->{
            String SelectedOption = null;
            SelectedOption = txtUpdateCategorieProd.getValue();
            int SelectedValue = 0;
            SelectedValue = valuesMap.get(SelectedOption);
            categId = SelectedValue;
        });


        ServiceProduit sp = new ServiceProduit();
        List<Produit> prods = sp.Show();

        for(int i=0;i<prods.size();i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("itemProduit.fxml"));

            try {
                AnchorPane anchorPane = fxmlLoader.load();
                HBox hBox = (HBox) anchorPane.getChildren().get(0);
                itemProduitController itemController = fxmlLoader.getController();
                textUpdateNomProd.setText(itemController.getData(prods.get(i)).getNom_produit());
                textUpdatePrixProd.setText(String.valueOf(itemController.getData(prods.get(i)).getPrix()));
                textUpdateQuantiteProd.setText(String.valueOf(itemController.getData(prods.get(i)).getQuantite()));
                textUpdateDescriptionProd.setText(itemController.getData(prods.get(i)).getDescription());
                this.id=itemController.getData(prods.get(i)).getId_produit();
            } catch (IOException ex) {
                Logger.getLogger(itemProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void UpdateProduit(ActionEvent event) {
        if (textUpdateNomProd.getText().isEmpty() || textUpdatePrixProd.getText().isEmpty()
                || textUpdateQuantiteProd.getText().isEmpty() || textUpdateDescriptionProd.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information manquante");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir tous les détails concernant votre produit.");
            Optional<ButtonType> option = alert.showAndWait();

        } else {
            modifProduit();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modifié avec succès");
            alert.setHeaderText(null);
            alert.setContentText("Votre produit a été modifié avec succès.");
            Optional<ButtonType> option = alert.showAndWait();
        }
    }

    private File selectedImageFile;
    private String imageName = null ;

    @FXML
    void ajouterImage(ActionEvent event) throws IOException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imgProduitInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imgProduitInput.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;

            Path destination = Paths.get(System.getProperty("user.dir"), "src","main","java", "uploads", imageName);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

        }
    }

    @FXML
    void clearFieldsProduit(ActionEvent event) {
        textUpdateNomProd.clear();
        textUpdatePrixProd.clear();
        textUpdateQuantiteProd.clear();
        textUpdateDescriptionProd.clear();
        imgProduitInput.setImage(null);
    }

    void modifProduit(){
        String nom = textUpdateNomProd.getText();
        int prix = Integer.parseInt(textUpdatePrixProd.getText());
        int quantite = Integer.parseInt(textUpdateQuantiteProd.getText());
        String description = textUpdateDescriptionProd.getText();
        String img = imageName;
        int categorie = categId;
        int offre = 1;


        Produit l = new Produit(
                id,
                nom, prix, quantite,description, img,categorie,offre);
        ServiceProduit sl = new ServiceProduit();
        sl.modifier(l);
    }
}
