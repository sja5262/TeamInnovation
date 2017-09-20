/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author Jared
 */
public class FileController {

    AppController app;
    private String fileContents = "";
    private File file;
    FileChooser fileChooser = new FileChooser();
    int i = 0;

    public FileController(AppController app) {
        this.app = app;
    }

    public void openDialog() throws IOException {

    }

    public void importText() throws FileNotFoundException, IOException {
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"));
        file = fileChooser.showOpenDialog(app.getStage());
       
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String text = "";
            while ((text = reader.readLine()) != null) {
                fileContents += text;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void ocr() throws IOException {
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("PNG Images", "*.png"),
                new ExtensionFilter("TIFF Images", "*.tiff"));
        file = fileChooser.showOpenDialog(app.getStage());
        System.out.println(file.getAbsolutePath());
        String fileName = "C:\\Users\\Jared\\Desktop\\output" + Integer.toString(i);
        String command = "tesseract " + file.getAbsolutePath() + " " + fileName;
        //File ocrText = 
        Process process = Runtime.getRuntime().exec(command);
        i++;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContents() {
        return fileContents;
    }

    public void setFileContents(String fileContents) {
        this.fileContents = fileContents;
    }
}
