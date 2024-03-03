package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class cardcontroller implements Initializable {

        @FXML
        private HBox AddToCart;

        @FXML
        private Text productName;

        @FXML
        private Text produitprix;

        @FXML
        private Text produitquantite;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

   /* public void setProductData(produit produit) {
        // Instantiate the produitService

        productName.setText("Nom: "+produit.getNom());
        produitprix.setText("Prix " + produit.getPrix());
        produitquantite.setText("Quantite " + produit.getQuantite());



        // add product to cart btn
        AddToCart.setOnMouseClicked(event -> {
            System.out.println("product added to command : " + produit.getId_produit());




            MainFX.GlobalData.produits.add(produit.getId_produit());

            //HBox addedCartModel = (HBox) ((Node) event.getSource()).getScene().lookup("#addedCartModel");
            //addedCartModel.setVisible(true);



            /*else { // produit existe deja dans la commande => msg affiché
                Text addedCartModelText = (Text) ((Node) event.getSource()).getScene().lookup("#addedCartModelText");
                addedCartModelText.setText("Product is ALready Added To Cart");

                HBox addedCartModel = (HBox) ((Node) event.getSource()).getScene().lookup("#addedCartModel");
                addedCartModel.setVisible(true);

            }


        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }*/
}


