package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class listeproduitcontroller {




    @FXML
    private GridPane gridpane;

    @FXML
    private GridPane productsListContainer;




    @FXML
    private void initialize() {
       // afficherProduitsDansGridPane();

    }

    /*private void afficherProduitsDansGridPane() {
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


    }*/

}
