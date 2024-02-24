
package controller;

import services.UtilisateurServices;
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
import test.MainFx;

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
     nom.setText(MainFx.connecteduser.getNom());
     prenom.setText(MainFx.connecteduser.getPrenom());
     email.setText(MainFx.connecteduser.getEmail());
     adresse.setText(MainFx.connecteduser.getAddresse());
     tel.setText(Integer.toString(MainFx.connecteduser.getTel()));
    }    

    @FXML
    private void modif(ActionEvent event) {
        MainFx.connecteduser.setNom(nom.getText());
        MainFx.connecteduser.setPrenom(prenom.getText());
        MainFx.connecteduser.setEmail(email.getText());
        MainFx.connecteduser.setAddresse(adresse.getText());
        MainFx.connecteduser.setTel(Integer.parseInt(tel.getText()));
        LocalDate localDate = date.getValue();
        Date sqlDate = Date.valueOf(localDate);
        MainFx.connecteduser.setDate_naiss(sqlDate);
        UtilisateurServices us = new UtilisateurServices();
        if(us.updateUser(MainFx.connecteduser,MainFx.connecteduser.getId()))
        {
                    try {
                        MainFx.m = 0;
            // Load the new FXML file
            Parent root = FXMLLoader.load(getClass().getResource("/testing.fxml"));
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
