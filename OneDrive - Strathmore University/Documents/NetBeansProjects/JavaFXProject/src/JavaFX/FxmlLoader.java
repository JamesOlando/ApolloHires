package JavaFX;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


public class FxmlLoader {
    private Pane view;
    
    
    public Pane getPage(String fileName) throws FileNotFoundException
    {
        try{
            URL fileUrl = Main.class.getResource("/JavaFX/" + fileName + ".fxml");
            if(fileUrl == null){
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }
            
            // Load the FXML file with a properly initialized FXMLLoader
            FXMLLoader loader = new FXMLLoader(fileUrl);
            view = loader.load();
            
        }catch (IOException e){
            System.out.println("No page " + fileName + " please check FxmlLoader. ");
        }
        return view;
    }
}
