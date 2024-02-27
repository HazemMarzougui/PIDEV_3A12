package Controllers;


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

public class cardprod {

    private Produit produit;

    Service_offre so = new Service_offre();

    @FXML
    private Button id_delete_offre;

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

    @FXML
    private Button id_update_offre;


    private Evenement evenement;

    @FXML
    void delete_and_return_to_prod(ActionEvent event) throws SQLException {
        try {
            System.out.println(crroffre);
            so.supprimer(crroffre);
            Parent root=FXMLLoader.load(getClass().getResource("/client/event/client.fxml"));
            id_nom_produit.getScene().setRoot(root);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void update_and_return_to_prod(ActionEvent event) {


        try{
            Parent root= loadRootLayout();
            id_nom_produit.getScene().setRoot(root);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private Offre crroffre;

    private Parent loadRootLayout() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/offre/modify_offre.fxml"));
        modify_offre controller = new modify_offre();
        loader.setController(controller);
        Parent root = loader.load();
        controller.setData(crroffre,produit); // Add data to the controller
        return root;
    }





   /* public void setData(Produit produit) throws SQLException {
        id_nom_produit.setText(produit.getNom_produit());
        id_description.setText(produit.getDescription());

        id_quantite.setText(String.valueOf(produit.getQuantite()));
        this.produit=produit;

        ObservableList<Offre> offresList = null;
        try {
            offresList = FXCollections.observableList(so.afficher());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Filter the list based on the search text
        List<Offre> filteredprodList = offresList.stream()
                .filter(e -> e.getId_produit_offre() == produit.getId_produit() )
                .collect(Collectors.toList());

        System.out.println(filteredprodList);
        id_prix_remise_offre.setText(String.valueOf(produit.getPrix() / (1 - (filteredprodList.get(0).getMontant_remise() / 100))));

    }*/

  /*  public void setData(Produit produit) throws SQLException {
        // Définition des données du produit dans l'interface utilisateur
        id_nom_produit.setText(produit.getNom_produit());
        id_description.setText(produit.getDescription());
        id_quantite.setText(String.valueOf(produit.getQuantite()));
        this.produit = produit;

        // Récupération de la liste des offres pour le produit
        ObservableList<Offre> offresList = null;
        try {
            offresList = FXCollections.observableList(so.afficher());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Filtrage de la liste des offres pour le produit spécifique
        List<Offre> filteredprodList = offresList.stream()
                .filter(e -> e.getId_produit_offre() == produit.getId_produit())
                .collect(Collectors.toList());

        // Vérification de la présence d'offres
        if (!filteredprodList.isEmpty()) {
            // Calcul du prix avant remise
            double prixApresRemise = produit.getPrix(); // Obtenez le prix après remise
            double montantRemise = filteredprodList.get(0).getMontant_remise(); // Obtenez le montant de la remise
            double prixAvantRemise = prixApresRemise / (1 - (montantRemise / 100)); // Calcul du prix avant remise

            // Affichage du prix avant remise dans l'interface utilisateur
            id_prix_remise_offre.setText(String.valueOf(prixAvantRemise));
        } else {
            // Si aucune offre n'est trouvée, affichez simplement le prix d'origine
            id_prix_remise_offre.setText(String.valueOf(produit.getPrix()));
        }
    }
*/

  /*  public void setData(Produit produit) throws SQLException {
        // Définition des données du produit dans l'interface utilisateur
        id_nom_produit.setText(produit.getNom_produit());
        id_description.setText(produit.getDescription());
        id_quantite.setText(String.valueOf(produit.getQuantite()));
        this.produit = produit;

        // Récupération de la liste des offres pour le produit
        ObservableList<Offre> offresList = null;
        try {
            offresList = FXCollections.observableList(so.afficher());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Filtrage de la liste des offres pour le produit spécifique
        List<Offre> filteredprodList = offresList.stream()
                .filter(e -> e.getId_produit_offre() == produit.getId_produit())
                .collect(Collectors.toList());

        // Vérification de la présence d'offres
        if (!filteredprodList.isEmpty()) {
            // Calcul du prix après remise
            double prixAvantRemise = produit.getPrix(); // Obtenez le prix avant remise
            double montantRemise = filteredprodList.get(0).getMontant_remise(); // Obtenez le montant de la remise
            double prixApresRemise = prixAvantRemise * (1 - (montantRemise / 100)); // Calcul du prix après remise

            // Affichage du prix après remise dans l'interface utilisateur
            id_prix_remise_offre.setText(String.valueOf(prixApresRemise));
        } else {
            // Si aucune offre n'est trouvée, affichez simplement le prix d'origine
            id_prix_remise_offre.setText(String.valueOf(produit.getPrix()));
        }
    }
*/

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
