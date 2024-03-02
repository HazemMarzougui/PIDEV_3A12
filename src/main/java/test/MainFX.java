package test;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/event/client.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/event/client.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/admin/event/admin_event.fxml"));
        Parent root= fxmlLoader.load();
        primaryStage.setScene(new Scene (root,1090,656));
        primaryStage.setTitle("Evenement");
        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
