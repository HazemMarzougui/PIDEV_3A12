package Controller;

import Entities.Conseil;
import Entities.Produit;
import Entities.Review;
import Services.CategorieService;
import Services.ConseilService;
import Services.ProduitService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;




public class ItemCardController extends Node implements Initializable{

    ConseilService conseilService = new ConseilService();
    CategorieService cs = new CategorieService();
    ProduitService ps = new ProduitService();


    @FXML
    private Rating valiue_stars;
    @FXML
    private HBox HBoxx;
    @FXML
    private Button btn_consulter;

    @FXML
    private Text totalReviews;

    @FXML
    private Text description;




    @FXML
    private Text id_typeC;

    @FXML
    private Label id_produit;

    @FXML
    private Label video;

    @FXML
    private Text nom_conseil;
    @FXML
    private Button OneConseil;

    private Conseil conseil;
    public Conseil getConseil()
    {
        return conseil ;
    }

    private FrontController frontController ;

    public void setFrontController(FrontController frontController) {
        this.frontController = frontController;
    }

    private Consumer<Conseil> onConsultClickListener;

    public void setOnConsultClickListener(Consumer<Conseil> onConsultClickListener) {
        this.onConsultClickListener = onConsultClickListener;
    }


    public void SetData(Conseil conseil) throws SQLException {
        List<Review> reviewList = conseilService.getAllComments(conseil.getId_conseil());
        if (!reviewList.isEmpty())
        {
            Review review = reviewList.get(0);
            totalConseilByStars(conseil, review);
        }
        getReviewsNumberByConseil(conseil);

        nom_conseil.setText("" + conseil.getNom_conseil());
        video.setText("" + conseil.getVideo());
        description.setText("" + conseil.getDescription());
        int idProduit = conseil.getId_produit();
        Produit ProduitName = ps.getProduitsName(idProduit);
        id_produit.setText(ProduitName.getNom_produit());
        int idCategory = conseil.getId_typeC();
        String categoryName = cs.getCategoriesName(idCategory);
        id_typeC.setText(categoryName);
        OneConseil.setOnMouseClicked(event -> {
            System.out.println(" " + conseil.getId_conseil());

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/IteemDetails.fxml"));
                Parent root = fxmlLoader.load();
                ItemDetailsController itemDetailsController = fxmlLoader.getController();
                Conseil conseil1 = conseilService.getconseilByID(conseil.getId_conseil());
                itemDetailsController.setConseil(conseil1); // Use the new method

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Consulter Conseil");
                stage.show();
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        });
        disableRating();
    }

   @FXML
    void consulterOneConseil(ActionEvent event) throws IOException {
        /*
        if (onConsultClickListener != null) {
            onConsultClickListener.accept(conseil);
        }
         */
    }

    private void getReviewsNumberByConseil(Conseil conseil) {
        int reviewNumber = conseilService.getTotalConseilsReviews(conseil.getId_conseil());
        totalReviews.setText(String.valueOf(reviewNumber));
    }

    private void totalConseilByStars(Conseil conseil, Review review) {
        // Assuming totalStarsAverage is a Rating control
        int starCount = conseilService.getTotalConseilReviewsByStar(conseil.getId_conseil(), review.getValue());

        // Ensure the star count is within the valid range (0 to 5)
        starCount = Math.max(0, Math.min(5, starCount));

        valiue_stars.setRating(starCount);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Conseil defaultConseil = new Conseil();
        Review defaultReview = new Review();
        totalConseilByStars(defaultConseil,defaultReview);
        video.setVisible(false);
        id_produit.setVisible(false);
    }


    public void disableRating() {
        valiue_stars.setDisable(true);
    }

}
