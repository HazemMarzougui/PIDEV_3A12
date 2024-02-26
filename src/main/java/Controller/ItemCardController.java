package Controller;

import Entities.Conseil;
import Services.CategorieService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemCardController{

    CategorieService cs = new CategorieService();

    @FXML
    private HBox HBoxx;
    @FXML
    private Button btn_consulter;

    @FXML
    private Text description;

    @FXML
    private Text id_typeC;

    @FXML
    private Text nom_conseil;

    private Conseil conseil;


    public void SetData(Conseil conseil) throws SQLException {
        nom_conseil.setText("" + conseil.getNom_conseil());
        description.setText("" + conseil.getDescription());


        int idCategory = conseil.getId_typeC();
        String categoryName = cs.getCategoriesName(idCategory);
        id_typeC.setText(categoryName);
    }



    @FXML
    void consulterOneConseil(ActionEvent event) {

    }




}
