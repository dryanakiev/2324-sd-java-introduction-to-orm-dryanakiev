import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connectionInstance = null;

    private DBConnection() {

    }

    public static Connection getInstance() {
        if (connectionInstance == null) {
            try {
                String connectionURL = "jdbc:sqlserver://TL011T\\\\SQLEXPRESS;database=StorageManagementSystem;integratedSecurity=true";

                connectionInstance = DriverManager.getConnection(connectionURL);

                return connectionInstance;
            } catch (SQLException exception) {
                throw new RuntimeException(exception);
            }
        }
        else {
            return connectionInstance;
        }
    }
}
