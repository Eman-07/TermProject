package com.example.chatapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Gui {
    Stage stage;

    public void start(Stage stage) throws IOException {
        stage.setTitle("Chat App");
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("Gui-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

}
