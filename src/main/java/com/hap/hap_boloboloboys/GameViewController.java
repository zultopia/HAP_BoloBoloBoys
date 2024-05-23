package com.hap.hap_boloboloboys;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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
    @FXML
    private GridPane gridPane2;

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

        populateLadang();
        deckAktif();
    }

    private void populateLadang() {
        gridPane.getChildren().clear();

        Image petakImage = loadImage("/assets/Petak.png");

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                ImageView petakImageView = new ImageView(petakImage);
                petakImageView.setFitWidth(95);
                petakImageView.setFitHeight(110);
                petakImageView.getStyleClass().add("image-view");

                // Add an overlay ImageView for card image
                ImageView cardImageView = new ImageView();
                cardImageView.setFitWidth(75);
                cardImageView.setFitHeight(90);
                cardImageView.getStyleClass().add("image-view");

                // Use a StackPane to hold both ImageViews
                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(petakImageView, cardImageView);

                // Show card details on right-click
                cardImageView.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        showCardDetails(cardImageView);
                    }
                });

                gridPane.add(stackPane, col, row);

                // Setup drag and drop
                setupDragAndDropInLadang(cardImageView, petakImageView);
            }
        }
    }

    private void deckAktif() {
        gridPane2.getChildren().clear();
    
        String[] cardImages = {
            "/card/hewan/Ayam.png",
            "/card/hewan/Beruang.png",
            "/card/hewan/Domba.png",
            "/card/hewan/HiuDarat.png",
            "/card/hewan/Kuda.png",
            "/card/item/Layout.png"
        };
    
        Image petakImage = loadImage("/assets/Petak.png");
    
        for (int col = 0; col < cardImages.length; col++) {
            Image cardImage = loadImage(cardImages[col]);
            if (cardImage == null) {
                System.err.println("Error loading card image: " + cardImages[col]);
                continue;
            }
            ImageView petakImageView = new ImageView(petakImage);
            petakImageView.setFitWidth(95);
            petakImageView.setFitHeight(110);
            petakImageView.getStyleClass().add("image-view");
    
            // Add an overlay ImageView for card image
            ImageView cardImageView = new ImageView(cardImage);
            cardImageView.setFitWidth(75);
            cardImageView.setFitHeight(90);
            cardImageView.getStyleClass().add("image-view");
            setupDragAndDropFromDeckAktif(cardImageView, petakImageView); // <-- Panggil metode ini
    
            // Use a StackPane to hold both ImageViews
            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(petakImageView, cardImageView);
    
            gridPane2.add(stackPane, col, 0);
        }
    }    

    private void setupDragAndDropFromDeckAktif(ImageView cardImageView, ImageView petakImageView) {
        cardImageView.setOnDragDetected(event -> {
            Dragboard db = cardImageView.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(cardImageView.getImage());
            db.setContent(content);
            event.consume();
        });

        petakImageView.setOnDragOver(event -> {
            if (event.getGestureSource() != petakImageView && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        petakImageView.setOnDragEntered(event -> {
            if (event.getGestureSource() != petakImageView && event.getDragboard().hasImage()) {
                petakImageView.setOpacity(0.3);
            }
        });

        petakImageView.setOnDragExited(event -> {
            petakImageView.setOpacity(1);
        });

        petakImageView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasImage()) {
                // Check if card is already in populateLadang
                boolean isInLadang = (boolean) cardImageView.getProperties().getOrDefault("isInLadang", false);
                if (!isInLadang) {
                    cardImageView.setImage(db.getImage());
                    cardImageView.getProperties().put("isInLadang", true);
                    success = true;
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });

        cardImageView.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
                cardImageView.setImage(null);
                cardImageView.getProperties().put("isInLadang", false);
            }
            event.consume();
        });
    }

    private void setupDragAndDropInLadang(ImageView cardImageView, ImageView petakImageView) {
        cardImageView.setOnDragDetected(event -> {
            Dragboard db = cardImageView.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(cardImageView.getImage());
            db.setContent(content);
            event.consume();
        });
    
        petakImageView.setOnDragOver(event -> {
            if (event.getGestureSource() != petakImageView && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });
    
        petakImageView.setOnDragEntered(event -> {
            if (event.getGestureSource() != petakImageView && event.getDragboard().hasImage()) {
                petakImageView.setOpacity(0.3);
            }
        });
    
        petakImageView.setOnDragExited(event -> {
            petakImageView.setOpacity(1);
        });
    
        petakImageView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasImage()) {
                // Check if card is already in populateLadang
                boolean isInLadang = (boolean) cardImageView.getProperties().getOrDefault("isInLadang", false);
                if (!isInLadang) {
                    cardImageView.setImage(db.getImage());
                    cardImageView.getProperties().put("isInLadang", true);
                    success = true;
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
    
        cardImageView.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
                cardImageView.setImage(null);
                cardImageView.getProperties().put("isInLadang", false);
            }
            event.consume();
        });
    }    

    private Image loadImage(String path) {
        try {
            return new Image(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            System.err.println("Error loading image from path: " + path);
            return null;
        }
    }   

    private void showCardDetails(ImageView cardImageView) {
        String cardName = getCardNameFromImagePath(cardImageView.getImage());
    
        Label nameLabel = new Label("Nama Kartu: " + cardName);
        Label infoLabel = new Label("Umur/Berat: ...");
        Label activeItemsLabel = new Label("Item Aktif: ...");
    
        Button closeButton = new Button("Kembali");
        closeButton.setOnAction(event -> {
            ((Stage) nameLabel.getScene().getWindow()).close();
        });
    
        Button harvestButton = new Button("Panen");
        harvestButton.setOnAction(event -> {
            if (checkHarvestConditions(cardImageView)) {
                handleHarvestAction(cardImageView);
                ((Stage) nameLabel.getScene().getWindow()).close();
            } else {
                System.out.println("Harvest conditions not met!");
            }
        });
    
        harvestButton.setDisable(!checkHarvestConditions(cardImageView));
    
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(gridPane.getScene().getWindow());
        popupStage.setTitle("Detail Kartu");
    
        ImageView cardImage = new ImageView(cardImageView.getImage());
        cardImage.setFitWidth(200);
        cardImage.setFitHeight(250);
    
        VBox vbox = new VBox(nameLabel, infoLabel, activeItemsLabel, cardImage, harvestButton, closeButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px;");
        vbox.setEffect(new DropShadow());
    
        Scene scene = new Scene(vbox, 600, 500);
        popupStage.setScene(scene);
    
        // Calculate position to center popup relative to the main window
        Stage gameViewControllerStage = (Stage) gridPane.getScene().getWindow();
        double centerXPosition = gameViewControllerStage.getX() + gameViewControllerStage.getWidth() / 2d;
        double centerYPosition = gameViewControllerStage.getY() + gameViewControllerStage.getHeight() / 2d;
    
        popupStage.setX(centerXPosition - scene.getWidth() / 2d);
        popupStage.setY(centerYPosition - scene.getHeight() / 2d);
    
        popupStage.setResizable(false);  // Fixed size
        popupStage.show();
    }          

    private boolean checkHarvestConditions(ImageView cardImageView) {
        // Implement the conditions to check if the card can be harvested
        // For example:
        // return cardImageView.getImage() != null && someOtherCondition;
        return true;  // Replace with actual condition
    }

    private void handleHarvestAction(ImageView cardImageView) {
        // Implement the logic for harvesting the card
        System.out.println("Harvesting card: " + cardImageView.getImage());
    }

    private String getCardNameFromImagePath(Image image) {
        // Implement logic to extract card name from image path
        return "Card Name";
    }

    @FXML
    private void handleButtonLadangClick() {
        // Handle actions when any ladang button is clicked
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
        populateLadang(); 
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
        populateLadang(); 
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