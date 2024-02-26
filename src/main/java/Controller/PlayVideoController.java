package Controller;

import Entities.Conseil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PlayVideoController  {

    @FXML
    private Label lbl_duration;

    @FXML
    private Slider slider;

    @FXML
    private MediaView mv_video;

    private Media media;
    private MediaPlayer mediaPlayer;


    @FXML
    private ImageView icon_player;

    private boolean isPlayed = false ;

    private UpdateConseilController updateConseilController ;

    public void setUpdateConseilController(UpdateConseilController updateConseilController) {
        this.updateConseilController = updateConseilController;
    }

    @FXML
    void Lire_video(ActionEvent event) {
        String videoPath = updateConseilController.getTf_video().getText();
        if (videoPath != null && !videoPath.isEmpty()) {
            media = new Media(new File(videoPath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mv_video.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
            isPlayed = true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("No video file selected");
            alert.showAndWait();
        }

    }

    @FXML
    void togglePlayback(ActionEvent event) {
        // Retrieve the text from the TextField
        String videoPath = updateConseilController.getTf_video().getText();  // get the path of video setted in field

        if (videoPath != null && !videoPath.isEmpty()) {
            if (mediaPlayer == null || !isPlayed) {
                // If mediaPlayer is not initialized or video is not played, start playing
                media = new Media(new File(videoPath).toURI().toString());   // convert this path to media
                mediaPlayer = new MediaPlayer(media);   // load this media in mediaplayer
                mv_video.setMediaPlayer(mediaPlayer);  // set this mediaplayer in mediaview
                Image image = new Image(String.valueOf(getClass().getResource("/uploads/pause.png")));
                icon_player.setImage(image);
                //mediaPlayer.play();
                //isPlayed = true;
                mediaPlayer.currentTimeProperty().addListener((((observable, oldValue, newValue) -> {
                    slider.setValue(newValue.toSeconds());
                })));

                mediaPlayer.setOnReady(() ->{
                    Duration totalDuration = media.getDuration();
                    slider.setValue(totalDuration.toSeconds());
                });


            } else {
                // If mediaPlayer is already initialized and video is played, toggle between pause and play
                if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    Image image = new Image(String.valueOf(getClass().getResource("/uploads/jouerr.png")));
                    icon_player.setImage(image);
                    mediaPlayer.pause();
                    mediaPlayer.currentTimeProperty().addListener((((observable, oldValue, newValue) -> {
                        slider.setValue(newValue.toSeconds());
                        lbl_duration.setText("Duration : " + slider.getValue() + " / " + media.getDuration().toString());
                    })));

                    mediaPlayer.setOnReady(() ->{
                        Duration totalDuration = media.getDuration();
                        slider.setValue(totalDuration.toSeconds());
                        lbl_duration.setText("Duration : 00 / " + totalDuration);
                    });

                } else if (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
                    Image image = new Image(String.valueOf(getClass().getResource("/uploads/pause.png")));
                    icon_player.setImage(image);
                    mediaPlayer.play();
                    mediaPlayer.currentTimeProperty().addListener((((observable, oldValue, newValue) -> {
                        slider.setValue(newValue.toSeconds());
                        lbl_duration.setText("Duration : " + slider.getValue() + " / " + media.getDuration().toString());
                    })));

                    mediaPlayer.setOnReady(() ->{
                        Duration totalDuration = media.getDuration();
                        slider.setValue(totalDuration.toSeconds());
                        lbl_duration.setText("Duration : " + slider.getValue() + " / " + media.getDuration().toString());
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

    @FXML
    private void sliderPressed(MouseEvent event) {
        mediaPlayer.seek(Duration.seconds(slider.getValue()));
    }


}
