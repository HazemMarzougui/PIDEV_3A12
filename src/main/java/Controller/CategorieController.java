package Controller;

import Entities.Categorie;
import Entities.Conseil;
import Services.CategorieService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CategorieController implements Initializable {

    @FXML
    private Text bonjour;

    @FXML
    private Button btn_addCat;

    @FXML
    private Button btn_refr;


    @FXML
    private ComboBox<String> combo_menu;

    @FXML
    private Pane btn_userList;

    @FXML
    private TableColumn<Categorie, Integer> col_idcat;

    @FXML
    private TableColumn<Categorie, String> col_nomCat;

    @FXML
    private Pane content_area;

    @FXML
    private ImageView dashIcon;

    @FXML
    private Label dashText;

    @FXML
    private Pane dash_btn;

    @FXML
    private TableView<Categorie> tv_categories;

    public  TableView<Categorie> getTv_categories()
    {
        return  tv_categories ;
    }

    @FXML
    private Label userText;

    @FXML
    private ImageView user_icon;

    CategorieService categorieService = new CategorieService();
    ObservableList observableList ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> management = FXCollections.observableArrayList("Liste Conseils", "Statistiques");
        combo_menu.setItems(management);
        combo_menu.setOnAction(event ->
        {
            String selectedItem = combo_menu.getSelectionModel().getSelectedItem();
            navigateToPage(selectedItem);
        });

        try {
            observableList = FXCollections.observableList(categorieService.displayCategorie());
            tv_categories.setItems(observableList);
            col_idcat.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
            col_nomCat.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    @FXML
    void Click(MouseEvent event) throws IOException
    {
        if (event.getButton() == MouseButton.PRIMARY) {
            Categorie selectedCategorie = tv_categories.getSelectionModel().getSelectedItem();
            if (selectedCategorie != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UpdateCategorie.fxml"));
                Parent root = fxmlLoader.load();
                UpdateCategorieController updateCategorieController = fxmlLoader.getController();
                updateCategorieController.setCategorie(selectedCategorie);
                updateCategorieController.setCategorieControllerController(this);
                Scene updateConseilScene = new Scene(root);

                Stage updateConseilStage = new Stage();
                updateConseilStage.setScene(updateConseilScene);
                updateConseilStage.setTitle("Gérer Conseil");

                updateConseilStage.showAndWait();
            }
        }

    }

    @FXML
    void add_cat(ActionEvent event)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AddCategorie.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ajouter Categorie");

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void refresh(ActionEvent event) {
        try {
            ObservableList<Categorie> observableList = FXCollections.observableList(categorieService.displayCategorie());
            tv_categories.setItems(observableList);
            col_idcat.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
            col_nomCat.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void navigateToPage(String selectedItem) {
        // Implement navigation logic based on the selected item
        switch (selectedItem) {
            case "Liste Conseils":
                try {
                    Parent categorieListRoot = FXMLLoader.load(getClass().getResource("/ConseilList.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(categorieListRoot));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Liste Categories":
                // Navigate to the "CategorieList.fxml" file
                try {
                    Parent categorieListRoot = FXMLLoader.load(getClass().getResource("/CategorieList.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(categorieListRoot));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "Statistiques":
                // Navigate to the "Statistiques" page
                System.out.println("Navigating to Statistiques page");
                break;
            default:
                // Handle unknown selection
                System.out.println("Unknown selection");
        }
    }


}
