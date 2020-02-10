package clientgui;

/* @author Andrea Pallotta */

import java.util.Optional;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;

/**
 * Main class method. Creates the stage and launches the login Scene
 * @author andreapallotta
 */
public class ClientGUI extends Application {
    
    public static void main(String[] args) {
    launch(args);
  }

    /**
     * loads the login.fxml scene and contains the WindowEvent to terminate the program
     * @param primaryStage type Stage
     * @throws Exception to handle the window closeRequest
     */
  @Override
  public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest((WindowEvent we) -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Exit Confermation");
            alert.setHeaderText("You are about to terminate the program");
            alert.setContentText("Are you sure you want to exit?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                System.exit(0);
            } 
            
            else {
                we.consume();
            }
         });
  }

  
}