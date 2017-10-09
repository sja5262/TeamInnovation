package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class ResetPasswordViewController implements Initializable {

    @FXML private TextField usernameField;
    @FXML private TextField idField;
    @FXML private Button submitButton;
    @FXML private Label userLabel;
    @FXML private TextArea questionArea;
    @FXML private TextField responseField;
    @FXML private Button responseButton;
    @FXML private TextField newField;
    @FXML private TextField confirmField;
    @FXML private Button saveButton;
    @FXML private Label matchLabel;
    @FXML private Label responseLabel;
    @FXML private Label newLabel;
    @FXML private Label confirmLabel;
    @FXML private Button backButton;
    AppController app;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void setUp(AppController app) {
        this.app = app;
    }
    
    public Boolean passwordsMatch() {
        return newField.getText().equals(confirmField.getText());
    }
    
    @FXML
    protected void handleSubmitButtonAction() {
        if (usernameField.getText().equals("testusername") 
                && idField.getText().equals("1234")) {
            secondSection();
            userLabel.setText("Username/ID combination found");
            userLabel.setTextFill(Color.GREEN);
            userLabel.setVisible(true);
        } else {
            userLabel.setVisible(true);
        }
    }
    
    @FXML
    protected void handleResponseButtonAction() {
        if (responseField.getText().equals("test")) {
            responseLabel.setText("Reponse verified");
            responseLabel.setTextFill(Color.GREEN);
            responseLabel.setVisible(true);
            thirdSection();
        } else {
            responseLabel.setVisible(true);
        }
    }
    
    @FXML protected void handleSaveButtonAction() throws IOException {
        if (passwordsMatch()) {
            matchLabel.setText("Password updated");
            matchLabel.setTextFill(Color.GREEN);
            //save new password to database
        } else {
            matchLabel.setVisible(true);
        }
    }
    
    @FXML protected void handleBackButtonAction() throws IOException {
        app.showLogin();
    }
    
    private void secondSection() {
        usernameField.setFocusTraversable(false);
        usernameField.setEditable(false);
        idField.setOpacity(25);
        idField.setEditable(false);
        idField.setFocusTraversable(false);
        submitButton.setOpacity(25);
        submitButton.setDisable(true);
        questionArea.setOpacity(100);
        responseField.setOpacity(100);
        responseField.setEditable(true);
        responseField.setFocusTraversable(true);
        responseField.requestFocus();
        responseButton.setVisible(true);
        responseButton.setFocusTraversable(true);
    }
    
    private void thirdSection() {
        responseField.setFocusTraversable(false);
        responseField.setEditable(false);
        submitButton.setOpacity(25);
        responseButton.setDisable(true);
        newLabel.setOpacity(100);
        newField.setOpacity(100);
        newField.setEditable(true);
        newField.requestFocus();
        newField.setFocusTraversable(true);
        confirmLabel.setOpacity(100);
        confirmField.setOpacity(100);
        confirmField.setEditable(true);
        confirmField.setFocusTraversable(true);
        saveButton.setVisible(true);
        saveButton.setFocusTraversable(true);
    }
    
    @FXML protected void handleKeyPressed (KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER && 
                (idField.isFocused() || submitButton.isFocused())) {
            handleSubmitButtonAction();
        } else if(keyEvent.getCode() == KeyCode.ENTER && 
                (responseField.isFocused() || responseButton.isFocused())) {
            handleResponseButtonAction();
        } else if (keyEvent.getCode() == KeyCode.ENTER && 
                (confirmField.isFocused() || saveButton.isFocused())) {
            handleSaveButtonAction();
        } else if (keyEvent.getCode() == KeyCode.ENTER && backButton.isFocused()) {
            handleBackButtonAction();
        }
    }
}
