package Controller;

import Entities.Conseil;
import Services.ConseilService;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConseilController implements Initializable {

    ConseilService conseilService = new ConseilService();
    ObservableList<Conseil> observableList;

    @FXML
    private Button cat_btn;


    @FXML
    private TableColumn<Conseil, String> col_description;

    @FXML
    private TableColumn<Conseil, Integer> col_id_produit;

    @FXML
    private TableColumn<Conseil, Integer> col_idconseil;

    @FXML
    private TableColumn<Conseil, Integer> col_idtypeC;

    @FXML
    private TableColumn<Conseil, String> col_nomC;

    @FXML
    private TableColumn<Conseil, String> col_video;

    @FXML
    private TableView<Conseil> tv_conseils;

    public TableView<Conseil> getTv_conseils() {
        return tv_conseils;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
             observableList = FXCollections.observableList(conseilService.displayConseil());
            tv_conseils.setItems(observableList);

            col_idconseil.setCellValueFactory(new PropertyValueFactory<>("id_conseil"));
            col_nomC.setCellValueFactory(new PropertyValueFactory<>("nom_conseil"));
            col_video.setCellValueFactory(new PropertyValueFactory<>("video"));
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_id_produit.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
            col_idtypeC.setCellValueFactory(new PropertyValueFactory<>("id_typeC"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
     void add_conseil(ActionEvent event) {
        try {
            // Load the "Ajouter Conseil" form
            FXMLLoader addConseilLoader = new FXMLLoader(getClass().getResource("/AddConseil.fxml"));
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
    void refresh(ActionEvent event) {
        try {
            ObservableList<Conseil> observableList = FXCollections.observableList(conseilService.displayConseil());
            tv_conseils.setItems(observableList);

            col_idconseil.setCellValueFactory(new PropertyValueFactory<>("id_conseil"));
            col_nomC.setCellValueFactory(new PropertyValueFactory<>("nom_conseil"));
            col_video.setCellValueFactory(new PropertyValueFactory<>("video"));
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_id_produit.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
            col_idtypeC.setCellValueFactory(new PropertyValueFactory<>("id_typeC"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void Click(MouseEvent event) throws IOException {
        if (event.getButton() == MouseButton.PRIMARY) {
            Conseil selectedConseil = tv_conseils.getSelectionModel().getSelectedItem();
            if (selectedConseil != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UpdateConseil.fxml"));
                Parent root = fxmlLoader.load();
                UpdateConseilController updateConseilController = fxmlLoader.getController();
                updateConseilController.setConseil(selectedConseil);
                updateConseilController.setConseilController(this);
                Scene updateConseilScene = new Scene(root);

                Stage updateConseilStage = new Stage();
                updateConseilStage.setScene(updateConseilScene);
                updateConseilStage.setTitle("Gérer Conseil");

                updateConseilStage.showAndWait();
            }
        }
    }

    private void refreshTableView() {
        try {
            ObservableList<Conseil> observableList = FXCollections.observableList(conseilService.displayConseil());
            tv_conseils.setItems(observableList);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // Gérer l'erreur selon vos besoins
        }
    }

    @FXML
    void go_categorie(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CategorieList.fxml"));
        cat_btn.getScene().setRoot(root);
    }












}





