package com.example.chatapp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;

public class Login extends Application {
    private BorderPane root = new BorderPane();

    @Override
    public void start(Stage stage) throws Exception {
        initials();

        stage.setTitle("Chat App - Login");
        VBox layout = new VBox();

        // Labels and fields
        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #fffefe;");

        TextField usernameField = new TextField();
        usernameField.setMaxWidth(200);
        usernameField.setMinWidth(50);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #fffefe;");

        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(200);
        passwordField.setMinWidth(50);
        passwordField.setAlignment(Pos.CENTER);

        // Buttons
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        HBox loginStuff = new HBox();
        loginStuff.getChildren().addAll(loginButton, registerButton);
        loginStuff.setAlignment(Pos.CENTER);
        loginStuff.setSpacing(10);

        // Layout
        layout.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginStuff);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        layout.setStyle("-fx-background-color: #000000");

        // Login button action
        loginButton.setOnAction(e -> validateLogin(stage, usernameField, passwordField));

        // Register button action
        registerButton.setOnAction(e -> {
            try {
                register();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Handle "Enter" key for login
        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                validateLogin(stage, usernameField, passwordField);
            }
        });

        root.setCenter(layout);
        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    // Method to validate user login
    public void validateLogin(Stage stage, TextField usernameField, PasswordField passwordField) {
        try {
            if (validateUser(usernameField.getText(), passwordField.getText())) {
                stage.close();
                Selection selection = new Selection();
                selection.start(stage);
            } else {
                showAlert("Error", "Invalid username or password!", Alert.AlertType.ERROR);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void register() throws IOException {
        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.setTitle("Register");

        // Create UI components
        Label label1 = new Label("Username:");
        label1.setStyle("-fx-font-size: 18px; -fx-text-fill: #fffefe;");

        TextField textField1 = new TextField();
        textField1.setMaxWidth(200);
        textField1.setMinWidth(50);

        Label label2 = new Label("Password:");
        label2.setStyle("-fx-font-size: 18px; -fx-text-fill: #fffefe;");
        PasswordField textField2 = new PasswordField();
        textField2.setMaxWidth(200);
        textField2.setMinWidth(50);

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            try {
                if (isUserExists(textField1.getText())) {
                    showAlert("Error", "Username already exists!", Alert.AlertType.ERROR);
                } else {
                    // Write the new user to the file
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("Users.txt", true))) {
                        writer.write(textField1.getText() + ":" + textField2.getText());
                        writer.newLine();
                    }
                    showAlert("Success", "User registered successfully!", Alert.AlertType.INFORMATION);
                    alertStage.close();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> alertStage.close());

        VBox registerBox = new VBox();
        registerBox.getChildren().addAll(label1, textField1, label2, textField2, okButton, cancelButton);
        registerBox.setAlignment(Pos.CENTER);
        registerBox.setSpacing(10);
        registerBox.setStyle("-fx-background-color: #000000");

        Scene scene = new Scene(registerBox, 300, 200);
        alertStage.setScene(scene);
        alertStage.showAndWait();
    }

    // Helper method to validate user credentials
    public boolean validateUser(String username, String password) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("Users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(":");
                if (credentials.length == 2 && credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Helper method to check if a username already exists
    private boolean isUserExists(String username) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("Users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(":");
                if (credentials.length > 0 && credentials[0].equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Helper method to show alerts
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Initialize the user data file
    public void initials() throws IOException {
        File userData = new File("Users.txt");
        if (!userData.exists()) {
            userData.createNewFile();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
