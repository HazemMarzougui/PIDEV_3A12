package Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import entities.Evenement;

import services.Service_evenement;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class modify_event {


    @FXML
    private DatePicker id_date_deb;

    @FXML
    private DatePicker id_date_fin;

    @FXML
    private TextField id_desc;

    @FXML
    private TextField id_nom_modify;

    @FXML
    private Button id_update;

    private Evenement oldevent;


    @FXML
    void modify_and_return(ActionEvent event) {
        try {
            // Convert LocalDate objects to java.util.Date objects
            Date dateDebut = java.sql.Date.valueOf(id_date_deb.getValue());
            Date dateFin = java.sql.Date.valueOf(id_date_fin.getValue());

            // Create Evenement object with converted dates
            Evenement modifiedEvent = new Evenement(oldevent.getId_evenement(), id_nom_modify.getText(), dateDebut, dateFin, id_desc.getText());

            // Call the modifier method from your service class
            Service_evenement se = new Service_evenement();
            se.modifier(modifiedEvent);

            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Événement modifié avec succès");
            alert.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("/admin.fxml"));
            id_nom_modify.getScene().setRoot(root);
        } catch (SQLException e) {
            // Show error message if modification fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Erreur lors de la modification de l'événement : " + e.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData(Evenement event) {
        id_nom_modify.setText(event.getNom_event());
        id_desc.setText(event.getDescription());
        Date SQLdate = new Date(event.getDate_debut().getTime());
        id_date_deb.setValue(SQLdate.toLocalDate());

        Date SQLdate2 = new Date(event.getDate_fin().getTime());
        id_date_fin.setValue(SQLdate2.toLocalDate());
        oldevent = event;


    }


}