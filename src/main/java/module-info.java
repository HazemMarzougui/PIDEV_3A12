module com.example.gestionproduit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires twilio;


    opens gui to javafx.fxml;
    opens entities to javafx.base;
    exports gui;
}