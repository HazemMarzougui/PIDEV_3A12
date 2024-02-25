package gui;

import entities.Categorie;
import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.ServiceCategorie;
import services.ServiceProduit;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class listCategorieController implements Initializable {

    @FXML
    private AnchorPane listCategoriePane;

    @FXML
    private VBox vBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column=0;
        int row=0;
        try {
            ServiceCategorie cs = new ServiceCategorie();
            List<Categorie> categs = cs.Show();

            for(int i=0;i<categs.size();i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemCategorie.fxml"));

                try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    HBox hBox = (HBox) anchorPane.getChildren().get(0);
                    itemCategorieController itemController = fxmlLoader.getController();
                    itemController.setData(categs.get(i));
                    vBox.getChildren().add(hBox);

                } catch (IOException ex) {
                    Logger.getLogger(itemCategorieController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
