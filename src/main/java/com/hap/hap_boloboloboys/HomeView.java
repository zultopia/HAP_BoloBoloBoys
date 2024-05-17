package com.hap.hap_boloboloboys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HomeView extends Application {
    private boolean isSoundOn = true;
    private MediaPlayer mediaPlayer;

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeView.class.getResource("/com/hap/hap_boloboloboys/HomeView.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 1000, 800);
        scene.getStylesheets().add(getClass().getResource("/css/main-styles.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/css/colors.css").toExternalForm());

        Image soundImage = new Image(getClass().getResourceAsStream("/assets/Sound.png"));
        ImageView soundImageView = new ImageView(soundImage);
        soundImageView.setFitWidth(180);
        soundImageView.setFitHeight(300);

        Button toggleSoundButton = new Button();
        toggleSoundButton.setGraphic(soundImageView);
        toggleSoundButton.setStyle("-fx-background-color: transparent;");
        toggleSoundButton.setOnAction(event -> toggleSound());

        StackPane buttonContainer = new StackPane(toggleSoundButton);
        buttonContainer.setAlignment(Pos.TOP_RIGHT);
        StackPane.setMargin(toggleSoundButton, new Insets(10, 10, 0, 0));

        VBox rootLayout = (VBox) root;
        Button fxmlButton = (Button) rootLayout.lookup("#toggleSoundButton");
        rootLayout.getChildren().remove(fxmlButton);
        rootLayout.getChildren().add(toggleSoundButton);

        stage.setOnShown(event -> playSound());

        stage.setTitle("Hap App");
        stage.setScene(scene);

        stage.setWidth(800);
        stage.setHeight(600);

        stage.show();
    }

    private void playSound() {
        URL soundURL = getClass().getResource("/sounds/fine.mp3");
        if (soundURL != null) {
            Media sound = new Media(soundURL.toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } else {
            System.out.println("Sound file not found!");
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

    public static void main(String[] args) {
        launch();
    }
}