package com.example.chatapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ToolsController extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Chat App");

        FXMLLoader loader = new FXMLLoader(ToolsController.class.getResource("tools-view.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
