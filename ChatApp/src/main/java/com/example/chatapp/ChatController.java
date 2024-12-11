package com.example.chatapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.Serializable;

public class ChatController extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chat App");


        String videoPath = new File("src/main/resources/com/example/chatapp/videos/bgVideo.mp4").toURI().toString();
        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
//
//        // Adjust MediaView to cover the window
        mediaView.setFitWidth(600);
        mediaView.setFitHeight(600);
        mediaView.setPreserveRatio(false);
//
//        // Start the video in a loop
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();



        Image img1 = new Image("file:images/server.jpg");
        ImageView view1 = new ImageView(img1);
        view1.setFitHeight(300);
        view1.setFitWidth(240);

        Image img2 = new Image("file:images/client .jpg");
        ImageView view2 = new ImageView(img2);
        view2.setFitHeight(300);
        view2.setFitWidth(240);




        Button serverButton = new Button();
        serverButton.setGraphic(view1);



//        HBox hbox = new HBox();
//        hbox.setAlignment(Pos.CENTER);
//        hbox.getChildren().addAll(view1, view2);



        Button clientButton = new Button();
        clientButton.setGraphic(view2);



//        Text serverText = new Text("SERVER");
//        serverText.setFont(Font.font("Verdana", 16));  // Set custom font and size
        //serverText.setFill(Color.WHITE);   // Set font and size

//        Text clientText = new Text("CLIENT");
//        clientText.setFont(Font.font("Verdana", 16));  // Set custom font and size
        //clientText.setFill(Color.WHITE);

//        VBox serverBox = new VBox();
//        serverBox.setAlignment(Pos.CENTER);
//        serverBox.getChildren().addAll(view1, serverText);
//        serverBox.setSpacing(5);

//        VBox clientBox = new VBox();
//        clientBox.setAlignment(Pos.CENTER);
//        clientBox.getChildren().addAll(view2, clientText);
//        clientBox.setSpacing(5);

        HBox hbox = new HBox();
//        hbox.setAlignment(Pos.CENTER);
//        hbox.getChildren().addAll(serverBox, clientBox);
//        hbox.setSpacing(50);




////
        hbox.getChildren().addAll(serverButton, clientButton);
        hbox.setSpacing(100);
        hbox.setAlignment(Pos.CENTER);
//
        BorderPane root = new BorderPane();
        root.setCenter(hbox);


        //creating overlay using stackpane in which background vid will play
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(mediaView, root);


        addHoverSound(serverButton);
        addHoverSound(clientButton);

        Scene choiceScene = new Scene(stackPane,800,600, Color.BLACK);
        choiceScene.setCursor(Cursor.HAND);

        // Load the FXML file
        FXMLLoader chatLoader = new FXMLLoader(ChatController.class.getResource("chat-view.fxml"));
        Scene chatScene = new Scene(chatLoader.load());

        // Set the scene and make the window resizable
        primaryStage.setScene(choiceScene);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
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
//                Gui gui = new Gui();
//                gui.start(primaryStage);
                Server server = new Server();
                server.start(primaryStage);
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