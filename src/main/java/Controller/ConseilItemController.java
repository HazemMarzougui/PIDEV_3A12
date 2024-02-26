package Controller;

import Entities.Conseil;
import Services.CategorieService;
import Services.ConseilService;
import Services.ProduitService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConseilItemController implements Initializable {


    private Conseil conseil ;
    CategorieService cs = new CategorieService();
    ProduitService ps = new ProduitService();
    private boolean selected = false;

    public Conseil getConseil()
    {
        return conseil ;
    }

    @FXML
    private HBox HBoxx;

    public HBox getHBoxx() {
        return HBoxx;
    }

    @FXML
    private Label description;

    @FXML
    private Label id_conseil;

    @FXML
    private Label id_produit;

    @FXML
    private Label id_typeC;

    @FXML
    private Label nom_conseil;

    @FXML
    private Label video;

    private ConseilController parentController;

    public void setParentController(ConseilController parentController) {
        this.parentController = parentController;
    }

    private Runnable onUpdateClickListener;


    public void setOnUpdateClickListener(Runnable onUpdateClickListener) {
        this.onUpdateClickListener = onUpdateClickListener;
    }
    private Runnable onDeleteClickListener;

    public void setOnDeleteClickListener(Runnable onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    public void SetData(Conseil conseil) throws SQLException {
        id_conseil.setText("" + conseil.getId_conseil());
        nom_conseil.setText("" + conseil.getNom_conseil());
        video.setText("" + conseil.getVideo());
        description.setText("" + conseil.getDescription());
        //id_produit.setText("" + conseil.getId_produit());
        int idProduit = conseil.getId_produit();
        String ProduitName = ps.getProduitsName(idProduit);
        id_produit.setText(ProduitName);

        int idCategory = conseil.getId_typeC();
        String categoryName = cs.getCategoriesName(idCategory);
        id_typeC.setText(categoryName);
    }

    @FXML
    void delete_conseil(ActionEvent event)
    {
        if (onDeleteClickListener != null) {
            onDeleteClickListener.run();
        }
    }

    @FXML
    void update_conseil(ActionEvent event) throws IOException {
        if (onUpdateClickListener != null) {
            onUpdateClickListener.run();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

