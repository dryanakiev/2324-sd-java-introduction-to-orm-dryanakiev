import java.sql.*;

public class SqlConnection {

    private static Connection connectionInstance = null;

    private SqlConnection() {

    }

    public static Connection getInstance() {
        if (connectionInstance == null) {
            try {
                String connectionURL = "jdbc:sqlserver://TL011T\\SQLEXPRESS;database=StorageManagementSystem";

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

    public static void main(String[] args) throws SQLException {
        Connection connection = getInstance();

        Statement statement = connection.createStatement();

        ResultSet set = statement.executeQuery("SELECT * FROM StorageManagementSystem");

        while (set.next()) {
            System.out.println("ID: " + set.getInt(1));
            System.out.println("Name: " + set.getString(2));
            System.out.println("Username: " + set.getString(3));
            System.out.println("Password: " + set.getString(4));
            System.out.println("Email:" + set.getString(5));
            System.out.println("Phone number:" + set.getString(6));
        }
    }

}
