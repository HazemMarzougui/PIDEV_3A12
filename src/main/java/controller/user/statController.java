package controller.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import services.UtilisateurServices;

import java.net.URL;
import java.util.ResourceBundle;

public class statController implements Initializable {

    @FXML
    private ImageView adminIMG;
    @FXML
    private Label username;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnS;
    @FXML
    private Button btnSignout;
    @FXML
    private ImageView adminIMG1;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TextField search;
    @FXML
    private Label titre_dash;
    @FXML
    private Canvas pnItems;
    @FXML
    private Canvas pnItems1;

    private void drawPieChart(int activeUsers, int blockedUsers) {
        // Get the GraphicsContext of the canvas
        GraphicsContext gc = pnItems.getGraphicsContext2D();

        // Set the size of the canvas
        pnItems.setWidth(500);
        pnItems.setHeight(300);

        // Define the colors to use for the chart
        Color[] colors = { Color.GREEN, Color.RED, Color.ORANGE };

        // Calculate the percentage of active, blocked, and pending users
        int total = activeUsers + blockedUsers ;
        double activePercentage = (double) activeUsers / total;
        double blockedPercentage = (double) blockedUsers / total;

        // Define the angles for the chart
        double activeAngle = activePercentage * 360;
        double blockedAngle = blockedPercentage * 360;

        // Draw the pie chart
        double startX = 300;
        double startY = 150;
        double radius = 100;
        double currentAngle = 0;
        for (int i = 0; i < 3; i++) {
            double angle = 0;
            switch (i) {
                case 0:
                    angle = activeAngle;
                    gc.setFill(colors[i]);
                    gc.fillText("Activés: " + activeUsers, startX + radius - 20, startY - radius + 20);
                    break;
                case 1:
                    angle = blockedAngle;
                    gc.setFill(colors[i]);
                    gc.fillText("Bloqués: " + blockedUsers, startX - radius - 50, startY + radius - 10);
                    break;
            }
            gc.fillArc(startX - radius, startY - radius, radius * 2, radius * 2, currentAngle, angle, ArcType.ROUND);
            currentAngle += angle;
        }
    }
    private void drawPieChart1(int activeUsers, int blockedUsers) {
        // Get the GraphicsContext of the canvas
        GraphicsContext gc = pnItems1.getGraphicsContext2D();

        // Set the size of the canvas
        pnItems1.setWidth(500);
        pnItems1.setHeight(300);

        // Define the colors to use for the chart
        Color[] colors = { Color.GREEN, Color.RED};

        // Calculate the percentage of active, blocked, and pending users
        int total = activeUsers + blockedUsers ;
        double activePercentage = (double) activeUsers / total;
        double blockedPercentage = (double) blockedUsers / total;

        // Define the angles for the chart
        double activeAngle = activePercentage * 360;
        double blockedAngle = blockedPercentage * 360;

        // Draw the pie chart
        double startX = 300;
        double startY = 150;
        double radius = 100;
        double currentAngle = 0;
        for (int i = 0; i < 3; i++) {
            double angle = 0;
            switch (i) {
                case 0:
                    angle = activeAngle;
                    gc.setFill(colors[i]);
                    gc.fillText("Admin: " + activeUsers, startX + radius + 10, startY - radius + 20);
                    break;
                case 1:
                    angle = blockedAngle;
                    gc.setFill(colors[i]);
                    gc.fillText("Client : " + blockedUsers, startX - radius - 80, startY + radius - 10);
                    break;

            }
            gc.fillArc(startX - radius, startY - radius, radius * 2, radius * 2, currentAngle, angle, ArcType.ROUND);
            currentAngle += angle;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UtilisateurServices Us = new UtilisateurServices();
        drawPieChart(Us.getUserCountAc(), Us.getUserCountB());
        drawPieChart1(Us.getUserCountA(),Us.getUserCountC());
    }

    @FXML
    private void btnOverview(ActionEvent event) {
    }

    @FXML
    private void btnOrders(ActionEvent event) {
    }

    @FXML
    private void btnS(ActionEvent event) {
    }

    @FXML
    private void btnSignout(ActionEvent event) {
    }

}
