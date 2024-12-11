package com.example.chatapp;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Gui implements Runnable {
    private ServerSocket serverSocket;
    private PrintWriter out;
    private BufferedReader in;
    private int port = 12345;

    private Contact contact;
    private int contactIndex;

    @Override
    public void run() {
        contactIndex = getContactIndex(contact);
        try {
            // Wait for a client connection
            getMessageDisplayArea().appendText("Waiting for " + contact.getName() + " to connect...\n");
            Socket clientSocket = serverSocket.accept();
            getMessageDisplayArea().appendText(contact.getName() + " connected.\n");

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Continuously read messages from the client
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // Append the received message to the display area
                getMessageDisplayArea().appendText(contact.getName() + ": " + inputLine + "\n");

                // Add the received message to the chat history
                Sms receivedMessage = new Sms(inputLine, contact.getName());
                getContactList().get(contactIndex).addMessage(receivedMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Contact chatOnline() {
        contact = super.chatOnline(); // Choose the contact to chat with
        if (contact != null) {
            contactIndex = getContactIndex(contact);
            try {
                serverSocket = new ServerSocket(port);
                new Thread(this).start(); // Start the server thread
            } catch (IOException e) {
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
        getContactList().add(new Contact("Eman", "05213510"));
        getContactList().add(new Contact("Sami", "05213510"));
        getContactList().add(new Contact("Moeed", "05213510"));
        updateContactButtons();
    }
}
