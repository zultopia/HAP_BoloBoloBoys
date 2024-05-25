package com.hap.hap_boloboloboys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeView extends Application {
    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeView.class.getResource("/com/hap/hap_boloboloboys/HomeView.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 1445, 900);
        scene.getStylesheets().add(getClass().getResource("/css/main-styles.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/css/colors.css").toExternalForm());

        stage.setTitle("Hap App");
        stage.setScene(scene);

        stage.setWidth(1445);
        stage.setHeight(900);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}