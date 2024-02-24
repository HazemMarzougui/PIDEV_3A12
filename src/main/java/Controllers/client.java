package Controllers;
import entities.Evenement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import services.Service_evenement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
public class client {
    @FXML
    private ImageView id_image;

    @FXML
    private TextField id_search;

    @FXML
    void form(ActionEvent event) {
    }
    @FXML
    private Button id_search_button;

    @FXML
    private VBox id_vbox;

    Service_evenement se = new Service_evenement();
    @FXML
    void search_event(ActionEvent event) {
        try {
            id_vbox.getChildren().clear(); // Clear previous content

            String searchText = id_search.getText(); // Assuming id_search is the TextField where the user enters the search text

            ObservableList<Evenement> observableList = FXCollections.observableList(se.afficher());

            // Filter the list based on the search text
            List<Evenement> filteredList = observableList.stream()
                    .filter(e -> e.getNom_event().toLowerCase().contains(searchText.toLowerCase()))
                    .collect(Collectors.toList());

            // Load and display filtered data
            for (Evenement evenement : filteredList) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/cardtemplate_client.fxml"));
                HBox cardBox = fxmlLoader.load();
                cardtemplateclient cardController = fxmlLoader.getController();
                cardController.setData(evenement);
                id_vbox.getChildren().add(cardBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void initialize()
    {

        try{
            ObservableList<Evenement> observableList = FXCollections.observableList(se.afficher()) ;

            for (int i = 0; i < observableList.size(); i++) {
                FXMLLoader fxmlLoader= new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/cardtemplate_client.fxml"));
                HBox cardBox = fxmlLoader.load();
                cardtemplateclient cardController = fxmlLoader.getController();

                cardController.setData(observableList.get(i));
                id_vbox.getChildren().add(cardBox);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}





