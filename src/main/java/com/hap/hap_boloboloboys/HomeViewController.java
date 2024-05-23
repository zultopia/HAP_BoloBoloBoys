package com.hap.hap_boloboloboys;

import java.net.URL;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeViewController {
    @FXML
    private Label timeLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Button toggleSoundButton;

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    private boolean isSoundOn = true;
    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDateTime now = LocalDateTime.now();
            timeLabel.setText(now.format(timeFormatter));
            dateLabel.setText(now.format(dateFormatter));
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();

        createToggleSoundButton();
        playSound();
    }

    @FXML
    private void createToggleSoundButton() {
        Image soundImage = new Image(getClass().getResourceAsStream("/assets/Sound.png"));
        ImageView soundImageView = new ImageView(soundImage);
        soundImageView.setFitWidth(120);
        soundImageView.setFitHeight(120);

        toggleSoundButton.setGraphic(soundImageView);
        toggleSoundButton.setOnAction(event -> toggleSound());
    }

    private void playSound() {
        URL soundURL = getClass().getResource("/sounds/Tangled.mp3");
        if (soundURL != null) {
            Media sound = new Media(soundURL.toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } else {
            System.out.println("Sound file not found!");
        }
    }

    private void stopSound() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    private void toggleSound() {
        if (isSoundOn) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.play();
        }
        isSoundOn = !isSoundOn;
    }

    @FXML
    protected void onGameMenuClick() {
        Stage currentStage = (Stage) timeLabel.getScene().getWindow();
        stopSound(); // Stop sound when leaving HomeView
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hap/hap_boloboloboys/GameView.fxml"));
            Parent gameRoot = loader.load();

            Scene scene = new Scene(gameRoot, 1000, 800);
            scene.getStylesheets().add(getClass().getResource("/css/game-styles.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/css/colors.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Game Page");
            stage.show(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentStage.close(); 
    }

    @FXML
    protected void onHomeMenuClick() {
        Stage currentStage = (Stage) timeLabel.getScene().getWindow();
        stopSound(); // Stop sound when leaving HomeView
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hap/hap_boloboloboys/HomeView.fxml"));
            Parent homeRoot = loader.load();

            Scene scene = new Scene(homeRoot, 1000, 800);
            scene.getStylesheets().add(getClass().getResource("/css/main-styles.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/css/colors.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Home Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentStage.close(); 
    }

    @FXML
    protected void onSettingMenuClick() {
        Stage currentStage = (Stage) timeLabel.getScene().getWindow();
        stopSound(); // Stop sound when leaving HomeView
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hap/hap_boloboloboys/SettingView.fxml"));
            Parent settingRoot = loader.load();

            Scene scene = new Scene(settingRoot, 1000, 800);
            scene.getStylesheets().add(getClass().getResource("/css/setting-styles.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/css/colors.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Setting Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentStage.close(); 
    }
}