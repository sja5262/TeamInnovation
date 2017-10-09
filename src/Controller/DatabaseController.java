package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {

    private AppController app;
    private final String url = "jdbc:sqlserver://DESKTOP-C3ACMOB;"
            + "integratedSecurity=true;databaseName=testDB";
    private String sql = "";
    ResultSet result;
    Statement state;

    /**
     * Default constructor for DatabaseController
     *
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
     *
     * @param user Username of the person attempting to login
     * @param password Password being tested against the password stored in the
     * database
     * @return Returns a Boolean: true if login is successful, false if not. If
     * login is successful, AppController will display MainView. Otherwise,
     * error message is displayed in login window
     * @throws java.sql.SQLException If SQL query is invalid
     */
    public Boolean authentication(String user, String password) throws SQLException {
        sql = "SELECT * FROM Users WHERE username='" + user + "'";
        result = state.executeQuery(sql);
        while (result.next()) {
            if (result.getString(3).equals(password)) {
                result.close();
                return true;
            }
        }
        result.close();
        return false;
    }
    /**
     * Performs the validation for the password reset page
     * @param username The username of the user attempting to reset password
     * @param userID The user_id of the user attempting to reset password
     * @return Returns true if the username/user_id combination is found in DB
     * @throws SQLException Throws error if SQL query is invalid
     */
    public Boolean isUserValid(String username, int userID) throws SQLException {
        sql = "SELECT * FROM Users WHERE user_id=" + userID;
        result = state.executeQuery(sql);
        while (result.next()) {
            if (result.getString("username").equals(username)) {
                result.close();
                return true;
            } 
        }
        result.close();
        return false;
    }

    /**
     * Creates entry in SQL Server database
     *
     * @param path The file pathway to the image
     * @param date The date the image was imported
     * @param userID The user_id of the user who imported the image
     * @throws java.sql.SQLException Throws exception if the SQL INSERT
     * statement fails
     */
    public void imageEntry(String path, String date, int userID) throws SQLException {
        sql = "INSERT INTO Image (file_path, capture_date, user_id) VALUES"
                + path + ", " + date + ", " + userID;
        state.executeUpdate(sql);
    }

    /**
     * Creates OCR entry in SQL Server database
     *
     * @param path The file pathway to the resultant text file of the OCR
     * process
     * @param date The date on which the OCR was completed
     * @param userID The user_id of whom initiated the OCR process
     * @throws SQLException Throws exception if the SQL INSERT statement fails
     */
    public void ocrEntry(String path, String date, int userID) throws SQLException {
        sql = "INSERT INTO Ocr (file_path, ocr_timestamp, user_id) "
                + "VALUES ('" + path + "', "
                + "'" + date + "'" + ", " + userID + ")";
        state.executeUpdate(sql);
    }

    /**
     * Gets all User instances in the database table
     *
     * @return Returns a ResultSet of the User instances
     * @throws java.sql.SQLException Throws error if query is invalid
     */
    public ResultSet getUsers() throws SQLException {
        ResultSet users = state.executeQuery("SELECT * FROM Users");
        while (users.next()) {
            return users;
        }
        return null;
    }

    /**
     * Gets the user_id associated with a given username
     *
     * @param username The username with which a SQL SELECT...WHERE statement
     * will be executed
     * @return Returns an int of the user_id of the user
     * @throws java.sql.SQLException Throws error if SQL query is invalid
     */
    public int getUserID(String username) throws SQLException {
        sql = "SELECT user_id FROM Users WHERE username='" + username + "'";
        ResultSet userID = state.executeQuery(sql);
        while (userID.next()) {
            return userID.getInt("user_id");
        }
        return 0;
    }

}
