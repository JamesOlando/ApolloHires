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

public class UpdateClientView extends AnchorPane {

    private TextField clientIdField;
    private TextField clientFirstnameField;
    private TextField clientSurnameField;
    private TextField clientContactField;
    private JFXTextArea outputArea;

    public UpdateClientView() {
        // Create components
        Label headerLabel = new Label("Update Client Information");
        headerLabel.setStyle("-fx-font-size: 22; -fx-font-weight: bold; -fx-font-style: italic;");
        AnchorPane.setTopAnchor(headerLabel, 10.0);
        AnchorPane.setLeftAnchor(headerLabel, 0.0);
        AnchorPane.setRightAnchor(headerLabel, 0.0);
        headerLabel.setAlignment(Pos.CENTER);

        Label clientIdLabel = new Label("Enter the Client ID:");
        clientIdLabel.setFont(Font.font(16));
        AnchorPane.setLeftAnchor(clientIdLabel, 26.0);
        AnchorPane.setTopAnchor(clientIdLabel, 80.0);

        clientIdField = new TextField();
        AnchorPane.setLeftAnchor(clientIdField, 189.0);
        AnchorPane.setTopAnchor(clientIdField, 80.0);
        clientIdField.setPrefWidth(245.0);

        Button searchButton = new Button("Search");
        searchButton.setFont(Font.font("System Bold", 12));
        searchButton.setOnAction(event -> handleSearchAction());
        AnchorPane.setLeftAnchor(searchButton, 450.0);
        AnchorPane.setTopAnchor(searchButton, 80.0);

        Label firstNameLabel = new Label("Enter new First Name:");
        firstNameLabel.setFont(Font.font(16));
        AnchorPane.setLeftAnchor(firstNameLabel, 26.0);
        AnchorPane.setTopAnchor(firstNameLabel, 130.0);

        clientFirstnameField = new TextField();
        AnchorPane.setLeftAnchor(clientFirstnameField, 189.0);
        AnchorPane.setTopAnchor(clientFirstnameField, 130.0);
        clientFirstnameField.setPrefWidth(245.0);

        Label surnameLabel = new Label("Enter new Surname:");
        surnameLabel.setFont(Font.font(16));
        AnchorPane.setLeftAnchor(surnameLabel, 26.0);
        AnchorPane.setTopAnchor(surnameLabel, 180.0);

        clientSurnameField = new TextField();
        AnchorPane.setLeftAnchor(clientSurnameField, 189.0);
        AnchorPane.setTopAnchor(clientSurnameField, 180.0);
        clientSurnameField.setPrefWidth(245.0);

        Label contactLabel = new Label("Enter new Phone No:");
        contactLabel.setFont(Font.font(16));
        AnchorPane.setLeftAnchor(contactLabel, 26.0);
        AnchorPane.setTopAnchor(contactLabel, 230.0);

        clientContactField = new TextField();
        AnchorPane.setLeftAnchor(clientContactField, 189.0);
        AnchorPane.setTopAnchor(clientContactField, 230.0);
        clientContactField.setPrefWidth(245.0);

        Button updateButton = new Button("Update");
        updateButton.setFont(Font.font("System Bold", 12));
        updateButton.setOnAction(event -> handleUpdateAction());
        AnchorPane.setLeftAnchor(updateButton, 285.0);
        AnchorPane.setTopAnchor(updateButton, 286.0);

        outputArea = new JFXTextArea();
        outputArea.setStyle("-fx-background-color: #fff;");
        AnchorPane.setLeftAnchor(outputArea, 9.0);
        AnchorPane.setTopAnchor(outputArea, 325.0);
        outputArea.setPrefWidth(503.0);
        outputArea.setPrefHeight(93.0);

        // Add all components to the AnchorPane
        this.getChildren().addAll(headerLabel, clientIdLabel, clientIdField, searchButton,
                firstNameLabel, clientFirstnameField, surnameLabel, clientSurnameField,
                contactLabel, clientContactField, updateButton, outputArea);
    }

    private void handleUpdateAction() {
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

        // Perform the update operation
        try {
            // Call the updateClient method (assumed to be implemented in AHOperations)
            boolean isUpdated = ahOperations.updateOperation(clientId, firstName, surname, contact);

            // Append success or failure message to the output text
            if (isUpdated) {
                outputText += "Client updated successfully!";
            } else {
                outputText += "Failed to update client. Please check the information and try again.";
            }
        } catch (Exception e) {
            // Handle any exceptions (e.g., database errors) and append error message to output text
            outputText += "An error occurred while updating the client: " + e.getMessage();
        }

        // Set the final output text to the output area
        outputArea.setText(outputText);
    }

    private void handleSearchAction() {
        // Logic to search for a client by client ID
        String clientId = clientIdField.getText();

        // Implement your logic to fetch client details from the database here
        // Example status update:
        outputArea.setText("Searching for client with ID: " + clientId);
        // Populate the fields with fetched data, e.g.:
        // clientFirstnameField.setText(client.getFirstName());
        // clientSurnameField.setText(client.getSurname());
        // clientContactField.setText(client.getContact());
    }

    public static void main(String[] args) {
        Application.launch(UpdateClientApp.class, args);
    }

    public static class UpdateClientApp extends Application {
        @Override
        public void start(Stage primaryStage) {
            UpdateClientView updateClientView = new UpdateClientView();
            Scene scene = new Scene(updateClientView, 516, 429); // Adjusted height for outputArea
            primaryStage.setTitle("Update Client");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
}
