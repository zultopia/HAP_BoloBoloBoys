package com.hap.hap_boloboloboys;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
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
import javafx.stage.WindowEvent;
import java.util.List;
import java.util.ArrayList;

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
    public GridPane storeGridPane = new GridPane();
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

    public Stage popupStage;

    public Store toko = new Store();
    public static int[] prices = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    public static int[] amounts = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    Label[] hargaLabels = new Label[9];
    Label[] jumlahLabels = new Label[9];

    public Person player1;
    public Person player2;
    public int currentPlayer = 1;

    public int numRows = 4;
    public static int numCols = 5;
    public Ladang ladang;

    public Deck deck;
    private List<Image> activeDeck = new ArrayList<>();
    private List<ImageView> shuffledImages = new ArrayList<>();
    public boolean isInLadangku = true;

    @FXML
    private GridPane gridPaneBear;
    @FXML
    private GridPane gridPaneTime;

    private Timeline bearAttackTimer;
    private Label bearAttackTimerLabel;
    private SimpleBooleanProperty popupOpen = new SimpleBooleanProperty(false);

    @FXML
    public void initialize() { // Inisialisasi
        setButtonStyle(buttonLadangku, false);
        setButtonStyle(buttonLadangmu, false);
        setButtonStyle(buttonToko, false);
        setButtonStyle(buttonSave, false);
        setButtonStyle(buttonLoad, false);
        setButtonStyle(buttonPlugin, false);

        // Inisialisasi pemain
        player1 = new Person("player1");
        player2 = new Person("player2");
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

        updatePlayer1MoneyLabel(player1.getWealth());
        player1MoneyLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 32px;");

        updatePlayer2MoneyLabel(player2.getWealth());
        player2MoneyLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 32px;");

        setCountDeckPasive();
        deckCountLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;");

        populateLadang(currentPlayer);
        deckAktif();
        initializeToko();

        // Platform.runLater(() -> showShufflePopup());
    }

    @FXML
    public void initializeToko() {
        // Iterate product in store
        int i = 0;
        for (String key : toko.getItems().keySet()) {
            prices[i] = toko.getItemPrice(key);
            amounts[i] = toko.getItemQuantity(key);
            i++;
        }
    }

    @FXML
    public void handleNextButtonClick(ActionEvent event) {
        System.out.println("Button Next Clicked!");
        if (currentPlayer == 1) {
            currentPlayer = 2;
        } else {
            currentPlayer = 1;
        }
        System.out.println("Now is " + currentPlayer);
        currentTurn++;
        if (currentPlayer == 1) {
            // Retrieve information before first
            player2.ladangku = ladang;
            player2.setDeck(deck);

            // Change the main game interface
            ladang = player1.ladangku;
            deck = player1.getDeck();
            System.out.println("Size " + player1.getDeck().calculateSize());
            populateLadang(currentPlayer);
            deckAktif();
        } else {
            // Retrieve information before first
            player1.ladangku = ladang;
            player1.setDeck(deck);

            // Change the main game interface
            ladang = player2.ladangku;
            deck = player2.getDeck();
            System.out.println(player2.getDeck().calculateSize());
            populateLadang(currentPlayer);
            deckAktif();
        }
        updateTurnLabel(currentTurn);
        setCountDeckPasive();

        if (currentTurn > 20) {
            turnLabel.setText("-");
            determineWinner();
            disableNextButton();
        }

        popupOpen.set(true);
        // showShufflePopup();

        popupOpen.addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                Random random = new Random();
                if (random.nextInt(10) == 0) {
                    System.out.println("Bear attack!");
                    playSound("Rawr.mp3");
                    displayPopupImage("Beruang.png");
                    int bearAttackTime = random.nextInt(31) + 30;
                    System.out.println("Bear attack in " + bearAttackTime + " seconds.");
                    showBearAttackTimer(bearAttackTime);
                }
            }
        });
    }

    public void setCountDeckPasive() {
        int deckCount = 0;
        if (currentPlayer == 1) {
            deckCount = player1.getInventory().getSize();
        } else {
            deckCount = player2.getInventory().getSize();
        }
        updateDeckCountLabel(deckCount, 40);
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

    public void populateLadang(int requestedPl) {
        gridPane.getChildren().clear(); // RequestedPL represent whose ladang to be displayed, check i it's in ladang musuh or not using
        // isInLadangku

        if (requestedPl == 1) {
            ladang = player1.ladangku;
        } else {
            ladang = player2.ladangku;
        }

        Image petakImage = loadImage("/assets/Petak.png");

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                final int finalRow = row;
                final int finalCol = col;

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
                        showCardDetails(cardImageView, finalRow, finalCol);
                    }
                });

                gridPane.add(stackPane, col, row);
                setupDragAndDropInLadang(cardImageView, petakImageView, row, col);
            }
        }
    }

    public void deckAktif() {
        gridPane2.getChildren().clear();
        deck = (currentPlayer == 1) ? player1.getDeck() : player2.getDeck();

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

        cardImageView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                showSellOption(col);
            }
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
            System.out.println(row + " " + col);
            Dragboard db = cardImageView.startDragAndDrop(TransferMode.MOVE);
            String id = "row=" + row + ",col=" + col + ",isFromLadang=1";
            ClipboardContent content = new ClipboardContent();
            content.putImage(cardImageView.getImage());
            content.putString(id);
            db.setContent(content);
            event.consume();
        });

        cardImageView.setOnDragOver(event -> {
            if (event.getGestureSource() != petakImageView && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
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

        cardImageView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasImage()) {
                System.out.println(row + " " + col);
                success = true;
                System.out.println(db.getString() + "dari deck");
                int firstRow = Integer.parseInt(db.getString().split(",")[0].split("=")[1]);
                int firstCol = Integer.parseInt(db.getString().split(",")[1].split("=")[1]);
                int isFromLadang = Integer.parseInt(db.getString().split(",")[2].split("=")[1]);
                System.out.println(firstRow + " " + firstCol);
                System.out.println(row + " " + col);
                Card selected;
                // Get selected card
                if (isFromLadang == 0) {
                    System.out.println("From deck");
                    try {
                        selected = deck.pop(firstCol);
                    } catch (InventoryException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    if (ladang.getPetak(firstRow, firstCol).getKartu() instanceof Item
                            || ladang.getPetak(firstRow, firstCol).getKartu() instanceof Product) {
                        return;
                    }
                    System.out.println("From ladang");
                    selected = ladang.takeCard(firstRow, firstCol);
                }
                try {
                    if ((ladang.getPetak(row, col).getKartu() instanceof Plant
                            || ladang.getPetak(row, col).getKartu() instanceof Animal)) {
                        if (selected instanceof Item) {
                            Card kartu = ladang.getPetak(row, col).getKartu();
                            ((Item) selected).applyEffect((Creature) kartu);
                            ladang.plantKartu(row, col, kartu);

                        } else {
                            System.out.println("Kartu tidak bisa ditanam");
                            deck.putToDeck(selected, firstCol);
                            return;
                        }
                    } else if (ladang.getPetak(row, col).getKartu() == null) {
                        if (selected instanceof Plant) {
                            ladang.plantKartu(row, col, (Plant) selected);
                        } else if (selected instanceof Animal) {
                            ladang.plantKartu(row, col, (Animal) selected);
                        } else {
                            System.out.println("Kartu yang ditanam bukan tanaman atau hewan");
                            deck.putToDeck(selected, firstCol);
                            return;
                        }
                        cardImageView.setImage(db.getImage());
                    }
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

        petakImageView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasImage()) {
                System.out.println(row + " " + col);
                success = true;
                System.out.println(db.getString() + "dari deck");
                int firstRow = Integer.parseInt(db.getString().split(",")[0].split("=")[1]);
                int firstCol = Integer.parseInt(db.getString().split(",")[1].split("=")[1]);
                int isFromLadang = Integer.parseInt(db.getString().split(",")[2].split("=")[1]);
                System.out.println(firstRow + " " + firstCol);
                System.out.println(row + " " + col);
                Card selected;
                // Get selected card
                if (isFromLadang == 0) {
                    System.out.println("From deck");
                    try {
                        selected = deck.pop(firstCol);
                    } catch (InventoryException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    if (ladang.getPetak(firstRow, firstCol).getKartu() instanceof Item
                            || ladang.getPetak(firstRow, firstCol).getKartu() instanceof Product) {
                        return;
                    }
                    System.out.println("From ladang");
                    selected = ladang.takeCard(firstRow, firstCol);
                }
                try {
                    if ((ladang.getPetak(row, col).getKartu() instanceof Plant
                            || ladang.getPetak(row, col).getKartu() instanceof Animal)) {
                        if (selected instanceof Item) {
                            Card kartu = ladang.getPetak(row, col).getKartu();
                            ((Item) selected).applyEffect((Creature) kartu);
                            ladang.plantKartu(row, col, kartu);

                        } else {
                            System.out.println("Kartu tidak bisa ditanam");
                            deck.putToDeck(selected, firstCol);
                            return;
                        }
                    } else if (ladang.getPetak(row, col).getKartu() == null) {
                        if (selected instanceof Plant) {
                            ladang.plantKartu(row, col, (Plant) selected);
                        } else if (selected instanceof Animal) {
                            ladang.plantKartu(row, col, (Animal) selected);
                        } else {
                            System.out.println("Kartu yang ditanam bukan tanaman atau hewan");
                            deck.putToDeck(selected, firstCol);
                            return;
                        }
                        cardImageView.setImage(db.getImage());
                    }
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

    public void showCardDetails(ImageView cardImageView, int row, int col) {
        // String cardName = getCardNameFromImagePath(cardImageView.getImage());

        // Create Labels
        Card card = ladang.getPetak(row, col).getKartu();
        Label nameLabel = new Label(card.getCardName());
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;");
        Label infoLabel;
        if (card instanceof Plant) {
            Plant plant = (Plant) card;
            infoLabel = new Label("Umur: " + plant.getAge() + " (" + plant.getHarvestTarget() + ")");
        } else {
            Animal animal = (Animal) card;
            infoLabel = new Label("Berat: " + animal.getWeight() + " (" + animal.getHarvestTarget() + ")");
        }
        String item = "";
        Creature creature = (Creature) card;
        for (int i = 0; i < 6; i++) {
            if (creature.getEffectValue(i) != 0) {
                item += "- " + creature.getEffectName(i) + "(" + creature.getEffectValue(i) + ")\n";
            }
        }
        Label activeItemsLabel = new Label("Item aktif: \n" + item);

        // Create Buttons
        Button closeButton = new Button("Kembali");
        closeButton.setOnAction(event -> ((Stage) nameLabel.getScene().getWindow()).close());

        Button harvestButton = new Button("Panen");
        harvestButton.setDisable(!checkHarvestConditions(row, col));
        harvestButton.setOnAction(event -> {
            if (checkHarvestConditions(row, col)) {
                handleHarvestAction(nameLabel, row, col);
                // ((Stage) nameLabel.getScene().getWindow()).close();
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

    public boolean checkHarvestConditions(int row, int col) {
        // Implement the conditions to check if the card can be harvested
        // return true if can harvest
        Card card = ladang.getPetak(row, col).getKartu();
        if (((Creature) card).canHarvest()) {
            return true;
        }
        return false;
    }

    public void handleHarvestAction(Label nameLabel, int row, int col) {
        if (deck.isFull()) {
            System.out.println("Deck sudah penuh!");
        } else {
            Creature card = (Creature) ladang.takeCard(row, col);
            Product product = card.harvest();
            deck.putToDeck(product);
            populateLadang(currentPlayer);
            deckAktif();
            System.out.println("Harvesting " + card.getCardName() + ": sucessed!");
            ((Stage) nameLabel.getScene().getWindow()).close();
        }
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
        populateLadang(currentPlayer);
    }

    public void setNumCols(int col) {
        numCols = col;
        populateLadang(currentPlayer);
    }

    @FXML
    public void handleButtonLadangkuClick() {
        toggleButtonState(buttonLadangku);
        if (activeButton == buttonLadangku) {
            populateLadang(currentPlayer);
        } else {

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
        storeGridPane.setHgap(20);
        storeGridPane.setVgap(10);
        storeGridPane.setPadding(new Insets(10));
        storeGridPane.setAlignment(Pos.CENTER);

        // Create ImageViews for the products
        int index = 0;
        for (Map.Entry<String, Pair<Product, Integer>> entry : toko.getItems().entrySet()) {
            final String productName = entry.getKey();
            Product product = entry.getValue().getKey();
            int quantity = entry.getValue().getValue();
            int price = product.getPrice();

            if (quantity <= 0) {
                continue;
            }

            ImageView productImageView = new ImageView();
            productImageView.setFitWidth(100);
            productImageView.setFitHeight(100);
            Image productImage = loadImage(product.getImgPath());
            if (productImage != null) {
                productImageView.setImage(productImage);
                productImageView.setOnMouseClicked(event -> {
                    buyProduct(productName, productImageView);
                });
            } else {
                System.err.println("Error loading image for product: " + productName);
            }

            hargaLabels[index] = new Label("Harga: Rp " + price);
            hargaLabels[index].setFont(Font.font(14));
            jumlahLabels[index] = new Label("Jumlah: " + quantity);
            jumlahLabels[index].setFont(Font.font(14));

            VBox productBox = new VBox(10);
            productBox.setAlignment(Pos.CENTER);
            productBox.setStyle(
                    "-fx-background-color: rgba(255, 255, 255, 0.8); -fx-border-color: black; -fx-border-width: 1px;");
            productBox.setPadding(new Insets(5));
            productBox.getChildren().addAll(productImageView, hargaLabels[index], jumlahLabels[index]);

            storeGridPane.add(productBox, index % 3, index / 3);
            index++;
        }

        // Layouts
        VBox mainBox = new VBox(20, nameLabel, outputLabel, storeGridPane, new Button("Kembali"));
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

        // Save a reference to the popupStage for later use
        this.popupStage = popupStage;
    }

    public void buyProduct(String productName, ImageView productImageView) {
        Pair<Product, Integer> item = toko.getItems().get(productName);

        int playerMoney = 0;
        if (currentPlayer == 1) {
            playerMoney = player1.getWealth();
        } else {
            playerMoney = player2.getWealth();
        }
        // Cek apakah item ada di toko dan uang pemain cukup
        if (item == null || playerMoney < item.getKey().getPrice() || item.getValue() <= 0) {
            if (item == null || item.getValue() <= 0) {
                displayOutput("Produk " + productName + " sudah habis.", Color.RED);
            } else {
                displayOutput("Uang Anda tidak cukup untuk membeli Produk " + productName, Color.RED);
            }
            return;
        }

        // Cek apakah ada slot kosong di deck aktif
        boolean foundEmptySlot = false;
        for (int col = 0; col < 6; col++) {
            StackPane stackPane = (StackPane) gridPane2.getChildren().get(col);
            ImageView petakImageView = (ImageView) stackPane.getChildren().get(0);
            ImageView cardImageView = (ImageView) stackPane.getChildren().get(1);
            if (cardImageView.getImage() == null) {
                foundEmptySlot = true;
                break;
            }
        }

        if (!foundEmptySlot) {
            // Pesan error jika deck aktif sudah penuh
            displayOutput("Deck Aktif sudah penuh. Tidak dapat menambahkan produk.", Color.RED);
            return;
        }

        // Jika semua kondisi terpenuhi, lakukan pengurangan uang dan penghapusan item
        // dari toko
        playerMoney -= item.getKey().getPrice();
        toko.removeItem(productName, 1);
        displayOutput("Anda baru saja membeli Produk " + productName + "\nSisa uang Anda: Rp " + playerMoney,
                Color.GREEN);

        // Tambahkan item ke deck aktif
        for (int col = 0; col < 6; col++) {
            StackPane stackPane = (StackPane) gridPane2.getChildren().get(col);
            ImageView petakImageView = (ImageView) stackPane.getChildren().get(0);
            ImageView cardImageView = (ImageView) stackPane.getChildren().get(1);
            if (cardImageView.getImage() == null) {
                cardImageView.setImage(productImageView.getImage());
                break;
            }
        }

        if (currentPlayer == 1) {
            player1.getDeck().putToDeck(item.getKey());
        } else {
            player2.getDeck().putToDeck(item.getKey());
        }

        updateStoreDisplay();

        if (currentPlayer == 1) {
            updatePlayer1MoneyLabel(player1.getWealth());
        } else {
            updatePlayer2MoneyLabel(player2.getWealth());
        }
    }

    public void sellProduct(int index) {
        if (deck.getCard(index) != null && deck.getCard(index) instanceof Product) {
            int playerMoney = 0;
            if (currentPlayer == 1) {
                playerMoney = player1.getWealth();
            } else {
                playerMoney = player2.getWealth();
            }

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
            updateHargaProduk(index, sellPrice);
            updateJumlahProduk(index, 1);

            // Update deck aktif display
            deckAktif();

            // Update toko display
            updateStoreDisplay();
        }
    }

    public void updateStoreDisplay() {
        storeGridPane.getChildren().clear(); // Clear the storeGridPane before updating it
        int index = 0;
        for (Map.Entry<String, Pair<Product, Integer>> entry : toko.getItems().entrySet()) {
            final String productName = entry.getKey();
            Product product = entry.getValue().getKey();
            int quantity = entry.getValue().getValue();
            int price = product.getPrice();

            if (quantity <= 0) {
                continue;
            }

            ImageView productImageView = new ImageView();
            productImageView.setFitWidth(100);
            productImageView.setFitHeight(100);
            Image productImage = loadImage(product.getImgPath());
            if (productImage != null) {
                productImageView.setImage(productImage);
                productImageView.setOnMouseClicked(event -> {
                    buyProduct(productName, productImageView);
                });
            } else {
                System.err.println("Error loading image for product: " + productName);
            }

            hargaLabels[index].setText("Harga: Rp " + price);
            jumlahLabels[index].setText("Jumlah: " + quantity);

            VBox productBox = new VBox(10);
            productBox.setAlignment(Pos.CENTER);
            productBox.setStyle(
                    "-fx-background-color: rgba(255, 255, 255, 0.8); -fx-border-color: black; -fx-border-width: 1px;");
            productBox.setPadding(new Insets(5));
            productBox.getChildren().addAll(productImageView, hargaLabels[index], jumlahLabels[index]);

            storeGridPane.add(productBox, index % 3, index / 3);
            index++;
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
            sellProduct(index);
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
                try {
                    // load game state
                    Load.loadGameState(folderName);
                    currentTurn = Load.getCurrentTurn();
                    toko = Load.getStore();

                    // load player
                    Load.loadPlayer(folderName, "player1");
                    player1 = Load.getPerson("player1");
                    Load.loadPlayer(folderName, "player2");
                    player2 = Load.getPerson("player2");

                    updatePlayer1MoneyLabel(player1.getWealth());
                    updatePlayer2MoneyLabel(player2.getWealth());
                    setCountDeckPasive();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("Load from: " + folderName + ", Format: " + selectedFormat);
                populateLadang(currentPlayer);
                deckAktif();
                initializeToko();
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
        ImageView bearImageView = new ImageView(
                new Image(getClass().getResource("/assets/Beruang.png").toExternalForm()));
        bearImageView.setFitWidth(130);
        bearImageView.setFitHeight(130);

        bearAttackTimerLabel = new Label();
        bearAttackTimerLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");

        gridPaneBear.getChildren().add(bearImageView);
        gridPaneTime.getChildren().add(bearAttackTimerLabel);

        final int[] countdown = { timeInSeconds };
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

    private void showShufflePopup() {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = nextButton.getScene();
        if (scene != null) {
            popupStage.initOwner(scene.getWindow());
        } else {
            System.err.println("Scene or nextButton is null. Cannot initialize popup.");
            return;
        }

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        reshuffleCards(gridPane);
    
        Image shuffleImage = new Image(getClass().getResource("/assets/Shuffle.png").toString());
        ImageView shuffleImageView = new ImageView(shuffleImage);
        shuffleImageView.setFitWidth(120);
        shuffleImageView.setFitHeight(170);
        shuffleImageView.setOnMouseClicked(e -> {
            reshuffleCards(gridPane);
        });

        shuffleImageView.setOnMouseEntered(e -> shuffleImageView.setOpacity(0.7));
        shuffleImageView.setOnMouseExited(e -> shuffleImageView.setOpacity(1.0));

        gridPane.add(shuffleImageView, 0, 2, 2, 1);
        GridPane.setHalignment(shuffleImageView, HPos.CENTER);
        GridPane.setValignment(shuffleImageView, VPos.TOP);
        GridPane.setMargin(shuffleImageView, new Insets(10));

        Scene popupScene = new Scene(gridPane, 500, 500);
        popupStage.setScene(popupScene);
        popupStage.show();

        popupStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            if (activeDeck.size() >= 6) {
                showErrorDialog("Deck aktif anda sudah penuh");
                event.consume();
            } else {
                popupOpen.set(false);
            }
        });

        popupStage.setOnCloseRequest(event -> {
            popupOpen.set(false);
        });
    }

    private void reshuffleCards(GridPane gridPane) {
        gridPane.getChildren().removeIf(node -> !(node instanceof ImageView && ((ImageView) node).getImage().getUrl().endsWith("Shuffle.png")));

        List<Image> images = loadImagesShuffle();

        int numRows = 2;
        int numCols = 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int index = i * numCols + j;
                if (index < images.size()) {
                    ImageView imageView = new ImageView(images.get(index));
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(120);
                    Image image = imageView.getImage(); 
                    handleCardSelection(imageView, image);

                    gridPane.add(imageView, j, i);
                    GridPane.setHalignment(imageView, HPos.CENTER);
                    GridPane.setValignment(imageView, VPos.CENTER);
                    GridPane.setMargin(imageView, new Insets(10));
                    shuffledImages.add(imageView);
                }
            }
        }
    }

    private List<Image> loadImagesShuffle() {
        List<Image> images = new ArrayList<>();
        File dir = new File(getClass().getResource("/card/hewan").getFile());
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                images.add(new Image(file.toURI().toString()));
            }
        }

        dir = new File(getClass().getResource("/card/item").getFile());
        files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                images.add(new Image(file.toURI().toString()));
            }
        }

        dir = new File(getClass().getResource("/card/tumbuhan").getFile());
        files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                images.add(new Image(file.toURI().toString()));
            }
        }

        java.util.Collections.shuffle(images);

        return images;
    }

    private void handleCardSelection(ImageView selectedImageView, Image selectedImage) {
        boolean foundEmptySlot = false;
        for (int col = 0; col < 6; col++) {
            StackPane stackPane = (StackPane) gridPane2.getChildren().get(col);
            ImageView cardImageView = (ImageView) stackPane.getChildren().get(1);
            if (cardImageView.getImage() == null) {
                foundEmptySlot = true;
                cardImageView.setImage(selectedImage);
                break;
            }
        }
    
        // if (!foundEmptySlot) {
        //     displayOutput("Deck Aktif sudah penuh. Tidak dapat menambahkan produk.", Color.RED);
        //     return;
        // }
    
        // if (currentPlayer == 1) {
        //     player1.getDeck().putToDeck(selectedImage); 
        // } else {
        //     player2.getDeck().putToDeck(selectedImage);
        // }
    }
}