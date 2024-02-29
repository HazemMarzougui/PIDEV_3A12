package controller;

import entities.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.UtilisateurServices;
import test.MainFx;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import nl.captcha.Captcha;
import javafx.embed.swing.SwingFXUtils;


public class SinginController implements Initializable {

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

    public Captcha setCaptcha() {
        Captcha captchaV = new Captcha.Builder(250, 150)
                .addText()
                .addBackground()
                .addNoise()
                // .gimp()
                .addBorder()
                .build();

        System.out.println(captchaV.getImage());
        Image image = SwingFXUtils.toFXImage(captchaV.getImage(), null);

        cap.setImage(image);

        return captchaV;
    }
    Captcha captcha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        captcha =  setCaptcha();
    }

    @FXML
private void signin(ActionEvent event) throws SQLException, IOException, NullPointerException, RuntimeException {
  UtilisateurServices Us = new UtilisateurServices();
    Utilisateur p1 = new Utilisateur();
        if (captcha.isCorrect(code.getText())) {
            if (!(Us.login(emailField.getText(), passwordField.getText()) == null)) {
                MainFx.connecteduser = Us.login(emailField.getText(), passwordField.getText());
                if ("Admin".equals(MainFx.connecteduser.getRole())) {
                    try {
                        // Load the new FXML file
                        // Load the FXML file into a Parent object
                        // Get the primary stage
                        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

// Close the primary stage
                        primaryStage.close();

// Load the FXML file into a Parent object
                        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/testing.fxml")));

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
                } else {
                    if (MainFx.connecteduser.isIs_Actif() == true) {
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
                    } else {
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Echec de connexion");
                        alert.setHeaderText("Votre compte est bloqué , merci de contacter un Admin");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            alert.close();
                        }
                    }


                }

            } else {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Echec de connexion");
                alert.setHeaderText("E-mail ou mot de passe invalide");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    alert.close();
                }

            }
        }else {
            captcha =  setCaptcha();
            code.setText("");
        }

}
@FXML
private void forget1(ActionEvent event) throws IOException {

    MainFx.page=1;
    Parent root = FXMLLoader.load(getClass().getResource("/ForgetPassword.fxml"));
    // Get the current scene and set the new scene
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
@FXML
    private void gggg(ActionEvent event) throws IOException {

}
}




