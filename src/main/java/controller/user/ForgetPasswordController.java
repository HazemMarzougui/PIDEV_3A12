
package controller.user;

import entities.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UtilisateurServices;
import test.MainFX;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;


public class ForgetPasswordController implements Initializable {

@FXML
    private TextField emailField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void sendMail(ActionEvent event) throws IOException, SQLException {
        // Set the SMTP host and port for sending the email
    String host = "smtp.gmail.com";
    String port = "587";
    String username = "businessfadi7@gmail.com";
    String password = "rtqq dbok ltrw pspy";

        // Set the properties for the email session
    Properties properties = new Properties();
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.auth", "true"); // Enable authentication
    properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS encryption
    
    // Create a new email session using the specified properties
    Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
        @Override
        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
          return new javax.mail.PasswordAuthentication(username, password);
        }
    });
    
    try {
        // Create a new email message
        Message msg = new MimeMessage(session);

        // Set the "From" address for the email
       // msg.setFrom(new InternetAddress("ahmed.benabid2503@gmail.com"));
        
        // Add the "To" address for the email (including the recipient's name)
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailField.getText()));

        // Set the subject and body text for the email
                Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        msg.setSubject("Mot de passe oublié");
        msg.setText("Mr/Mme , voila votre code pour modifier votre mot de passe : "+ Integer.toString(randomNumber));

                // Create an alert to notify the user that the email was sent successfully
      String em =emailField.getText();
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setTitle("Confirmation d'envoie");
        alert.setHeaderText("Voulez-vous envoyez ce mail à "+em+" ?");
        alert.setContentText("Cette action est requise.");

        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();  

        // Send the email
         
          if (result.get() == ButtonType.OK) {
              System.out.println("En cours d'envoie...");
           Transport.send(msg);
                   System.out.println("Envoyé avec succès !");
                           UtilisateurServices Us = new UtilisateurServices();
        Utilisateur p1 = new Utilisateur();
        System.out.println(Us.updateToken(emailField.getText(),Integer.toString(randomNumber)));
              MainFX.page=2;
        Parent root = FXMLLoader.load(getClass().getResource("/user/CodePass.fxml"));
            // Get the current scene and set the new scene
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            // Close the dialog and do nothing
            alert.close();
            System.out.println("Echec d'envoie!");
        }

      
        // Print a message to the console to indicate that the email was sent successfully

        
      
        
       
    } catch (AddressException e) {
        // Create an alert to notify the user that there was an error with the email address
        e.printStackTrace();
    System.out.println("Failed to send email: " + e.getMessage());
    } catch (MessagingException e) {
        e.printStackTrace();
    System.out.println("Failed to send email: " + e.getMessage());
    }
    }
    
}
