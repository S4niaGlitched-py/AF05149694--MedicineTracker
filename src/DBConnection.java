import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/medicine_db",
                "root",          // 👈 your MySQL username
                "Sania@123"  // 👈 your MySQL password
            );
            System.out.println("✅ Connected to Database!");
        } catch (Exception e) {
            System.out.println("❌ Connection Failed: " + e.getMessage());
        }
        return con;
    }
}