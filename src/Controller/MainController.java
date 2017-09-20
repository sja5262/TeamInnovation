/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Message;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {
    
    AppController app;
    @FXML private TextArea inputText;
    @FXML private TextArea outputText;
    //@FXML private TextField cipherValue;
    Message m = new Message();

    @FXML
    protected void handleDecryptButtonAction(ActionEvent event) throws IOException {
        System.out.println("decrypt");
        outputText.setText(m.decryptCaesar(inputText.getText()));
    }
    
    @FXML
    protected void handleImportButtonAction(ActionEvent event) throws IOException {
        System.out.println("import");
        app.getFile().importFile();
        inputText.setText("");
        inputText.setText(app.getFile().getFileContents());
        app.getFile().setFileContents("");
    }
    
    @FXML
    protected void handleClearButtonAction(ActionEvent event) throws IOException {
        inputText.setText("");
    }
    
    public void setUp(AppController app) {
        this.app = app;
    }
}
