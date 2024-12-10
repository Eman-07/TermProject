package com.example.chatapp;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

//import javax.print.attribute.standard.Media;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Selection extends Application {

    private BorderPane root = new BorderPane();
    @FXML
    AnchorPane anchorSelection;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Tools Selection");

        FXMLLoader selectionLoader = new FXMLLoader(Selection.class.getResource("selection-view.fxml"));

        Button forwardButton = new Button("Forward");
        Button backButton = new Button("Back");
        HBox backforward = new HBox();
        backforward.getChildren().addAll(backButton, forwardButton);
        backforward.setSpacing(10);
        backforward.setAlignment(Pos.BASELINE_RIGHT);

        // Load initial content (Chat or selection screen)
        root.setCenter(selectionLoader.load());
        root.setBottom(backforward);

        Button chatButton = (Button) root.lookup("#chatAppButton");
        Button creatorButton = (Button) root.lookup("#creatorsButton");
        Button toolButton = (Button) root.lookup("#toolsButton");


        //adding sounds
        addHoverSound(chatButton);
        addHoverSound(toolButton);
        addHoverSound(creatorButton);

        //apply animation on anchor
//        anchorSelection.setStyle("-fx-background-color: linear-gradient(to bottom, #1e3c72, #2a5298);");

        toolButton.setOnAction(e -> {
            root.setCenter(null);
            // Load the Tools view and set it in the center of the BorderPane
            FXMLLoader toolsLoader = new FXMLLoader(getClass().getResource("tools-view.fxml"));
            Node toolsContent = null;
            try {
                toolsContent = toolsLoader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            root.setCenter(toolsContent);

        });


        chatButton.setOnAction(e -> {
            try {
                chatOpen();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Scene scene = new Scene(root, 600,600 );
        createReactiveBackground(scene);
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




    public void chatOpen() throws IOException {
        root.setCenter(null);
        FXMLLoader chatLoader = new FXMLLoader(Selection.class.getResource("chat-view.fxml"));
        root.setCenter(chatLoader.load());
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
