package Controllers;
import entities.Evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.Service_evenement;

import java.io.IOException;
import java.sql.SQLException;

public class cardtemplate {
    @FXML
    private Label id_date_deb;

    @FXML
    private Label id_date_fin;

    @FXML
    private Button id_event_name_backend_offre;

    @FXML
    private Label id_description;

    Service_evenement se = new Service_evenement();

    @FXML
    private Label id_event;

    @FXML
    private Label id_nom;

    private Evenement event;
    @FXML
    private Button id_delete;

    @FXML
    void go_to_backend_offre(ActionEvent event) {

        try{
            Parent root= loadRootLayoutt();
            id_event_name_backend_offre.getScene().setRoot(root);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private Parent loadRootLayoutt() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/offre/offre_admin.fxml"));
        admin_offre controller = new admin_offre();
        loader.setController(controller);
        System.out.println(event);
        controller.setData(event); // Add data to the controller
        Parent root = loader.load();

        return root;
    }

    @FXML
    void update_event(ActionEvent event) {
        try{
            Parent root= loadRootLayout();
            id_event.getScene().setRoot(root);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private Parent loadRootLayout() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/event/modify.fxml"));
        modify_event controller = new modify_event();
        loader.setController(controller);
        Parent root = loader.load();
        controller.setData(event); // Add data to the controller
        return root;

    }


    public void setData(Evenement event)
    {
        id_event.setText(""+event.getId_evenement());
        id_event_name_backend_offre.setText(""+event.getNom_event());
        id_description.setText(""+event.getDescription());
        id_date_deb.setText(""+event.getDate_debut());
        id_date_fin.setText(""+event.getDate_fin());
        this.event=event;

    }

    @FXML
    void delete_event(ActionEvent event) {

        try {
            se.supprimer(this.event);
            Parent root= FXMLLoader.load(getClass().getResource("/admin/event/admin_event.fxml"));
            id_nom.getScene().setRoot(root);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

    }



}
