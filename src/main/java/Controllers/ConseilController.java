package Controllers;

import Entities.Conseil;
import Services.ConseilService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;



public class ConseilController
{

    ConseilService conseilService = new ConseilService();



    @FXML
    private Button btn_ajouter;

    @FXML
    private TableColumn<Conseil, String> col_description;

    @FXML
    private TableColumn<Conseil, Integer> col_id;

    @FXML
    private TableColumn<Conseil, Integer> col_idproduit;

    @FXML
    private TableColumn<Conseil, Integer> col_idtypec;

    @FXML
    private TableColumn<Conseil, Integer> col_iduser;

    @FXML
    private TableColumn<Conseil, String> col_nom;

    @FXML
    private TableColumn<Conseil, String> col_video;

    @FXML
    private TextField tf_description;

    @FXML
    private TextField tf_idproduit;

    @FXML
    private TextField tf_idtypec;

    @FXML
    private TextField tf_iduser;

    @FXML
    private TextField tf_nomConseil;

    @FXML
    private TextField tf_video;

    @FXML
    private MediaView mv_video;

    @FXML
    private TableView<Conseil> tv_conseils;

    int id = 0 ;



    @FXML
    void AfficherConseil(ActionEvent event)
    {
        try {
            ObservableList<Conseil> observableList = FXCollections.observableList(conseilService.displayConseil());
            tv_conseils.setItems(observableList);

            col_id.setCellValueFactory(new PropertyValueFactory<>("id_conseil"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_conseil"));
            col_video.setCellValueFactory(new PropertyValueFactory<>("video"));
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_iduser.setCellValueFactory(new PropertyValueFactory<>("id_user"));
            col_idproduit.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
            col_idtypec.setCellValueFactory(new PropertyValueFactory<>("id_typeC"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
/*
    @FXML
    void AjouterConseil(ActionEvent event) {
        try {
            // Use a FileChooser to allow the user to select a video file
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mkv")); // Add more supported video formats if needed
            File selectedFile = fileChooser.showOpenDialog(new Stage());

            if (selectedFile != null) {
                // Copy the selected video file to a specific folder in your project
                String destinationFolder = "C:\\Users\\hazem\\IdeaProjects\\PIDEV\\src\\main\\resources\\uploads"; // Set the correct path
                File destinationFile = new File(destinationFolder, selectedFile.getName());
                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Add the Conseil with the video file path
                conseilService.ajouterConseil(new Conseil(
                        tf_nomConseil.getText(),
                        destinationFile.getPath(), // Use the path of the copied video file
                        tf_description.getText(),
                        Integer.parseInt(tf_iduser.getText()),
                        Integer.parseInt(tf_idproduit.getText()),
                        Integer.parseInt(tf_idtypec.getText())
                ));

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success !");
                alert.setContentText("Ajout Effectué");
                alert.showAndWait();
            } else {
                // Handle the case where no file was selected
            }
        } catch (IOException | SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error !");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }
 */
@FXML
void AjouterConseil(ActionEvent event) {
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
                Integer.parseInt(tf_iduser.getText()),
                Integer.parseInt(tf_idproduit.getText()),
                Integer.parseInt(tf_idtypec.getText())
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
    void ModifierConseil(ActionEvent event) {
        // Récupérer l'objet Conseil sélectionné dans le TableView
        Conseil conseil = tv_conseils.getSelectionModel().getSelectedItem();

        // Vérifier si un élément a été effectivement sélectionné
        if (conseil != null) {
            // Mettre à jour les propriétés du Conseil avec les valeurs des champs de texte
            conseil.setNom_conseil(tf_nomConseil.getText());
            conseil.setVideo(tf_video.getText());
            conseil.setDescription(tf_description.getText());
            conseil.setId_user(Integer.parseInt(tf_iduser.getText()));
            conseil.setId_produit(Integer.parseInt(tf_idproduit.getText()));
            conseil.setId_typeC(Integer.parseInt(tf_idtypec.getText()));

            // Appeler la méthode de mise à jour dans votre service ou gestionnaire de données
            try {
                conseilService.modifierConseil(conseil,id); // Assuming you have an update method in your service
                // Rafraîchir la TableView après la modification
                refreshTableView();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                // Gérer l'erreur selon vos besoins
            }

            // Réinitialiser les champs de texte et activer le bouton d'ajout
            resetFields();
            btn_ajouter.setDisable(false);
        }
    }

    // Méthode pour rafraîchir la TableView après la modification
    private void refreshTableView() {
        try {
            ObservableList<Conseil> observableList = FXCollections.observableList(conseilService.displayConseil());
            tv_conseils.setItems(observableList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // Gérer l'erreur selon vos besoins
        }
    }

    // Méthode pour réinitialiser les champs de texte
    private void resetFields() {
        tf_nomConseil.clear();
        tf_video.clear();
        tf_description.clear();
        tf_iduser.clear();
        tf_idproduit.clear();
        tf_idtypec.clear();
    }

    @FXML
    void Click(MouseEvent event) {
        if (event.getButton()== MouseButton.PRIMARY) {
            // Récupérer l'objet Panier sélectionné dans le TableView
            Conseil conseil = tv_conseils.getSelectionModel().getSelectedItem();
            // Vérifi  er si un élément a été effectivement sélectionné
            if (conseil != null) {
                // Récupérer les données du Panier et les afficher dans les champs de texte appropriés
                id = conseil.getId_conseil();
                tf_nomConseil.setText(conseil.getNom_conseil());
                tf_video.setText(conseil.getVideo());
                tf_description.setText(conseil.getDescription());
                tf_iduser.setText(String.valueOf(conseil.getId_user()));
                tf_idproduit.setText(String.valueOf(conseil.getId_produit()));
                tf_idtypec.setText(String.valueOf(conseil.getId_typeC()));
                btn_ajouter.setDisable(true);

                Media media = new Media(new File(conseil.getVideo()).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mv_video.setMediaPlayer(mediaPlayer);
                mediaPlayer.play();
                //mv_video.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #333333; -fx-border-width: 2px;");
            }
        }
    }




    @FXML
    void SupprimerConseil(ActionEvent event) {
        Conseil conseil = tv_conseils.getSelectionModel().getSelectedItem();
        if (conseil != null) {
            try {
                conseilService.deleteConseil(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            resetFields();
            refreshTableView();
        }
    }


    @FXML
    void Clear(ActionEvent event) {
        resetFields();
    }

    @FXML
    void uploadVideo(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mkv")); // Add more supported video formats if needed
        File selectedFile = fileChooser.showOpenDialog(new Stage());


        if (selectedFile != null) {
            tf_video.setText(selectedFile.getPath());
            String destinationFolder = "C:\\Users\\hazem\\IdeaProjects\\PIDEV\\src\\main\\resources\\uploads"; // Set the correct path
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

/*    @FXML
    void mediaPlayer(ActionEvent event) {


            Conseil conseil = tv_conseils.getSelectionModel().getSelectedItem();
            Media media = new Media(new File(conseil.getVideo()).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mv_video.setMediaPlayer(mediaPlayer);
             mediaPlayer.play();
        }
 */


    @FXML
    void go_categorie(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GestionCategorie.fxml"));
        tf_nomConseil.getScene().setRoot(root);
    }

 }



