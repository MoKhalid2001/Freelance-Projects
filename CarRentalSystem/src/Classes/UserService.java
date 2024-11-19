
package Classes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users = new ArrayList<>();
    private Connection connection;
    
    public UserService(Connection connection) {
        this.connection = connection;
    }
    
    // Method to authenticate a user based on username, password, and role (for Admin/Staff)
    public boolean authenticateUser(String username, String password, String role) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ? AND role = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);  // Consider hashing passwords for security
            stmt.setString(3, role);

            ResultSet rs = stmt.executeQuery();
            return rs.next();  // Returns true if a matching user is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Method to authenticate a customer
    public boolean authenticateCustomer(String username, String password) {
        String query = "SELECT * FROM customers WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);  // Consider hashing passwords for security

            ResultSet rs = stmt.executeQuery();
            return rs.next();  // Returns true if a matching customer is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
