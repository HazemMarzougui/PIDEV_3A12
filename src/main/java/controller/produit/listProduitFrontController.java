package controller.produit;

import entities.Produit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.ServiceProduit;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;



public class listProduitFrontController implements Initializable {

    @FXML
    private Pagination pag;

    private MyListener myListener;


    @FXML
    private Pane content_area;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ServiceProduit cs = new ServiceProduit();
            List<Produit> prods = cs.Show();
            pag.setPageCount((int) Math.ceil(prods.size() / 3.0)); // Nombre total de pages nécessaire pour afficher toutes les cartes
            pag.setPageFactory(pageIndex -> {
                HBox hbox = new HBox();
                hbox.setSpacing(10);
                hbox.setAlignment(Pos.CENTER);
                int itemsPerPage = 3; // Nombre d'articles à afficher par page
                int page = pageIndex * itemsPerPage;
                for (int i = page; i < Math.min(page + itemsPerPage, prods.size()); i++) {

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/produit/listProduitFrontCard.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        anchorPane.getStyleClass().add("ct");
                        listProduitFrontCardController itemController = fxmlLoader.getController();
                        itemController.setData(prods.get(i), myListener);
                        hbox.getChildren().add(anchorPane);
                        HBox.setMargin(anchorPane, new Insets(10)); // Marges entre les cartes
                    } catch (IOException ex) {
                        Logger.getLogger(listProduitFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                return hbox;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}