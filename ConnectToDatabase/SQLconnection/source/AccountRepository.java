import java.sql.*;
import java.util.*;

// CRUD - Create / Read / Update / Delete
public class AccountRepository {
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

    // Read all accounts from database method
    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<>();

        String query = "SELECT * FROM [Accounts]";

        Connection connectionInstance;
        Statement statement;
        ResultSet set;

        try {
            connectionInstance = DbConnection.getInstance();
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


    // Read single user from database by id method
    public Account getAccountById(int id) {
        Account account;

        String query = "SELECT * FROM Accounts WHERE Id = ${id}";

        Connection connectionInstance;
        Statement statement;
        ResultSet set;

        try {
            connectionInstance = DbConnection.getInstance();
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

        try {
            connectionInstance = DbConnection.getInstance();
            statement = connectionInstance.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAccount(Account account) {

        String query = "UPDATE Accounts SET " +
                "[Name] = ${account.getName()}," +
                "[Username] = ${account.getUsername()}," +
                "[Password] = ${account.getPassword()}," +
                "[Email] = ${account.getEmail()}," +
                "[PhoneNumber] = ${account.getPhoneNumber()} " +
                "WHERE [Id] = ${account.getId()}";


        Connection connectionInstance;
        Statement statement;

        try {
            connectionInstance = DbConnection.getInstance();
            statement = connectionInstance.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        AccountRepository repository = new AccountRepository();

        for (Account account : repository.getAccounts()) {
            System.out.println(account.toString() + "\n\n");
        }
    }
}
