package Controllers;


import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.Service_produit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class admin_offre {
    @FXML
    private Button id_add_offre;

    @FXML
    private Button id_search_button_offre;

    @FXML
    private TextField id_search_offre;

    @FXML
    private VBox id_vbox_offre;

    Service_produit sp = new Service_produit();
    @FXML
    void add_event_offre(ActionEvent event) {

    }

    @FXML
    void search_event_offre(ActionEvent event) {

    }

    @FXML
    void initialize() {
        try {
            ObservableList<Produit> observableList = FXCollections.observableList(sp.afficher());

            for (int i = 0; i < observableList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/admin/offre/cardprod.fxml"));
                VBox cardBox = fxmlLoader.load();
                cardprod cardProd = fxmlLoader.getController();

                cardProd.setData(observableList.get(i));
                id_vbox_offre.getChildren().add(cardBox);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}
