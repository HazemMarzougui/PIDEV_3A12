package controller;

import entities.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.UtilisateurServices;
import test.MainFx;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DashboardController implements Initializable {
    @FXML
    private Label username;
    @FXML
    private Label titre_dash;

    @FXML
    private ImageView adminIMG;
    @FXML
    private ImageView adminIMG1;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnS;

    @FXML
    private Button btnSignout1;

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

    public boolean SupAdmin ;
    {
        SupAdmin=(MainFx.connecteduser.getId() ==86);
    }


    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(SupAdmin);

        System.out.println(MainFx.m);
        if (MainFx.m == 1){
            pnItems.getChildren().clear();
            if(MainFx.connecteduser.getPhoto() != null){
            Image image = new Image(MainFx.connecteduser.getPhoto());
            adminIMG.setImage(image);}
            username.setText(MainFx.connecteduser.getNom() +" "+ MainFx.connecteduser.getPrenom());
            titre_dash.setText("Modifier Profile");
            Node[] nodes = new Node[1];
            try {
                nodes[0] = FXMLLoader.load(getClass().getResource("/Modifier_back.fxml"));

               /* nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });*/
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
            titre_dash.setText("Modifier Mot de passe");
            Node[] nodes = new Node[1];
            try {
                nodes[0] = FXMLLoader.load(getClass().getResource("/Changerpwd.fxml"));

               /* nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });*/
                pnItems.getChildren().add(nodes[0]);
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else  if (MainFx.m == 3){
            pnItems.getChildren().clear();
            if(MainFx.connecteduser.getPhoto() != null){
            Image image = new Image(MainFx.connecteduser.getPhoto());
            adminIMG.setImage(image);}
            username.setText(MainFx.connecteduser.getNom() +" "+ MainFx.connecteduser.getPrenom());
            titre_dash.setText("Photo de Profile");
            Node[] nodes = new Node[1];
            try {
                nodes[0] = FXMLLoader.load(getClass().getResource("/upload_image.fxml"));

               /* nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });*/
                pnItems.getChildren().add(nodes[0]);
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else  if (MainFx.m == 4  ){
                pnItems.getChildren().clear();
                if(MainFx.connecteduser.getPhoto() != null){
                Image image = new Image(MainFx.connecteduser.getPhoto());
                adminIMG.setImage(image);}
                username.setText(MainFx.connecteduser.getNom() + " " + MainFx.connecteduser.getPrenom());
            if (SupAdmin) {
                titre_dash.setText("Ajouter Admin");
                Node[] nodes = new Node[1];
                try {
                    nodes[0] = FXMLLoader.load(getClass().getResource("/ajouter_admin.fxml"));

               /* nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });*/
                    pnItems.getChildren().add(nodes[0]);
                } catch (IOException ex) {
                    Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }else{
            showError("Acces impossible pour plus d'information contacter le support .");
            }
        }
        else{
            ObservableList<String> list = FXCollections.observableArrayList("Nom","Prenom");
            comb.setItems(list);
            if(MainFx.connecteduser.getPhoto() != null){
                Image image = new Image(MainFx.connecteduser.getPhoto());
            adminIMG.setImage(image);}
            username.setText(MainFx.connecteduser.getNom() +" "+ MainFx.connecteduser.getPrenom());
            titre_dash.setText("Liste des utilisateurs !");
            try{
                UtilisateurServices Us = new UtilisateurServices();
                List<Utilisateur> users;
                //affichage
                users = Us.afficherListe();

                Node[] nodes = new Node[users.size()];
                for (int i = 0; i < nodes.length; i++) {
                    try {
                        final int j = i;
                        nodes[i] = FXMLLoader.load(getClass().getResource("/Item.fxml"));
                        //give the items some effect
                        Label nlabel = (Label) nodes[i].lookup("#nom");
                        nlabel.setText(users.get(i).getNom() +" "+ users.get(i).getPrenom());
                        Label elabel = (Label) nodes[i].lookup("#email");
                        elabel.setText(users.get(i).getEmail());
                        Label rlabel = (Label) nodes[i].lookup("#role");
                        rlabel.setText(users.get(i).getRole());
                        Button myButton = (Button) nodes[i].lookup("#button");
                        if(Objects.equals(users.get(i).getRole(), "Admin") && !SupAdmin){
                            myButton.setText(" ");
                        }
                        else {
                            if (users.get(i).isIs_Actif() == false) {
                                myButton.setText("Activer");
                                myButton.setStyle("-fx-background-radius: 20px; -fx-border-color: green;");
                            } else {
                                myButton.setText("Bloquer");
                                myButton.setStyle("-fx-background-radius: 20px; -fx-border-color: red;");
                            }
                        }
                        myButton.setOnAction(event -> {
                            Utilisateur user = users.get(j);
                            if(Objects.equals(user.getRole(), "Admin") && !SupAdmin){
                                myButton.setText(" ");
                            }
                            else {
                                if (user.isIs_Actif() == false) {
                                    user.setIs_Actif(true);
                                    myButton.setText("Bloquer");
                                    myButton.setStyle("-fx-background-radius: 20px; -fx-border-color: red;");
                                } else {
                                    user.setIs_Actif(false);
                                    myButton.setText("Activer");
                                    myButton.setStyle("-fx-background-radius: 20px; -fx-border-color: green;");
                                }
                            }
                            Us.updateUser(user,user.getId());
                            // clear and re-populate the container with updated items
                            pnItems.getChildren().clear();
                            try {
                                for (int k = 0; k < nodes.length; k++) {
                                    pnItems.getChildren().add(nodes[k]);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        nodes[i].setOnMouseEntered(event -> {
                            nodes[j].setStyle("-fx-background-color : #064b1c");

                        });
                        nodes[i].setOnMouseExited(event -> {
                            nodes[j].setStyle("-fx-background-color : #177c4d");
                        });
                        pnItems.getChildren().add(nodes[i]);
                    } catch (IOException | NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
            search.textProperty().addListener((observable, oldValue, newValue) -> {
                pnItems.getChildren().clear();
                try {
                    UtilisateurServices Us = new UtilisateurServices();
                    List<Utilisateur> users;
                    if(newValue.isEmpty()) {
                        // If the search string is empty, display the complete list
                        users = Us.afficherListe();
                    } else {
                        // If the search string is not empty, execute the search and display the results
                        users = Us.afficherListeS(newValue);
                    }
                    Node[] nodes = new Node[users.size()];
                    for (int i = 0; i < nodes.length; i++) {
                        try {
                            final int j = i;
                            nodes[i] = FXMLLoader.load(getClass().getResource("/Item.fxml"));
                            //give the items some effect
                            Label nlabel = (Label) nodes[i].lookup("#nom");
                            nlabel.setText(users.get(i).getNom() +" "+ users.get(i).getPrenom());
                            Label elabel = (Label) nodes[i].lookup("#email");
                            elabel.setText(users.get(i).getEmail());
                            Label rlabel = (Label) nodes[i].lookup("#role");
                            rlabel.setText(users.get(i).getRole());
                            Button myButton = (Button) nodes[i].lookup("#button");
                            if(users.get(i).isIs_Actif() == false){
                                myButton.setText("Activer");
                                myButton.setStyle("-fx-background-radius: 20px; -fx-border-color: green;");
                            }
                            else{
                                myButton.setText("Bloquer");
                                myButton.setStyle("-fx-background-radius: 20px; -fx-border-color: red;");
                            }
                            myButton.setOnAction(event -> {
                                Utilisateur user = users.get(j);
                                if (user.isIs_Actif() == false) {
                                    user.setIs_Actif(true);
                                    myButton.setText("Bloquer");
                                    myButton.setStyle("-fx-background-radius: 20px; -fx-border-color: red;");
                                } else {
                                    user.setIs_Actif(false);
                                    myButton.setText("Activer");
                                    myButton.setStyle("-fx-background-radius: 20px; -fx-border-color: green;");
                                }
                                Us.updateUser(user,user.getId());
                                // clear and re-populate the container with updated items
                                pnItems.getChildren().clear();
                                try {
                                    for (int k = 0; k < nodes.length; k++) {
                                        pnItems.getChildren().add(nodes[k]);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                            nodes[i].setOnMouseEntered(event -> {
                                nodes[j].setStyle("-fx-background-color : #064b1c");
                            });
                            nodes[i].setOnMouseExited(event -> {
                                nodes[j].setStyle("-fx-background-color : #177c4d");
                            });
                            pnItems.getChildren().add(nodes[i]);
                        } catch (IOException | NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            comb.valueProperty().addListener((observable,ildValue,newValue) -> {
                pnItems.getChildren().clear();
                try {
                    UtilisateurServices Us = new UtilisateurServices();
                    List<Utilisateur> users = null;
                    if(newValue == null) {
                        // If the search string is empty, display the complete list
                        users = Us.afficherListe();
                    } else if(newValue == "Nom") {
                        // If the search string is not empty, execute the search and display the results
                        users = Us.triNom();
                    }
                    else if(newValue == "Prenom") {
                        // If the search string is not empty, execute the search and display the results
                        users = Us.triPrenom();
                    }
                    Node[] nodes = new Node[users.size()];
                    for (int i = 0; i < nodes.length; i++) {
                        try {
                            final int j = i;
                            nodes[i] = FXMLLoader.load(getClass().getResource("/Item.fxml"));
                            //give the items some effect
                            Label nlabel = (Label) nodes[i].lookup("#nom");
                            nlabel.setText(users.get(i).getNom() +" "+ users.get(i).getPrenom());
                            Label elabel = (Label) nodes[i].lookup("#email");
                            elabel.setText(users.get(i).getEmail());
                            Label rlabel = (Label) nodes[i].lookup("#role");
                            rlabel.setText(users.get(i).getRole());
                            Button myButton = (Button) nodes[i].lookup("#button");
                            if(users.get(i).isIs_Actif() == false){
                                myButton.setText("Activer");
                                myButton.setStyle("-fx-background-radius: 20px; -fx-border-color: green;");
                            }
                            else{
                                myButton.setText("Bloquer");
                                myButton.setStyle("-fx-background-radius: 20px; -fx-border-color: red;");
                            }
               /* myButton.setOnAction(event -> {
                Utilisateur user = users.get(j);
                if (user.isIs_Actif() == false) {
                    user.setIs_Actif(true);
                    myButton.setText("Bloquer");
                    myButton.setStyle("-fx-background-radius: 20px; -fx-border-color: red;");
                } else {
                    user.setIs_Actif(false);
                    myButton.setText("Activer");
                    myButton.setStyle("-fx-background-radius: 20px; -fx-border-color: green;");
                }
                Us.updateUser(user,user.getId());
                // clear and re-populate the container with updated items
                pnItems.getChildren().clear();
                try {
                    for (int k = 0; k < nodes.length; k++) {
                        pnItems.getChildren().add(nodes[k]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                        });*/
                            nodes[i].setOnMouseEntered(event -> {
                                nodes[j].setStyle("-fx-background-color : #064b1c");
                            });
                            nodes[i].setOnMouseExited(event -> {
                                nodes[j].setStyle("-fx-background-color : #177c4d");
                            });
                            pnItems.getChildren().add(nodes[i]);
                        } catch (IOException | NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }


    }

    @FXML
    void btnOrders(ActionEvent event) throws IOException {
        pnItems.getChildren().clear();
        if(MainFx.connecteduser.getPhoto() != null){
            Image image = new Image(MainFx.connecteduser.getPhoto());
        adminIMG.setImage(image);}
        username.setText(MainFx.connecteduser.getNom() +" "+ MainFx.connecteduser.getPrenom());
        titre_dash.setText("Mon Profile");
        Node[] nodes = new Node[1];
        nodes[0] = FXMLLoader.load(getClass().getResource("/ItemP.fxml"));
        //give the items some effect
        Label nlabel = (Label) nodes[0].lookup("#name");
        nlabel.setText(MainFx.connecteduser.getNom() +" "+ MainFx.connecteduser.getPrenom());
        Label elabel = (Label) nodes[0].lookup("#emailp");
        elabel.setText(MainFx.connecteduser.getEmail());
        Label rlabel = (Label) nodes[0].lookup("#rolep");
        rlabel.setText(MainFx.connecteduser.getRole());
        Label tlabel = (Label) nodes[0].lookup("#tel");
        tlabel.setText(Integer.toString(MainFx.connecteduser.getTel()));
        Label alabel = (Label) nodes[0].lookup("#adresse");
        alabel.setText(MainFx.connecteduser.getAddresse());

        if(MainFx.connecteduser.getDate_naiss() != null) {
            Label dlabel = (Label) nodes[0].lookup("#naiss");
            dlabel.setText(MainFx.connecteduser.getDate_naiss().toString());
        }
              /* nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : #064b1c");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : #177c4d");
                });*/
        pnItems.getChildren().add(nodes[0]);


    }

    @FXML
    void btnOverview(ActionEvent event) throws IOException {
        MainFx.m=0;
        Parent root = FXMLLoader.load(getClass().getResource("/testing.fxml"));
        // Get the current scene and set the new scene
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void btnS(ActionEvent event) throws IOException {
        pnItems.getChildren().clear();
        Image image = new Image(MainFx.connecteduser.getPhoto());
        adminIMG.setImage(image);
        username.setText(MainFx.connecteduser.getNom() +" "+ MainFx.connecteduser.getPrenom());
        titre_dash.setText("Mon Profile");
        Node[] nodes = new Node[1];
        nodes[0] = FXMLLoader.load(getClass().getResource("/Stat.fxml"));
        pnItems.getChildren().add(nodes[0]);
    }

    @FXML
    void btnSignout(ActionEvent event) {
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
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
