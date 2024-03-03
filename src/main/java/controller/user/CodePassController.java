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


public class CodePassController implements Initializable {

    @FXML
    private TextField codeField;
    @FXML
    private Button code;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void code(ActionEvent event) throws IOException {
        UtilisateurServices Us = new UtilisateurServices();
        int id = Us.isTokenExist(codeField.getText()) ;
        if ( id != 0)
        {
            MainFX.page=3;
            MainFX.idd = id;
        Parent root = FXMLLoader.load(getClass().getResource("/user/Changeforget.fxml"));
            // Get the current scene and set the new scene
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else{
            System.out.println("leee");
        }
    }
    
}
