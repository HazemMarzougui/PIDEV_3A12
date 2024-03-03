package test;

import entities.Utilisateur;
import entities.commande;
import entities.panier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.MyDB;

import java.util.ArrayList;
import java.util.List;


public class MainFX extends Application {

    //////userr
    private Parent fxml;
    private VBox vbox;
    public static int page=0;
    public static Utilisateur connecteduser = new Utilisateur();

    public static int m = 0;
    public static int idd = 0;


    public static class GlobalData {
        public static List<Integer> produits = new ArrayList<>();
        public static List<Float> prix = new ArrayList<>();
        public static List<Integer> quantites = new ArrayList<>();
        public static List<panier> PanierProCom = new ArrayList<>();
        public static List<panier> CommandeC = new ArrayList<>();

        public static int globalLong = 0;
        public static commande idc ;



    }
    @Override
    public void start(Stage primaryStage) throws Exception {
          /* FXMLLoader= new FXMLLoader(getClass().getResource("/dashboard.fxml"));
           // FXMLLoader= new FXMLLoader(getClass().getResource("/front office.fxml"));

        Parent root= fxmlLoader.load();
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gerer commande");
        stage.show();
*/
        MyDB db = MyDB.getInstance();
        System.out.println(db);
        Parent root = FXMLLoader.load(getClass().getResource("/user/Login.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }





    public static void main(String[] args) {
        launch(args);
    }

}
