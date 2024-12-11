package com.example.chatapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Selection extends Application {

//    private BorderPane root = new BorderPane();

    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Selection");

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(40);

        // Load initial content (Chat or selection screen)
//        root.setCenter(selectionLoader.load());

        Button chatButton = new Button("");
        chatButton.setStyle("-fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 15; -fx-border-radius: 15;");
        Button creatorButton = new Button("");
        creatorButton.setStyle("-fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 15; -fx-border-radius: 15;");
        Button toolButton = new Button("");
        toolButton.setStyle("-fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 15; -fx-border-radius: 15;");

        //adding sounds
        addHoverSound(chatButton);
        addHoverSound(toolButton);
        addHoverSound(creatorButton);




        hbox.getChildren().addAll(chatButton, toolButton, creatorButton);

        Scene scene = new Scene(hbox, 900,300 );
//        createReactiveBackground(scene);

        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Create a rectangle with rounded corners
//        Rectangle clip = new Rectangle(200, 200);
//        clip.setArcWidth(50);
//        clip.setArcHeight(50);

        Image tools = new Image("file:images/tools.png");
        ImageView toolImageView = new ImageView(tools);
        toolImageView.setFitHeight(200);
        toolImageView.setFitWidth(200);
//        toolImageView.setClip(clip);
        toolButton.setGraphic(toolImageView);

        Image creator = new Image("file:images/creators.png");
        ImageView creatorImageView = new ImageView(creator);
        creatorImageView.setFitHeight(200);
        creatorImageView.setFitWidth(200);
        creatorButton.setGraphic(creatorImageView);

        Image chat = new Image("file:images/chathub.png");
        ImageView chatImageView = new ImageView(chat);
        chatImageView.setFitHeight(200);
        chatImageView.setFitWidth(200);
        chatButton.setGraphic(chatImageView);


        addHoverEffect(chatButton);
        addHoverEffect(toolButton);
        addHoverEffect(creatorButton);


        // Handle "BackSpace" key for moving back to previous window
        hbox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.BACK_SPACE) {
                Login login = new Login();
                try {
                    login.start(primaryStage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });



        toolButton.setOnAction(e -> {
            try {
                ToolsController toolsController = new ToolsController();
                toolsController.start(primaryStage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });




        chatButton.setOnAction(e -> {
            try {
                ChatController chatController = new ChatController();
                chatController.start(primaryStage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
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




        //creating function to control scaling of button

    }

//    private void createReactiveBackground(Scene scene) {
//        // Create a rectangle to serve as the background
//        Rectangle background = new Rectangle(1920, 1080);
//        background.setFill(Color.LIGHTBLUE);
//        .getChildren().add(0, background);
//
//        // Add a mouse move listener to change the background color
//        scene.setOnMouseMoved(event -> {
//            double x = event.getX();
//            double y = event.getY();
//
//            // Calculate the color based on mouse position
//            double red = Math.min(1.0, x / 600);
//            double blue = Math.min(1.0, y / 600);
//            double green = (red + blue) / 2;
//
//            background.setFill(Color.color(red, green, blue));
//        });
//    }


    // Helper method to add hover effect to a button
    private void addHoverEffect(Button button) {
        button.setOnMouseEntered(e -> {
            button.setScaleX(1.1); // Increase size horizontally
            button.setScaleY(1.1); // Increase size vertically
        });

        button.setOnMouseExited(e -> {
            button.setScaleX(1.0); // Reset size horizontally
            button.setScaleY(1.0); // Reset size vertically
        });
    }
}

//        try {
//            // Load the Chat view and set it in the center of the BorderPane
////            FXMLLoader chatLoader = new FXMLLoaderXMLLoader(getClass().getResource("gui-view.fxml"));
////            Node chatContent = chatLoader.load();
////            root.setCenter(chatContent);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
