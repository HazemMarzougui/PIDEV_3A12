package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import entities.produit;
import javafx.scene.layout.VBox;
import services.produitService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class listeproduitcontroller {


    produitService ps = new produitService();

    @FXML
    private GridPane gridpane;

    @FXML
    private GridPane productsListContainer;




    @FXML
    private void initialize() {
        afficherProduitsDansGridPane();

    }

    private void afficherProduitsDansGridPane() {
        int column = 0;
        int row = 1;
        try {
            List<produit> produits = ps.getAllProducts();
            for (int i = 0; i < produits.size(); i++) {

                cardcontroller productCardController = new cardcontroller();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/card.fxml"));
                fxmlLoader.setController(productCardController);
                VBox productCard = fxmlLoader.load();
                productCardController.setProductData(produits.get(i));
                if (column == 3) {
                    column = 0;
                    ++row;
                }
                productsListContainer.add(productCard,column++,row);
                GridPane.setMargin(productCard, new Insets(0, 20, 20, 10));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Gérer l'exception correctement
        }


    }

}
