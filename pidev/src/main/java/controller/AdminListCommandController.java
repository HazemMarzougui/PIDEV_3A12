package controller;

import entities.panier;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.layout.Pane;
import services.commandeService;
import entities.commande;
import services.panierService;
import test.MainFX;

public class AdminListCommandController implements Initializable {


    commandeService c =new commandeService();
    panierService p =new panierService();

    @FXML
    private GridPane commandDetailsContainer;

    @FXML
    public HBox commandModel;

    public HBox getCommandModel() {
        return commandModel;
    }

    @FXML
    private GridPane commandsListContainer;



    @FXML
    void close_commandDetailsModel(MouseEvent event) {

        commandModel.setVisible(false);
        MainFX.GlobalData.PanierProCom.clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        commandModel.setVisible(false);
        afficherCommandeDansGridPane();

    }



    private void afficherCommandeDansGridPane() {
        int column = 0;
        int row = 1;
        try {
            List<commande> commandes = c.getAllCommand();
            for (commande commande : commandes) {
                AdminListCommandItem adminListCommandItem = new AdminListCommandItem();



                    int Column = 0;
                    int Row = 1;
                    // commandModel.setVisible(true); // Vous avez commenté cette ligne
                    commandModel.setVisible(true);

                        List<panier> paniers = MainFX.GlobalData.PanierProCom; // Supposons que getId() retourne l'ID de la commande
                        for (int i = 0; i < paniers.size(); i++) {
                            AdminProduitCommanderController adminProduitCommanderController = new AdminProduitCommanderController();
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AdminProduitCommander.fxml"));
                            fxmlLoader.setController(adminProduitCommanderController);
                            HBox commandee = fxmlLoader.load();
                            adminProduitCommanderController.setDatProduitt(paniers.get(i));
                            if (Column == 1) {
                                Column = 0;
                                ++Row;
                            }
                            commandDetailsContainer.add(commandee, Column++, Row);
                            GridPane.setMargin(commandee, new Insets(0, 5, 5, 5));
                        }



                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AdminListCommandItem.fxml"));
                fxmlLoader.setController(adminListCommandItem);
                HBox commandeitem = fxmlLoader.load();
                adminListCommandItem.setCommandeData(commande);
                if (column == 1) {
                    column = 0;
                    ++row;
                }
                commandsListContainer.add(commandeitem, column++, row);
                GridPane.setMargin(commandeitem, new Insets(0, 5, 5, 5));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Gérer l'exception correctement
        }
    }

}
