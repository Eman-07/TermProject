package com.example.chatapp;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//package com.example.chatapp;
public class Server extends Gui implements Runnable {

    private ServerSocket serverSocket;
    private PrintWriter out;
    private int port;

    private Contact contact;

    public Server(int port){
        this.port = port;
    }


    @Override
    public void run() {

        try {
            getMessageDisplayArea().setText("Waiting for " +contact.getName() +" connection...\n");
            Socket clientSocket = serverSocket.accept();
            getMessageDisplayArea().setText(contact.getName()+" connected.\n");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                getMessageDisplayArea().setText(contact.getName()+": " + inputLine + "\n");

                getContactList().get(getContactIndex(contact)).getChatHistory().add(new Sms(inputLine,contact.getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void chatOnline() {
        // Create a dialog to select a contact
        Dialog<String> contactDialog = new ChoiceDialog<>(
                getContactList().isEmpty() ? null : getContactList().get(0).getName(),
                getContactList().stream().map(Contact::getName).toList()
        );
        contactDialog.setTitle("Chat Online");
        contactDialog.setHeaderText("Select a contact to chat with");
        contactDialog.setContentText("Contact:");

        // Show the dialog and get the selected contact
        contactDialog.showAndWait().ifPresent(selectedContact -> {
            if (selectedContact != null && !selectedContact.isEmpty()) {
                getMessageDisplayArea().clear();
                getMessageDisplayArea().setText("Chatting with: " + selectedContact + "\n");
                contact = getContactList().stream().filter(contact -> contact.getName().equals(selectedContact)).findFirst().orElse(null);
                // Optionally, add more logic for initializing the chat with the selected contact
                System.out.println("Chat initialized with " + selectedContact);
                run();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Contact Selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select a valid contact to chat with.");
                alert.showAndWait();
            }
        });
    }

}
