package Controllers;

import entities.Evenement;
import entities.Offre;
import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import services.Service_offre;
import services.Service_produit;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class client_offre {

    @FXML
    private Button id_search_button_offre;

    @FXML
    private TextField id_search_offre;

    @FXML
    private VBox id_vbox_offre;
    private Evenement current_event;
    Service_offre so = new Service_offre();
    Service_produit sp = new Service_produit();
    @FXML
    void search_offre(ActionEvent event) {

    }

    public void setData(Evenement event)
    {
        this.current_event=event;

    }


    @FXML
    void initialize() {
        try {
            ObservableList<Offre> observableList = FXCollections.observableList(so.afficher());
            // Filter the list based on the search text
            List<Offre> filteredList = observableList.stream()
                    .filter(e -> e.getId_evenement_offre() == current_event.getId_evenement())
                    .collect(Collectors.toList());

            for (int i = 0; i < filteredList.size(); i++) {
                int prodId = filteredList.get(i).getId_produit_offre();

                /////prod
                ObservableList<Produit> prodList = FXCollections.observableList(sp.afficher());
                // Filter the list based on the search text
                List<Produit> filteredprodList = prodList.stream()
                        .filter(e -> e.getId_produit() == prodId)
                        .collect(Collectors.toList());


                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/client/offre/cardprod_client.fxml"));
                VBox cardBox = fxmlLoader.load();
                cardprod_client cardProd = fxmlLoader.getController();

                cardProd.setData(filteredprodList.get(0),current_event);
                id_vbox_offre.getChildren().add(cardBox);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
