package com.hap.hap_boloboloboys;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameViewController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button nextButton;

    @FXML
    private ImageView nextImageView;

    private boolean isButtonLadangkuActive = false;
    private boolean isButtonLadangmuActive = false;
    private boolean isButtonTokoActive = false;
    private boolean isButtonSaveActive = false;
    private boolean isButtonLoadActive = false;
    private boolean isButtonPluginActive = false;

    private Button buttonLadangku;
    private Button buttonLadangmu;
    private Button buttonToko;
    private Button buttonSave;
    private Button buttonLoad;
    private Button buttonPlugin;

    @FXML
    private void initialize() {
        Image nextImage = new Image(getClass().getResourceAsStream("/assets/Next.png"));
        nextImageView.setImage(nextImage);
        nextImageView.setFitWidth(100);
        nextImageView.setFitHeight(100);
        nextButton.setGraphic(nextImageView);
        nextButton.setOnMouseClicked(event -> handleButtonNextClick());

        StackPane.setMargin(nextButton, new Insets(10, 10, 10, 10));
        StackPane.setAlignment(nextButton, Pos.TOP_LEFT);
    }

    @FXML
    private void handleButtonLadangkuClick() {
        System.out.println("APA JING");
        isButtonLadangkuActive = !isButtonLadangkuActive;
        updateButtonState(buttonLadangku, isButtonLadangkuActive);
        if (isButtonLadangkuActive) {
            runMethodLadangku();
        } else {
            stopMethodLadangku();
        }
    }

    @FXML
    private void handleButtonLadangmuClick() {
        isButtonLadangmuActive = !isButtonLadangmuActive;
        updateButtonState(buttonLadangmu, isButtonLadangmuActive);
        if (isButtonLadangmuActive) {
            runMethodLadangmu();
        } else {
            stopMethodLadangmu();
        }
    }

    @FXML
    private void handleButtonTokoClick() {
        isButtonTokoActive = !isButtonTokoActive;
        updateButtonState(buttonToko, isButtonTokoActive);
        if (isButtonTokoActive) {
            runMethodToko();
        } else {
            stopMethodToko();
        }
    }

    @FXML
    private void handleButtonSaveClick() {
        isButtonSaveActive = !isButtonSaveActive;
        updateButtonState(buttonSave, isButtonSaveActive);
        if (isButtonSaveActive) {
            runMethodSave();
        } else {
            stopMethodSave();
        }
    }

    @FXML
    private void handleButtonLoadClick() {
        isButtonLoadActive = !isButtonLoadActive;
        updateButtonState(buttonLoad, isButtonLoadActive);
        if (isButtonLoadActive) {
            runMethodLoad();
        } else {
            stopMethodLoad();
        }
    }

    @FXML
    private void handleButtonPluginClick() {
        isButtonPluginActive = !isButtonPluginActive;
        updateButtonState(buttonPlugin, isButtonPluginActive);
        if (isButtonPluginActive) {
            runMethodPlugin();
        } else {
            stopMethodPlugin();
        }
    }

    private void updateButtonState(Button button, boolean isActive) {
        if (isActive) {
            button.getStyleClass().remove("active");
            button.setStyle("-fx-background-color: white;");
        } else {
            button.getStyleClass().add("active");
            button.setStyle("-fx-background-color: #ED8930;");
        }
    }

    private void runMethodLadangku() {
        System.out.println("Button Ladangku clicked and method executed");
    }

    private void stopMethodLadangku() {
        System.out.println("Stopping method for Button Ladangku");
    }

    private void runMethodLadangmu() {
        System.out.println("Button Ladangmu clicked and method executed");
    }

    private void stopMethodLadangmu() {
        System.out.println("Stopping method for Button Ladangmu");
    }

    private void runMethodToko() {
        System.out.println("Button Toko clicked and method executed");
    }

    private void stopMethodToko() {
        System.out.println("Stopping method for Button Toko");
    }

    private void runMethodSave() {
        System.out.println("Button Save clicked and method executed");
    }

    private void stopMethodSave() {
        System.out.println("Stopping method for Button Save");
    }

    private void runMethodLoad() {
        System.out.println("Button Load clicked and method executed");
    }

    private void stopMethodLoad() {
        System.out.println("Stopping method for Button Load");
    }

    private void runMethodPlugin() {
        System.out.println("Button Plugin clicked and method executed");
    }

    private void stopMethodPlugin() {
        System.out.println("Stopping method for Button Plugin");
    }

    @FXML
    private void handleButtonNextClick() {
        System.out.println("Button Next clicked");
        // Implementasi logika untuk button Next
    }

    @FXML
    protected void onGameMenuClick() {
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
            scene.getStylesheets().add(getClass().getResource("/css/setting-styles.css").toExternalForm());
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