package controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import entities.panier;

import java.sql.SQLException;
import java.util.List;
import services.commandeService;
import entities.commande;
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
