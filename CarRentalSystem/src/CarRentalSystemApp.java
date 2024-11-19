
import Classes.DatabaseConnection;
import java.sql.Connection;

public class CarRentalSystemApp {
     // Global connection variable
    private static Connection connection;
    
    public static void main(String[] args) {
        // Set the look and feel to the system's default (optional)
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Establish a single database connection when the application starts
        connection = DatabaseConnection.connect();
        if (connection != null) {
            System.out.println("Database connected successfully at app startup!");
        } else {
            System.out.println("Failed to connect to the database.");
        }
        
        // Launch the Login Screen
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login(connection).setVisible(true);
            }
        });
    }
    
    // Method to close the connection when needed (optional)
    public static void closeConnection() {
        DatabaseConnection.closeConnection(connection);
    }
    
}
