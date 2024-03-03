package controller.user;

import entities.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.UtilisateurServices;
import test.MainFX;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class bckintController implements Initializable {

    @FXML
    private ImageView adminIMG1;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnS;

    @FXML
    private ComboBox<String> comb;

    @FXML
    private Pane content_area;

    @FXML
    private VBox pnItems;

    @FXML
    private TextField search;

    @FXML
    private Label titre_dash;

    public boolean SupAdmin;

    {
        SupAdmin = (MainFX.connecteduser.getId() == 86);
    }
    DashboardController d =new DashboardController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println(SupAdmin);
        System.out.println(MainFX.m);

        if (MainFX.m == 1) {

            pnItems.getChildren().clear();
          /*  if (MainFX.connecteduser.getPhoto() != null) {
                Image image = new Image(MainFX.connecteduser.getPhoto());
                d.getAdminIMG().setImage(image);
            }*/
           // d.getUsername().setText(MainFX.connecteduser.getNom() + " " + MainFX.connecteduser.getPrenom());
            titre_dash.setText("Modifier Profile");
            Node[] nodes = new Node[1];
            try {
                nodes[0] = FXMLLoader.load(getClass().getResource("/user/Modifier_back.fxml"));

               /*  nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });*/
                pnItems.getChildren().add(nodes[0]);

            } catch (IOException ex) {
                Logger.getLogger(bckintController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        else if (MainFX.m == 2) {

            pnItems.getChildren().clear();
          /*  Image image = new Image(MainFX.connecteduser.getPhoto());
            d.getAdminIMG().setImage(image);*/
           // d.getUsername().setText(MainFX.connecteduser.getNom() + " " + MainFX.connecteduser.getPrenom());
            titre_dash.setText("Modifier Mot de passe");
            Node[] nodes = new Node[1];
            try {
                nodes[0] = FXMLLoader.load(getClass().getResource("/user/Changerpwd.fxml"));

               /* nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });*/
                pnItems.getChildren().add(nodes[0]);
            } catch (IOException ex) {
                Logger.getLogger(bckintController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else if (MainFX.m == 3) {
            pnItems.getChildren().clear();
          /*  if (MainFX.connecteduser.getPhoto() != null) {
                Image image = new Image(MainFX.connecteduser.getPhoto());
                d.getAdminIMG().setImage(image);
            }*/
          //  d.getUsername().setText(MainFX.connecteduser.getNom() + " " + MainFX.connecteduser.getPrenom());
            titre_dash.setText("Photo de Profile");
            Node[] nodes = new Node[1];
            try {
                nodes[0] = FXMLLoader.load(getClass().getResource("/user/upload_image.fxml"));

               /* nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });*/
                pnItems.getChildren().add(nodes[0]);
            } catch (IOException ex) {
                Logger.getLogger(bckintController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else if (MainFX.m == 4) {
            pnItems.getChildren().clear();
         /*   if (MainFX.connecteduser.getPhoto() != null) {
                Image image = new Image(MainFX.connecteduser.getPhoto());
                d.getAdminIMG().setImage(image);
            }*/
          //  d.getUsername().setText(MainFX.connecteduser.getNom() + " " + MainFX.connecteduser.getPrenom());
            if (SupAdmin) {
                titre_dash.setText("Ajouter Admin");
                Node[] nodes = new Node[1];
                try {
                    nodes[0] = FXMLLoader.load(getClass().getResource("/user/ajouter_admin.fxml"));

               /* nodes[0].setOnMouseEntered(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });
                nodes[0].setOnMouseExited(event -> {
                    nodes[0].setStyle("-fx-background-color : transparent");
                });*/
                    pnItems.getChildren().add(nodes[0]);
                } catch (IOException ex) {
                    Logger.getLogger(bckintController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                showError("Acces impossible pour plus d'information contacter le support .");
            }
        }
        else{
            pnItems.getChildren().clear();

            ObservableList<String> list = FXCollections.observableArrayList("Nom", "Prenom");
            comb.setItems(list);
          /*  if (MainFX.connecteduser.getPhoto() != null) {
                Image image = new Image(MainFX.connecteduser.getPhoto());
                d.getAdminIMG().setImage(image);
            }*/
           /* d.getUsername().setText(MainFX.connecteduser.getNom() + " " + MainFX.connecteduser.getPrenom());
            titre_dash.setText("Liste des utilisateurs !");
*/
            affichage();

            recherche();

            trie();

        }


    }


    @FXML
    void btnOrders(ActionEvent event) throws IOException {

        pnItems.getChildren().clear();
       /* if (MainFX.connecteduser.getPhoto() != null) {
            Image image = new Image(MainFX.connecteduser.getPhoto());
            d.getAdminIMG().setImage(image);
        }*/
      /*  d.getUsername().setText(MainFX.connecteduser.getNom() + " " + MainFX.connecteduser.getPrenom());*/
        titre_dash.setText("Mon Profile");
        Node[] nodes = new Node[1];
        nodes[0] = FXMLLoader.load(getClass().getResource("/user/ItemP.fxml"));
        //give the items some effect
        Label nlabel = (Label) nodes[0].lookup("#name");
        nlabel.setText(MainFX.connecteduser.getNom() + " " + MainFX.connecteduser.getPrenom());
        Label elabel = (Label) nodes[0].lookup("#emailp");
        elabel.setText(MainFX.connecteduser.getEmail());
        Label rlabel = (Label) nodes[0].lookup("#rolep");
        rlabel.setText(MainFX.connecteduser.getRole());
        Label tlabel = (Label) nodes[0].lookup("#tel");
        tlabel.setText(Integer.toString(MainFX.connecteduser.getTel()));
        Label alabel = (Label) nodes[0].lookup("#adresse");
        alabel.setText(MainFX.connecteduser.getAddresse());

        if (MainFX.connecteduser.getDate_naiss() != null) {
            Label dlabel = (Label) nodes[0].lookup("#naiss");
            dlabel.setText(MainFX.connecteduser.getDate_naiss().toString());
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
        //MainFX.m=0;
        Parent fxml = FXMLLoader.load(getClass().getResource("/user/bckint.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }

    @FXML
    void btnS(ActionEvent event) throws IOException {
        pnItems.getChildren().clear();
      //  Image image = new Image(MainFX.connecteduser.getPhoto());
   //    d.getAdminIMG().setImage(image);
    //    d.getUsername().setText(MainFX.connecteduser.getNom() + " " + MainFX.connecteduser.getPrenom());
        titre_dash.setText("Mon Profile");
        Node[] nodes = new Node[1];
        nodes[0] = FXMLLoader.load(getClass().getResource("/user/Stat.fxml"));
        pnItems.getChildren().add(nodes[0]);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void recherche() {
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            pnItems.getChildren().clear();
            try {
                UtilisateurServices Us = new UtilisateurServices();
                List<Utilisateur> users;
                if (newValue.isEmpty()) {
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
                        nodes[i] = FXMLLoader.load(getClass().getResource("/user/Item.fxml"));
                        //give the items some effect
                        Label nlabel = (Label) nodes[i].lookup("#nom");
                        nlabel.setText(users.get(i).getNom() + " " + users.get(i).getPrenom());
                        Label elabel = (Label) nodes[i].lookup("#email");
                        elabel.setText(users.get(i).getEmail());
                        Label rlabel = (Label) nodes[i].lookup("#role");
                        rlabel.setText(users.get(i).getRole());
                        Button myButton = (Button) nodes[i].lookup("#button");
                        if (users.get(i).isIs_Actif() == false) {
                            myButton.setText("Activer");
                            myButton.setStyle("-fx-background-radius: 20px; -fx-border-color: green;");
                        } else {
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
                            Us.updateUser(user, user.getId());
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
                Logger.getLogger(bckintController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void trie() {
        comb.valueProperty().addListener((observable, ildValue, newValue) -> {
            pnItems.getChildren().clear();
            try {
                UtilisateurServices Us = new UtilisateurServices();
                List<Utilisateur> users = null;
                if (newValue == null) {
                    // If the search string is empty, display the complete list
                    users = Us.afficherListe();
                } else if (newValue == "Nom") {
                    // If the search string is not empty, execute the search and display the results
                    users = Us.triNom();
                } else if (newValue == "Prenom") {
                    // If the search string is not empty, execute the search and display the results
                    users = Us.triPrenom();
                }
                Node[] nodes = new Node[users.size()];
                for (int i = 0; i < nodes.length; i++) {
                    try {
                        final int j = i;
                        nodes[i] = FXMLLoader.load(getClass().getResource("/user/Item.fxml"));
                        //give the items some effect
                        Label nlabel = (Label) nodes[i].lookup("#nom");
                        nlabel.setText(users.get(i).getNom() + " " + users.get(i).getPrenom());
                        Label elabel = (Label) nodes[i].lookup("#email");
                        elabel.setText(users.get(i).getEmail());
                        Label rlabel = (Label) nodes[i].lookup("#role");
                        rlabel.setText(users.get(i).getRole());
                        Button myButton = (Button) nodes[i].lookup("#button");
                        if (users.get(i).isIs_Actif() == false) {
                            myButton.setText("Activer");
                            myButton.setStyle("-fx-background-radius: 20px; -fx-border-color: green;");
                        } else {
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
                Logger.getLogger(bckintController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void affichage() {
        try {
            UtilisateurServices Us = new UtilisateurServices();
            List<Utilisateur> users;
            //affichage
            users = Us.afficherListe();

            Node[] nodes = new Node[users.size()];
            for (int i = 0; i < nodes.length; i++) {
                try {
                    final int j = i;
                    nodes[i] = FXMLLoader.load(getClass().getResource("/user/Item.fxml"));
                    //give the items some effect
                    Label nlabel = (Label) nodes[i].lookup("#nom");
                    nlabel.setText(users.get(i).getNom() + " " + users.get(i).getPrenom());
                    Label elabel = (Label) nodes[i].lookup("#email");
                    elabel.setText(users.get(i).getEmail());
                    Label rlabel = (Label) nodes[i].lookup("#role");
                    rlabel.setText(users.get(i).getRole());
                    Button myButton = (Button) nodes[i].lookup("#button");
                    if (Objects.equals(users.get(i).getRole(), "Admin") && !SupAdmin) {
                        myButton.setText(" ");
                    } else {
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
                        if (Objects.equals(user.getRole(), "Admin") && !SupAdmin) {
                            myButton.setText(" ");
                        } else {
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
                        Us.updateUser(user, user.getId());
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
            Logger.getLogger(bckintController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
