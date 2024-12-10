package com.example.chatapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.File;
import java.io.IOException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Selection{

    private BorderPane root = new BorderPane();
    @FXML
    AnchorPane anchorSelection;

    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Selection");
        FXMLLoader selectionLoader = new FXMLLoader(Selection.class.getResource("selection-view.fxml"));

        // Load initial content (Chat or selection screen)
        root.setCenter(selectionLoader.load());

        Button chatButton = (Button) root.lookup("#chatAppButton");
        Button creatorButton = (Button) root.lookup("#creatorsButton");
        Button toolButton = (Button) root.lookup("#toolsButton");

        //adding sounds
        addHoverSound(chatButton);
        addHoverSound(toolButton);
        addHoverSound(creatorButton);



        // Handle "BackSpace" key for moving back to previous window
        root.setOnKeyPressed(event -> {
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

        Scene scene = new Scene(root, 600,600 );
        createReactiveBackground(scene);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @FXML
    Button toolsButton;
    @FXML
    Button chatAppButton;
    @FXML
    Button creatorsButton;

    public void tools(ActionEvent event) {
        System.out.println("tools clicked");


    }

    public void chat(ActionEvent event) {
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

    private void createReactiveBackground(Scene scene) {
        // Create a rectangle to serve as the background
        Rectangle background = new Rectangle(1920, 1080);
        background.setFill(Color.LIGHTBLUE);
        root.getChildren().add(0, background);

        // Add a mouse move listener to change the background color
        scene.setOnMouseMoved(event -> {
            double x = event.getX();
            double y = event.getY();

            // Calculate the color based on mouse position
            double red = Math.min(1.0, x / 600);
            double blue = Math.min(1.0, y / 600);
            double green = (red + blue) / 2;

            background.setFill(Color.color(red, green, blue));
        });
    }
}

//        try {
//            // Load the Chat view and set it in the center of the BorderPane
////            FXMLLoader chatLoader = new FXMLLoaderXMLLoader(getClass().getResource("chat-view.fxml"));
////            Node chatContent = chatLoader.load();
////            root.setCenter(chatContent);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
