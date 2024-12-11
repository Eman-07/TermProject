//package com.example.chatapp;
//
//import javafx.scene.control.Alert;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class Server extends Gui implements Runnable {
//
//    private ServerSocket serverSocket;
//    private PrintWriter out;
//    private int port;
//    private Gui gui;
//
//    public Server(int port, Gui gui) {
//        this.port = port;
//        this.gui = gui;
//    }
//
//    @Override
//    public void run() {
//        try {
//            serverSocket = new ServerSocket(port);
//            gui.getMessageDisplayArea().appendText("Server started on port " + port + "\n");
//
//            Socket clientSocket = serverSocket.accept();
//            gui.getMessageDisplayArea().appendText("Client connected: " + clientSocket.getInetAddress() + "\n");
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                gui.getMessageDisplayArea().appendText("Client: " + inputLine + "\n");
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (serverSocket != null) {
//                    serverSocket.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        Server server = new Server(9090, new Gui());
//    }
//}
package com.example.chatapp;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Gui implements Runnable {
    private ServerSocket serverSocket;
    private PrintWriter out;
    private int port;

    private String contactId;
    private Contact contact;
    private int contactIndex;

    @Override
    public void run() {
        contactIndex = getContactIndex(contact);

//        try {
//            getChatArea().append("Waiting for " +contact.getName() +" connection...\n");
//            Socket clientSocket = serverSocket.accept();
//            getChatArea().append(contact.getName()+" connected.\n");
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                getChatArea().append(contact.getName()+": " + inputLine + "\n");
//
//                getContacts().get(contactIndex).getChatHistory().add(new Sms(inputLine,contact.getName()));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
                getContactList().clear();
                getMessageDisplayArea().setText("Chatting with: " + selectedContact + "\n");

                contact = findContactByName(selectedContact);

                // Optionally, add more logic for initializing the chat with the selected contact
                System.out.println("Chat initialized with " + selectedContact);
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