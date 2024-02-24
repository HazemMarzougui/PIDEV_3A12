package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.UtilisateurServices;
import test.MainFx;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class uploadimageController implements Initializable {

    private String imagePath; // Declare imagePath as a class-level variable

    @FXML
    private ImageView imageView;

    @FXML
    private Button img;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String photoPath = MainFx.connecteduser.getPhoto();
        if (photoPath != null) {
            Image image = new Image(photoPath);
            imageView.setImage(image);
        } else {
            // Handle the case where the photoPath is null or invalid
            // For example, you can set a default image or display an error message
        }
    }

    @FXML
    void img(ActionEvent event) {
        // You need to set the imagePath variable with the path of the selected image
        // Here is an example of how you can do it:
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            // Set the imagePath variable with the path of the selected image
            imagePath = selectedFile.toURI().toString();
        }

        // Set the stored image path to the photo property of MainFx.connecteduser
        MainFx.connecteduser.setPhoto(imagePath);

        // Update the user in the database
        UtilisateurServices us = new UtilisateurServices();
        if (us.updateUser(MainFx.connecteduser, MainFx.connecteduser.getId())) {
            try {
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
            MainFx.m = 0;

        }
    }
}
