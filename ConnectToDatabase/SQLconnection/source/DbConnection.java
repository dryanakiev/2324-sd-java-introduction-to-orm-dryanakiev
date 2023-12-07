import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection connectionInstance = null;

    public static Connection getInstance() {
        if (connectionInstance == null) {
            try {
                String connectionURL = "jdbc:sqlserver://.\\\\SQLEXPRESS;databaseName=StorageManagementSystem;integratedSecurity=true";

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
