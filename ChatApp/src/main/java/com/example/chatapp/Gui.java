package com.example.chatapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Gui extends Application {

    private BorderPane mainLayout;
    private VBox contactVBox;
    private TextArea messageDisplayArea;
    private TextField messageInputField;
    private Button sendButton;

    private ObservableList<Contact> contactList = FXCollections.observableArrayList();
    private Map<String, Button> contactButtons = new HashMap<>();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Chat Application");

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
        inputBox.setAlignment(Pos.BASELINE_CENTER);

        mainLayout.setBottom(inputBox);


        // Scene
        Scene scene = new Scene(mainLayout, 800, 600);
        stage.setScene(scene);
        stage.show();

        // Initialize Data
        setupContacts();
        configureSendButton();

        // Menu Actions
        addContact.setOnAction(e -> showAddContactDialog());
        deleteContact.setOnAction(e -> showDeleteContactDialog());
        modifyContact.setOnAction(e -> showModifyContactDialog());
        userManual.setOnAction(e -> showUserManualDialog());

            //chatOnlineAction
        startChat.setOnAction(e->chatOnline());
    }

    public static void main(String[] args) {
        launch(args);
    }


    public void chatOnline() {
        // Create a dialog to select a contact
        Dialog<String> contactDialog = new ChoiceDialog<>(
                contactList.isEmpty() ? null : contactList.get(0).getName(),
                contactList.stream().map(Contact::getName).toList()
        );
        contactDialog.setTitle("Chat Online");
        contactDialog.setHeaderText("Select a contact to chat with");
        contactDialog.setContentText("Contact:");

        // Show the dialog and get the selected contact
        contactDialog.showAndWait().ifPresent(selectedContact -> {
            if (selectedContact != null && !selectedContact.isEmpty()) {
                messageDisplayArea.clear();
                messageDisplayArea.setText("Chatting with: " + selectedContact + "\n");

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


    private void setupContacts() {
        Contact alice = new Contact("Alice", "123-456-7890");
        alice.addMessage(new Sms("Hi Alice!", "You"));
        alice.addMessage(new Sms("Hello!", "Alice"));

        Contact bob = new Contact("Bob", "987-654-3210");
        bob.addMessage(new Sms("Hey Bob, how are you?", "You"));
        bob.addMessage(new Sms("I’m good, thanks!", "Bob"));

        contactList.addAll(alice, bob);
        updateContactButtons();
    }

    private void updateContactButtons() {
        contactVBox.getChildren().clear();
        contactButtons.clear();

        for (Contact contact : contactList) {
            Button contactButton = new Button(contact.getName());
            contactButton.setPrefWidth(contactVBox.getPrefWidth());
            contactButton.setOnAction(e -> displayChatHistory(contact));

            contactButtons.put(contact.getName(), contactButton);
            contactVBox.getChildren().add(contactButton);
        }
    }

    private void configureSendButton() {
        sendButton.setOnAction(e -> sendMessage());
        messageInputField.setOnAction(e -> sendMessage());
    }

    private void displayChatHistory(Contact contact) {
        StringBuilder chatHistory = new StringBuilder();
        contact.getChatHistory().forEach(sms -> chatHistory.append(sms.toString()).append("\n"));
        messageDisplayArea.setText(chatHistory.toString());
    }

    private void sendMessage() {
        String selectedContactName = contactVBox.getChildren().stream()
                .filter(node -> node instanceof Button && ((Button) node).isFocused())
                .map(node -> ((Button) node).getText())
                .findFirst().orElse(null);

        if (selectedContactName != null) {
            Contact selectedContact = contactList.stream()
                    .filter(contact -> contact.getName().equals(selectedContactName))
                    .findFirst().orElse(null);

            if (selectedContact != null) {
                String messageContent = messageInputField.getText().trim();
                if (!messageContent.isEmpty()) {
                    Sms message = new Sms(messageContent, "You");
                    selectedContact.addMessage(message);
                    displayChatHistory(selectedContact);
                    messageInputField.clear();
                }
            }
        }
    }

    private void showAddContactDialog() {
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

    private void showDeleteContactDialog() {
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

    private void showModifyContactDialog() {
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

    private void showUserManualDialog() {
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
        return contactList.stream()
                .filter(contact -> contact.getName().equals(contactName))
                .findFirst()
                .orElse(null);
    }

    public int getContactIndex(Contact contact) {
        return contactList.indexOf(contact);
    }
}
