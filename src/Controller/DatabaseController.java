package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {

    private AppController app;
    private final String url = "jdbc:derby://localhost:1527/Employees";
    private final String username = "testadmin";
    private final String password = "biiteotb";
    
    public DatabaseController(AppController app) {
        this.app = app;
    }

    public void connect() {
        try {
            Connection connect = DriverManager.getConnection(url, username, password);
            System.out.println("connected to database");
            Statement state = connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet results = state.executeQuery("SELECT * FROM Workers");
            while(results.next()) {
                System.out.println("First Name: " + results.getString("first_name"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
