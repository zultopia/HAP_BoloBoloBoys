package com.hap.hap_boloboloboys;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameViewController {

    @FXML
    private MenuBar menuBar;
    @FXML
    private Button nextButton;
    @FXML
    private ImageView nextImageView;
    @FXML
    private GridPane gridPane;

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
    private Label turnLabel;
    @FXML
    private Label player1MoneyLabel;
    @FXML
    private Label player2MoneyLabel;
    @FXML
    private Label deckCountLabel;

    private int numRows = 4; 
    private int numCols = 5;

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

        turnLabel.setText("1");
        turnLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");

        player1MoneyLabel.setText("2");
        player1MoneyLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 32px;");

        player2MoneyLabel.setText("3");
        player2MoneyLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 32px;");

        deckCountLabel.setText("40/40");
        deckCountLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;");

        populateGridPane();
    }

    private void populateGridPane() {
        gridPane.getChildren().clear(); // Clear existing content

        Image petakImage = new Image(getClass().getResourceAsStream("/assets/Petak.png"));

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                ImageView imageView = new ImageView(petakImage);
                imageView.setFitWidth(95); 
                imageView.setFitHeight(110);
                imageView.getStyleClass().add("image-view");
                gridPane.add(imageView, col, row);
            }
        }
    }

    @FXML
    private void handleButtonLadangClick() {
        // Handle actions when any ladang button is clicked
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
        populateGridPane(); 
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
        populateGridPane(); 
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

    // Methods to update labels
    public void updateTurnLabel(int turn) {
        turnLabel.setText("Turn: " + turn);
    }

    public void updatePlayer1MoneyLabel(int money) {
        player1MoneyLabel.setText("Player 1 Money: " + money);
    }

    public void updatePlayer2MoneyLabel(int money) {
        player2MoneyLabel.setText("Player 2 Money: " + money);
    }

    public void updateDeckCountLabel(int currentDeck, int totalDeck) {
        deckCountLabel.setText("Deck: " + currentDeck + "/" + totalDeck);
    }
}