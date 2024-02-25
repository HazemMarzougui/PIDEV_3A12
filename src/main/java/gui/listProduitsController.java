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
import javafx.util.converter.IntegerStringConverter;
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


public class listProduitsController implements Initializable {

    @FXML
    private AnchorPane listProduitPane;

    @FXML
    private VBox vBox;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int column=0;
        int row=0;
        try {
            ServiceProduit sp = new ServiceProduit();
            List<Produit> prods = sp.Show();

            for(int i=0;i<prods.size();i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("itemProduit.fxml"));

                try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    HBox hBox = (HBox) anchorPane.getChildren().get(0);
                    itemProduitController itemController = fxmlLoader.getController();
                    itemController.setData(prods.get(i));
                    vBox.getChildren().add(hBox);

                } catch (IOException ex) {
                    Logger.getLogger(itemProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
