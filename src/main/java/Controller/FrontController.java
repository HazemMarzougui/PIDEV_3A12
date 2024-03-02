package Controller;

import Entities.Conseil;
import Services.ConseilService;
import Test.MainFX;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FrontController implements Initializable {

    ConseilService conseilService = new ConseilService();


    @FXML
    private TextField search_bar;

    @FXML
    private GridPane gridPane;


    @FXML
    private Text nbreConseils;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Pane content_area;

    private ItemDetailsCardController itemDetailsCardController;

    private List<ItemCardController> itemCardControllers = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            int NombreConseil = conseilService.ConseilNumbers();
            nbreConseils.setText(String.valueOf(NombreConseil));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conseilService = new ConseilService();
            List<Conseil> conseils = conseilService.displayConseil();

            GridPane scrollPaneContent = new GridPane();
            scrollPaneContent.setHgap(10); // Set horizontal gap between columns
            scrollPaneContent.setVgap(15); // Set vertical gap between rows

            int column = 0;
            int row = 0;

            for (int i = 0; i < conseils.size(); i++) {
                Conseil conseil = conseils.get(i);

                // Create a ConseilItemController
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ItemCard.fxml"));
                Pane conseilItem = fxmlLoader.load();
                ItemCardController item = fxmlLoader.getController();
                item.SetData(conseil);

                // Set margins for spacing
                Insets margin = new Insets(0, 10, 15, 10);
                GridPane.setMargin(conseilItem, margin);

                scrollPaneContent.add(conseilItem, column, row);

                // Update column and row indices
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }

                itemCardControllers.add(item);
            }

            ScrollPane scrollPane = (ScrollPane) content_area.lookup("#scrollPane");
            scrollPane.setContent(scrollPaneContent);

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        search_bar.textProperty().addListener((observable, oldValue, newValue) -> {
            gridPane.getChildren().clear();
            try {
                ConseilService conseilService = new ConseilService();
                List<Conseil> conseils;

                if (newValue.isEmpty()) {
                    // If the search string is empty, display the complete list
                    conseils = conseilService.displayConseil();
                } else {
                    // If the search string is not empty, execute the search and display the results
                    conseils = conseilService.searchProducts(newValue);
                }

                // Update the UI with the search results
                updateUIWithConseils(conseils);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void updateUIWithConseils(List<Conseil> conseils) {
        GridPane scrollPaneContent = new GridPane();

        for (int i = 0; i < conseils.size(); i++) {
            Conseil conseil = conseils.get(i);

            // Create a ConseilItemController
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ItemCard.fxml"));
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

    @FXML
    void searchConseil(KeyEvent event) {

    }







}
