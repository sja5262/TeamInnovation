package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseController {

    private AppController app;
    private final String url = "jdbc:sqlserver://DESKTOP-C3ACMOB;integratedSecurity=true;databaseName=testDB";
    Statement state;
    
    public DatabaseController(AppController app) {
        this.app = app;
    }

    public void connect() {
        try {
            Connection connect = DriverManager.getConnection(url);
            System.out.println("connected to database");
            state = connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);       
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Boolean authentication(String user, String password) {
        String sql = "SELECT * FROM testtable WHERE username='" + user + "'";
        try {
            ResultSet result = state.executeQuery(sql);
            while (result.next()) {
            System.out.println(result.getString(1));
            if (result.getString(2).equals(password)) {
                return true;
            }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ResultSet getUsers() {
        try {
            ResultSet results = state.executeQuery("SELECT * FROM testtable");
            while(results.next()) {
                return results;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);   
        }
        return null;
    }
    
}
