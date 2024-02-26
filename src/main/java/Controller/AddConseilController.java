package Controller;

import Entities.Categorie;
import Entities.Produit;
import Services.CategorieService;
import Services.ConseilService;
import Services.ProduitService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Entities.Conseil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AddConseilController  implements Initializable {

    ConseilService conseilService = new ConseilService();
    CategorieService categorieService = new CategorieService();

    ProduitService ps = new ProduitService();

    private ConseilController conseilController ;

    @FXML
    private Label Description_empty;

    @FXML
    private Label Video_empty;

    @FXML
    private Label cqtegorie_empty;

    @FXML
    private Label nomC_empty;

    @FXML
    private Label produit_empty;


    @FXML
    private TextField tf_description;

    @FXML
    private TextField tf_nomConseil;

    @FXML
    private ComboBox<Categorie> combo_typeC;

    @FXML
    private ComboBox<Produit> combo_produit;


    @FXML
    private TextField tf_video;


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
    void Ajouter_conseil(ActionEvent event) {
        try {
            String nomConseil = tf_nomConseil.getText().trim();
            String videoPath = tf_video.getText().trim();
            String description = tf_description.getText().trim();

            if(nomConseil.isEmpty())
            {
              nomC_empty.setVisible(true);
            }
            if(videoPath.isEmpty())
            {
                Video_empty.setVisible(true);
            }
            if(description.isEmpty())
            {
                Description_empty.setVisible(true);
            }
            Produit selectedProduit = combo_produit.getValue();    /// getting all combo categorie values
            if (selectedProduit == null) {
                produit_empty.setVisible(true);
            }
            Categorie selectedCategorie = combo_typeC.getValue();    /// getting all combo categorie values
            if (selectedCategorie == null) {
                cqtegorie_empty.setVisible(true);
            }

            // Add the Conseil with the video file path
            conseilService.ajouterConseil(new Conseil(
                    nomConseil,
                    videoPath, // Use the path of the selected video file
                    description,
                    selectedProduit.getId_produit(),
                    selectedCategorie.getId_categorie()
            ));

            showAlert("Success", "Ajout Effectué", Alert.AlertType.INFORMATION);
        } catch (SQLException e) {
            showAlert("Error", "Impossible d'ajouter une conseil", Alert.AlertType.ERROR);
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


    @FXML
    void annuler_conseil(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomC_empty.setVisible(false);
        Video_empty.setVisible(false);
        Description_empty.setVisible(false);
        produit_empty.setVisible(false);
        cqtegorie_empty.setVisible(false);
        List<Categorie> allCategories = null;
        try {
            allCategories = categorieService.displayCategorie();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Set categories as items in the ComboBox
        combo_typeC.setItems(FXCollections.observableList(allCategories));

        List<Produit> allProducts = null ;
        try {
        allProducts = ps.displayProduit();
        } catch (SQLException e) {
        throw new RuntimeException(e);
        }
        // Set products as items in the ComboBox
        if (combo_produit != null) {
            combo_produit.setItems(FXCollections.observableList(allProducts));
        } else {
            System.err.println("combo_produit is null");
        }

    }
}
