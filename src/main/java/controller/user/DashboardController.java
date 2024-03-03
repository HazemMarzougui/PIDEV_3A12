package controller.user;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import test.MainFX;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class DashboardController implements Initializable {
    @FXML
    private Label username;
    @FXML
    private Label titre_dash;

    @FXML
    private ImageView adminIMG;

    public Label getUsername() {
        return username;
    }

    public ImageView getAdminIMG() {
        return adminIMG;
    }


    @FXML
    private ImageView adminIMG1;

    @FXML
    private Button btnOrders;

     @FXML
     private Button btnOverview;

    @FXML
    private Button btnS;

    @FXML
    private Pane btnSignout1;

    @FXML
    private Pane btn_userList;

    @FXML
    private ComboBox<String> comb;

    @FXML
    private Pane content_area;

    @FXML
    private ImageView dashIcon;

    @FXML
    private Label dashText;

    @FXML
    private Pane dash_btn;

    @FXML
    private VBox pnItems;

    @FXML
    private TextField search;

    @FXML
    private Label userText;

    @FXML
    private ImageView user_icon;

    public boolean SupAdmin;

    {
        SupAdmin = (MainFX.connecteduser.getId() == 86);
    }

    public void initialize(URL location, ResourceBundle resources) {

        if (MainFX.connecteduser.getPhoto() != null) {
            Image image = new Image(MainFX.connecteduser.getPhoto());
            adminIMG.setImage(image);
        }
        username.setText(MainFX.connecteduser.getNom() + " " + MainFX.connecteduser.getPrenom());

    }

    @FXML
    void btnSignout(MouseEvent event) {

        content_area.getChildren().clear();
        try {
            // Load the new FXML file
            Parent root = FXMLLoader.load(getClass().getResource("/user/Login.fxml"));
            // Get the current scene and set the new scene
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void user(MouseEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("/user/bckint.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }

    @FXML
    void commande(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/AdminListCommand.fxml")));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }



      /* Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/produit/gestionProduit.fxml")));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);*/





    @FXML
    void open_product(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/produit/gestionProduit.fxml")));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }



    @FXML
    void open_categorie(MouseEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/produit/gestionCategorie.fxml")));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }


    @FXML
    void gestion_evenement(MouseEvent event) throws IOException {


        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/admin/event/admin_event.fxml")));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }




}