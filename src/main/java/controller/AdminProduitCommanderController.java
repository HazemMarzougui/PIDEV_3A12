package controller;

import entities.panier;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
public class AdminProduitCommanderController {

    @FXML
    private Text prixu_c;

    @FXML
    private Text qunatitec;


    public void setDatProduitt(panier panier){
        prixu_c.setText(""+panier.getPrix_u());
        qunatitec.setText(""+panier.getQuantite());


    }
}
