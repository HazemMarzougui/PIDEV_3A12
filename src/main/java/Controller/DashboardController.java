package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private ComboBox<String> combo_menu;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> management = FXCollections.observableArrayList("Liste Conseils", "Liste Categories", "Statistiques");
        combo_menu.setItems(management);

        // Add an event handler to the ComboBox
        combo_menu.setOnAction(event -> {
            String selectedItem = combo_menu.getSelectionModel().getSelectedItem();
            navigateToPage(selectedItem);
        });
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
                try {
                    Parent stat = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Statistique.fxml")));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(stat));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                // Handle unknown selection
                System.out.println("Unknown selection");
        }
    }
}


