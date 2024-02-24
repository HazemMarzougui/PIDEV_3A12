package Controller;

import Services.ConseilService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Entities.Conseil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

public class AddConseilController {

    ConseilService conseilService = new ConseilService();

    @FXML
    private TextField tf_description;

    @FXML
    private TextField tf_idproduit;

    @FXML
    private TextField tf_nomConseil;

    @FXML
    private TextField tf_typeC;

    @FXML
    private TextField tf_video;

    @FXML
    private Label upload_btn;

    @FXML
    void upload_video(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mkv")); // Add more supported video formats if needed
        File selectedFile = fileChooser.showOpenDialog(new Stage());


        if (selectedFile != null) {
            tf_video.setText(selectedFile.getPath());
            String destinationFolder = "C:\\Users\\hazem\\IdeaProjects\\Conseils\\src\\main\\resources\\uploaded_video"; // Set the correct path
            File destinationFile = new File(destinationFolder, selectedFile.getName());
            Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } else {
            // Handle the case where no file was selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("No video file selected");
            alert.showAndWait();
        }
    }

    @FXML
    void Ajouter_conseil(ActionEvent event)
    {
        try {
            String videoPath = tf_video.getText();

            if (videoPath.isEmpty()) {
                // Handle the case where no video file was selected
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Please select a video file");
                alert.showAndWait();
                return;
            }
            // You may want to perform additional validation on other fields
            // Add the Conseil with the video file path
            conseilService.ajouterConseil(new Conseil(
                    tf_nomConseil.getText(),
                    videoPath, // Use the path of the selected video file
                    tf_description.getText(),
                    Integer.parseInt(tf_idproduit.getText()),
                    Integer.parseInt(tf_typeC.getText())
            ));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success !");
            alert.setContentText("Ajout Effectué");
            alert.showAndWait();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error !");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void Annuler_conseil(ActionEvent event) {
        Node source = (Node) event.getSource();
        // Get the Stage to which the source node belongs
        Stage stage = (Stage) source.getScene().getWindow();
        // Close the stage
        stage.close();
    }



}
