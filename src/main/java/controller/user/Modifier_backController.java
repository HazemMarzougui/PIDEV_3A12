
package controller.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.UtilisateurServices;
import test.MainFX;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class Modifier_backController implements Initializable {

    @FXML
    private HBox itemC12;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private HBox itemC121;
    @FXML
    private TextField email;
    @FXML
    private TextField tel;
    @FXML
    private HBox itemC1211;
    @FXML
    private TextField adresse;
    @FXML
    private DatePicker date;
    @FXML
    private HBox itemC11111;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     nom.setText(MainFX.connecteduser.getNom());
     prenom.setText(MainFX.connecteduser.getPrenom());
     email.setText(MainFX.connecteduser.getEmail());
     adresse.setText(MainFX.connecteduser.getAddresse());
     tel.setText(Integer.toString(MainFX.connecteduser.getTel()));

        Date userBirthDate = MainFX.connecteduser.getDate_naiss();
        if (userBirthDate != null) {
            // Assuming date is a DatePicker component
            date.setValue(userBirthDate.toLocalDate());
        }
    }    

    @FXML
    private void modif(ActionEvent event) {
        MainFX.connecteduser.setNom(nom.getText());
        MainFX.connecteduser.setPrenom(prenom.getText());
        MainFX.connecteduser.setEmail(email.getText());
        MainFX.connecteduser.setAddresse(adresse.getText());
        MainFX.connecteduser.setTel(Integer.parseInt(tel.getText()));
        LocalDate localDate = date.getValue();
        Date sqlDate = Date.valueOf(localDate);
        MainFX.connecteduser.setDate_naiss(sqlDate);
        UtilisateurServices us = new UtilisateurServices();
        if(us.updateUser(MainFX.connecteduser,MainFX.connecteduser.getId()))
        {
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
        }
    }
    
}
