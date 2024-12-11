package com.example.chatapp;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

public class Client extends Gui implements Runnable {

    private Socket socket; // Changed to Socket for client-side connection
    private PrintWriter out;

    private String serverAddress;
    private int port = 12345;

    private String contactId;
    private Contact contact;
    private int contactIndex;

    @Override
    public void run() {
        contactIndex = getContactIndex(contact);

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                getMessageDisplayArea().appendText(contact.getName()+" : " + inputLine + "\n");
                getContactList().get(contactIndex).getChatHistory().add(new Sms(inputLine,contact.getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Contact chatOnline() {
        contact = super.chatOnline(); // Choose the contact to chat with

        // Create a TextInputDialog
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Input Alert");
        textInputDialog.setHeaderText("Enter a value");
        textInputDialog.setContentText("Please enter your message:");
        textInputDialog.showAndWait();

        serverAddress = textInputDialog.getEditor().getText();

        if (contact != null) {
            contactIndex = getContactIndex(contact);
            try {
                socket = new Socket(serverAddress, port); // Client connects to the server
                new Thread(this).start(); // Start client thread
                getMessageInputField().appendText("Successfully connected to " + contact.getName()+"\n");
            } catch (IOException e) {
                getMessageInputField().appendText("Failed to connect to " + contact.getName()+"\n");
                System.out.println("Error Agya: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void sendButton(String message) {
        if (out != null && message != null && !message.isEmpty()) {
            // Send the message to the client
            out.println(message);

            // Append the sent message to the display area
            getMessageDisplayArea().appendText("Me: " + message + "\n");

            // Add the sent message to the chat history
            getContactList().get(contactIndex).addMessage(new Sms(message, "You"));
        }
    }

    @Override
    public void sendMessage() {
        // Get the message content
        String messageContent = getMessageInputField().getText().trim();
        if (!messageContent.isEmpty()) {
            // Send the message using the sendButton logic
            sendButton(messageContent);

            // Clear the input field
            getMessageInputField().clear();
        } else {
            // Show a warning if the message is empty
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Message");
            alert.setHeaderText(null);
            alert.setContentText("Message cannot be empty.");
            alert.showAndWait();
        }
    }

    public void dummyContacts() {
        getContactList().add(new Contact("eman", "05213510"));
        getContactList().add(new Contact("sami", "05213510"));
        getContactList().add(new Contact("moeed", "05213510"));
        updateContactButtons();
    }
}
