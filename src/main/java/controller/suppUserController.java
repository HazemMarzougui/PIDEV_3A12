package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import test.MainFx;
import services.UtilisateurServices;
import services.PasswordHasher;

import java.io.IOException;

import static services.PasswordHasher.checkPassword;

public class suppUserController {

    @FXML
    private Label email1112;
    @FXML
    private TextField pwd;
    @FXML
    void supp(ActionEvent event) {
        UtilisateurServices us = new UtilisateurServices();
        String currentPassword = pwd.getText();

        if (checkPassword(currentPassword, MainFx.connecteduser.getPassword())) {
            us.deleteUser(MainFx.connecteduser.getId());
            try {
                // Load the new FXML file
                Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
                // Get the current scene and set the new scene
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                showError("Delete Successfully ! ");

            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }else {
            showError("Incorrect Password,Try again please .");
        }
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
