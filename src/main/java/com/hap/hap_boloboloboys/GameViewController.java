package com.hap.hap_boloboloboys;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameViewController {

    @FXML
    private MenuBar menuBar;
    @FXML
    private GridPane gridPane;
    @FXML
    private GridPane gridPane2;
    @FXML
    private Button nextButton;

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
    private int currentPlayer = 1; 
    private int currentTurn = 1;
    @FXML
    private Label player1MoneyLabel;
    @FXML
    private Label player2MoneyLabel;
    @FXML
    private Label deckCountLabel;

    private int numRows = 4; 
    private int numCols = 5;
    private int[] prices = {50000, 60000, 70000, 55000, 65000, 75000, 52000, 63000, 74000};
    private int[] amounts = {3, 3, 3, 3, 3, 3, 3, 3, 3};
    private int playerMoney = 200000;
    Label[] hargaLabels = new Label[9];
    Label[] jumlahLabels = new Label[9];
    private Stage popupStage;

    @FXML
    private void initialize() { // Inisialisasi
        setButtonStyle(buttonLadangku, false);
        setButtonStyle(buttonLadangmu, false);
        setButtonStyle(buttonToko, false);
        setButtonStyle(buttonSave, false);
        setButtonStyle(buttonLoad, false);
        setButtonStyle(buttonPlugin, false);

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
    private void handleNextButtonClick(ActionEvent event) { 
        System.out.println("Button Next Clicked!");
        currentPlayer = (currentPlayer == 1) ? 2 : 1;   // Change turn
        currentTurn++;
        updateTurnLabel(currentTurn);
        if (currentTurn > 20) {
            turnLabel.setText("-");
            determineWinner();
            disableNextButton();
        }
    }

    private void determineWinner() {
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

    private void showWinnerDialog(String winner) {
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

    private void disableNextButton() {
        nextButton.setDisable(true);
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

                ImageView cardImageView = new ImageView();
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
    
            ImageView cardImageView = new ImageView(cardImage);
            cardImageView.setFitWidth(75);
            cardImageView.setFitHeight(90);
            cardImageView.getStyleClass().add("image-view");
            setupDragAndDropFromDeckAktif(cardImageView, petakImageView); 
    
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
                // Check if card is already in ladang
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
            }
            event.consume();
        });
    }
    
    private void setupDragAndDropInLadang(ImageView cardImageView, ImageView petakImageView) {
        cardImageView.setOnDragDetected(event -> {
            boolean isInLadang = (boolean) cardImageView.getProperties().getOrDefault("isInLadang", false);
            if (isInLadang) {
                Dragboard db = cardImageView.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putImage(cardImageView.getImage());
                db.setContent(content);
                event.consume();
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
                cardImageView.setImage(db.getImage());
                cardImageView.getProperties().put("isInLadang", true);
                success = true;
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
            setButtonStyle(button, false); 
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

    @FXML
    private void runMethodToko() {
        System.out.println("Button Toko clicked and method executed");

        // Create Labels
        Label nameLabel = new Label("Toko");
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 24px;");

        // Create GridPane to arrange the products and their info
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20); // Increased horizontal gap
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.setAlignment(Pos.CENTER); // Center align gridPane

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
                productImageViews[i].setOnMouseClicked(event -> buyProduct(index));
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

        // Add products to GridPane
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                VBox productBox = new VBox(10);
                productBox.setAlignment(Pos.CENTER); // Center align each product box
                productBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-border-color: black; -fx-border-width: 1px;");
                productBox.setPadding(new Insets(5));
                productBox.getChildren().addAll(productImageViews[i * 3 + j], hargaLabels[i * 3 + j], jumlahLabels[i * 3 + j]);
                gridPane.add(productBox, j, i);
            }
        }

        // Layouts
        VBox mainBox = new VBox(20, nameLabel, gridPane, new Button("Kembali"));
        mainBox.setAlignment(Pos.TOP_CENTER);
        mainBox.setPadding(new Insets(20));
        mainBox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px;");
        mainBox.setEffect(new DropShadow());

        // Scene and Stage
        Scene scene = new Scene(mainBox, 800, 700);
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

    private void buyProduct(int index) {
        if (amounts[index] > 0 && playerMoney >= prices[index]) {
            playerMoney -= prices[index];
            amounts[index]--;
            updateHargaProduk(index, prices[index]);
            updateJumlahProduk(index, amounts[index]);
            System.out.println("Anda baru saja membeli Produk " + (index + 1));
            System.out.println("Sisa uang Anda: Rp " + playerMoney);
    
            // Update related labels after purchase
            hargaLabels[index].setText("Harga: Rp " + prices[index]);
            jumlahLabels[index].setText("Jumlah: " + amounts[index]);
        } else if (amounts[index] == 0) {
            System.out.println("Produk " + (index + 1) + " sudah habis.");
        } else {
            System.out.println("Uang Anda tidak cukup untuk membeli Produk " + (index + 1));
        }
    }    

    private void updateHargaProduk(int index, int newPrice) {
        prices[index] = newPrice;
    }

    private void updateJumlahProduk(int index, int newAmount) {
        amounts[index] = newAmount;
    }

    private void stopMethodToko() {
        System.out.println("Stopping method for Button Toko");
    }

    private void runMethodSave() {
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

    private void stopMethodSave() {
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

    private void runMethodLoad() {
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
                // Perform loading action here, you can load from a folder with the provided name
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

    private void stopMethodLoad() {
        System.out.println("Stopping method for Button Load");
        if (popupStage != null) {
            popupStage.close();
            popupStage = null;
        }
    }

    private void runMethodPlugin() {
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
    
    private void displayPluginStatus(String message, boolean isSuccess) {
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
    
    private boolean runPlugin(String fileName) {
        // Perform plugin loading action here
        // Return true if plugin is loaded successfully, false otherwise
        // Example:
        return fileName.endsWith(".jar");
    }    

    private void stopMethodPlugin() {
        System.out.println("Stopping method for Button Plugin");
        if (popupStage != null) {
            popupStage.close();
            popupStage = null;
        }
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
}