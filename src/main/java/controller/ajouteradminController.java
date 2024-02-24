package controller;

import entities.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.UtilisateurServices;
import test.MainFx;

import java.io.IOException;
import java.sql.SQLException;

public class ajouteradminController {

    @FXML
    private Label Cemail;

    @FXML
    private Label Cnom;

    @FXML
    private Label Cnom1;

    @FXML
    private Label Cpassword;

    @FXML
    private TextField adresse;

    @FXML
    private DatePicker date;

    @FXML
    private TextField email;

    @FXML
    private HBox itemC11111;

    @FXML
    private HBox itemC12;

    @FXML
    private HBox itemC121;

    @FXML
    private HBox itemC1211;

    @FXML
    private TextField mdp;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField tel;

    @FXML
    void ajouteadmin(ActionEvent event) throws SQLException {

        UtilisateurServices Us = new UtilisateurServices();
        Utilisateur p1 = new Utilisateur(nom.getText(),prenom.getText(),email.getText(),mdp.getText(),"Admin",true);
        if(nom.getText().isEmpty() || email.getText().isEmpty() || mdp.getText().length() < 8 || !mdp.getText().matches(".*\\d.*") || mdp.getText().isEmpty()){
            if(nom.getText().isEmpty()){
                Cnom.setText("Champs nom vide");
            }
            else{
                Cnom.setText("");
            }
            if(prenom.getText().isEmpty()){
                Cnom1.setText("Champs prenom vide");
            }
            else{
                Cnom1.setText("");
            }
            if(email.getText().isEmpty()){
                Cemail.setText("Champs email vide");
            }
            else if(!email.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
                Cemail.setText("Email pas valide");
            }
            else{
                Cemail.setText("");
            }

            if (mdp.getText().isEmpty()) {
                Cpassword.setText("Champs vide");
            }
            else if (mdp.getText().length() < 8) {
                Cpassword.setText("at least 8 characters long.");
            }
            else if (!mdp.getText().matches(".*\\d.*")) {
                Cpassword.setText(" at least one digit.");
            }
            else{
                Cpassword.setText("");
            }
        }
        else {
            if(Us.isEmailUnique(p1.getEmail())){
                Us.signUp(p1);
                try {
                    MainFx.page=0;

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


}
