package com.hap.hap_boloboloboys;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    @FXML
    public void initialize() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDateTime now = LocalDateTime.now();
            timeLabel.setText(now.format(timeFormatter));
            dateLabel.setText(now.format(dateFormatter));
        }), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    @FXML
    protected void onHelloButtonClick() {
        // Handle
    }

    @FXML
    protected void onGameMenuClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hap/hap_boloboloboys/GameView.fxml"));
            Parent gameRoot = loader.load();
        
            Scene scene = new Scene(gameRoot, 1000, 800);
            scene.getStylesheets().add(getClass().getResource("/css/main-styles.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/css/colors.css").toExternalForm());
        
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Game Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onHomeMenuClick() {
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
    }

    @FXML
    protected void onSettingMenuClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/hap/hap_boloboloboys/SettingView.fxml"));
            Parent settingRoot = loader.load();
        
            Scene scene = new Scene(settingRoot, 1000, 800);
            scene.getStylesheets().add(getClass().getResource("/css/main-styles.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/css/colors.css").toExternalForm());
        
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Setting Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}