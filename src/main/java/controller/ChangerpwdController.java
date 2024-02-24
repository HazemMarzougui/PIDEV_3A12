package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.UtilisateurServices;
import test.MainFx;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modif(ActionEvent event) {
        if( checkPassword(mdp.getText(), MainFx.connecteduser.getPassword())){
            UtilisateurServices us = new UtilisateurServices();
            if(us.updatePwd(mdp1.getText(),MainFx.connecteduser.getId() )){
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
    
}
