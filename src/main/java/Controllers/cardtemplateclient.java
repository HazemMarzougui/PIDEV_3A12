package Controllers;


import entities.Evenement;
import entities.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.ClientEventMappingService;
import services.SMSService; // Your SMS service integration class
import services.UserService;

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
    void form(ActionEvent event) {

    }

    private Evenement event;

    public void setData(Evenement event)
    {
        id_event.setText(""+event.getId_evenement());
        id_nom.setText(event.getNom_event());
        id_description.setText(""+event.getDescription());
        id_date_deb.setText(""+event.getDate_debut());
        id_date_fin.setText(""+event.getDate_fin());
        this.event=event;

    }

    private UserService userService = new UserService();

    private String composeMessage(Evenement event) {
        // You need to implement this method according to your requirements
        return "Your message content based on the event";
    }

    @FXML
    void handleJoinButton(ActionEvent event) {
        // Handle button click
        if (this.event != null) {
            try {
                System.out.println(this.event.getClientId());
                // Fetch the user associated with this event (assuming each event has a client/user ID)
                user client = userService.getUserById(this.event.getClientId()); // Assuming there's a method to get user by ID
                System.out.println(client);
                if (client != null) {
                    // Get the client's phone number
                    String clientPhoneNumber = String.valueOf(client.getNumber());

                    // Assuming you have a method to compose the SMS message based on the event
                    String message = composeMessage(this.event);

                    // Send SMS
                    /*boolean smsSent = SMSService.sendSMS("+21653658515", message);

                    if (smsSent) {
                        System.out.println("SMS sent successfully to: " + clientPhoneNumber);
                    } else {
                        System.out.println("Failed to send SMS to: " + clientPhoneNumber);
                    }*/
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
