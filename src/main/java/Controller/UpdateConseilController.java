package Controller;

import Entities.Conseil;
import Services.ConseilService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;

public class UpdateConseilController {
    ConseilService conseilService = new ConseilService();

    @FXML
    private Button btn_modifier;

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





    public TextField getTf_video() {
        return tf_video;
    }

    private ConseilController conseilController;

    int id = 0;

    Stage stage;

    private Conseil selectedConseil;

    public void setConseil(Conseil conseil) {
        this.selectedConseil = conseil;
        tf_nomConseil.setText(conseil.getNom_conseil());
        tf_video.setText(conseil.getVideo());
        tf_description.setText(conseil.getDescription());
        tf_idproduit.setText(String.valueOf(conseil.getId_produit()));
        tf_typeC.setText(String.valueOf(conseil.getId_typeC()));
    }

    public void setConseilController(ConseilController conseilController) {
        this.conseilController = conseilController;
    }



    @FXML
    void Annuler_conseil(ActionEvent event) {
        Node source = (Node) event.getSource();
        // Get the Stage to which the source node belongs
        Stage stage = (Stage) source.getScene().getWindow();
        // Close the stage
        stage.close();
    }

    @FXML
    void Modifier_conseil(ActionEvent event) {
        Conseil conseil = conseilController.getTv_conseils().getSelectionModel().getSelectedItem();
        if (conseil != null) {
            conseil.setNom_conseil(tf_nomConseil.getText());
            conseil.setVideo(tf_video.getText());
            conseil.setDescription(tf_description.getText());
            conseil.setId_produit(Integer.parseInt(tf_idproduit.getText()));
            conseil.setId_typeC(Integer.parseInt(tf_typeC.getText()));
            try {
                conseilService.modifierConseil(conseil, conseil.getId_conseil());
                refreshTableView();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success !");
                alert.setContentText("update Effectué");
                alert.showAndWait();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    void Supprimer_conseil(ActionEvent event) {
        Conseil conseil = conseilController.getTv_conseils().getSelectionModel().getSelectedItem();
        if (conseil != null) {
            try {
                int id = conseil.getId_conseil();
                conseilService.deleteConseil(id);
                conseilController.observableList.remove(conseil);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Art deleted successfully ");
                alert.showAndWait();
            } catch (SQLException e) {
                refreshTableView();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

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

    private void refreshTableView() {
        try {
            ObservableList<Conseil> observableList = FXCollections.observableList(conseilService.displayConseil());
            conseilController.getTv_conseils().setItems(observableList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // Gérer l'erreur selon vos besoins
        }
    }

    @FXML
    void play_video(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MediaPlayer.fxml"));
            Parent playVideoRoot = fxmlLoader.load();

            PlayVideoController playVideoController = fxmlLoader.getController();
            playVideoController.setUpdateConseilController(this);
            playVideoController.Lire_video(event);

            Stage playVideoStage = new Stage();
            playVideoStage.setScene(new Scene(playVideoRoot));
            playVideoStage.setTitle("Video");
            playVideoStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }
}



