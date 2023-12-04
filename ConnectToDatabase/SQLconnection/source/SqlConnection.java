import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlConnection {

    // CRUD - Create / Read / Update / Delete

    // Read query
    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<>();
        Account account = new Account();

        String query = "SELECT * FROM Accounts";

        Connection connectionInstance;
        Statement statement;
        ResultSet set;

        try {
            connectionInstance = DBConnection.getInstance();
            statement = connectionInstance.createStatement();
            set = statement.executeQuery(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
