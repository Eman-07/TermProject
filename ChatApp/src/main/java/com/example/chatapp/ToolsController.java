package com.example.chatapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

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

        Image img1 = new Image(getClass().getResource("/com/example/chatapp/images/bmi.png").toExternalForm());
        ImageView view1 = new ImageView(img1);
        view1.setFitHeight(200);
        view1.setFitWidth(200);

        Image img2 = new Image(getClass().getResource("/com/example/chatapp/images/weather.png").toExternalForm());
        ImageView view2 = new ImageView(img2);
        view2.setFitHeight(200);
        view2.setFitWidth(200);

        Image img3 = new Image(getClass().getResource("/com/example/chatapp/images/translator.png").toExternalForm());
        ImageView view3 = new ImageView(img3);
        view3.setFitHeight(200);
        view3.setFitWidth(200);

        Image img4 = new Image(getClass().getResource("/com/example/chatapp/images/content.png").toExternalForm());
        ImageView view4 = new ImageView(img4);
        view4.setFitHeight(200);
        view4.setFitWidth(200);

        Image img5 = new Image(getClass().getResource("/com/example/chatapp/images/plagrism.png").toExternalForm());
        ImageView view5 = new ImageView(img5);
        view5.setFitHeight(200);
        view5.setFitWidth(200);

        Image img6 = new Image(getClass().getResource("/com/example/chatapp/images/textToSpeech.png").toExternalForm());
        ImageView view6 = new ImageView(img6);
        view6.setFitHeight(200);
        view6.setFitWidth(200);



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
        Button button2 = new Button();
        button2.setGraphic(view2);
        Button button3 = new Button();
        button3.setGraphic(view3);
        Button button4 = new Button();
        button4.setGraphic(view4);
        Button button5 = new Button();
        button5.setGraphic(view5);
        Button button6 = new Button();
        button6.setGraphic(view6);

        // Add buttons to the grid, specifying their row and column positions
        gridPane.add(button1, 0, 0); // Column 0, Row 0
        gridPane.add(button2, 1, 0); // Column 1, Row 0
        gridPane.add(button3, 2, 0); // Column 2, Row 0
        gridPane.add(button4, 0, 1); // Column 0, Row 1
        gridPane.add(button5, 1, 1); // Column 1, Row 1
        gridPane.add(button6, 2, 1); // Column 2, Row 1

        // Create the scene and set it in the stage
        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.show();



    }
}
