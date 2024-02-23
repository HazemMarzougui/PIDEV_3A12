package test;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class MainFX extends Application {
    public static class GlobalData {
        public static List<Integer> produits = new ArrayList<>();
        public static List<Float> prix = new ArrayList<>();
        public static List<Integer> quantites = new ArrayList<>();
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/front office.fxml"));

        Parent root= fxmlLoader.load();
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gerer commande");
        stage.show();
    }





    public static void main(String[] args) {
        launch(args);
    }

}
