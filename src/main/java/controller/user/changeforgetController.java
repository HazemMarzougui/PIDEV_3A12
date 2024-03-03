package controller.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UtilisateurServices;
import test.MainFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class changeforgetController implements Initializable {

    @FXML
    private TextField codeField;
    @FXML
    private TextField codeField1;
    @FXML
    private Button change;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void code(ActionEvent event) {
                    UtilisateurServices us = new UtilisateurServices();
            if(us.updatePwd(codeField.getText(),MainFX.idd)){
                    try {
                        MainFX.page=0;
            // Load the new FXML file
            Parent root = FXMLLoader.load(getClass().getResource("/user/Login.fxml"));
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
