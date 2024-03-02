package Controller;

import Entities.Conseil;
import Entities.Produit;
import Entities.Review;
import Services.ConseilService;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.Rating;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentItemController {

    private Review review;

    ConseilService conseilService = new ConseilService();



    @FXML
    private VBox vboxComment;

    public Review getReview()
    {
        return review;
    }

    @FXML
    private Text comment;

    @FXML
    private Text titlee;

    @FXML
    private HBox valueStar;

    @FXML
    private Text datecreation;

    @FXML
    private Rating valueStars;


    public void SetDataComment(Review review) {
        titlee.setText(review.getTitle());
        comment.setText(review.getComments());
        valueStars.setRating(review.getValue()); // Use setRating to set the rating value
        datecreation.setText(review.getDateCreation());
    }

}
