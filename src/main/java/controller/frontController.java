package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import test.MainFX;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class frontController implements Initializable {

    @FXML
    private HBox achatBtn;

    @FXML
    private ImageView achatIcon;

    @FXML
    private ImageView adminIMG;
    public ImageView getAdminIMG() {
        return adminIMG;
    }

    @FXML
    private HBox collectBtn;

    @FXML
    private HBox commandsBtn;

    @FXML
    private ImageView commandsIcon;

    @FXML
    private Pane content_area;

    @FXML
    private HBox dashboardBtn;

    @FXML
    private ImageView dashboardIcon;

    @FXML
    private HBox fundrisingBtn;

    @FXML
    private HBox productsBtn;

    @FXML
    private ImageView productsIcon;

    @FXML
    private HBox profileBtn;

    @FXML
    private ImageView profileIcon;

    @FXML
    private HBox sideBarLogout;

    @FXML
    private Label username;

    @FXML
    public Label getUsername() {
        return username;
    }
    @FXML
    private Label titre_dash;
    @FXML
    void logout(MouseEvent event) {

    }

    @FXML
    void open_achatList(MouseEvent event) {

    }



    @FXML
    void open_dashboard(MouseEvent event) {

    }

    @FXML
    void open_fundrisingList(MouseEvent event) {

    }

    @FXML
    void open_productsList(MouseEvent event) throws IOException {


        Parent fxml = FXMLLoader.load(getClass().getResource("/produit/listProduitFront.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }

    @FXML
    void open_commandsList(MouseEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("/ListePanier.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }

    @FXML
    void open_event(MouseEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("/client/event/client.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }


    @FXML
    void open_profile(MouseEvent event) {
    }
    @FXML
    void profile(ActionEvent event) throws IOException {

        content_area.getChildren().clear();
        if(MainFX.connecteduser.getPhoto() != null){
            Image image = new Image(MainFX.connecteduser.getPhoto());
            adminIMG.setImage(image);}
        username.setText(MainFX.connecteduser.getNom() +" "+ MainFX.connecteduser.getPrenom());
        titre_dash.setText("Mon Profile");
        Node[] nodes = new Node[1];
        nodes[0] = FXMLLoader.load(getClass().getResource("/user/UserP.fxml"));
        //give the items some effect
        Label nlabel = (Label) nodes[0].lookup("#name");
        nlabel.setText(MainFX.connecteduser.getNom() +" "+ MainFX.connecteduser.getPrenom());
        Label elabel = (Label) nodes[0].lookup("#emailp");
        elabel.setText(MainFX.connecteduser.getEmail());
        Label tlabel = (Label) nodes[0].lookup("#tel");
        tlabel.setText(Integer.toString(MainFX.connecteduser.getTel()));
        Label alabel = (Label) nodes[0].lookup("#adresse");
        alabel.setText(MainFX.connecteduser.getAddresse());

        if(MainFX.connecteduser.getDate_naiss() != null){
            Label dlabel = (Label) nodes[0].lookup("#naiss");
            dlabel.setText(MainFX.connecteduser.getDate_naiss().toString());
        }
              /*  nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : #02030A");
                });*/
        content_area.getChildren().add(nodes[0]);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println(MainFX.m);
        if(MainFX.m == 0){
            content_area.getChildren().clear();
            if(MainFX.connecteduser.getPhoto() != null){
                Image image = new Image(MainFX.connecteduser.getPhoto());
                adminIMG.setImage(image);}
            username.setText(MainFX.connecteduser.getNom() +" "+ MainFX.connecteduser.getPrenom());
            titre_dash.setText("");
        }
        else if (MainFX.m == 1){
            content_area.getChildren().clear();
            if(MainFX.connecteduser.getPhoto() != null){
                Image image = new Image(MainFX.connecteduser.getPhoto());
                adminIMG.setImage(image);}
            username.setText(MainFX.connecteduser.getNom() +" "+ MainFX.connecteduser.getPrenom());
            titre_dash.setText("Modifier Profile");
            Node[] nodes = new Node[1];
            try {
                nodes[0] = FXMLLoader.load(getClass().getResource("/user/Modifier_front.fxml"));

               /* nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : #transparent");
                });*/
                content_area.getChildren().add(nodes[0]);
            } catch (IOException ex) {
               // Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else  if (MainFX.m == 2){
            content_area.getChildren().clear();
            if(MainFX.connecteduser.getPhoto() != null){
                Image image = new Image(MainFX.connecteduser.getPhoto());
                adminIMG.setImage(image);}
            username.setText(MainFX.connecteduser.getNom() +" "+ MainFX.connecteduser.getPrenom());
            titre_dash.setText("Modifier Mot de passe");
            Node[] nodes = new Node[1];
            try {
                nodes[0] = FXMLLoader.load(getClass().getResource("/user/PwdUser.fxml"));

               /* nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : #transparent");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : #transparent");
                });*/
                content_area.getChildren().add(nodes[0]);
            } catch (IOException ex) {
              //  Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else  if (MainFX.m == 3){
            content_area.getChildren().clear();
            if(MainFX.connecteduser.getPhoto() != null){
                Image image = new Image(MainFX.connecteduser.getPhoto());
                adminIMG.setImage(image);}
            username.setText(MainFX.connecteduser.getNom() +" "+ MainFX.connecteduser.getPrenom());
            titre_dash.setText("Photo de profile");
            Node[] nodes = new Node[1];
            try {
                nodes[0] = FXMLLoader.load(getClass().getResource("/user/uploadImg_user.fxml"));

               /* nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : #transparent");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : #transparent");
                });*/
                content_area.getChildren().add(nodes[0]);
            } catch (IOException ex) {
               // Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else if (MainFX.m == 4){

            content_area.getChildren().clear();
            if(MainFX.connecteduser.getPhoto() != null){
                Image image = new Image(MainFX.connecteduser.getPhoto());
                adminIMG.setImage(image);}
            username.setText(MainFX.connecteduser.getNom() +" "+ MainFX.connecteduser.getPrenom());
            titre_dash.setText("supprimer compte");
            Node[] nodes = new Node[1];
            try {
                nodes[0] = FXMLLoader.load(getClass().getResource("/user/supp_user.fxml"));

               /* nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : #transparent");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : #transparent");
                });*/
                content_area.getChildren().add(nodes[0]);
            } catch (IOException ex) {
              //  Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}

   /* @FXML
    void initialize() {

        try {
            ObservableList<produit> observableList = FXCollections.observableList(se.afficher());

            for (int i = 0; i < observableList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/card.fxml"));
                HBox cardBox = fxmlLoader.load();
                card cardController = fxmlLoader.getController();

                cardController.setData(observableList.get(i));
                id_vbox.getChildren().add(cardBox);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}*/


   /* public void getData(produit produit) throws SQLException {

        services.produitService produitService=new produitService();
        ObservableList<produit> observableList = FXCollections.observableList(produitService.getAllProducts());
        productName.setText(produit.getNom());
        prix.setText(String.valueOf(produit.getPrix()));
        quantite.setText(String.valueOf(produit.getQuantite()));


    }*/














