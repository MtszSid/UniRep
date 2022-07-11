package com.example.collatz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader logInLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene logInScene = new Scene(logInLoader.load(), 620, 240);

        FXMLLoader CollatzLoader = new FXMLLoader(HelloApplication.class.getResource("collatz-view.fxml"));
        Scene CollatzScene = new Scene(CollatzLoader.load(), 620, 240);

        LoginController ctrl =  logInLoader.getController();
        ctrl.SetCollatzScene(CollatzScene);

        stage.setTitle("Collatz!");
        stage.setScene(logInScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}