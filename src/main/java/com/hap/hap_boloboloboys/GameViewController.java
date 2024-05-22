package com.hap.hap_boloboloboys;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameViewController {

    @FXML
    private MenuBar menuBar;
    @FXML
    private Button nextButton;
    @FXML
    private ImageView nextImageView;

    private Button activeButton = null;

    @FXML
    private Button buttonLadangku;
    @FXML
    private Button buttonLadangmu;
    @FXML
    private Button buttonToko;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonLoad;
    @FXML
    private Button buttonPlugin;

    @FXML
    private void initialize() {
        // Set initial styles for buttons
        setButtonStyle(buttonLadangku, false);
        setButtonStyle(buttonLadangmu, false);
        setButtonStyle(buttonToko, false);
        setButtonStyle(buttonSave, false);
        setButtonStyle(buttonLoad, false);
        setButtonStyle(buttonPlugin, false);

        Image nextImage = new Image(getClass().getResourceAsStream("/assets/Next.png"));
        nextImageView.setImage(nextImage);
        nextImageView.setFitWidth(100);
        nextImageView.setFitHeight(100);
        nextButton.setGraphic(nextImageView);
        nextButton.setOnMouseClicked(event -> handleButtonNextClick());
    }

    @FXML
    private void handleButtonLadangkuClick() {
        toggleButtonState(buttonLadangku);
        if (activeButton == buttonLadangku) {
            runMethodLadangku();
        } else {
            stopMethodLadangku();
        }
    }

    @FXML
    private void handleButtonLadangmuClick() {
        toggleButtonState(buttonLadangmu);
        if (activeButton == buttonLadangmu) {
            runMethodLadangmu();
        } else {
            stopMethodLadangmu();
        }
    }

    @FXML
    private void handleButtonTokoClick() {
        toggleButtonState(buttonToko);
        if (activeButton == buttonToko) {
            runMethodToko();
        } else {
            stopMethodToko();
        }
    }

    @FXML
    private void handleButtonSaveClick() {
        toggleButtonState(buttonSave);
        if (activeButton == buttonSave) {
            runMethodSave();
        } else {
            stopMethodSave();
        }
    }

    @FXML
    private void handleButtonLoadClick() {
        toggleButtonState(buttonLoad);
        if (activeButton == buttonLoad) {
            runMethodLoad();
        } else {
            stopMethodLoad();
        }
    }

    @FXML
    private void handleButtonPluginClick() {
        toggleButtonState(buttonPlugin);
        if (activeButton == buttonPlugin) {
            runMethodPlugin();
        } else {
            stopMethodPlugin();
        }
    }

    private void toggleButtonState(Button button) {
        if (activeButton != null) {
            setButtonStyle(activeButton, false);
        }

        if (activeButton != button) {
            setButtonStyle(button, true);
            activeButton = button;
        } else {
            activeButton = null;
        }
    }

    private void setButtonStyle(Button button, boolean isActive) {
        if (isActive) {
            button.getStyleClass().add("active");
            button.setStyle("-fx-background-color: #ED8930;");
        } else {
            button.getStyleClass().remove("active");
            button.setStyle("-fx-background-color: white;");
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