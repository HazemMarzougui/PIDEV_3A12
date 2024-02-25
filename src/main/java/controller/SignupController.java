
package controller;

import entities.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UtilisateurServices;
import test.MainFx;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    private ComboBox comb;
        @FXML
    private TextField nom;
        @FXML
    private TextField nom1;
        @FXML
    private TextField email;
        @FXML
    private TextField pwd;
        @FXML
    private Label Cnom;
        @FXML
    private Label Cnom1;
        @FXML
    private Label Cemail;
        @FXML
    private Label Cpassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void signup(ActionEvent event) throws SQLException {
         UtilisateurServices Us = new UtilisateurServices();
         Utilisateur p1 = new Utilisateur(nom.getText(),nom1.getText(),email.getText(),pwd.getText(),"Client",true);
         if(nom.getText().isEmpty() || email.getText().isEmpty() || pwd.getText().length() < 8 || !pwd.getText().matches(".*\\d.*") || pwd.getText().isEmpty()){
         if(nom.getText().isEmpty()){
             Cnom.setText("Champs nom vide");
         }
             else{
             Cnom.setText("");
         }
         if(nom1.getText().isEmpty()){
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

         if (pwd.getText().isEmpty()) {
             Cpassword.setText("Champs vide");
         }
         else if (pwd.getText().length() < 8) {
             Cpassword.setText("at least 8 characters long.");
         }
         else if (!pwd.getText().matches(".*\\d.*")) {
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
            Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
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
