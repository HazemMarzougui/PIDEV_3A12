package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import entities.produit;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class frontController {

    @FXML
    private HBox achatBtn;

    @FXML
    private ImageView achatIcon;

    @FXML
    private HBox collectBtn;

    @FXML
    private HBox commandsBtn;

    @FXML
    private ImageView commandsIcon;

    @FXML
    private HBox dashboardBtn;

    @FXML
    private ImageView dashboardIcon;

    @FXML
    private HBox fundrisingBtn;

    @FXML
    private HBox productsBtn;

    @FXML
    private ImageView productsIcon;

    @FXML
    private HBox profileBtn;

    @FXML
    private ImageView profileIcon;

    @FXML
    private HBox sideBarLogout;
    @FXML
    private Pane content_area;


    @FXML
    void logout(MouseEvent event) {

    }

    @FXML
    void open_achatList(MouseEvent event) {

    }



    @FXML
    void open_dashboard(MouseEvent event) {

    }

    @FXML
    void open_fundrisingList(MouseEvent event) {

    }

    @FXML
    void open_productsList(MouseEvent event) throws IOException {


        Parent fxml = FXMLLoader.load(getClass().getResource("/listeproduit.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }

    @FXML
    void open_commandsList(MouseEvent event) throws IOException {


        Parent fxml = FXMLLoader.load(getClass().getResource("/ListePanier.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }

    @FXML
    void open_profile(MouseEvent event) {

    }
}

   /* @FXML
    void initialize() {

        try {
            ObservableList<produit> observableList = FXCollections.observableList(se.afficher());

            for (int i = 0; i < observableList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/card.fxml"));
                HBox cardBox = fxmlLoader.load();
                card cardController = fxmlLoader.getController();

                cardController.setData(observableList.get(i));
                id_vbox.getChildren().add(cardBox);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}*/


   /* public void getData(produit produit) throws SQLException {

        services.produitService produitService=new produitService();
        ObservableList<produit> observableList = FXCollections.observableList(produitService.getAllProducts());
        productName.setText(produit.getNom());
        prix.setText(String.valueOf(produit.getPrix()));
        quantite.setText(String.valueOf(produit.getQuantite()));


    }*/














