package Controller;

import Entities.Conseil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayVideoController implements Initializable {

    @FXML
    private MediaView mv_video;

    private Media media;
    private MediaPlayer mediaPlayer;

    private boolean isPlayed = false ;

    private UpdateConseilController updateConseilController ;




    public void setUpdateConseilController(UpdateConseilController updateConseilController) {
        this.updateConseilController = updateConseilController;
    }


    @FXML
    void Lire_video(ActionEvent event) {
        // Retrieve the text from the TextField
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
    void pause_video(ActionEvent event) {
        if (mediaPlayer != null && isPlayed) {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
            } else if (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
                mediaPlayer.play();
            }
        }
    }

    @FXML
    void togglePlayback(ActionEvent event) {
        // Retrieve the text from the TextField
        String videoPath = updateConseilController.getTf_video().getText();

        if (videoPath != null && !videoPath.isEmpty()) {
            if (mediaPlayer == null || !isPlayed) {
                // If mediaPlayer is not initialized or video is not played, start playing
                media = new Media(new File(videoPath).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mv_video.setMediaPlayer(mediaPlayer);
                mediaPlayer.play();
                isPlayed = true;
            } else {
                // If mediaPlayer is already initialized and video is played, toggle between pause and play
                if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    mediaPlayer.pause();
                } else if (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
                    mediaPlayer.play();
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
    void stop_video(ActionEvent event) {
        if (mediaPlayer != null && isPlayed) {
            mediaPlayer.stop();
            isPlayed = false;

            // Clear the media player from the MediaView
            mv_video.setMediaPlayer(null);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
