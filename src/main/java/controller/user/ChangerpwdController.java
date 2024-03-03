package controller.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.UtilisateurServices;
import test.MainFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static services.PasswordHasher.checkPassword;

public class ChangerpwdController implements Initializable {

    @FXML
    private HBox itemC12;
    @FXML
    private TextField mdp;
    @FXML
    private HBox itemC121;
    @FXML
    private TextField mdp1;
    @FXML
    private HBox itemC1211;
    @FXML
    private TextField mdp2;
    @FXML
    private HBox itemC11111;
    @FXML
    private Label er1;

    @FXML
    private Label er2;

    @FXML
    private Label er3;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void modif(ActionEvent event) {

        String currentPassword = mdp.getText();
        String newPassword = mdp1.getText();
        String confirmPassword = mdp2.getText();

        // Clear error labels
        er1.setText("");
        er2.setText("");
        er3.setText("");

        // Check if current password is correct
        if (checkPassword(currentPassword, MainFX.connecteduser.getPassword())) {
            // Check if the new password and confirmation match
            if (!newPassword.equals(confirmPassword)) {
                // Display an error message or handle the mismatch appropriately
                er3.setText("Passwords do not match.");
                return;
            }

            // Check if the new password meets your criteria (you can customize isValidPassword method)
            if (!UtilisateurServices.isValidPassword(newPassword)) {
                // Display an error message or handle the invalid password appropriately
                er2.setText("Invalid password. Password must be at least 8 characters long and not empty.");
                return;
            }

            // Update the password
            UtilisateurServices us = new UtilisateurServices();
            if (us.updatePwd(newPassword, MainFX.connecteduser.getId())) {
                try {
                    MainFX.m = 0;
                    // Load the new FXML file
                    Parent root = FXMLLoader.load(getClass().getResource("/user/testing.fxml"));
                    // Get the current scene and set the new scene
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                }
            } else {
                // Password update failed
                // Display an error message or handle the failure appropriately
            }
        } else {
            // Current password is incorrect
            er1.setText("Current password is incorrect");
        }
    }

}
