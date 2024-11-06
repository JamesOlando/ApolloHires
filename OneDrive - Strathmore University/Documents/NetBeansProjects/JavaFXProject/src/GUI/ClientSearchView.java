package GUI;

import Motor_Vehicle.AHOperations;
import com.jfoenix.controls.JFXTextArea;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ClientSearchView extends AnchorPane {

    private TextField clientIdField;
    private JFXTextArea outputArea;

    public ClientSearchView() {
        // Create components
        Label titleLabel = new Label("Search for Clients");
        titleLabel.setFont(Font.font("System Bold Italic", 22));
        AnchorPane.setTopAnchor(titleLabel, 20.0);
        AnchorPane.setLeftAnchor(titleLabel, 0.0);
        AnchorPane.setRightAnchor(titleLabel, 0.0);
        titleLabel.setAlignment(javafx.geometry.Pos.CENTER); 

        Label clientIdLabel = new Label("Enter the client_id:");
        clientIdLabel.setFont(Font.font(16));
        AnchorPane.setLeftAnchor(clientIdLabel, 26.0);
        AnchorPane.setTopAnchor(clientIdLabel, 80.0);

        clientIdField = new TextField();
        AnchorPane.setLeftAnchor(clientIdField, 189.0);
        AnchorPane.setTopAnchor(clientIdField, 80.0);
        clientIdField.setPrefWidth(245.0); // Set the preferred width directly on the TextField


        Button searchButton = new Button("Search");
        searchButton.setFont(Font.font("System Bold", 12));
        searchButton.setOnAction(event -> handleSelectAction());
        AnchorPane.setLeftAnchor(searchButton, 285.0);
        AnchorPane.setTopAnchor(searchButton, 116.0);

        outputArea = new JFXTextArea();
        outputArea.setStyle("-fx-background-color: #fff;");
        AnchorPane.setLeftAnchor(outputArea, 9.0);
        AnchorPane.setTopAnchor(outputArea, 155.0);
        outputArea.setPrefWidth(503.0);  // Set the preferred width directly on the JFXTextArea
        outputArea.setPrefHeight(263.0); // Set the preferred height directly on the JFXTextArea

        // Add all components to the AnchorPane
        this.getChildren().addAll(titleLabel, clientIdLabel, clientIdField, searchButton, outputArea);
    }

   private String handleSelectAction() {
    // Retrieve the client ID from the text field
    String id = clientIdField.getText();

    // Ensure the client ID is not empty
    if (id == null || id.isEmpty()) {
        outputArea.setText("Please enter a valid client ID."); // Display message in the text area
        return null; // Return null or an empty string if invalid
    }

    // Create an instance of AHOperations to interact with the database
    AHOperations aho1 = new AHOperations();
    
    // Call the selectOperation method with the retrieved client ID
    // Assume selectOperation returns an array or list of strings with client information
    String[] clientInfo = aho1.selectOperation(id); // This should return an array of strings

    // Check if client information was retrieved
    if (clientInfo == null || clientInfo.length < 4) {
        outputArea.setText("Client not found."); // Display message if client is not found
        return null;
    }

    // Prepare the output text with client information
    String outputText = String.format("Client ID: %s\nFirst Name: %s\nSurname: %s\nContact: %s\n",
            clientInfo[0], clientInfo[1], clientInfo[2], clientInfo[3]);

    // Display the result in the output area
    outputArea.setText(outputText);
    
    // Return the result for further processing if needed
    return outputText;
}




    public static void main(String[] args) {
        Application.launch(ClientSearchApp.class, args);
    }

    public static class ClientSearchApp extends Application {
        @Override
        public void start(Stage primaryStage) {
            ClientSearchView clientSearchView = new ClientSearchView();
            Scene scene = new Scene(clientSearchView, 516, 429);
            primaryStage.setTitle("Client Search");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
}
