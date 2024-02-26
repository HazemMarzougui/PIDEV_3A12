package Controller;

import Entities.Conseil;
import Services.CategorieService;
import Services.ConseilService;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ConseilController implements Initializable {

    ConseilService conseilService = new ConseilService();
    CategorieService categorieService = new CategorieService();
    ObservableList<Conseil> observableList;

    @FXML
    private Button cat_btn;


    @FXML
    private Pane content_area;

    @FXML
    private GridPane gridPane;

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
        try {
            conseilService = new ConseilService();
            List<Conseil> conseils = conseilService.displayConseil();

            GridPane scrollPaneContent = new GridPane();

            for (int i = 0; i < conseils.size(); i++) {
                Conseil conseil = conseils.get(i);

                // Create a ConseilItemController
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ConseilItem.fxml"));
                HBox conseilItem = fxmlLoader.load();
                ConseilItemController conseilItemController = fxmlLoader.getController();
                conseilItemController.SetData(conseil);

                // Add conseilItem to the GridPane
                scrollPaneContent.add(conseilItem, 0, i);

                // Add ConseilItemController to the list
                conseilItemControllers.add(conseilItemController);

                // Set the callback for the update button click
                conseilItemController.setOnUpdateClickListener(() -> {
                    try {
                        updateConseil(conseil); // Open UpdateConseilController with the selected Conseil
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                conseilItemController.setOnDeleteClickListener(() -> {
                    try {
                        ConseilService conseilService = new ConseilService();
                        conseilService.deleteConseil(conseil.getId_conseil()); // Open UpdateConseilController with the selected Conseil
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
        search_bar.textProperty().addListener((observable, oldValue, newValue) -> {
            gridPane.getChildren().clear();
            try {
                ConseilService Us = new ConseilService();
                List<Conseil> users;
                if (newValue.isEmpty()) {
                    // If the search string is empty, display the complete list
                    users = Us.displayConseil();
                } else {
                    // If the search string is not empty, execute the search and display the results
                    users = Us.searchProducts(newValue);
                }
                updateUIWithConseils(users);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    });
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
        } catch (IOException e) {
            e.printStackTrace();
        }

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


    @FXML
    void go_categorie(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CategorieList.fxml"));
        cat_btn.getScene().setRoot(root);
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


    private void updateUIWithConseils(List<Conseil> conseils) {
        GridPane scrollPaneContent = new GridPane();

        for (int i = 0; i < conseils.size(); i++) {
            Conseil conseil = conseils.get(i);

            // Create a ConseilItemController
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ConseilItem.fxml"));
            HBox conseilItem;
            try {
                conseilItem = fxmlLoader.load();
                ConseilItemController item = fxmlLoader.getController();
                item.SetData(conseil);

                // Add conseilItem to the GridPane
                scrollPaneContent.add(conseilItem, 0, i);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }

        // Set the updated content to the ScrollPane
        ScrollPane scrollPane = (ScrollPane) content_area.lookup("#scrollPane");
        scrollPane.setContent(scrollPaneContent);
    }



    public void removeConseil(Conseil conseil) {
        observableList.remove(conseil);
    }














}





