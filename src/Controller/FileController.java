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
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    FileChooser textFileChooser = new FileChooser();
    FileChooser imageFileChooser = new FileChooser();
    DateFormat df = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
    
    public FileController(AppController app) {
        this.app = app;
    }

    public void openDialog() throws IOException {

    }

    public void importText() throws FileNotFoundException, IOException {
        textFileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Text Files", "*.txt"));
        file = textFileChooser.showOpenDialog(app.getStage());
       
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
        imageFileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("PNG Images", "*.png"),
                new ExtensionFilter("TIFF Images", "*.tiff"));
        file = imageFileChooser.showOpenDialog(app.getStage());
        System.out.println(file.getAbsolutePath());
        Date date = Calendar.getInstance().getTime();
        String fileName = "C:\\Users\\Jared\\Desktop\\OCR_" + df.format(date);
        String command = "tesseract " + file.getAbsolutePath() + " " + fileName;
        Process process = Runtime.getRuntime().exec(command);
    }
    
    public void saveOutput() throws IOException {
        File outputFile = textFileChooser.showSaveDialog(app.getStage());
        try {
            FileWriter writer = new FileWriter(outputFile); 
            writer.write(app.getMain().getOutputText());
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }           
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
