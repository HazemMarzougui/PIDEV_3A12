package Controller;

import Entities.Conseil;
import Services.CategorieService;
import Services.ConseilService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConseilController implements Initializable {

    ConseilService conseilService = new ConseilService();
    CategorieService categorieService = new CategorieService();
    ObservableList<Conseil> observableList;

    @FXML
    private Button cat_btn;


    @FXML
    private ComboBox<String> combo_tri;

    @FXML
    private Pane content_area;

    @FXML
    private GridPane gridPane;

    @FXML
    private ComboBox<String> combo_menu;

    @FXML
    private ScrollPane scrollPane;

    public ScrollPane getScrollPane()
    {
        return scrollPane;
    }
    @FXML
    private TextField search_bar;


    private List<ConseilItemController> conseilItemControllers = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> Management = FXCollections.observableArrayList( "Liste Categories", "Statistiques");
        combo_menu.setItems(Management);

        combo_menu.setOnAction(event ->
        {
         String selectedItem = combo_menu.getSelectionModel().getSelectedItem();
            navigateToPage(selectedItem);
        });
        try {
            conseilService = new ConseilService();
            List<Conseil> conseils = conseilService.displayConseil();

            GridPane scrollPaneContent = new GridPane();

            for (int i = 0; i < conseils.size(); i++) {
                Conseil conseil = conseils.get(i);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ConseilItem.fxml"));
                HBox conseilItem = fxmlLoader.load();
                ConseilItemController conseilItemController = fxmlLoader.getController();
                conseilItemController.SetData(conseil);
                scrollPaneContent.add(conseilItem, 0, i);
                conseilItemControllers.add(conseilItemController);

                conseilItemController.setOnUpdateClickListener(() -> {
                    try {
                        updateConseil(conseil);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                conseilItemController.setOnDeleteClickListener(() -> {
                    try {
                        ConseilService conseilService = new ConseilService();
                        conseilService.deleteConseil(conseil.getId_conseil());
                        refresh();

                    }  catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            ScrollPane scrollPane = (ScrollPane) content_area.lookup("#scrollPane");
            scrollPane.setContent(scrollPaneContent);

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        search_bar.textProperty().addListener((observable, oldValue, newValue) -> {  // textProperty -> definir le valeur de searchBar(TextField)
            gridPane.getChildren().clear();
            try {
                ConseilService Us = new ConseilService();
                List<Conseil> conseilList;
                if (newValue.isEmpty()) {
                    conseilList = Us.displayConseil();
                } else {

                    conseilList = Us.searchProducts(newValue);
                }
                updateUIWithConseils(conseilList);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    });


        ObservableList<String> list = FXCollections.observableArrayList("Nom A -> Z" , "Type Conseil");
        combo_tri.setItems(list);

        combo_tri.setOnAction(event ->
        {
            String selectedItem = combo_tri.getSelectionModel().getSelectedItem();
            try {
                navigateSort(selectedItem);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void navigateSort(String selectedSort) throws SQLException {
        switch (selectedSort) {
            case "Nom A -> Z":
                    List<Conseil> conseilList = conseilService.sortConseilByNom();
                    updateUIWithConseils(conseilList);

                break;
            case "Type Conseil":

                break;
            default:
                // Handle unknown selection
                System.out.println("Unknown selection");
        }
    }

    @FXML
     void add_conseil(ActionEvent event) {
        try {
            // Load the "Ajouter Conseil" form
            FXMLLoader addConseilLoader = new FXMLLoader(getClass().getResource("/AjouterConseil.fxml"));
            Parent addConseilRoot = addConseilLoader.load();
            Scene addConseilScene = new Scene(addConseilRoot);

            // Create a new Stage for the "Ajouter Conseil" form
            Stage addConseilStage = new Stage();
            addConseilStage.setScene(addConseilScene);
            addConseilStage.setTitle("Ajouter Conseil");

            // Show the "Ajouter Conseil" form as a dialog
            addConseilStage.showAndWait();
            refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchConseil(KeyEvent event) {
        /*
        // Get the search text from the input field
        String searchQuery = search_bar.getText(); // Convert to lowercase for case-insensitive search

        // Filter Conseil items based on the search query
        List<ConseilItemController> filteredConseils = conseilItemControllers.stream()
                .filter(conseilItemController -> conseilItemController.getData().getNom_conseil().contains(searchQuery))
                .collect(Collectors.toList());

        // Update the UI with the filtered Conseils
        updateUIWithFilteredConseils(filteredConseils);

         */
    }

    @FXML
    void updateConseil(Conseil selectedConseil) throws IOException {
        FXMLLoader updateConseilLoader = new FXMLLoader(getClass().getResource("/ModifierConseil.fxml"));
        Parent updateConseilRoot = updateConseilLoader.load();
        UpdateConseilController updateConseilController = updateConseilLoader.getController();
        updateConseilController.setConseil(selectedConseil);
        updateConseilController.setConseilController(this);

        Stage updateConseilStage = new Stage();
        updateConseilStage.setScene(new Scene(updateConseilRoot));
        updateConseilStage.setTitle("Update Conseil");
        updateConseilStage.show();
    }

    private void updateUIWithConseils(List<Conseil> conseils) {
        GridPane scrollPaneContent = new GridPane();

        for (int i = 0; i < conseils.size(); i++) {
            Conseil conseil = conseils.get(i);

            // Create a ConseilItemController
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ConseilItem.fxml"));

            try {
                Parent conseilItem = fxmlLoader.load();
                ItemCardController item = fxmlLoader.getController();
                item.SetData(conseil);       ////////////////////////////// NBBBB : GHALTA

                // Add conseilItem to the GridPane
                scrollPaneContent.add(conseilItem, 0, i);
                System.out.println("Number of children in scrollPaneContent: " + scrollPaneContent.getChildren().size());

            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Error loading ConseilItem.fxml: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Unknown error: " + e.getMessage());
            }
        }

        // Set the updated content to the ScrollPane
        ScrollPane scrollPane = (ScrollPane) content_area.lookup("#scrollPane");
        scrollPane.setContent(scrollPaneContent);
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

    public void removeConseil(Conseil conseil) {
        observableList.remove(conseil);
        Image originalImage = new Image(String.valueOf(getClass().getResource("/uploads/verifie.png")));
        double targetWidth = 50; // Set the desired width
        double targetHeight = 50; // Set the desired height
        Image resizedImage = new Image(originalImage.getUrl(), targetWidth, targetHeight, true, true);
        Notifications notification = Notifications.create();
        notification.graphic(new ImageView(resizedImage));
        notification.text("Conseil est ajouté avec Succés");
        notification.title("Ajout Effectué");
        notification.hideAfter(Duration.seconds(4));
        notification.position(Pos.BOTTOM_RIGHT);
        notification.darkStyle();
        refresh();
    }

    private void refreshGridPane(List<Conseil> conseils) {
        GridPane scrollPaneContent = new GridPane();

        for (int i = 0; i < conseils.size(); i++) {
            Conseil conseil = conseils.get(i);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ConseilItem.fxml"));
            HBox conseilItem;
            try {
                conseilItem = fxmlLoader.load();
                ConseilItemController conseilItemController = fxmlLoader.getController();
                conseilItemController.SetData(conseil);
                scrollPaneContent.add(conseilItem, 0, i);
                conseilItemControllers.add(conseilItemController);

                conseilItemController.setOnUpdateClickListener(() -> {
                    try {
                        updateConseil(conseil);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                conseilItemController.setOnDeleteClickListener(() -> {
                    try {
                        ConseilService conseilService = new ConseilService();
                        conseilService.deleteConseil(conseil.getId_conseil());
                        Image originalImage = new Image(String.valueOf(getClass().getResource("/uploads/verifie.png")));
                        double targetWidth = 50; // Set the desired width
                        double targetHeight = 50; // Set the desired height
                        Image resizedImage = new Image(originalImage.getUrl(), targetWidth, targetHeight, true, true);
                        Notifications notification = Notifications.create();
                        notification.graphic(new ImageView(resizedImage));
                        notification.text("Conseil est ajouté avec Succés");
                        notification.title("Ajout Effectué");
                        notification.hideAfter(Duration.seconds(4));
                        notification.position(Pos.BOTTOM_RIGHT);
                        notification.darkStyle();

                        refreshGridPane(conseilService.displayConseil()); // Refresh after deletion
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        ScrollPane scrollPane = (ScrollPane) content_area.lookup("#scrollPane");
        scrollPane.setContent(scrollPaneContent);
    }

    public void refresh() {
        try {
            conseilService = new ConseilService();
            List<Conseil> conseils = conseilService.displayConseil();
            refreshGridPane(conseils); // Refresh after adding or updating Conseil
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}





