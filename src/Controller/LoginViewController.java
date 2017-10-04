package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginViewController implements Initializable {

    AppController app;
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private Label invalidLabel;
    @FXML private Button loginButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       usernameField.requestFocus();
       invalidLabel.setVisible(false);
    }    
    

    @FXML
    protected void handleLoginButtonAction() throws IOException {
        if(app.getDb().authentication(usernameField.getText(), passwordField.getText())) {
            System.out.println("Login successful");
            app.showMain();
        } else {
            System.out.println("Login Failed");
            invalidLabel.setVisible(true);
        }
    }
    
    public void setUp(AppController app) {
        this.app = app;
    }
}
