package com.example.chatapp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Selection2 {
    public void start(Stage primaryStage) throws Exception {

        Image img = new Image("file:images/2.png");
        ImageView view = new ImageView(img);

        Button chatApp = new Button();
        chatApp.setGraphic(view);

        Image img2 = new Image("file:images/2 (2).png");
        ImageView view2 = new ImageView(img2);

        Button tools = new Button();
        tools.setGraphic(view2);

        Image img3 = new Image("file:images/2 (3).png");
        ImageView view3 = new ImageView(img3);

        Button creators = new Button();

        creators.setGraphic(view3);

        HBox hbox = new HBox(chatApp,tools, creators);
        hbox.getChildren().addAll(chatApp,tools, creators);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(hbox);

        Scene sc = new Scene(hbox, 800,600, Color.BLACK);

        sc.setCursor(Cursor.HAND);

        primaryStage.setScene(sc);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        primaryStage.show();




    }


}
