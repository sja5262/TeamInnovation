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
    
    /**
     * Defaul constructor for DatabaseController
     * @param app Takes in AppController object as an argument for two-way
     * communication
     */
    public DatabaseController(AppController app) {
        this.app = app;
    }

    /**
     * Attempts to connect to the database defined in the String object: url
     */
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
    
    /**
     * Handles authentication for logging in
     * @param user Username of the person attempting to login
     * @param password Password being tested against the password stored in the
     * database
     * @return Returns a Boolean: true if login is successful, false if not.
     * If login is successful, AppController will display MainView.  Otherwise,
     * error message is displayed in login window
     */
    public Boolean authentication(String user, String password) {
        String sql = "SELECT * FROM [User] WHERE username='" + user + "'";
        try {
            ResultSet result = state.executeQuery(sql);
            while (result.next()) {
                if (result.getString(3).equals(password)) {
                    app.getMain();
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Creates entry in SQL Server database
     * @param path The file pathway to the image
     * @param date The date the image was imported
     * @param userID The user_id of the user who imported the image
     */
    public void imageEntry(String path, String date, int userID) {
        String sql = "INSERT INTO Image (file_path, capture_date, user_id) VALUES"
                + path + ", " + date + ", " + userID;
        
    }
    /**
     * Creates OCR entry in SQL Server database
     * @param path The file pathway to the resultant text file of the OCR process
     * @param date The date on which the OCR was completed
     * @param userID The user_id of whom initiated the OCR process
     * @throws SQLException Throws exception if the SQL INSERT statement fails
     */
    public void ocrEntry(String path, String date, int userID) throws SQLException {
        String sql = "INSERT INTO Ocr (file_path, ocr_timestamp, user_id) "
                + "VALUES ('" + path + "', " + 
                "'" + date + "'" + ", " + userID + ")";
        state.executeUpdate(sql);
    }
    /**
     * Gets all User instances in the database table
     * @return Returns a ResultSet of the User instances
     */
    public ResultSet getUsers() {
        try {
            ResultSet results = state.executeQuery("SELECT * FROM [User]");
            while(results.next()) {
                return results;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);   
        }
        return null;
    }
    /**
     * Gets the user_id associated with a given username
     * @param username The username with which a SQL SELECT...WHERE statement 
     * will be executed
     * @return Returns an int of the user_id of the user
     */
    public int getUserID(String username) {
        String sql = "SELECT * FROM [User] WHERE username='" + username + "'";
        try {
            ResultSet result = state.executeQuery(sql);
            while (result.next()) {
                return result.getInt("user_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
