package controller;

import entities.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.UtilisateurServices;
import test.MainFx;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Optional;



public class SinginController {


    @FXML
private TextField emailField;
    @FXML
private TextField passwordField;
        @FXML
    private ImageView cap;
        @FXML
    private TextField code;
    
        private VBox vbox;
    private Parent fxml;

@FXML
private void signin(ActionEvent event) throws SQLException, IOException, NullPointerException, RuntimeException {
  UtilisateurServices Us = new UtilisateurServices();
    Utilisateur p1 = new Utilisateur();
    if(!(Us.login(emailField.getText(), passwordField.getText()) == null)){
        MainFx.connecteduser = Us.login(emailField.getText(),passwordField.getText());
        if("Admin".equals(MainFx.connecteduser.getRole())){
                   try {
            // Load the new FXML file
            Parent root = FXMLLoader.load(getClass().getResource("/testing.fxml"));
            // Get the current scene and set the new scene
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        else{
            if(MainFx.connecteduser.isIs_Actif() == true){
                   try {
            // Load the new FXML file
                       // Load the FXML file into a Parent object
                       // Get the primary stage
                       Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

// Close the primary stage
                       primaryStage.close();

// Load the FXML file into a Parent object
                       Parent root = FXMLLoader.load(getClass().getResource("/front office.fxml"));

// Create a new Scene with the loaded Parent
                       Scene scene = new Scene(root);

// Create a new Stage
                       Stage stage = new Stage();

// Set the Scene to the Stage
                       stage.setScene(scene);

// Set the stage style to UNDECORATED
                       stage.initStyle(StageStyle.UNDECORATED);

// Center the stage on the screen
                       stage.centerOnScreen();

// Show the stage
                       stage.show();

                   } catch (IOException e) {
            e.printStackTrace();
        }
            }
            else
            {
                     Alert alert = new Alert(AlertType.CONFIRMATION);
       alert.setTitle("Echec de connexion");
        alert.setHeaderText("Votre compte est bloqué , merci de contacter un Admin");
        Optional<ButtonType> result = alert.showAndWait();  
          if (result.get() == ButtonType.OK) {
           alert.close();
        }
            }
        
        }

    }
    else
    {
     Alert alert = new Alert(AlertType.CONFIRMATION);
       alert.setTitle("Echec de connexion");
        alert.setHeaderText("E-mail ou mot de passe invalide");
        Optional<ButtonType> result = alert.showAndWait();  
          if (result.get() == ButtonType.OK) {
           alert.close();
        }

    }

}
@FXML
private void forget1(ActionEvent event) throws IOException {
    MainFx.page=1;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            // Get the current scene and set the new scene
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
}
@FXML
    private void gggg(ActionEvent event) throws IOException, URISyntaxException {
        // Desktop.getDesktop().browse(new URI("http://www.google.com"));
        // Parent root = FXMLLoader.load(getClass().getResource("GoogleAuth.fxml"));
        // Scene scene = new Scene(root);
        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // stage.setScene(scene);
        // stage.show();

        String myVariable = "undefined"; // replace with your variable value

        try {
            URL url = new URL("http://localhost:5000/my-variable"); // replace with your URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // set request method and headers
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // create JSON payload
            String payload = "{\"myVariable\": \"" + myVariable + "\"}";

            // send payload
            OutputStream os = connection.getOutputStream();
            os.write(payload.getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();

            // read response
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        Desktop.getDesktop().browse(new URI("http://localhost/zeroWateSignIn/"));
        Parent root = FXMLLoader.load(getClass().getResource("/GoogleAuth.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }


}

    
    

