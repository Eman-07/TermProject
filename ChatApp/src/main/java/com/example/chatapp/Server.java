package com.example.chatapp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
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
    private int port = 12345;

    private String contactId;
    private Contact contact;
    private int contactIndex;
    private String contactofInterestName;


    @Override
    public void run() {
        contactIndex = getContactIndex(contact);
        System.out.println("the index is : " +contactIndex);

        try {
            getMessageDisplayArea().setText("Waiting for " +contact.getName() +" connection...\n");
            Socket clientSocket = serverSocket.accept();
            getMessageDisplayArea().setText(contact.getName()+" connected.\n");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                getMessageDisplayArea().setText(contact.getName()+": " + inputLine + "\n");

                getContactList().get(contactIndex).getChatHistory().add(new Sms(inputLine,contact.getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public Contact chatOnline(){
        contact = super.chatOnline();
//        run();


        try {
            serverSocket = new ServerSocket(port);
            new Thread(this).start(); // Start server thread
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void test(){
        System.out.println(contactofInterestName);
        System.out.println(findContactByName(contactofInterestName));
        System.out.println(findContactByName("eman"));

    }

    public void dummyContacts(){
        System.out.println("Dummy contacts in server");
        getContactList().add(new Contact("eman","05213510"));
        getContactList().add(new Contact("sami","05213510"));
        getContactList().add(new Contact("moeed","05213510"));
        updateContactButtons();

        allContacts();
    }



    @Override
    public void sendButton(String message) {
        if (out != null) {
            out.println(message);
            getMessageDisplayArea().setText("Me: " + message + "\n");
        }

        getContactList().get(contactIndex).getChatHistory().add(new Sms(message,"You"));



    }


//    public Contact findContactByName(String contactName , ObservableList<Contact> contacts) {
//        for (Contact c : contacts) {
//            if (c.getName().equals(contactName)) {
//                return c;
//            }
//        }
//        return null;
//    }


}
