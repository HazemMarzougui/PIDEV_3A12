package Controllers;


import entities.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import services.Service_offre;

public class cardprod {

    private Produit produit;
    Service_offre so = new Service_offre();

    @FXML
    private Button id_add_offre;

    @FXML
    private ImageView id_image;

    @FXML
    private TextField id_description;

    @FXML
    private TextArea id_nom_produit;

    @FXML
    private TextField id_prix_remise_offre;

    @FXML
    private TextField id_quantite;

    @FXML
    private Button id_return;

    @FXML
    void return_to_admin(ActionEvent event) {

    }

    @FXML
    void to_offre(ActionEvent event) {

    }
    public void setData(Produit produit)
    {
        id_nom_produit.setText(produit.getNom_produit());
        id_description.setText(produit.getDescription());
        id_prix_remise_offre.setText(String.valueOf(produit.getPrix()));
        id_quantite.setText(String.valueOf(produit.getQuantite()));
        this.produit=produit;

    }
}
