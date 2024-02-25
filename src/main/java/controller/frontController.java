package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import test.MainFx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class frontController implements Initializable {
    @FXML
    private ImageView achatIcon;

    @FXML
    private ImageView adminIMG;

    @FXML
    private ImageView commandsIcon;

    @FXML
    private ImageView dashboardIcon;

    @FXML
    private ImageView productsIcon;

    @FXML
    private Label titre_dash;

    @FXML
    private Label username;
    @FXML
    private VBox pnItems;


    public void initialize(URL location, ResourceBundle resources) {

        System.out.println(MainFx.m);
        if(MainFx.m == 0){
            pnItems.getChildren().clear();
            Image image = new Image(MainFx.connecteduser.getPhoto());
            adminIMG.setImage(image);
            username.setText(MainFx.connecteduser.getNom() +" "+ MainFx.connecteduser.getPrenom());
            titre_dash.setText("Mon Profile");
        }
       else if (MainFx.m == 1){
            pnItems.getChildren().clear();
            Image image = new Image(MainFx.connecteduser.getPhoto());
            adminIMG.setImage(image);
            username.setText(MainFx.connecteduser.getNom() +" "+ MainFx.connecteduser.getPrenom());
            titre_dash.setText("Mon Profile");
            Node[] nodes = new Node[1];
            try {
                nodes[0] = FXMLLoader.load(getClass().getResource("/Modifier_front.fxml"));

                nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : #02030A");
                });
                pnItems.getChildren().add(nodes[0]);
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else  if (MainFx.m == 2){
            pnItems.getChildren().clear();
            Image image = new Image(MainFx.connecteduser.getPhoto());
            adminIMG.setImage(image);
            username.setText(MainFx.connecteduser.getNom() +" "+ MainFx.connecteduser.getPrenom());
            titre_dash.setText("Mon Profile");
            Node[] nodes = new Node[1];
            try {
                nodes[0] = FXMLLoader.load(getClass().getResource("/PwdUser.fxml"));

                nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : #02030A");
                });
                pnItems.getChildren().add(nodes[0]);
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else  if (MainFx.m == 3){
            pnItems.getChildren().clear();
            Image image = new Image(MainFx.connecteduser.getPhoto());
            adminIMG.setImage(image);
            username.setText(MainFx.connecteduser.getNom() +" "+ MainFx.connecteduser.getPrenom());
            titre_dash.setText("Mon Profile");
            Node[] nodes = new Node[1];
            try {
                nodes[0] = FXMLLoader.load(getClass().getResource("/uploadImg_user.fxml"));

                nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : #02030A");
                });
                pnItems.getChildren().add(nodes[0]);
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    void profile(ActionEvent event) throws IOException {
        pnItems.getChildren().clear();
        Image image = new Image(MainFx.connecteduser.getPhoto());
        adminIMG.setImage(image);
        username.setText(MainFx.connecteduser.getNom() +" "+ MainFx.connecteduser.getPrenom());
        titre_dash.setText("Mon Profile");
        Node[] nodes = new Node[1];
        nodes[0] = FXMLLoader.load(getClass().getResource("/UserP.fxml"));
        //give the items some effect
        Label nlabel = (Label) nodes[0].lookup("#name");
        nlabel.setText(MainFx.connecteduser.getNom() +" "+ MainFx.connecteduser.getPrenom());
        Label elabel = (Label) nodes[0].lookup("#emailp");
        elabel.setText(MainFx.connecteduser.getEmail());
        Label tlabel = (Label) nodes[0].lookup("#tel");
        tlabel.setText(Integer.toString(MainFx.connecteduser.getTel()));
        Label alabel = (Label) nodes[0].lookup("#adresse");
        alabel.setText(MainFx.connecteduser.getAddresse());
        Label dlabel = (Label) nodes[0].lookup("#naiss");
        dlabel.setText(MainFx.connecteduser.getDate_naiss().toString());
              /*  nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : #02030A");
                });*/
        pnItems.getChildren().add(nodes[0]);

    }
    @FXML
    void SignOut(ActionEvent event) {
        pnItems.getChildren().clear();
        try {
            // Load the new FXML file
            Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
            // Get the current scene and set the new scene
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

}
