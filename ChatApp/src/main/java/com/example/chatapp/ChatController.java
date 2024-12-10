package com.example.chatapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class ChatController extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chat App");

        HBox hbox = new HBox();
        Button serverButton = new Button("I am Server");
        Button clientButton = new Button("I am Client");
        hbox.getChildren().addAll(serverButton, clientButton);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(hbox);


        addHoverSound(serverButton);
        addHoverSound(clientButton);

        Scene choiceScene = new Scene(root,400,100, Color.BLACK);
        choiceScene.setCursor(Cursor.HAND);

        // Load the FXML file
        FXMLLoader chatLoader = new FXMLLoader(ChatController.class.getResource("chat-view.fxml"));
        Scene chatScene = new Scene(chatLoader.load());

        // Set the scene and make the window resizable
        primaryStage.setScene(choiceScene);
        primaryStage.setResizable(false);
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


        //Button action handler
        serverButton.setOnAction(event -> {
            try{
                Gui gui = new Gui();
                gui.start(primaryStage);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        });



    }











    // Helper method to add sound effect to a button
    private void addHoverSound(Button button) {
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            // Play a system beep sound
            try {
                Media sound = new Media(new File("C:\\Users\\Muhammad Eman\\Desktop\\TermProject\\ChatApp\\src\\main\\resources\\com\\example\\chatapp\\sounds\\hover.mp3").toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
            } catch (Exception e) {
                System.out.println("Error playing sound: " + e.getMessage());
            }

            System.out.println("hovered");
        });
    }


}
