package Controllers;


import entities.Evenement;
import entities.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.ClientEventMappingService;
import services.SMSService; // Your SMS service integration class
import services.UserService;

import java.io.IOException;
import java.sql.SQLException;


public class cardtemplateclient {



    @FXML
    private Button id_button_intrest;

    @FXML
    private Label id_date_deb;

    @FXML
    private Label id_date_fin;

    @FXML
    private Label id_description;

    @FXML
    private Label id_event;

    @FXML
    private Label id_nom;

    @FXML
    private Button id_event_name;

    @FXML
    void go_to_event(ActionEvent event) {
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/offre/offre_client.fxml"));
        client_offre controller = new client_offre();
        loader.setController(controller);
        System.out.println(event);
        controller.setData(event); // Add data to the controller
        Parent root = loader.load();

        return root;
    }

    @FXML
    void form(ActionEvent event) {


    }

    private Evenement event;

    public void setData(Evenement event)
    {
        id_event.setText(""+event.getId_evenement());
        id_event_name.setText(event.getNom_event());
        id_description.setText(""+event.getDescription());
        id_date_deb.setText(""+event.getDate_debut());
        id_date_fin.setText(""+event.getDate_fin());
        this.event=event;

    }

    private UserService userService = new UserService();

    private String composeMessage(Evenement event) {
        return "Bonjour, vous êtes inscrit à l'événement " + event.getNom_event() + " qui aura lieu du " +
                event.getDate_debut() + " au " + event.getDate_fin() + ". Description de l'événement : " +
                event.getDescription() + ". Nous avons hâte de vous y retrouver !";
    }


    @FXML
    void handleJoinButton(ActionEvent event) {
        // Handle button click
        if (this.event != null) {
            try {
                System.out.println(this.event.getClientId());
                // Fetch the user associated with this event (assuming each event has a client/user ID)
                user client = userService.getUserById(1);
                System.out.println(client);
                if (client != null) {
                    // Get the client's phone number
                    String clientPhoneNumber = String.valueOf(client.getNumber());

                    // Assuming you have a method to compose the SMS message based on the event
                    String message = composeMessage(this.event);

                    // Send SMS
                    boolean smsSent = SMSService.sendSMS("+21653658515", message);

                    if (smsSent) {
                        System.out.println("SMS sent successfully to: " + clientPhoneNumber);
                    } else {
                        System.out.println("Failed to send SMS to: " + clientPhoneNumber);
                    }
                } else {
                    System.out.println("Client not found for the event.");
                }
            } catch (SQLException e) {
                System.out.println("EXCEPTIO");
                e.printStackTrace();
                // Handle SQLException appropriately
            }
        }
    }


}
