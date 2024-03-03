package controller.event;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import com.mysql.cj.Session;
import entities.Evenement;
import entities.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.SMSService;
import services.UserService;

import java.util.Properties;

import java.io.IOException;
//import java.net.PasswordAuthentication;
import java.sql.SQLException;
import javax.mail.PasswordAuthentication;
import javax.mail.PasswordAuthentication;

public class cardtemplateclient {
   // ServiceEmail.EmailService emailService = new ServiceEmail.EmailService("medamine.bacha@esprit.tn", "Airplaness666");
   //private final ServiceEmail serviceEmail = new ServiceEmail();
   // private final ServiceEmail.EmailService serviceEmail = new ServiceEmail.EmailService();


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
        if (this.event != null) {
            //sendSMS();
            String recipientName = "BECHA"; // Replace with actual recipient name
            String recipientEmail = "medamine.bacha@esprit.tn"; // Replace with actual recipient email
            String formationTitle = "Formation Title"; // Replace with actual formation title
            sendEmail(recipientName, recipientEmail, formationTitle);
        }
    }



    @FXML
    private void sendEmail(String recipientName, String recipientEmail, String formationTitle) {


        /*String recipientName = "Recipient Name"; // You can set these values dynamically based on your application's logic
        String recipientEmail = "recipient@example.com"; // Example email
        String formationTitle = "Formation Title"; // Example formation title*/

        String host = "smtp.gmail.com";
        String username = "medamine.bacha@esprit.tn"; // Your Gmail email address
        String password = "Airplaness666"; // Your Gmail password

        //String recipientName = "Recipient Name"; // You can set this dynamically based on your application's logic
        String subject = "Welcome to " + event.getNom_event();
        String body = "Dear " + recipientName + ",\n\n" +
                "Welcome to " + event.getNom_event() + ". Thank you for your registration. Now you are all set to join the event!";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587"); // Update the port as needed

        // Send the email using JavaMail API
        try {
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            showAlert("Success", "Email sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to send email");
        }
    }




        private void showAlert(String title, String content) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }




   /* private void sendEmail() {
        try {
            // Fetch client details
            user client = userService.getUserById(1);
            if (client != null) {
                String clientEmail = client.getEmail();
                String message = composeMessage(event);

                // Create an instance of EmailService with your email credentials
                EmailService emailService = new EmailService("your_email@example.com", "your_password");
                boolean emailSent = emailService.sendEmail(clientEmail, "Reminder: Event Tomorrow", message);

                if (emailSent) {
                    System.out.println("Email sent successfully to: " + clientEmail);
                } else {
                    System.out.println("Failed to send email to: " + clientEmail);
                }
            } else {
                System.out.println("Client not found for the event.");
            }
        } catch (SQLException | MessagingException e) {
            e.printStackTrace();
        }
    }*/


 /* @FXML
    void sendSMS() {
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
*/

}
