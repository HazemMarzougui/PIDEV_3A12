package Controller;
import Entities.Categorie;
import Entities.Conseil;
import Entities.Produit;
import Services.CategorieService;
import Services.ConseilService;
import com.sun.scenario.effect.impl.prism.PrDrawable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class UpdateConseilController implements Initializable {
    ConseilService conseilService = new ConseilService();
    CategorieService categorieService = new CategorieService();

    private ConseilController conseilController ;

    public void setConseilController(ConseilController conseilController)
    {
        this.conseilController = conseilController;
    }


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
    private TextField tf_idproduit;

    @FXML
    private TextField tf_nomConseil;

    @FXML
    private ComboBox<Categorie> combo_typeC;

    @FXML
    private ComboBox<Produit> combo_produit;

    @FXML
    private TextField tf_video;

    public TextField getTf_video() {
        return tf_video;
    }

    int id = 0;

    Stage stage;

    private Conseil selectedConseil;

    public void setConseil(Conseil conseil) {
        this.selectedConseil = conseil;
        tf_nomConseil.setText(conseil.getNom_conseil());
        tf_video.setText(conseil.getVideo());
        tf_description.setText(conseil.getDescription());
        int produitId = conseil.getId_produit();

        // Find the category with the matching idTypeC
        Produit selectedProduct = null;
        for (Produit produit : combo_produit.getItems()) {
            if (produit.getId_produit() == produitId) {
                selectedProduct = produit;
                break;
            }
        }

        // Set the selected category in the ComboBox
        combo_produit.setValue(selectedProduct);

        int categoryId = conseil.getId_typeC();

        // Find the category with the matching idTypeC
        Categorie selectedCategory = null;
        for (Categorie cat : combo_typeC.getItems()) {
            if (cat.getId_categorie() == categoryId) {
                selectedCategory = cat;
                break;
            }
        }

        // Set the selected category in the ComboBox
        combo_typeC.setValue(selectedCategory);
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
            // Create a new Conseil with updated values
            Conseil updatedConseil = new Conseil(
                    tf_nomConseil.getText(),
                    tf_video.getText(),
                    tf_description.getText(),
                    combo_produit.getValue().getId_produit(),
                    combo_typeC.getValue().getId_categorie()
            );
            conseilService.modifierConseil(updatedConseil, selectedConseil.getId_conseil());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès !");
            alert.setContentText("Modification Effectuée");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur !");
            alert.setContentText("Impossible de modifier le conseil");
            alert.showAndWait();
            e.printStackTrace();
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
            alert.setContentText("Aucune fichier Video selectionné");
            alert.showAndWait();
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

    private Categorie getSelectedCategory() {
        int categoryId = selectedConseil.getId_typeC();

        // Find the category with the matching idTypeC
        for (Categorie cat : combo_typeC.getItems()) {
            if (cat.getId_categorie() == categoryId) {
                return cat;
            }
        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("no category");
        alert.showAndWait();
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            nomC_empty.setVisible(false);
            Video_empty.setVisible(false);
            Description_empty.setVisible(false);
            produit_empty.setVisible(false);
            cqtegorie_empty.setVisible(false);
            // Fetch categories from your service
            List<Categorie> categories = categorieService.displayCategorie();

            // Set categories as items in the ComboBox
            combo_typeC.setItems(FXCollections.observableList(categories));

            // Now set the selected value if available
            if (selectedConseil != null) {
                Categorie selectedCategory = getSelectedCategory(); // Implement this method to get the selected category
                combo_typeC.setValue(selectedCategory);
            }
        } catch (SQLException e) {
            // Handle the exception or display an error message
            e.printStackTrace();
        }
    }
    @FXML
    void annuler_conseil(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    }




