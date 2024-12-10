module com.example.chatapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens com.example.chatapp to javafx.fxml;
    exports com.example.chatapp;
}