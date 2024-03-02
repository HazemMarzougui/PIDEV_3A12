package Controller;

import Entities.Categorie;
import Entities.Conseil;
import Services.ConseilService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class StatistiqueController implements Initializable {

    ConseilService conseilService = new ConseilService();


    @FXML
    private Pane content_area;

    @FXML
    private Text creationDate;

    @FXML
    private PieChart pieChart;

    @FXML
    private ComboBox<String> combo_menu;

    @FXML
    private Text nbreConseil;

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
            int NombreConseil = conseilService.ConseilNumbers();
            nbreConseil.setText(String.valueOf(NombreConseil));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Conseil conseil = conseilService.getLastAddedConseil();
            String dateCreation = String.valueOf(conseil.getDateCreation()); // Replace with the actual method to get dateCreation
            creationDate.setText(dateCreation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        populatePieChart();

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

    private void populatePieChart() {
       try {
            Map<Integer, Long> conseilCounts = conseilService.getConseilCountByCategory();
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            for (Map.Entry<Integer, Long> entry : conseilCounts.entrySet()) {
                int idCategorie = entry.getKey();
                long conseilCount = entry.getValue();
                Categorie categoryName = conseilService.getCategoriesName(idCategorie);

                // Create a label that includes the category name and the number of conseils
                String label = categoryName + " (" + conseilCount + " conseils)";

                // Add data to the PieChart with the customized label
                PieChart.Data data = new PieChart.Data(label, conseilCount);
                pieChartData.add(data);
            }
            pieChart.setData(pieChartData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







}
