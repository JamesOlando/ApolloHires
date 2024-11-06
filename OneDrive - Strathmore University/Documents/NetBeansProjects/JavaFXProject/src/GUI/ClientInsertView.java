package GUI;

import Motor_Vehicle.AHOperations;
import com.jfoenix.controls.JFXTextArea;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ClientInsertView extends AnchorPane {

    private TextField clientIdField;
    private TextField clientFirstnameField;
    private TextField clientSurnameField;
    private TextField clientContactField;
    private JFXTextArea outputArea;

    public ClientInsertView() {
        // Create components
        Label titleLabel = new Label("Insert New Clients");
        titleLabel.setFont(Font.font("System Bold Italic", 22));
        AnchorPane.setTopAnchor(titleLabel, 10.0);
        AnchorPane.setLeftAnchor(titleLabel, 0.0);
        AnchorPane.setRightAnchor(titleLabel, 0.0);
        titleLabel.setAlignment(Pos.CENTER);

        Label clientIdLabel = new Label("Enter the Client ID:");
        clientIdLabel.setFont(Font.font(16));
        AnchorPane.setLeftAnchor(clientIdLabel, 26.0);
        AnchorPane.setTopAnchor(clientIdLabel, 80.0);

        clientIdField = new TextField();
        AnchorPane.setLeftAnchor(clientIdField, 189.0);
        AnchorPane.setTopAnchor(clientIdField, 80.0);
        clientIdField.setPrefWidth(245.0);

        Label clientFirstnameLabel = new Label("Enter the First Name:");
        clientFirstnameLabel.setFont(Font.font(16));
        AnchorPane.setLeftAnchor(clientFirstnameLabel, 26.0);
        AnchorPane.setTopAnchor(clientFirstnameLabel, 128.0);

        clientFirstnameField = new TextField();
        AnchorPane.setLeftAnchor(clientFirstnameField, 189.0);
        AnchorPane.setTopAnchor(clientFirstnameField, 128.0);
        clientFirstnameField.setPrefWidth(245.0);

        Label clientSurnameLabel = new Label("Enter the Surname:");
        clientSurnameLabel.setFont(Font.font(16));
        AnchorPane.setLeftAnchor(clientSurnameLabel, 26.0);
        AnchorPane.setTopAnchor(clientSurnameLabel, 178.0);

        clientSurnameField = new TextField();
        AnchorPane.setLeftAnchor(clientSurnameField, 189.0);
        AnchorPane.setTopAnchor(clientSurnameField, 178.0);
        clientSurnameField.setPrefWidth(245.0);

        Label clientContactLabel = new Label("Enter the Phone No:");
        clientContactLabel.setFont(Font.font(16));
        AnchorPane.setLeftAnchor(clientContactLabel, 26.0);
        AnchorPane.setTopAnchor(clientContactLabel, 235.0);

        clientContactField = new TextField();
        AnchorPane.setLeftAnchor(clientContactField, 189.0);
        AnchorPane.setTopAnchor(clientContactField, 235.0);
        clientContactField.setPrefWidth(245.0);

        Button insertButton = new Button("Insert");
        insertButton.setFont(Font.font("System Bold", 12));
        insertButton.setOnAction(event -> handleInsertAction());
        AnchorPane.setLeftAnchor(insertButton, 285.0);
        AnchorPane.setTopAnchor(insertButton, 286.0);

        outputArea = new JFXTextArea();
        outputArea.setStyle("-fx-background-color: #fff;");
        AnchorPane.setLeftAnchor(outputArea, 9.0);
        AnchorPane.setTopAnchor(outputArea, 325.0);
        outputArea.setPrefWidth(503.0);
        outputArea.setPrefHeight(93.0);

        // Add all components to the AnchorPane
        this.getChildren().addAll(titleLabel, clientIdLabel, clientIdField, clientFirstnameLabel,
                clientFirstnameField, clientSurnameLabel, clientSurnameField, clientContactLabel,
                clientContactField, insertButton, outputArea);
    }

    private void handleInsertAction() {
    // Retrieve the client information from the text fields
    String clientId = clientIdField.getText();
    String firstName = clientFirstnameField.getText();
    String surname = clientSurnameField.getText();
    String contactStr = clientContactField.getText(); // Keep contact as String for validation

    // Create an instance of AHOperations to interact with the database
    AHOperations ahOperations = new AHOperations();

    // Prepare the output text with client information
    String outputText = String.format("Client ID: %s\nFirst Name: %s\nSurname: %s\nContact: %s\n",
            clientId, firstName, surname, contactStr);

    // Variable to store the contact as an integer
    int contact;

    // Perform input validation for the contact number
    try {
        contact = Integer.parseInt(contactStr); // Convert String to int
    } catch (NumberFormatException e) {
        // If conversion fails, append error message and return
        outputText += "Invalid contact number. Please enter a valid integer.";
        outputArea.setText(outputText);
        return; // Exit the method
    }

    // Perform the insert operation
    try {
        // Call the insertClient method (assumed to be implemented in AHOperations)
        boolean isInserted = ahOperations.insertOperation(clientId, firstName, surname, contact);

        // Append success or failure message to the output text
        if (isInserted) {
            outputText += "Client inserted successfully!";
        } else {
            outputText += "Failed to insert client. Please check the information and try again.";
        }
    } catch (Exception e) {
        // Handle any exceptions (e.g., database errors) and append error message to output text
        outputText += "An error occurred while inserting the client: " + e.getMessage();
    }

    // Set the final output text to the output area
    outputArea.setText(outputText);
}



    public static void main(String[] args) {
        Application.launch(ClientInsertApp.class, args);
    }

    public static class ClientInsertApp extends Application {
        @Override
        public void start(Stage primaryStage) {
            ClientInsertView clientInsertView = new ClientInsertView();
            Scene scene = new Scene(clientInsertView, 516, 429);
            primaryStage.setTitle("Client Insert");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
}
