package Controller;

import Entities.Conseil;
import Entities.Produit;
import Entities.Review;
import Services.CategorieService;
import Services.ConseilService;
import Services.ProduitService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Rating;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemDetailsController implements Initializable {

    ConseilService conseilService = new ConseilService();
    ProduitService ps = new ProduitService();
    CategorieService cs = new CategorieService();

    @FXML
    private Button bnt_play;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_rev;

    @FXML
    private VBox cartVBox;

    @FXML
    private GridPane commentsListContainer;

    @FXML
    private VBox content_vbox;

    @FXML
    private HBox dark_hbox_review;

    @FXML
    private GridPane gridPane;

    @FXML
    private Pane content_area;

    @FXML
    private Text description;

    @FXML
    private ImageView icon_player;

    @FXML
    private Label lbl_duration;

    @FXML
    private Text nom_conseil;

    @FXML
    private Text prdt_recommande;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ScrollPane scrollPaneReview;

    @FXML
    private HBox content_review;

    @FXML
    private Slider slider;

    @FXML
    private TextArea tf_comments;

    @FXML
    private Text type_conseil;

    @FXML
    private Rating valiue_stars;

    @FXML
    private Text video;

    @FXML
    private MediaView videoo;

    public Text getVideo()
    {
        return video ;
    }

    @FXML
    private TextField tf_title;


    @FXML
    private AnchorPane listCommeeents;

    @FXML
    private Text total_reviews;

    @FXML
    private Text average_rev;

    @FXML
    private Text averageReview;


    @FXML
    private ImageView image_video;

    @FXML
    private Rating totalStarsAverage;

    @FXML
    private Text total_reviewss;

    private boolean isPlayed = false ;

    private Media media;
    private MediaPlayer mediaPlayer;

    public void setConseil(Conseil conseil) throws SQLException, IOException {


        // nom_conseil.setText(conseil.getNom_conseil());
        System.out.println("Nom_conseil: " + conseil.getNom_conseil());
        nom_conseil.setText(conseil.getNom_conseil());
        video.setText(conseil.getVideo());
        description.setText(conseil.getDescription());

        int idProduit = conseil.getId_produit();
        Produit produit = ps.getProduitsName(idProduit);
        prdt_recommande.setText(produit.getNom_produit());

        int idCategory = conseil.getId_typeC();
        String categoryName = cs.getCategoriesName(idCategory);
        type_conseil.setText(categoryName);




        List<Review> reviewList = conseilService.getAllComments(conseil.getId_conseil());

        if (!reviewList.isEmpty()) {
            averageReview.setText(String.format("%.1f", conseilService.getAverageRatingForConseil(conseil.getId_conseil())));
            average_rev.setText(String.format("%.1f", conseilService.getAverageRatingForConseil(conseil.getId_conseil())));

            Review review = reviewList.get(0);
            System.out.println("Setting Conseil in ItemDetailsCardController: " + conseil);

            getReviewsNumberByConseil(conseil);
            countReviewByConseil(conseil);
            totalConseilByStars(conseil, review);
            System.out.println("DEBUG : " + review.getValue());
            gridReview(conseil);
        } else {
            // Handle the case when there are no reviews
            averageReview.setText(String.valueOf(0));
            average_rev.setText(String.valueOf(0));
            // You may also want to reset other UI components or display a message accordingly
        }
    }

    private List<CommentItemController> listCommentsItem = new ArrayList<>();

    private void gridReview(Conseil conseil) throws SQLException, IOException {
        List<Review> reviewList = conseilService.getAllComments(conseil.getId_conseil());
        // Clear existing items in the container
        GridPane commentsListContainer = new GridPane();
        for (int i = 0; i < reviewList.size(); i++) {
            Review review = reviewList.get(i);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/CommentsItem.fxml"));
                HBox commentsItem = fxmlLoader.load();
                CommentItemController itemComment = fxmlLoader.getController();
                itemComment.SetDataComment(review);
                commentsListContainer.add(commentsItem, 0, i);
                listCommentsItem.add(itemComment);
                ScrollPane scrollPane = (ScrollPane) content_review.getChildren().get(0); // assuming the ScrollPane is the first child
                scrollPane.setContent(commentsListContainer);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Conseil defaultConseil = new Conseil();
        Review defaultReview = new Review();
        try {
            // Load reviews and update the UI
            gridReview(defaultConseil);
            countReviewByConseil(defaultConseil);
            totalConseilByStars(defaultConseil,defaultReview);

            // Get and display the total number of reviews
            getReviewsNumberByConseil(defaultConseil);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        dark_hbox_review.setVisible(false);

        Image image = new Image(String.valueOf(getClass().getResource("/uploads/jouerr.png")));
        icon_player.setImage(image);

    }


    private double calculateAverageRating(List<Review> reviewList) {
        double totalRating = 0.0;
        for (Review review : reviewList) {
            totalRating += review.getValue();
        }
        return totalRating / reviewList.size();
    }

    private void getReviewsNumberByConseil(Conseil conseil) {
        int reviewNumber = conseilService.getTotalConseilsReviews(conseil.getId_conseil());
        total_reviews.setText(String.valueOf(reviewNumber));
    }

    private void countReviewByConseil(Conseil conseil) {
        int reviewNumber = conseilService.getTotalConseilsReviews(conseil.getId_conseil());
        total_reviewss.setText(String.valueOf(reviewNumber));
    }

    private void totalConseilByStars(Conseil conseil, Review review) {
        // Assuming totalStarsAverage is a Rating control
        int starCount = conseilService.getTotalConseilReviewsByStar(conseil.getId_conseil(), review.getValue());

        // Ensure the star count is within the valid range (0 to 5)
        starCount = Math.max(0, Math.min(5, starCount));

        totalStarsAverage.setRating(starCount);
    }





    @FXML
    void addReview(ActionEvent event) {

    }

    @FXML
    void add_revieww(ActionEvent event) {

    }




    @FXML
    void annuler(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }



    @FXML
    void partageFacebook(MouseEvent event) {

    }



    @FXML
    void slidePressed(MouseEvent event) {
        mediaPlayer.seek(Duration.seconds(slider.getValue()));
    }

    @FXML
    void togglePlayBack(ActionEvent event) {

        String videoPath = getVideo().getText();

        if (videoPath != null && !videoPath.isEmpty()) {
            if (mediaPlayer == null || !isPlayed) {
                media = new Media(new File(videoPath).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                videoo.setMediaPlayer(mediaPlayer);
                mediaPlayer.play();
                isPlayed = true;
                Image image = new Image(String.valueOf(getClass().getResource("/uploads/pause.png")));
                icon_player.setImage(image);
                mediaPlayer.currentTimeProperty().addListener((((observable, oldValue, newValue) -> {
                    slider.setValue(newValue.toSeconds());
                })));

                mediaPlayer.setOnReady(() ->{
                    Duration totalDuration = media.getDuration();
                    slider.setValue(totalDuration.toSeconds());
                });



            } else {
                if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    Image image = new Image(String.valueOf(getClass().getResource("/uploads/jouerr.png")));
                    image_video.setVisible(false);
                    icon_player.setImage(image);
                    mediaPlayer.pause();

                    mediaPlayer.currentTimeProperty().addListener((((observable, oldValue, newValue) -> {
                        slider.setValue(newValue.toSeconds());
                        lbl_duration.setText("Duration: " + formatDuration(newValue, media.getDuration()));
                    })));

                    mediaPlayer.setOnReady(() ->{
                        Duration totalDuration = media.getDuration();
                        slider.setValue(totalDuration.toSeconds());
                        lbl_duration.setText("Duration: " + formatDuration(totalDuration, totalDuration));
                    });


                } else if (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
                    Image image = new Image(String.valueOf(getClass().getResource("/uploads/pause.png")));
                    Image image1 = new Image(String.valueOf(getClass().getResource("/uploads/Logo.png")));
                    image_video.setImage(image1);
                    image_video.setVisible(true);
                    icon_player.setImage(image);
                    mediaPlayer.play();
                    mediaPlayer.currentTimeProperty().addListener((((observable, oldValue, newValue) -> {
                        slider.setValue(newValue.toSeconds());
                        lbl_duration.setText("Duration: " + formatDuration(newValue, media.getDuration()));
                    })));

                    mediaPlayer.setOnReady(() ->{
                        Duration totalDuration = media.getDuration();
                        slider.setValue(totalDuration.toSeconds());
                        lbl_duration.setText("Duration: " + formatDuration(totalDuration, totalDuration));
                    });
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("No video file selected");
            alert.showAndWait();
        }
    }

    private String formatDuration(Duration duration, Duration totalDuration) {
        long seconds = (long) duration.toSeconds();
        long totalSeconds = (long) totalDuration.toSeconds();

        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return String.format("%02d:%02d:%02d / %02d:%02d:%02d", hours, minutes, seconds,
                totalSeconds / 3600, (totalSeconds % 3600) / 60, totalSeconds % 60);
    }

}