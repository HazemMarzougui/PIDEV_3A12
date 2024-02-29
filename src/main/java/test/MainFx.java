package test;

import entities.Utilisateur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.UtilisateurServices;
import utils.MyDB;

import java.io.IOException;


public class MainFx extends Application {
    private Parent fxml;
    private VBox vbox;
    public static int page=0;
    public static Utilisateur connecteduser = new Utilisateur();

    public static int m = 0;
    public static int idd = 0;

    @Override

    public void start(Stage primaryStage) throws IOException {
        MyDB db = MyDB.getInstance();
        System.out.println(db);
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
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
