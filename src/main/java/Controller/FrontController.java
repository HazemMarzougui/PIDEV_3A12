package Controller;

import Entities.Conseil;
import Services.ConseilService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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
    private ScrollPane scrollPane;

    @FXML
    private Pane content_area;

    private List<ItemCardController> itemCardControllers = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            conseilService = new ConseilService();
            List<Conseil> conseils = conseilService.displayConseil();

            GridPane scrollPaneContent = new GridPane();

            for (int i = 0; i < conseils.size(); i++) {
                Conseil conseil = conseils.get(i);

                // Create a ConseilItemController
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ItemCard.fxml"));
                Pane conseilItem = fxmlLoader.load();
                ItemCardController item = fxmlLoader.getController();
                item.SetData(conseil);

                // Add conseilItem to the GridPane
                scrollPaneContent.add(conseilItem, 0, i);

                // Add ConseilItemController to the list
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



}
