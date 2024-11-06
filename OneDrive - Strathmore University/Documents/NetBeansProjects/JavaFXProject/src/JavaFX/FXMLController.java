package JavaFX;


import GUI.ClientInsertView;
import GUI.ClientSearchView;
import GUI.RemoveClientView;
import GUI.UpdateClientView;
import Motor_Vehicle.AHOperations;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class FXMLController implements Initializable {

    @FXML
    private ImageView Exit;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuBack;
    @FXML
    private Label MenuClose;
    @FXML
    private BorderPane mainPane;
    @FXML
    private AnchorPane slider;
    @FXML
    private TextField clientIdField; // Client ID field
    @FXML
    private TextField clientFirstnameField; // Firstname field
    @FXML
    private TextField clientSurnameField; // Surname field
    @FXML
    private TextField clientContactField; // Contact field

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Exit application on image click
        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        // Slider animation for Menu
        slider.setTranslateX(-176);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                MenuClose.setVisible(true);
            });
        });

        MenuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(-176);
            slide.play();
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);
            });
        });
    }

    @FXML
    private void handleButton1Action(ActionEvent event) throws IOException {
        System.out.println("You clicked Button 1!");
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("Dashboard");
        mainPane.setCenter(view);
    }

    @FXML
    private void handleButton2Action(ActionEvent event) throws IOException {
    System.out.println("You clicked Button 2!");
    
    // Create an instance of ClientSearchView
    ClientSearchView clientSearchView = new ClientSearchView();
    
    // Add the ClientSearchView to the mainPane
    mainPane.setCenter(clientSearchView);
}


    @FXML
    private void handleButton3Action(ActionEvent event) throws IOException {
        System.out.println("You clicked Button 3!");
    
    // Create an instance of ClientSearchView
    ClientInsertView clientInsertView = new ClientInsertView();
    
    // Add the ClientSearchView to the mainPane
    mainPane.setCenter(clientInsertView);
    }

    @FXML
    private void handleButton4Action(ActionEvent event) throws IOException {
        System.out.println("You clicked Button 5!");
    
    // Create an instance of ClientSearchView
    UpdateClientView updateClientView = new UpdateClientView();
    
    // Add the ClientSearchView to the mainPane
    mainPane.setCenter(updateClientView);
    }

    @FXML
    private void handleButton5Action(ActionEvent event) throws IOException {
       System.out.println("You clicked Button 5!");
    
    // Create an instance of ClientSearchView
    RemoveClientView removeClientView = new RemoveClientView();
    
    // Add the ClientSearchView to the mainPane
    mainPane.setCenter(removeClientView);
    }
    
  

   
     @FXML
    void handleInsertAction(ActionEvent event) {
        // Retrieve the values from the text fields
    String id = clientIdField.getText();
    String firstname = clientFirstnameField.getText();
    String surname = clientSurnameField.getText();
    int contact;

    // Ensure that the contact field is a valid integer
    try {
        contact = Integer.parseInt(clientContactField.getText());
    } catch (NumberFormatException e) {
        System.out.println("Invalid contact number. Please enter a valid integer.");
        return;
    }

    // Call the insertOperation method with the retrieved values
    AHOperations aho2 = new AHOperations();
    aho2.insertOperation(id, firstname, surname, contact);
    }
    
    @FXML
    private void handleUpdateAction(ActionEvent event) throws IOException {
        // Retrieve the values from the text fields
    String id = clientIdField.getText();
    String firstname = clientFirstnameField.getText();
    String surname = clientSurnameField.getText();
    int contact;

    // Ensure that the contact field is a valid integer
    try {
        contact = Integer.parseInt(clientContactField.getText());
    } catch (NumberFormatException e) {
        System.out.println("Invalid contact number. Please enter a valid integer.");
        return;
    }

    // Call the updateOperation method with the retrieved values
    AHOperations aho3 = new AHOperations();
    aho3.updateOperation(id, firstname, surname, contact);
    }
    
}
