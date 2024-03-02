package Controller;

import Entities.Conseil;
import Entities.Produit;
import Services.CategorieService;
import Services.ConseilService;
import Services.ProduitService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemDetailsCardController implements Initializable {

    ConseilService conseilService = new ConseilService();
    ProduitService ps =new ProduitService();
    CategorieService cs = new CategorieService();


    private FrontController frontController ;

    public void setFrontController(FrontController frontController)
    {
        this.frontController = frontController;
    }



    @FXML
    private Button bnt_play;



    @FXML
    private VBox cartVBox;


    @FXML
    private GridPane commentsListContainer;

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
    private Slider slider;

    @FXML
    private ImageView star1;

    @FXML
    private ImageView star2;

    @FXML
    private ImageView star3;

    @FXML
    private ImageView star4;

    @FXML
    private ImageView star5;

    @FXML
    private ImageView gif;

    @FXML
    private Text total_reviews;


    @FXML
    private Text total_reviewss;

    @FXML
    private Button btn_rev;

    @FXML
    private ScrollPane scrollPaneReview;

    @FXML
    private Text averageReview;

    @FXML
    private Text average_rev;

    @FXML
    private Text type_conseil;

    @FXML
    private Text video;

    public Text getVideo()
    {
        return video ;
    }

    @FXML
    private MediaView videoo;


    private boolean isPlayed = false ;

    private Media media;
    private MediaPlayer mediaPlayer;



    private ItemDetailsController parentController;

    public void setParentController(ItemDetailsController parentController) {
        this.parentController = parentController;
    }



    public void setConseil(Conseil conseil) throws SQLException {
        System.out.println("Setting Conseil in ItemDetailsCardController: " + conseil);

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
    }


    @FXML
    void addReview(ActionEvent event) {

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

// Retrieve the text from the TextField
        String videoPath = getVideo().getText();  // get the path of video setted in field

        System.out.println("Video Path: " + videoPath);
        if (videoPath != null && !videoPath.isEmpty()) {
            if (mediaPlayer == null || !isPlayed) {
                // If mediaPlayer is not initialized or video is not played, start playing
                media = new Media(new File(videoPath).toURI().toString());   // convert this path to media
                mediaPlayer = new MediaPlayer(media);   // load this media in mediaplayer
                videoo.setMediaPlayer(mediaPlayer);  // set this mediaplayer in mediaview
                mediaPlayer.play();
                isPlayed = true;
                System.out.println("MediaPlayer Status: " + mediaPlayer.getStatus());
                Image image = new Image(String.valueOf(getClass().getResource("/uploads/pause.png")));
                icon_player.setImage(image);
                //mediaPlayer.play();
                isPlayed = true;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        video.setVisible(true);
    }

}
