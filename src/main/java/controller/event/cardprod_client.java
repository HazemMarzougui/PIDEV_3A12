package controller.event;



import entities.Evenement;
import entities.Offre;
import entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import services.Service_offre;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
public class cardprod_client {


    @FXML
    private TextField id_description;

    @FXML
    private ImageView id_image;

    @FXML
    private TextArea id_nom_produit;

    @FXML
    private TextField id_prix_remise_offre;

    @FXML
    private TextField id_quantite;

    private Offre crroffre;

    private Produit produit;

    private Evenement evenement;
    Service_offre so = new Service_offre();

    public void setData(Produit produit,Evenement evenement) throws SQLException {
        // Définition des données du produit dans l'interface utilisateur
        id_nom_produit.setText(produit.getNom_produit());

        id_quantite.setText(String.valueOf(produit.getQuantite()));
        this.produit = produit;

        // Récupération de la liste des offres pour le produit
        ObservableList<Offre> offresList = null;
        try {
            offresList = FXCollections.observableList(so.afficher());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Recherche de l'offre associée au produit
        Optional<Offre> offreAssociee = offresList.stream()
                .filter(offre -> offre.getId_produit_offre() == produit.getId_produit())
                .findFirst();

        // Vérification de la présence d'une offre
        if (offreAssociee.isPresent()) {
            // Calcul du prix après remise
            Offre offre = offreAssociee.get();
            double montantRemise = offre.getMontant_remise();
            double prixAvantRemise = produit.getPrix();
            double prixApresRemise = prixAvantRemise * (1 - (montantRemise / 100));

            // Affichage du prix après remise dans l'interface utilisateur
            id_prix_remise_offre.setText(String.valueOf(prixApresRemise));
            this.crroffre=offre;
            this.evenement=evenement;
            id_description.setText(crroffre.getDescription());
        } else {
            // Si aucune offre n'est trouvée, affichez simplement le prix d'origine
            id_prix_remise_offre.setText(String.valueOf(produit.getPrix()));
        }
    }
}
