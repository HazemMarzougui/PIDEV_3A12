package gui;

import entities.Categorie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import services.ServiceCategorie;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class updateCategorieController implements Initializable {

    @FXML
    private Button btnClearCateg;

    @FXML
    private Button btnUpdateCateg;

    @FXML
    private TextField textUpdateNomCateg;




    private int id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceCategorie cs = new ServiceCategorie();
        List<Categorie> categs = cs.Show();

        for(int i=0;i<categs.size();i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("itemCategorie.fxml"));

            try {
                AnchorPane anchorPane = fxmlLoader.load();
                HBox hBox = (HBox) anchorPane.getChildren().get(0);
                itemCategorieController itemController = fxmlLoader.getController();
                textUpdateNomCateg.setText(itemController.getData(categs.get(i)).getNom_categorie());
                this.id=itemController.getData(categs.get(i)).getId_categorie();
            } catch (IOException ex) {
                Logger.getLogger(itemCategorieController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void UpdateCategorie(ActionEvent event) {
        if (textUpdateNomCateg.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information manquante");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir tous les détails concernant votre categorie.");
            Optional<ButtonType> option = alert.showAndWait();

        } else {
            modifCategorie();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modifié avec succès");
            alert.setHeaderText(null);
            alert.setContentText("Votre categorie a été modifié avec succès.");
            Optional<ButtonType> option = alert.showAndWait();
        }
    }

    @FXML
    void clearFieldsCategorie(ActionEvent event) {
        textUpdateNomCateg.clear();
    }

    void modifCategorie(){
        String nom = textUpdateNomCateg.getText();

        Categorie c = new Categorie(
                id, nom);
        ServiceCategorie sc = new ServiceCategorie();
        sc.modifier(c);
    }
}
