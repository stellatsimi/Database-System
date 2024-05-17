import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class connectDB {
    private static String server = "localhost";
    private static String port = "3306";
    private static String database = "travel_agency";
    private static String username = null;
    private static String password = null;

    public static String getServer() {
        return server;
    }

    public static void setServer(String server) {
        connectDB.server = server;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        connectDB.port = port;
    }

    public static String getDatabase() {
        return database;
    }

    public static void setDatabase(String database) {
        connectDB.database = database;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        connectDB.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        connectDB.password = password;
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://" + server + ":" + port + "/" + database;
        return DriverManager.getConnection(url, username, password);
    }

    public static String getAccess(String username, String password) {
        String query = "SELECT w.wrk_AT FROM worker w JOIN it ON w.wrk_AT = it.IT_AT WHERE w.wrk_lname = ? AND it.password = ?";

        try (Connection conn = connectDB.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                return result.getString("wrk_AT");
            }
        } catch (SQLException e) {
            System.err.println(e.getSQLState());
        }
        return null;
    }
}
