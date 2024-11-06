package GUI;

import com.jfoenix.controls.JFXTextArea;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import Motor_Vehicle.AHOperations;

public class RemoveClientView extends AnchorPane { // Extend AnchorPane

    private JFXTextArea textArea; // Text area to display messages
    private TextField clientIdField; // Text field for client ID input

    public RemoveClientView() { // Constructor
        // Initialize the UI components
        initialize();
    }

    private void initialize() {
        // Create header label
        Label headerLabel = new Label("Remove a Client");
        headerLabel.setStyle("-fx-font-size: 22; -fx-font-weight: bold; -fx-font-style: italic;");
        AnchorPane.setTopAnchor(headerLabel, 10.0);
        AnchorPane.setLeftAnchor(headerLabel, 160.0);

        // Create client ID label
        Label clientIdLabel = new Label("Enter the client_id:");
        clientIdLabel.setStyle("-fx-font-size: 16;");
        AnchorPane.setTopAnchor(clientIdLabel, 80.0);
        AnchorPane.setLeftAnchor(clientIdLabel, 26.0);

        // Create text field for client ID
        clientIdField = new TextField();
        AnchorPane.setTopAnchor(clientIdField, 80.0);
        AnchorPane.setLeftAnchor(clientIdField, 189.0);
        clientIdField.setPrefWidth(245.0);

        // Create delete button
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
        deleteButton.setOnAction(e -> handleDeleteAction());
        AnchorPane.setTopAnchor(deleteButton, 116.0);
        AnchorPane.setLeftAnchor(deleteButton, 285.0);

        // Create text area for messages
        textArea = new JFXTextArea();
        textArea.setPrefHeight(263.0);
        textArea.setPrefWidth(503.0);
        textArea.setStyle("-fx-background-color: #fff;");
        AnchorPane.setTopAnchor(textArea, 155.0);
        AnchorPane.setLeftAnchor(textArea, 9.0);

        // Add components to the AnchorPane
        this.getChildren().addAll(headerLabel, clientIdLabel, clientIdField, deleteButton, textArea);
    }

    private void handleDeleteAction() {
        String clientId = clientIdField.getText().trim(); // Get client ID from text field

        // Initialize AHOperations instance
        AHOperations ahOperations2 = new AHOperations();

        // Check if client ID is not empty
        if (!clientId.isEmpty()) {
            if (ahOperations2.deleteOperation(clientId)) {
                textArea.appendText("Client with ID " + clientId + " has been deleted successfully.\n");
            } else {
                textArea.appendText("Client with ID " + clientId + " not found.\n");
            }
            clientIdField.clear(); // Clear the text field after action
        } else {
            // Show error message if client ID is empty
            textArea.appendText("Please enter a valid client ID.\n");
        }
    }
}
