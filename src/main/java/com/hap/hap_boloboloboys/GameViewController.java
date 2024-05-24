package com.hap.hap_boloboloboys;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.util.Duration;

import com.hap.hap_boloboloboys.lib.card.*;
import com.hap.hap_boloboloboys.lib.config.*;
import com.hap.hap_boloboloboys.lib.field.*;
import com.hap.hap_boloboloboys.lib.person.*;
import com.hap.hap_boloboloboys.lib.store.*;
import com.hap.hap_boloboloboys.lib.util.*;

public class GameViewController {

    @FXML
    private MenuBar menuBar;
    @FXML
    public GridPane gridPane;
    @FXML
    public GridPane gridPane2;
    @FXML
    public Button nextButton;

    public Button activeButton = null;

    @FXML
    public Button buttonLadangku;
    @FXML
    public Button buttonLadangmu;
    @FXML
    public Button buttonToko;
    @FXML
    public Button buttonSave;
    @FXML
    public Button buttonLoad;
    @FXML
    public Button buttonPlugin;

    @FXML
    public Label turnLabel;
    public static int currentTurn = 1;
    @FXML
    public Label player1MoneyLabel;
    @FXML
    public Label player2MoneyLabel;
    @FXML
    public Label deckCountLabel;
    private Label outputLabel;
    public int numRows = 4;
    public static int numCols = 5;
    public static int[] prices = { 50000, 60000, 70000, 55000, 65000, 75000, 52000, 63000, 74000 };
    public static int[] amounts = { 3, 3, 3, 3, 3, 3, 3, 3, 3 };
    public int playerMoney = 100000;
    Label[] hargaLabels = new Label[9];
    Label[] jumlahLabels = new Label[9];
    public Stage popupStage;
    public Person player1;
    public Person player2;
    public int currentPlayer = 1;
    public Ladang ladang;
    public Store toko = new Store();
    public Deck deck;

    @FXML
    private GridPane gridPaneBear;
    @FXML
    private GridPane gridPaneTime;

    private Timeline bearAttackTimer;
    private Label bearAttackTimerLabel;

    @FXML
    public void initialize() { // Inisialisasi
        setButtonStyle(buttonLadangku, false);
        setButtonStyle(buttonLadangmu, false);
        setButtonStyle(buttonToko, false);
        setButtonStyle(buttonSave, false);
        setButtonStyle(buttonLoad, false);
        setButtonStyle(buttonPlugin, false);

        // Inisialisasi pemain
        player1 = new Person("Player 1");
        player2 = new Person("Player 2");
        Plant pl1 = new Plant("BIJI_STROBERI");
        Product pr1 = new Product("STROBERI");
        pl1.setImgPath("/card/tumbuhan/BijiStroberi.png");
        pr1.setImgPath("/card/produk/Produk7.png");
        try {
            player1.ladangku.plantKartu(0, 0, pl1);
            player1.putToDeck(pl1);
            player1.putToDeck(pl1);
        } catch (Exception e) {

        }

        currentPlayer = 1;
        currentTurn = 1;

        // Load the image for the Next button
        Image nextImage = loadImage("/assets/Next.png");
        ImageView nextImageView = new ImageView(nextImage);
        nextImageView.setFitWidth(100);
        nextImageView.setFitHeight(100);
        nextButton.setGraphic(nextImageView);
        nextButton.setOnAction(this::handleNextButtonClick);

        updateTurnLabel(currentPlayer);
        turnLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");

        updatePlayer1MoneyLabel(0);
        player1MoneyLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 32px;");

        updatePlayer2MoneyLabel(0);
        player2MoneyLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 32px;");

        updateDeckCountLabel(0, 40);
        deckCountLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;");

        populateLadang();
        deckAktif();
    }


    @FXML
    public void handleNextButtonClick(ActionEvent event) {
        System.out.println("Button Next Clicked!");
        Random random = new Random();
        // 1/10 chance to trigger bear attack
        if (random.nextInt(2) == 0) {
            System.out.println("Bear attack!");
            playSound("Rawr.mp3");
            displayPopupImage("Beruang.png");
            int bearAttackTime = random.nextInt(31) + 30; 
            System.out.println("Bear attack in " + bearAttackTime + " seconds.");
            showBearAttackTimer(bearAttackTime);
        }
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
        currentTurn++;
        updateTurnLabel(currentTurn);
        if (currentTurn > 20) {
            turnLabel.setText("-");
            determineWinner();
            disableNextButton();
        }
    }

    public void determineWinner() {
        int player1Money = Integer.parseInt(player1MoneyLabel.getText());
        int player2Money = Integer.parseInt(player2MoneyLabel.getText());

        String winner;
        if (player1Money > player2Money) {
            winner = "Player 1 Wins!";
        } else if (player2Money > player1Money) {
            winner = "Player 2 Wins!";
        } else {
            winner = "It's a tie!";
        }

        showWinnerDialog(winner);
    }

    public void showWinnerDialog(String winner) {
        Label winnerLabel = new Label(winner);
        winnerLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> {
            ((Stage) winnerLabel.getScene().getWindow()).close();
        });

        VBox vbox = new VBox(winnerLabel, closeButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px;");
        vbox.setEffect(new DropShadow());

        Scene scene = new Scene(vbox, 300, 200);

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(gridPane.getScene().getWindow());
        popupStage.setTitle("Game Over");
        popupStage.setScene(scene);

        Stage gameViewControllerStage = (Stage) gridPane.getScene().getWindow();
        double centerXPosition = gameViewControllerStage.getX() + gameViewControllerStage.getWidth() / 2d;
        double centerYPosition = gameViewControllerStage.getY() + gameViewControllerStage.getHeight() / 2d;

        popupStage.setX(centerXPosition - scene.getWidth() / 2d);
        popupStage.setY(centerYPosition - scene.getHeight() / 2d);

        popupStage.setResizable(false);
        popupStage.show();
    }

    public void disableNextButton() {
        nextButton.setDisable(true);
    }

    public void populateLadang() {
        gridPane.getChildren().clear();

        if (currentTurn == 1) {
            ladang = player1.ladangku;
        } else {
            ladang = player2.ladangku;
        }

        Image petakImage = loadImage("/assets/Petak.png");

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                ImageView petakImageView = new ImageView(petakImage);
                petakImageView.getProperties().put("ladang", true);
                petakImageView.setFitWidth(95);
                petakImageView.setFitHeight(110);
                petakImageView.getStyleClass().add("image-view");

                ImageView cardImageView;
                if (ladang.getPetak(row, col).getKartu() != null) {
                    Image cardImage = loadImage(ladang.getPetak(row, col).getKartu().getImgPath());
                    cardImageView = new ImageView(cardImage);
                } else {
                    cardImageView = new ImageView();
                }
                cardImageView.setFitWidth(75);
                cardImageView.setFitHeight(90);
                cardImageView.getStyleClass().add("image-view");

                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(petakImageView, cardImageView);

                cardImageView.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        showCardDetails(cardImageView);
                    }
                });

                gridPane.add(stackPane, col, row);
                setupDragAndDropInLadang(cardImageView, petakImageView, row, col);
            }
        }
    }

    public void deckAktif() {
        gridPane2.getChildren().clear();
        deck = (currentTurn == 1) ? player1.getDeck() : player2.getDeck();

        Image petakImage = loadImage("/assets/Petak.png");

        for (int col = 0; col < 6; col++) {
            ImageView petakImageView = new ImageView(petakImage);
            petakImageView.getProperties().put("ladang", false);
            petakImageView.setFitWidth(95);
            petakImageView.setFitHeight(110);
            petakImageView.getStyleClass().add("image-view");

            ImageView cardImageView;
            if (deck.getCard(col) != null) {
                Image cardImage = loadImage(deck.getCard(col).getImgPath());
                cardImageView = new ImageView(cardImage);
                int finalCol = col; // declare effectively final variable
                cardImageView.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        showSellOption(finalCol); // use finalCol instead of col
                    }
                });
            } else {
                cardImageView = new ImageView();
            }
            cardImageView.setFitWidth(75);
            cardImageView.setFitHeight(90);
            cardImageView.getStyleClass().add("image-view");

            // Setup drag and drop for cardImageView
            setupDragAndDropFromDeckAktif(cardImageView, petakImageView, col);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(petakImageView, cardImageView);

            gridPane2.add(stackPane, col, 0);
        }
    }

    public void setupDragAndDropFromDeckAktif(ImageView cardImageView, ImageView petakImageView, int col) {
        cardImageView.setOnDragDetected(event -> {
            Dragboard db = cardImageView.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(cardImageView.getImage());
            content.putString("row=" + 0 + ",col=" + col + ",isFromLadang=0"); // is from ladang set to false
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
                // // Check if card is already in ladang
                // boolean isInLadang = (boolean)
                // cardImageView.getProperties().getOrDefault("isInLadang", false);
                // if (!isInLadang) {
                int isFromLadang = Integer.parseInt(db.getString().split(",")[2].split("=")[1]);
                if (isFromLadang == 0) {
                    cardImageView.setImage(db.getImage());
                    System.out.println("From deck");
                    cardImageView.getProperties().put("isInLadang", true);
                    success = true;
                    int firstCol = Integer.parseInt(db.getString().split(",")[1].split("=")[1]);
                    System.out.println(firstCol + " " + col);
                    System.out.println(deck.getCard(firstCol).getCardName());
                    deck.moveCard(firstCol, col);
                    for (int i = 0; i < deck.getCapacity(); i++) {
                        if (deck.getCard(i) != null) {
                            System.out.println(deck.getCard(i).getCardName() + i);
                        }
                    }
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });

        cardImageView.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
                cardImageView.setImage(null);
            }
            event.consume();
        });
    }

    public void setupDragAndDropInLadang(ImageView cardImageView, ImageView petakImageView, int row, int col) {
        cardImageView.setOnDragDetected(event -> {
            // boolean isInLadang = (boolean)
            // cardImageView.getProperties().getOrDefault("isInLadang", false);
            // if (isInLadang) {
            System.out.println(row + " " + col);
            Dragboard db = cardImageView.startDragAndDrop(TransferMode.MOVE);
            String id = "row=" + row + ",col=" + col + ",isFromLadang=1"; // Send the ID instead, is from ladang set to true
            ClipboardContent content = new ClipboardContent();
            content.putImage(cardImageView.getImage());
            content.putString(id);
            db.setContent(content);
            event.consume();
            // }
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
                System.out.println(row + " " + col);
                cardImageView.setImage(db.getImage());
                success = true;
                System.out.println(db.getString() + "dari deck");
                int firstRow = Integer.parseInt(db.getString().split(",")[0].split("=")[1]);
                int firstCol = Integer.parseInt(db.getString().split(",")[1].split("=")[1]);
                int isFromLadang = Integer.parseInt(db.getString().split(",")[2].split("=")[1]);
                System.out.println(firstRow + " " + firstCol);
                System.out.println(row + " " + col);
                Card selected;
                if (isFromLadang == 0) {
                    System.out.println("From deck");
                    try {
                        selected = deck.pop(firstCol);
                    } catch (InventoryException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("From ladang");
                    selected = ladang.takeCard(firstRow, firstCol);
                }
                try {
                    if (selected instanceof Plant) {
                        System.out.println(((Plant) selected).getAge());
                    } else if (selected == null) {
                        System.out.println("not plant");
                    }
                    ladang.plantKartu(row, col, (Plant) selected);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < numRows; i++) {
                    for (int j = 0; j < numCols; j++) {
                        if (ladang.getPetak(i, j) != null)
                            System.out.print(ladang.getPetak(i, j).getKartu() + " ");
                    }
                    System.out.println();
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });

        cardImageView.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
                cardImageView.setImage(null);
            }
            event.consume();
        });
    }

    public Image loadImage(String path) {
        try {
            return new Image(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            System.err.println("Error loading image from path: " + path);
            return null;
        }
    }

    public void showCardDetails(ImageView cardImageView) {
        String cardName = getCardNameFromImagePath(cardImageView.getImage());

        // Create Labels
        Label nameLabel = new Label(cardName);
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;");
        Label infoLabel = new Label("Berat: 5 (8)");
        Label activeItemsLabel = new Label("Item aktif: Accelerate (1), Delay (1), Protect (1)");

        // Create Buttons
        Button closeButton = new Button("Kembali");
        closeButton.setOnAction(event -> ((Stage) nameLabel.getScene().getWindow()).close());

        Button harvestButton = new Button("Panen");
        harvestButton.setOnAction(event -> {
            if (checkHarvestConditions(cardImageView)) {
                handleHarvestAction(cardImageView);
                ((Stage) nameLabel.getScene().getWindow()).close();
            } else {
                System.out.println("Harvest conditions not met!");
            }
        });

        // Image View for Card
        ImageView cardImage = new ImageView(cardImageView.getImage());
        cardImage.setFitWidth(140);
        cardImage.setFitHeight(160);

        // Layouts
        VBox infoBox = new VBox(10, infoLabel, activeItemsLabel);
        infoBox.setAlignment(Pos.CENTER_LEFT);

        HBox cardAndInfoBox = new HBox(40, infoBox, cardImage);
        cardAndInfoBox.setAlignment(Pos.CENTER);

        HBox buttonBox = new HBox(10, harvestButton, closeButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox mainBox = new VBox(45, nameLabel, cardAndInfoBox, buttonBox);
        mainBox.setAlignment(Pos.TOP_CENTER);
        mainBox.setPadding(new Insets(20));
        mainBox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px;");
        mainBox.setEffect(new DropShadow());

        // Scene and Stage
        Scene scene = new Scene(mainBox, 600, 400);
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(gridPane.getScene().getWindow());
        popupStage.setTitle("Detail Kartu");
        popupStage.setScene(scene);

        // Position the popup stage at the center of the parent stage
        Stage parentStage = (Stage) gridPane.getScene().getWindow();
        popupStage.setX(parentStage.getX() + parentStage.getWidth() / 2 - scene.getWidth() / 2);
        popupStage.setY(parentStage.getY() + parentStage.getHeight() / 2 - scene.getHeight() / 2);

        popupStage.setResizable(false);
        popupStage.show();
    }

    public boolean checkHarvestConditions(ImageView cardImageView) {
        // Implement the conditions to check if the card can be harvested
        // For example:
        // return cardImageView.getImage() != null && someOtherCondition;
        return true; // Replace with actual condition
    }

    public void handleHarvestAction(ImageView cardImageView) {
        // Implement the logic for harvesting the card
        System.out.println("Harvesting card: " + cardImageView.getImage());
    }

    public String getCardNameFromImagePath(Image image) {
        // Implement logic to extract card name from image path
        return "Card Name";
    }

    @FXML
    public void handleButtonLadangClick() {
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
    public void handleButtonLadangkuClick() {
        toggleButtonState(buttonLadangku);
        if (activeButton == buttonLadangku) {
            runMethodLadangku();
        } else {
            stopMethodLadangku();
        }
    }

    @FXML
    public void handleButtonLadangmuClick() {
        toggleButtonState(buttonLadangmu);
        if (activeButton == buttonLadangmu) {
            runMethodLadangmu();
        } else {
            stopMethodLadangmu();
        }
    }

    @FXML
    public void handleButtonTokoClick() {
        toggleButtonState(buttonToko);
        if (activeButton == buttonToko) {
            runMethodToko();
        } else {
            stopMethodToko();
        }
    }

    @FXML
    public void handleButtonSaveClick() {
        toggleButtonState(buttonSave);
        if (activeButton == buttonSave) {
            runMethodSave();
        } else {
            stopMethodSave();
        }
    }

    @FXML
    public void handleButtonLoadClick() {
        toggleButtonState(buttonLoad);
        if (activeButton == buttonLoad) {
            runMethodLoad();
        } else {
            stopMethodLoad();
        }
    }

    @FXML
    public void handleButtonPluginClick() {
        toggleButtonState(buttonPlugin);
        if (activeButton == buttonPlugin) {
            runMethodPlugin();
        } else {
            stopMethodPlugin();
        }
    }

    public void toggleButtonState(Button button) {
        if (activeButton != null) {
            setButtonStyle(activeButton, false);
        }

        if (activeButton != button) {
            setButtonStyle(button, true);
            activeButton = button;
        } else {
            activeButton = null;
            setButtonStyle(button, false);
        }
    }

    public void setButtonStyle(Button button, boolean isActive) {
        if (isActive) {
            button.getStyleClass().add("active");
            button.setStyle("-fx-background-color: #ED8930;");
        } else {
            button.getStyleClass().remove("active");
            button.setStyle("-fx-background-color: white;");
        }
    }

    public void runMethodLadangku() {
        System.out.println("Button Ladangku clicked and method executed");
    }

    public void stopMethodLadangku() {
        System.out.println("Stopping method for Button Ladangku");
    }

    public void runMethodLadangmu() {
        System.out.println("Button Ladangmu clicked and method executed");
    }

    public void stopMethodLadangmu() {
        System.out.println("Stopping method for Button Ladangmu");
    }

    @FXML
    public void runMethodToko() {
        System.out.println("Button Toko clicked and method executed");

        // Create Labels
        Label nameLabel = new Label("Toko");
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;");

        // Create output label
        outputLabel = new Label();
        outputLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        outputLabel.setTextFill(Color.RED);

        // Create GridPane to arrange the products and their info
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.setAlignment(Pos.CENTER);

        // Create ImageViews for the products
        ImageView[] productImageViews = new ImageView[9];
        for (int i = 0; i < 9; i++) {
            final int index = i;
            productImageViews[i] = new ImageView();
            productImageViews[i].setFitWidth(100);
            productImageViews[i].setFitHeight(100);
            Image productImage = loadImage("/card/produk/Produk" + (i + 1) + ".png");
            if (productImage != null) {
                productImageViews[i].setImage(productImage);
                productImageViews[i].setOnMouseClicked(event -> {
                    buyProduct(index, productImageViews[index]);
                });
            } else {
                System.err.println("Error loading product image: Produk" + (i + 1) + ".png");
            }
        }

        for (int i = 0; i < 9; i++) {
            hargaLabels[i] = new Label("Harga: Rp " + prices[i]);
            hargaLabels[i].setFont(Font.font(14));
            jumlahLabels[i] = new Label("Jumlah: " + amounts[i]);
            jumlahLabels[i].setFont(Font.font(14));
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                VBox productBox = new VBox(10);
                productBox.setAlignment(Pos.CENTER);
                productBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-border-color: black; -fx-border-width: 1px;");
                productBox.setPadding(new Insets(5));
                productBox.getChildren().addAll(productImageViews[i * 3 + j], hargaLabels[i * 3 + j], jumlahLabels[i * 3 + j]);
                gridPane.add(productBox, j, i);
            }
        }

        // Layouts
        VBox mainBox = new VBox(20, nameLabel, outputLabel, gridPane, new Button("Kembali"));
        mainBox.setAlignment(Pos.TOP_CENTER);
        mainBox.setPadding(new Insets(20));
        mainBox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px;");
        mainBox.setEffect(new DropShadow());

        // Scene and Stage
        Scene scene = new Scene(mainBox, 800, 750);
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(buttonToko.getScene().getWindow());
        popupStage.setTitle("Detail Barang");
        popupStage.setScene(scene);
        popupStage.setResizable(false); // Set resizable to false to lock the size
        popupStage.show();

        // Close the popup and reset the button state when "Kembali" is clicked
        Button backButton = (Button) mainBox.getChildren().get(mainBox.getChildren().size() - 1);
        backButton.setOnAction(event -> {
            popupStage.close();
            toggleButtonState(buttonToko); // Reset button state
            stopMethodToko(); // Stop method toko
        });
    }

    public void buyProduct(int index, ImageView productImageView) {
        if (amounts[index] > 0 && playerMoney >= prices[index]) {
            boolean foundEmptySlot = false;
            for (int col = 0; col < 6; col++) {
                StackPane stackPane = (StackPane) gridPane2.getChildren().get(col);
                ImageView petakImageView = (ImageView) stackPane.getChildren().get(0);
                ImageView cardImageView = (ImageView) stackPane.getChildren().get(1);
                if (cardImageView.getImage() == null) {
                    cardImageView.setImage(productImageView.getImage());
                    foundEmptySlot = true;
                    // Buat kartu produk yang sesuai berdasarkan indeks
                    Product newProduct;
                    switch (index) {
                        case 0:
                            newProduct = new Product("DAGING_BERUANG");
                            newProduct.setImgPath("/card/produk/Produk1.png");
                            break;
                        case 1:
                            newProduct = new Product("DAGING_DOMBA");
                            newProduct.setImgPath("/card/produk/Produk2.png");
                            break;
                        case 2:
                            newProduct = new Product("DAGING_KUDA");
                            newProduct.setImgPath("/card/produk/Produk3.png");
                            break;
                        case 3:
                            newProduct = new Product("JAGUNG");
                            newProduct.setImgPath("/card/produk/Produk4.png");
                            break;
                        case 4:
                            newProduct = new Product("LABU");
                            newProduct.setImgPath("/card/produk/Produk5.png");
                            break;
                        case 5:
                            newProduct = new Product("SIRIP_HIU");
                            newProduct.setImgPath("/card/produk/Produk6.png");
                            break;
                        case 6:
                            newProduct = new Product("STROBERI");
                            newProduct.setImgPath("/card/produk/Produk7.png");
                            break;
                        case 7:
                            newProduct = new Product("SUSU");
                            newProduct.setImgPath("/card/produk/Produk8.png");
                            break;
                        case 8:
                            newProduct = new Product("TELUR");
                            newProduct.setImgPath("/card/produk/Produk9.png");
                            break;
                        default:
                            newProduct = null;
                            break;
                    }

                    if (newProduct != null) {
                        // Tambahkan produk ke deck pemain yang aktif berdasarkan giliran
                        if (currentPlayer == 1) {
                            player1.getDeck().putToDeck(newProduct);
                        } else {
                            player2.getDeck().putToDeck(newProduct);
                        }
                        playerMoney -= prices[index];
                        amounts[index]--;
                        updateHargaProduk(index, prices[index]);
                        updateJumlahProduk(index, amounts[index]);
                        displayOutput(
                                "Anda baru saja membeli Produk " + (index + 1) + "\nSisa uang Anda: Rp " + playerMoney,
                                Color.GREEN);

                        hargaLabels[index].setText("Harga: Rp " + prices[index]);
                        jumlahLabels[index].setText("Jumlah: " + amounts[index]);
                    }
                    break;
                }
            }

            if (!foundEmptySlot) {
                // Pesan error jika deck aktif sudah penuh
                displayOutput("Deck Aktif sudah penuh. Tidak dapat menambahkan produk.", Color.RED);
            }

        } else if (amounts[index] == 0) {
            displayOutput("Produk " + (index + 1) + " sudah habis.", Color.RED);
        } else {
            displayOutput("Uang Anda tidak cukup untuk membeli Produk " + (index + 1), Color.RED);
        }
    }

    public void displayOutput(String message, Color color) {
        outputLabel.setText(message);
        outputLabel.setTextFill(color);
    }

    public void updateHargaProduk(int index, int newPrice) {
        prices[index] = newPrice;
    }

    public void updateJumlahProduk(int index, int newAmount) {
        amounts[index] = newAmount;
    }

    public void stopMethodToko() {
        System.out.println("Stopping method for Button Toko");
    }

    public void runMethodSave() {
        System.out.println("Button Save clicked and method executed");
        Label saveLabel = new Label("Save Game State");
        saveLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;");

        // Create Format ComboBox
        ComboBox<String> formatComboBox = new ComboBox<>();
        formatComboBox.getItems().addAll("TXT", "XML", "YAML", "JSON");
        formatComboBox.getSelectionModel().selectFirst();

        // Create Folder TextField
        TextField folderTextField = new TextField();
        folderTextField.setPromptText("Enter folder name");

        // Create Save Button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            String selectedFormat = formatComboBox.getValue();
            String folderName = folderTextField.getText().trim();
            if (!folderName.isEmpty()) {
                // Perform saving action here, you can save to a folder with the provided name
                System.out.println("Save to: " + folderName + ", Format: " + selectedFormat);
                stopMethodSave();
            } else {
                System.out.println("Folder name cannot be empty!");
            }
        });

        // Create Back Button
        Button backButton = new Button("Kembali");
        backButton.setOnAction(event -> {
            popupStage.close();
            toggleButtonState(buttonSave);
            stopMethodSave();
        });

        HBox buttonBox = new HBox(20, saveButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, saveLabel, formatComboBox, folderTextField, buttonBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px;");
        vbox.setEffect(new DropShadow());

        Scene scene = new Scene(vbox, 400, 300);
        popupStage = new Stage();
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(gridPane.getScene().getWindow());
        popupStage.setTitle("Save State");
        popupStage.setScene(scene);
        popupStage.setResizable(false);
        popupStage.show();
    }

    public void stopMethodSave() {
        System.out.println("Stopping method for Button Save");
        if (popupStage != null) {
            popupStage.close();
            popupStage = null;
        }
    }

    public void loadPlayer(String folderName, String selectedFormat) {
        // Perform loading action here with the provided folderName and selectedFormat
        System.out.println("Load from: " + folderName + ", Format: " + selectedFormat);
    }

    public void runMethodLoad() {
        System.out.println("Button Load clicked and method executed");
        Label loadLabel = new Label("Load Game State");
        loadLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;");

        // Create Format ComboBox
        ComboBox<String> formatComboBox = new ComboBox<>();
        formatComboBox.getItems().addAll("TXT", "XML", "YAML", "JSON");
        formatComboBox.getSelectionModel().selectFirst();

        // Create Folder TextField
        TextField folderTextField = new TextField();
        folderTextField.setPromptText("Enter folder name");

        // Create Load Button
        Button loadButton = new Button("Load");
        loadButton.setOnAction(event -> {
            String selectedFormat = formatComboBox.getValue();
            String folderName = folderTextField.getText().trim();
            if (!folderName.isEmpty()) {
                // Perform loading action here, you can load from a folder with the provided
                // name
                System.out.println("Load from: " + folderName + ", Format: " + selectedFormat);
                stopMethodLoad();
            } else {
                System.out.println("Folder name cannot be empty!");
            }
        });

        // Create Back Button
        Button backButton = new Button("Kembali");
        backButton.setOnAction(event -> {
            popupStage.close();
            toggleButtonState(buttonLoad);
            stopMethodLoad();
        });

        HBox buttonBox = new HBox(20, loadButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, loadLabel, formatComboBox, folderTextField, buttonBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px;");
        vbox.setEffect(new DropShadow());

        Scene scene = new Scene(vbox, 400, 300);
        popupStage = new Stage();
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(gridPane.getScene().getWindow());
        popupStage.setTitle("Load State");
        popupStage.setScene(scene);
        popupStage.setResizable(false);
        popupStage.show();
    }

    public void stopMethodLoad() {
        System.out.println("Stopping method for Button Load");
        if (popupStage != null) {
            popupStage.close();
            popupStage = null;
        }
    }

    public void runMethodPlugin() {
        System.out.println("Button Plugin clicked and method executed");

        // Create label for file plugin
        Label nameLabel = new Label("Plugin");
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;");
        Label fileLabel = new Label("File Plugin: ");
        fileLabel.setStyle("-fx-font-weight: bold;");

        // Create label to display selected file name
        Label fileNameLabel = new Label("");
        fileNameLabel.setStyle("-fx-font-weight: bold;");

        // Create Choose File button
        Button chooseFileButton = new Button("Choose File");
        chooseFileButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JAR files", "*.jar"));
            File selectedFile = fileChooser.showOpenDialog(gridPane.getScene().getWindow());
            if (selectedFile != null) {
                fileNameLabel.setText(selectedFile.getName());
            }
        });

        // Create Upload button
        Button uploadButton = new Button("Upload");
        uploadButton.setOnAction(event -> {
            String fileName = fileNameLabel.getText();
            if (!fileName.isEmpty()) {
                // Perform plugin loading action here
                boolean isSuccess = runPlugin(fileName);
                if (isSuccess) {
                    displayPluginStatus("Plugin Loaded Successfully", true);
                } else {
                    displayPluginStatus("Error: File is not a valid JAR", false);
                }
            } else {
                displayPluginStatus("Error: No file selected", false);
            }
        });

        // Create Back Button
        Button backButton = new Button("Kembali");
        backButton.setOnAction(event -> {
            popupStage.close();
            toggleButtonState(buttonPlugin);
            stopMethodPlugin();
        });

        // Create an HBox to hold file label and file name label horizontally
        HBox fileBox = new HBox(5, fileLabel, fileNameLabel);
        fileBox.setAlignment(Pos.CENTER_LEFT);

        // Create an HBox to hold buttons horizontally
        HBox buttonBox = new HBox(10, chooseFileButton, uploadButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Create VBox to hold all components vertically
        VBox vbox = new VBox(10, nameLabel, fileBox, buttonBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: white; -fx-border-width: 1px;");

        // Create a label for displaying the status message
        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-font-weight: bold;");

        // Create another VBox to hold the vbox and status label
        VBox mainVBox = new VBox(10, vbox, statusLabel);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setPadding(new Insets(20));
        mainVBox.setStyle("-fx-background-color: white; -fx-border-width: 1px;");

        // Create the scene and stage for the popup
        Scene scene = new Scene(mainVBox, 400, 250);
        popupStage = new Stage();
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(gridPane.getScene().getWindow());
        popupStage.setTitle("Upload Plugin");
        popupStage.setScene(scene);
        popupStage.setResizable(false);
        popupStage.show();
    }

    public void displayPluginStatus(String message, boolean isSuccess) {
        Label statusLabel = new Label(message);
        if (isSuccess) {
            statusLabel.setTextFill(Color.GREEN);
        } else {
            statusLabel.setTextFill(Color.RED);
        }
        statusLabel.setStyle("-fx-font-weight: bold;");

        VBox mainVBox = (VBox) popupStage.getScene().getRoot();
        Label oldStatusLabel = (Label) mainVBox.getChildren().get(1); // Get the old status label
        mainVBox.getChildren().remove(oldStatusLabel); // Remove the old status label
        mainVBox.getChildren().add(statusLabel); // Add the new status label at the end
    }

    public boolean runPlugin(String fileName) {
        // Perform plugin loading action here
        // Return true if plugin is loaded successfully, false otherwise
        // Example:
        return fileName.endsWith(".jar");
    }

    public void stopMethodPlugin() {
        System.out.println("Stopping method for Button Plugin");
        if (popupStage != null) {
            popupStage.close();
            popupStage = null;
        }
    }

    @FXML
    public void onGameMenuClick() {
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
    public void onHomeMenuClick() {
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
    public void onSettingMenuClick() {
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
        turnLabel.setText("" + turn);
    }

    public void updatePlayer1MoneyLabel(int money) {
        player1MoneyLabel.setText("" + money);
    }

    public void updatePlayer2MoneyLabel(int money) {
        player2MoneyLabel.setText("" + money);
    }

    public void updateDeckCountLabel(int currentDeck, int totalDeck) {
        deckCountLabel.setText(currentDeck + "/" + totalDeck);
    }

    public void showErrorDialog(String errorMessage) {
        Label errorLabel = new Label(errorMessage);
        errorLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> {
            ((Stage) errorLabel.getScene().getWindow()).close();
        });

        VBox vbox = new VBox(errorLabel, closeButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: white; -fx-border-color: red; -fx-border-width: 2px;");
        vbox.setEffect(new DropShadow());

        Scene scene = new Scene(vbox, 400, 200);

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(gridPane.getScene().getWindow());
        popupStage.setTitle("Error");
        popupStage.setScene(scene);

        Stage gameViewControllerStage = (Stage) gridPane.getScene().getWindow();
        double centerXPosition = gameViewControllerStage.getX() + gameViewControllerStage.getWidth() / 2d;
        double centerYPosition = gameViewControllerStage.getY() + gameViewControllerStage.getHeight() / 2d;

        popupStage.setX(centerXPosition - scene.getWidth() / 2d);
        popupStage.setY(centerYPosition - scene.getHeight() / 2d);

        popupStage.setResizable(false);
        popupStage.show();
    }

    private MediaPlayer playSound(String soundFile) {
        String soundPath = getClass().getResource("/sounds/" + soundFile).toExternalForm();
        Media sound = new Media(soundPath);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        return mediaPlayer;
    }    

    private void displayPopupImage(String imageName) {
        String imagePath = "/assets/" + imageName;
        Image image = new Image(getClass().getResource(imagePath).toExternalForm());
        ImageView imageView = new ImageView(image);
    
        imageView.setFitWidth(300); 
        imageView.setFitHeight(300); 
    
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(imageView);
    
        Scene scene = new Scene(stackPane);
        scene.setFill(Color.TRANSPARENT); 
    
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(nextButton.getScene().getWindow());
        popupStage.setScene(scene);
        popupStage.show();
        // Menutup popup setelah 3 detik
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> popupStage.close());
        delay.play();
    }

    private void showBearAttackTimer(int timeInSeconds) {
        ImageView bearImageView = new ImageView(new Image(getClass().getResource("/assets/Beruang.png").toExternalForm()));
        bearImageView.setFitWidth(130);
        bearImageView.setFitHeight(130);

        bearAttackTimerLabel = new Label();
        bearAttackTimerLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");

        gridPaneBear.getChildren().add(bearImageView);
        gridPaneTime.getChildren().add(bearAttackTimerLabel);

        final int[] countdown = {timeInSeconds};
        bearAttackTimerLabel.setText("Waktu Serangan Beruang: " + countdown[0]);
        MediaPlayer mediaPlayer = playSound("Siren.mp3");
        bearAttackTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            countdown[0]--;
            bearAttackTimerLabel.setText("Waktu Serangan Beruang: " + countdown[0]);
            if (countdown[0] <= 0) {
                gridPaneBear.getChildren().remove(bearImageView);
                gridPaneTime.getChildren().remove(bearAttackTimerLabel);
                bearAttackTimer.stop();
                mediaPlayer.stop();
            }
        }));
        bearAttackTimer.setCycleCount(timeInSeconds);
        bearAttackTimer.play();
    }

    public void sellProduct(int index) {
        if (deck.getCard(index) != null && deck.getCard(index) instanceof Product) {
            Product product = (Product) deck.getCard(index);
            int sellPrice = product.getPrice();
            playerMoney += sellPrice;
            if (currentPlayer == 1) {
                updatePlayer1MoneyLabel(playerMoney);
            } else {
                updatePlayer2MoneyLabel(playerMoney);
            }
    
            // Remove the product from deck aktif
            try {
                deck.pop(index);
            } catch (InventoryException e) {
                System.out.println(e.getMessage());
                return;
            }
    
            // Add the product to toko
            toko.addItem(product.getCode(), product, 1);
    
            // Update deck aktif display
            deckAktif();
    
            // Update toko display
            updateStoreDisplay();
        }
    }
    
    public void updateStoreDisplay() {
        Map<String, Pair<Product, Integer>> items = toko.getItems();
        for (int i = 0; i < 9; i++) {
            Product product = toko.getItems().get(i).getKey();
            if (product != null) {
                amounts[i] = items.get(product.getCode()).getValue(); // Get the quantity from toko
                jumlahLabels[i].setText("Jumlah: " + amounts[i]);
            }
        }
    }    

    public void showSellOption(int index) {
        // Tampilkan dialog konfirmasi untuk menjual produk
        Alert sellConfirmation = new Alert(AlertType.CONFIRMATION);
        sellConfirmation.setTitle("Konfirmasi Penjualan");
        sellConfirmation.setHeaderText("Anda akan menjual produk ini.");
        sellConfirmation.setContentText("Apakah Anda yakin ingin menjual produk ini?");

        Optional<ButtonType> result = sellConfirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Panggil metode sellProduct dengan indeks yang sesuai
            sellProduct(index);
        }
    }
}