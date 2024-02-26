package Controllers;


import entities.Evenement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.Service_evenement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
public class admin_event {

    @FXML
    private Button id_add_event;

    @FXML
    private Button id_search_button;

    @FXML
    private VBox id_vbox;

    @FXML
    void add_event(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/admin/event/add_event.fxml"));
            id_add_event.getScene().setRoot(root);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    Service_evenement se = new Service_evenement();
    @FXML
    private TextField id_search;


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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/admin/event/cardtemplate.fxml"));
                HBox cardBox = fxmlLoader.load();
                cardtemplate cardController = fxmlLoader.getController();
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
                fxmlLoader.setLocation(getClass().getResource("/admin/event/cardtemplate.fxml"));
                HBox cardBox = fxmlLoader.load();
                cardtemplate cardController = fxmlLoader.getController();

                cardController.setData(observableList.get(i));
                id_vbox.getChildren().add(cardBox);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }



}
