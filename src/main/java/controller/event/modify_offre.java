package controller.event;
import entities.Evenement;
import entities.Offre;
import entities.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import services.Service_offre;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class modify_offre {

    @FXML
    private DatePicker id_date_deb_offre;

    @FXML
    private DatePicker id_date_fin_offre;

    @FXML
    private Button id_delete_offre;

    @FXML
    private TextArea id_desc_offre;

    @FXML
    private TextField id_prix_remise_offre;

    @FXML
    private Button id_save_offre;

    @FXML
    private Label id_nom_prod;

    @FXML
    void delete_and_return_to_prod(ActionEvent event) {

    }
    Service_offre so =new Service_offre();
    @FXML
    void save_and_return_to_prod(ActionEvent event) throws SQLException {
//  Offre(int id_offre, int montant_remise, Date date_debut, java.util.Date date_fin, String description, int id_evenement_offre, int id_produit_offre)
    Offre new_offer =new Offre(offre.getId_offre(),Integer.parseInt(id_prix_remise_offre.getText()),offre.getDate_debut(),offre.getDate_fin(),id_desc_offre.getText(),offre.getId_evenement_offre(),offre.getId_produit_offre());
        try {
            so.modifier(new_offer);
            Parent root=FXMLLoader.load(getClass().getResource("/client/event/client.fxml"));
            id_nom_prod.getScene().setRoot(root);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Parent loadRootLayout() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/offre/offre_admin.fxml"));
        admin_offre controller = new admin_offre();
        loader.setController(controller);
        controller.setData(ev); // Add data to the controller

        Parent root = loader.load();
        return root;
    }
    private Offre offre;
    private Evenement ev;
    private  Produit produit;
    public void setData(Offre offre,Produit p) {

        id_desc_offre.setText(offre.getDescription());
        Date SQLdate = new Date(offre.getDate_debut().getTime());
        id_date_deb_offre.setValue(SQLdate.toLocalDate());
        Date SQLdate2 = new Date(offre.getDate_fin().getTime());
        id_date_fin_offre.setValue(SQLdate2.toLocalDate());
        id_prix_remise_offre.setText(String.valueOf(offre.getMontant_remise()));
        id_nom_prod.setText(p.getNom_produit());
        this.offre=offre;
        this.produit=p;
    }

}
