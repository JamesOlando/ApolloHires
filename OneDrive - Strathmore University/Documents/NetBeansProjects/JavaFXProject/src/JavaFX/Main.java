package JavaFX;

import RMI.DatabaseInt;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main extends Application {
    private DatabaseInt databaseService;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Connect to the RMI Registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099); // Use the IP address of the server
            databaseService = (DatabaseInt) registry.lookup("ApolloHiresReg");

            // Load FXML
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Adjust the window size based on the content
            primaryStage.sizeToScene();

        } catch (IOException | java.rmi.NotBoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
