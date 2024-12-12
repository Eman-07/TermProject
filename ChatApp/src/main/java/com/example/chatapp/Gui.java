package com.example.chatapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Gui extends Application {

    private BorderPane mainLayout;
    private VBox contactVBox;
    private TextArea messageDisplayArea;
    private TextField messageInputField;
    private Button sendButton;

    public ObservableList<Contact> contactList = FXCollections.observableArrayList();
    private Contact selectedContact;

    private Map<String, Button> contactButtons = new HashMap<>();

    public Stage stage1 = new Stage();


    @Override
    public void start(Stage stage) {
        stage.setTitle("Chat Application");
        stage1 = stage;
        // Layouts
        mainLayout = new BorderPane();

        // Menu Bar
        MenuBar menuBar = new MenuBar();
        Menu optionsMenu = new Menu("Contacts");
        MenuItem addContact = new MenuItem("Add Contact");
        MenuItem deleteContact = new MenuItem("Delete Contact");
        MenuItem modifyContact = new MenuItem("Modify Contact");
        MenuItem userManual = new MenuItem("User Manual");

        Menu chatOnlineMenu = new Menu("Chat Online");
        MenuItem startChat = new MenuItem("Start Chat");
        chatOnlineMenu.getItems().add(startChat);

        optionsMenu.getItems().addAll(addContact, deleteContact, modifyContact, userManual);
        menuBar.getMenus().addAll(optionsMenu, chatOnlineMenu);
        mainLayout.setTop(menuBar);

        // Contacts Section
        contactVBox = new VBox();
        contactVBox.setPrefWidth(200);
        mainLayout.setLeft(contactVBox);

        // Chat Area
        messageDisplayArea = new TextArea();
        messageDisplayArea.setEditable(false);
        messageDisplayArea.setWrapText(true);
        mainLayout.setCenter(messageDisplayArea);

        messageInputField = new TextField();
        sendButton = new Button("Send");

        HBox inputBox = new HBox(messageInputField, sendButton);
        inputBox.setSpacing(20);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(10, 10, 10, 10));

        contactVBox.setPrefWidth(300);

        mainLayout.setBottom(inputBox);

        VBox rightBox = new VBox();
        rightBox.setPrefWidth(20);
        mainLayout.setRight(rightBox);



        // Scene
        Scene scene = new Scene(mainLayout, 1200, 600);
        stage.setScene(scene);
        stage.show();


        // menu Actions
        addContact.setOnAction(e -> showAddContactDialog());
        deleteContact.setOnAction(e -> showDeleteContactDialog());
        modifyContact.setOnAction(e -> showModifyContactDialog());
        userManual.setOnAction(e -> showUserManualDialog());
        startChat.setOnAction(e-> chatOnline());

// Handle the send button click event
        sendButton.setOnAction(e -> sendMessage());

// Handle the "Enter" key press for sending messages
        messageInputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendMessage();
            }
        });

        Image image = new Image("file:images/tools.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);
        imageView.setOpacity(100);


        dummyContacts();


        //BackSpace key for moving back to previous window
        mainLayout.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.BACK_SPACE) {
                Selection selection = new Selection();
                try {
                    ChatController chatController = new ChatController();
                    chatController.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

//
//    public void chatOnline() {
//
//        AtomicReference<String> name;
//        // Create a dialog to select a contact
//        Dialog<String> contactDialog = new ChoiceDialog<>(
//                contactList.isEmpty() ? null : contactList.get(0).getName(),
//                contactList.stream().map(Contact::getName).toList()
//        );
//        contactDialog.setTitle("Chat Online");
//        contactDialog.setHeaderText("Select a contact to chat with");
//        contactDialog.setContentText("Contact:");
//
//        // Show the dialog and get the selected contact
//        contactDialog.showAndWait().ifPresent(selectedContact -> {
//            if (selectedContact != null && !selectedContact.isEmpty()) {
//                Contact contact = findContactByName(selectedContact);
//
//                if (contact != null) {
//                    messageDisplayArea.clear();
//                    messageDisplayArea.setText("Chatting with: " + selectedContact + "\n");
//                    // Example logic to add chat history
//
//
//                    // Optionally, add more logic for initializing the chat with the selected contact
//                    System.out.println("Chat initialized with " + selectedContact);
//                    System.out.println(findContactByName(selectedContact));
//                } else {
//                    Alert alert = new Alert(Alert.AlertType.WARNING);
//                    alert.setTitle("Contact Not Found");
//                    alert.setHeaderText(null);
//                    alert.setContentText("The selected contact does not exist.");
//                    alert.showAndWait();
//                }
//            } else {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("No Contact Selected");
//                alert.setHeaderText(null);
//                alert.setContentText("Please select a valid contact to chat with.");
//                alert.showAndWait();
//            }
//        });
//    }


    public Contact chatOnline(){
        return contactList.get(0);
    }

    public void sendButton(String content) {
        sendButton.setOnAction(e -> {
            String message = messageInputField.getText();
            if (!message.isEmpty()) {
                messageDisplayArea.appendText("You: " + message + "\n");
                messageInputField.clear();
            }
        });


    }

//    public void dummyContacts() {
//        System.out.println("dummyContacts in gui");
//        Contact alice = new Contact("Alice", "123-456-7890");
//        alice.addMessage(new Sms("Hi Alice!", "You"));
//        alice.addMessage(new Sms("Hello!", "Alice"));
//
//        Contact bob = new Contact("Bob", "987-654-3210");
//        bob.addMessage(new Sms("Hey Bob, how are you?", "You"));
//        bob.addMessage(new Sms("I’m good, thanks!", "Bob"));
//
//        contactList.addAll(alice, bob);
//        updateContactButtons();
//    }

    public void dummyContacts(){}

    public void updateContactButtons() {
        contactVBox.getChildren().clear();
        contactButtons.clear();

        for (Contact contact : contactList) {
            Button contactButton = new Button(contact.getName());
            contactButton.setPrefWidth(300);
            contactButton.setCursor(Cursor.HAND);
            contactButton.setPrefHeight(50);

            // Update selectedContact when the button is clicked
            contactButton.setOnAction(e -> {
                selectedContact = contact;
                displayChatHistory(contact);
            });

            contactButtons.put(contact.getName(), contactButton);
            contactVBox.getChildren().add(contactButton);
        }
    }



    public void displayChatHistory(Contact contact) {
        StringBuilder chatHistory = new StringBuilder();
        contact.getChatHistory().forEach(sms -> chatHistory.append(sms.toString()).append("\n"));
        messageDisplayArea.setText(chatHistory.toString());
    }

    public void sendMessage() {
        // Check if a contact is selected
        if (selectedContact == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Contact Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a contact to send a message.");
            alert.showAndWait();
            return;
        }

        // Get the message content
        String messageContent = messageInputField.getText().trim();
        if (!messageContent.isEmpty()) {
            // Create and add the message to the contact's chat history
            Sms message = new Sms(messageContent, "You");
            selectedContact.addMessage(message);

            // Update the chat display
            displayChatHistory(selectedContact);

            // Clear the input field
            messageInputField.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Message");
            alert.setHeaderText(null);
            alert.setContentText("Message cannot be empty.");
            alert.showAndWait();
        }
    }


    public void showAddContactDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Contact");
        dialog.setHeaderText("Enter new contact name and number separated by a comma.");
        dialog.setContentText("Contact:");

        dialog.showAndWait().ifPresent(input -> {
            String[] parts = input.split(",");
            if (parts.length == 2) {
                Contact newContact = new Contact(parts[0].trim(), parts[1].trim());
                addContact(newContact);
            }
        });
    }

    public void showDeleteContactDialog() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>(null, contactList.stream().map(Contact::getName).toList());
        dialog.setTitle("Delete Contact");
        dialog.setHeaderText("Select a contact to delete.");
        dialog.setContentText("Contact:");

        dialog.showAndWait().ifPresent(name -> {
            Contact contactToRemove = contactList.stream()
                    .filter(contact -> contact.getName().equals(name))
                    .findFirst().orElse(null);
            if (contactToRemove != null) {
                removeContact(contactToRemove);
            }
        });
    }

    public void showModifyContactDialog() {
        ChoiceDialog<String> dialog = new ChoiceDialog<>(null, contactList.stream().map(Contact::getName).toList());
        dialog.setTitle("Modify Contact");
        dialog.setHeaderText("Select a contact to modify.");
        dialog.setContentText("Contact:");

        dialog.showAndWait().ifPresent(name -> {
            Contact contactToModify = contactList.stream()
                    .filter(contact -> contact.getName().equals(name))
                    .findFirst().orElse(null);
            if (contactToModify != null) {
                TextInputDialog modifyDialog = new TextInputDialog(contactToModify.getName() + ", " + contactToModify.getPhoneNumber());
                modifyDialog.setTitle("Modify Contact");
                modifyDialog.setHeaderText("Edit contact name and number separated by a comma.");
                modifyDialog.setContentText("Contact:");

                modifyDialog.showAndWait().ifPresent(input -> {
                    String[] parts = input.split(",");
                    if (parts.length == 2) {
                        contactToModify.setName(parts[0].trim());
                        contactToModify.setPhoneNumber(parts[1].trim());
                        updateContact(contactToModify);
                    }
                });
            }
        });
    }

    public void showUserManualDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Manual");
        alert.setHeaderText("How to use the Chat Application");
        alert.setContentText("1. Add Contact: Use the menu to add a new contact.\n" +
                "2. Delete Contact: Select a contact to delete.\n" +
                "3. Modify Contact: Edit contact details.\n" +
                "4. Send Message: Select a contact, type a message, and press send.");
        alert.showAndWait();
    }

    public void addContact(Contact contact) {
        contactList.add(contact);
        updateContactButtons();
    }

    public void removeContact(Contact contact) {
        contactList.remove(contact);
        contactButtons.remove(contact.getName());
        updateContactButtons();
    }

    public void updateContact(Contact contact) {
        removeContact(contact);
        addContact(contact);
    }



    public TextArea getMessageDisplayArea() {
        return messageDisplayArea;
    }

    public TextField getMessageInputField() {
        return messageInputField;
    }

    public void setMessageDisplayArea(TextArea messageDisplayArea) {
        this.messageDisplayArea = messageDisplayArea;
    }

    public ObservableList<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(ObservableList<Contact> contactList) {
        this.contactList = contactList;
    }

    public Map<String, Button> getContactButtons() {
        return contactButtons;
    }

    public void setContactButtons(Map<String, Button> contactButtons) {
        this.contactButtons = contactButtons;
    }



    public Contact findContactByName(String contactName) {
        for (Contact contact : contactList) {
            if (contact.getName().equals(contactName)) {
                return contact;
            }
        }
        return null;
    }

    public int getContactIndex(Contact contact) {
        return contactList.indexOf(contact);
    }


    public void allContacts(){
        for (Contact contact : contactList) {
            System.out.println(contact.getName());
        }
    }


}
