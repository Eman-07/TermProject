package com.example.chatapp;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
import javafx.stage.Stage;

import java.io.IOException;

public class ToolsController extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("TOOLS");
//
//        BorderPane root = new BorderPane();
//
//        FXMLLoader loader = new FXMLLoader(ToolsController.class.getResource("tools-view.fxml"));
//
//        root.setCenter(loader.load());
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//
//
//        // Handle "BackSpace" key for moving back to previous window
//        root.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.BACK_SPACE) {
//                Selection selection = new Selection();
//                try {
//                    selection.start(primaryStage);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });

        // Create a background image
        Image backgroundImage = new Image(getClass().getResource("/com/example/chatapp/images/SGB.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        // Create the StackPane and set the background
        StackPane root = new StackPane();
        root.setBackground(new Background(background));

        Image img1 = new Image(getClass().getResource("/com/example/chatapp/images/bmi.png").toExternalForm());
        ImageView view1 = new ImageView(img1);
        view1.setFitHeight(160);
        view1.setFitWidth(160);

        Image img2 = new Image(getClass().getResource("/com/example/chatapp/images/weather.png").toExternalForm());
        ImageView view2 = new ImageView(img2);
        view2.setFitHeight(160);
        view2.setFitWidth(160);

        Image img3 = new Image(getClass().getResource("/com/example/chatapp/images/translator.png").toExternalForm());
        ImageView view3 = new ImageView(img3);
        view3.setFitHeight(160);
        view3.setFitWidth(160);

        Image img4 = new Image(getClass().getResource("/com/example/chatapp/images/content.png").toExternalForm());
        ImageView view4 = new ImageView(img4);
        view4.setFitHeight(160);
        view4.setFitWidth(160);

        Image img5 = new Image(getClass().getResource("/com/example/chatapp/images/plagrism.png").toExternalForm());
        ImageView view5 = new ImageView(img5);
        view5.setFitHeight(160);
        view5.setFitWidth(160);

        Image img6 = new Image(getClass().getResource("/com/example/chatapp/images/textToSpeech.png").toExternalForm());
        ImageView view6 = new ImageView(img6);
        view6.setFitHeight(160);
        view6.setFitWidth(160);



        GridPane gridPane = new GridPane();

        // Set spacing between buttons and padding around the grid
        gridPane.setHgap(40);  // Horizontal gap between buttons
        gridPane.setVgap(40);  // Vertical gap between rows
        //gridPane.setPadding(new Insets(20)); // Padding around the grid

        // Set alignment to center for the entire grid
        gridPane.setAlignment(Pos.CENTER);

        // Create six buttons
        Button button1 = new Button();
        button1.setGraphic(view1);
        button1.setStyle("-fx-font-size: 14px; -fx-padding: 10 15; -fx-background-radius: 15; -fx-border-radius: 15;");
        Button button2 = new Button();
        button2.setGraphic(view2);
        button2.setStyle("-fx-font-size: 14px; -fx-padding: 10 15; -fx-background-radius: 15; -fx-border-radius: 15;");
        Button button3 = new Button();
        button3.setGraphic(view3);
        button3.setStyle("-fx-font-size: 14px; -fx-padding: 10 15; -fx-background-radius: 15; -fx-border-radius: 15;");
        Button button4 = new Button();
        button4.setGraphic(view4);
        button4.setStyle("-fx-font-size: 14px; -fx-padding: 10 15; -fx-background-radius: 15; -fx-border-radius: 15;");
        Button button5 = new Button();
        button5.setGraphic(view5);
        button5.setStyle("-fx-font-size: 14px; -fx-padding: 10 15; -fx-background-radius: 15; -fx-border-radius: 15;");
        Button button6 = new Button();
        button6.setGraphic(view6);
        button6.setStyle("-fx-font-size: 14px; -fx-padding: 10 15; -fx-background-radius: 15; -fx-border-radius: 15;");

        // Add buttons to the grid, specifying their row and column positions
        gridPane.add(button1, 0, 0); // Column 0, Row 0
        gridPane.add(button2, 1, 0); // Column 1, Row 0
        gridPane.add(button3, 2, 0); // Column 2, Row 0
        gridPane.add(button4, 0, 1); // Column 0, Row 1
        gridPane.add(button5, 1, 1); // Column 1, Row 1
        gridPane.add(button6, 2, 1); // Column 2, Row 1

        root.getChildren().add(gridPane);
        // Create the scene and set it in the stage
        Scene scene = new Scene(root, 1200, 600);


//        // Get the scene's width and height properties
//        ReadOnlyDoubleProperty widthProperty = scene.widthProperty();
//        ReadOnlyDoubleProperty heightProperty = scene.heightProperty();
//
//        // Bind the fitWidth and fitHeight of the images to the scene's width and height (as percentages)
//        view1.fitWidthProperty().bind(widthProperty.multiply(0.2));  // 20% of the scene width
//        view1.fitHeightProperty().bind(heightProperty.multiply(0.3)); // 20% of the scene height
//
//        view2.fitWidthProperty().bind(widthProperty.multiply(0.2));  // 20% of the scene width
//        view2.fitHeightProperty().bind(heightProperty.multiply(0.3)); // 20% of the scene height
//
//        view3.fitWidthProperty().bind(widthProperty.multiply(0.2));  // 20% of the scene width
//        view3.fitHeightProperty().bind(heightProperty.multiply(0.3)); // 20% of the scene height
//
//        view4.fitWidthProperty().bind(widthProperty.multiply(0.2));  // 20% of the scene width
//        view4.fitHeightProperty().bind(heightProperty.multiply(0.3)); // 20% of the scene height
//
//        view5.fitWidthProperty().bind(widthProperty.multiply(0.2));  // 20% of the scene width
//        view5.fitHeightProperty().bind(heightProperty.multiply(0.3)); // 20% of the scene height
//
//        view6.fitWidthProperty().bind(widthProperty.multiply(0.2));  // 20% of the scene width
//        view6.fitHeightProperty().bind(heightProperty.multiply(0.3)); // 20% of the scene height




        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.show();

        addHoverSound(button1);
        addHoverSound(button2);
        addHoverSound(button3);
        addHoverSound(button4);
        addHoverSound(button5);
        addHoverSound(button6);

        addHoverEffect(button1);
        addHoverEffect(button2);
        addHoverEffect(button3);
        addHoverEffect(button4);
        addHoverEffect(button5);
        addHoverEffect(button6);



    }


    // Helper method to add sound effect to a button
    private void addHoverSound(Button button) {
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            // Play a system beep sound
            try {
                // Media sound = new Media(new File("C:\\Users\\Muhammad Eman\\Desktop\\TermProject\\ChatApp\\src\\main\\resources\\com\\example\\chatapp\\sounds\\hover.mp3").toURI().toString());
                Media sound = new Media(getClass().getResource("/com/example/chatapp/sounds/hover.mp3").toExternalForm());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
            } catch (Exception e) {
                System.out.println("Error playing sound: " + e.getMessage());
            }

            System.out.println("hovered");
        });
    }


    //METHOD TO ADD HOVER EFFECT
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
