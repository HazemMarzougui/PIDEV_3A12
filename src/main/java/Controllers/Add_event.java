package Controllers;
import entities.Evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.Service_evenement;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class Add_event {

    @FXML
    private Button add_button;

    @FXML
    private DatePicker id_date_deb;

    @FXML
    private DatePicker id_date_fin;

    @FXML
    private TextField id_desc;

    @FXML
    private TextField id_nom;
    Service_evenement se = new Service_evenement();
    @FXML
    void add_and_return(ActionEvent event) {


        try {
            // Convert LocalDate objects to java.util.Date objects
            Date dateDebut = java.sql.Date.valueOf(id_date_deb.getValue());
            Date dateFin = java.sql.Date.valueOf(id_date_fin.getValue());

            // Create Evenement object with converted dates
            se.ajouter(new Evenement(id_nom.getText(), dateDebut, dateFin, id_desc.getText()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Événement ajouté avec succès");
            alert.showAndWait();
            Parent root= FXMLLoader.load(getClass().getResource("/admin/event/admin_event.fxml"));
            id_nom.getScene().setRoot(root);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Erreur lors de l'ajout de l'événement : " + e.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


 }
