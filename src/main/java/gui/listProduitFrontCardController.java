package gui;

import entities.Categorie;
import entities.Produit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.ServiceCategorie;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class listProduitFrontCardController implements Initializable {

    @FXML
    private Label labelCategorieProduit;

    @FXML
    private Label labelDescriptionProduit;

    @FXML
    private Label labelNomProduit;

    @FXML
    private Label labelPrixProduit;

    @FXML
    private Label labelQteProduit;

    @FXML
    private ImageView imgProd;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    MyListener myListener;
    Produit prods;
    ServiceCategorie sc = new ServiceCategorie();
    public void setData (Produit prod, MyListener myListener){
        this.prods = prod ;
        this.myListener = myListener;

        try {
            File file = new File("src/main/java/uploads/" + prod.getImage());
            String localUrl = file.toURI().toURL().toString();
            Image image = new Image(localUrl);
            imgProd.setImage(image);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        labelNomProduit.setText(prod.getNom_produit());
        labelPrixProduit.setText(String.valueOf(prod.getPrix())+" DT  |  ");
        labelQteProduit.setText(String.valueOf(prod.getQuantite()));
        labelDescriptionProduit.setText(prod.getDescription());
        labelCategorieProduit.setText(sc.getById(prod.getId_categorie()).getNom_categorie());

    }
}
