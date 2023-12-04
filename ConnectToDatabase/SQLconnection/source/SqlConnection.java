import java.sql.*;
import java.util.*;

public class SqlConnection {
    private static Connection connectionInstance = null;

    private SqlConnection() {
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


    // CRUD - Create / Read / Update / Delete


    // Mapping database object to class object method
    public Account mapToAccount(ResultSet set) {
        Account account = new Account();

        try {
            account.setId(set.getInt(0));
            account.setName(set.getString(1));
            account.setUsername(set.getString(2));
            account.setPassword(set.getString(3));
            account.setEmail(set.getString(4));
            account.setPhoneNumber(set.getString(5));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return account;
    }

    // Read all accounts from table method
    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<>();
        Account account;

        String query = "SELECT * FROM Accounts";

        Connection connectionInstance;
        Statement statement;
        ResultSet set;

        try {
            connectionInstance = SqlConnection.getInstance();
            statement = connectionInstance.createStatement();
            set = statement.executeQuery(query);

            while (set.next()) {
                accounts.add(mapToAccount(set));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return accounts;
    }

    // Read single user from table by id method
    public Account getAccountById(int id) {
        Account account;

        String query = "SELECT * FROM Accounts WHERE Id = ${id}";

        Connection connectionInstance;
        Statement statement;
        ResultSet set;

        try {
            connectionInstance = SqlConnection.getInstance();
            statement = connectionInstance.createStatement();
            set = statement.executeQuery(query);

            account = mapToAccount(set);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return account;
    }

    // Create single user and store in database method

    public void addAccount(Account account) {
        String query = "INSERT INTO Accounts VALUES (" +
                "${account.getId()}," +
                "${account.getName()}," +
                "${account.getUsername()}," +
                "${account.getPassword()}," +
                "${account.getEmail()}," +
                "${account.getPhoneNumber()})";


        Connection connectionInstance;
        Statement statement;
        ResultSet set;

        try {
            connectionInstance = SqlConnection.getInstance();
            statement = connectionInstance.createStatement();
            set = statement.executeQuery(query);

            account = mapToAccount(set);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
