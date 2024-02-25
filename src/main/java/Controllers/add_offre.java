package Controllers;

import entities.Evenement;
import entities.Offre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import services.Service_offre;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class add_offre {

    @FXML
    private Button id_add_offre;

    @FXML
    private DatePicker id_date_deb_offre;

    @FXML
    private DatePicker id_date_fin_offre;

    @FXML
    private TextArea id_desc_offre;

    @FXML
    private TextField id_prix_remise_offre;
    Service_offre so = new Service_offre();

    @FXML
    void add_and_return_to_offre(ActionEvent event) {
    }

}