
import Controller.AppController;
import javafx.application.Application;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jared
 */
public class Decryption extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AppController app = new AppController(stage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
