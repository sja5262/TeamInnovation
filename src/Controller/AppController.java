/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jared
 */
public class AppController {

    Parent root;
    private MainController main;
    private LoginViewController loginView;
    private FileController file;
    private DatabaseController db;
    private Stage stage;

    public AppController(Stage stage) throws IOException {
        this.db = new DatabaseController(this);
        db.connect();
        this.file = new FileController(this);
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginView.fxml"));
        root = loader.load();
        loginView = (LoginViewController) loader.getController();
        loginView.setUp(this);
        stage.setTitle("Login");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void showMain() throws IOException {
        stage.hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainView.fxml"));
        root = loader.load();
        main = (MainController) loader.getController();
        main.setUp(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public MainController getMain() {
        return main;
    }

    public void setMain(MainController main) {
        this.main = main;
    }

    public FileController getFile() {
        return file;
    }

    public void setFile(FileController file) {
        this.file = file;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public LoginViewController getLoginView() {
        return loginView;
    }

    public void setLoginView(LoginViewController loginView) {
        this.loginView = loginView;
    }

    public DatabaseController getDb() {
        return db;
    }

    public void setDb(DatabaseController db) {
        this.db = db;
    }
}
