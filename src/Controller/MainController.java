/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Cipher.Caesar;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class MainController {
    
    AppController app;
    @FXML private TextArea inputArea;
    @FXML private TextArea outputArea;
    private String outputText = "";
    //@FXML private TextField cipherValue;
    Caesar m = new Caesar();

    @FXML
    protected void handleDecryptButtonAction(ActionEvent event) throws IOException {
        System.out.println("decrypt");
        outputText = m.decryptCaesar(inputArea.getText());
        outputArea.setText(outputText);
    }
    
    @FXML
    protected void handleImportButtonAction(ActionEvent event) throws IOException {
        System.out.println("import");
        app.getFile().importText();
        clearText();
        inputArea.setText(app.getFile().getFileContents());
        app.getFile().setFileContents("");
    }
    
    @FXML
    protected void handleClearButtonAction(ActionEvent event) throws IOException {
        clearText();
    }
    
    @FXML 
    protected void handleOcrButtonAction(ActionEvent event) throws IOException {
        System.out.println("OCR");
        app.getFile().ocr();
    }
    
    @FXML
    protected void handleSaveButtonAction(ActionEvent event) throws IOException {
        System.out.println("save");
        app.getFile().saveOutput();
        
    }
    public void setUp(AppController app) {
        this.app = app;
    }
    
    public void clearText() {
        inputArea.setText("");
        outputArea.setText("");
    }

    public String getOutputText() {
        return outputText;
    }
}
