
import java.sql.*;

public class MedicineStockDAO {

    Connection con = DBConnection.getConnection();

    // ───── CREATE ─────────────────────────────────
    public void addStock(String medicineName, int quantityAvailable,
                         String expiryDate, int reorderLevel, String lastUpdated) {
        try {
            String sql = "INSERT INTO medicine_stock "
                       + "(medicine_name, quantity_available, expiry_date, reorder_level, last_updated) "
                       + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, medicineName);
            ps.setInt   (2, quantityAvailable);
            ps.setString(3, expiryDate);
            ps.setInt   (4, reorderLevel);
            ps.setString(5, lastUpdated);
            ps.executeUpdate();
            System.out.println("✅ Medicine Stock Added!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── READ (View All) ────────────────────────
    public void viewAllStock() {
        try {
            String sql   = "SELECT * FROM medicine_stock";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n-------- Medicine Stock --------");
            while (rs.next()) {
                System.out.println(
                    "ID: "            + rs.getInt   ("id")                 +
                    " | Medicine: "   + rs.getString("medicine_name")      +
                    " | Qty: "        + rs.getInt   ("quantity_available") +
                    " | Expiry: "     + rs.getString("expiry_date")        +
                    " | Reorder at: " + rs.getInt   ("reorder_level")      +
                    " | Updated: "    + rs.getString("last_updated")
                );
            }
            System.out.println("--------------------------------");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── READ (Low Stock Alert) ─────────────────
    public void checkLowStock() {
        try {
            String sql = "SELECT * FROM medicine_stock WHERE quantity_available <= reorder_level";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n⚠️ LOW STOCK MEDICINES ⚠️");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                    "❗ " + rs.getString("medicine_name") +
                    " | Qty Left: " + rs.getInt("quantity_available") +
                    " | Reorder Level: " + rs.getInt("reorder_level") +
                    " | Expiry: " + rs.getString("expiry_date")
                );
            }
            if (!found) System.out.println("✅ All medicines are well stocked!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── UPDATE ─────────────────────────────────
    public void updateStock(int id, int newQty, String newDate) {
        try {
            String sql = "UPDATE medicine_stock SET quantity_available=?, last_updated=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt   (1, newQty);
            ps.setString(2, newDate);
            ps.setInt   (3, id);
            ps.executeUpdate();
            System.out.println("✅ Stock Updated!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── DELETE ─────────────────────────────────
    public void deleteStock(int id) {
        try {
            String sql = "DELETE FROM medicine_stock WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("🗑️ Stock Record Deleted!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
