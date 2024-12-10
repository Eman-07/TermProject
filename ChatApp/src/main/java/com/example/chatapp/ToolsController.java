package com.example.chatapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ToolsController extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Chat App");

        BorderPane root = new BorderPane();

        FXMLLoader loader = new FXMLLoader(ToolsController.class.getResource("tools-view.fxml"));

        root.setCenter(loader.load());
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();



        // Handle "BackSpace" key for moving back to previous window
        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.BACK_SPACE) {
                Selection selection = new Selection();
                try {
                    selection.start(primaryStage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
