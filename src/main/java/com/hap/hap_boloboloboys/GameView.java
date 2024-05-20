// package com.hap.hap_boloboloboys;

// import javafx.application.Application;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.StackPane;
// import javafx.stage.Stage;

// public class GameView extends Application {

//     @Override
//     public void start(@SuppressWarnings("exports") Stage stage) {
//         // Button Next
//         Image nextImage = new Image(getClass().getResourceAsStream("/assets/next.png"));
//         ImageView nextImageView = new ImageView(nextImage);
//         nextImageView.setFitWidth(200);
//         nextImageView.setFitHeight(300);

//         Button nextButton = new Button();
//         nextButton.setGraphic(nextImageView);
//         nextButton.setStyle("-fx-background-color: transparent;");
//         nextButton.setOnAction(event -> handleNextButton());

//         StackPane root = new StackPane(nextButton);
//         root.setAlignment(Pos.CENTER); 
//         Scene scene = new Scene(root, 400, 300);
//         stage.setScene(scene);
//         stage.setTitle("Game View");
//         stage.show();
//     }

//     private void handleNextButton() {
//         System.out.println("Button Next clicked!");
//         // Implementasi
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }
// }