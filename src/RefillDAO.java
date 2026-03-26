import java.sql.*;

public class RefillDAO {

    Connection con = DBConnection.getConnection();

    // ───── CREATE ─────────────────────────────────
    public void addRefill(String patientName, String medicineName,
                          String lastRefillDate, String nextRefillDate,
                          int quantityLeft, String reminderStatus) {
        try {
            String sql = "INSERT INTO refill_reminders "
                       + "(patient_name, medicine_name, last_refill_date, next_refill_date, quantity_left, reminder_status) "
                       + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, patientName);
            ps.setString(2, medicineName);
            ps.setString(3, lastRefillDate);
            ps.setString(4, nextRefillDate);
            ps.setInt   (5, quantityLeft);
            ps.setString(6, reminderStatus);
            ps.executeUpdate();
            System.out.println("✅ Refill Reminder Added!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── READ (View All) ────────────────────────
    public void viewAllRefills() {
        try {
            String sql   = "SELECT * FROM refill_reminders";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n-------- All Refill Reminders --------");
            while (rs.next()) {
                System.out.println(
                    "ID: "              + rs.getInt   ("id")               +
                    " | Patient: "      + rs.getString("patient_name")     +
                    " | Medicine: "     + rs.getString("medicine_name")    +
                    " | Last Refill: "  + rs.getString("last_refill_date") +
                    " | Next Refill: "  + rs.getString("next_refill_date") +
                    " | Qty Left: "     + rs.getInt   ("quantity_left")    +
                    " | Status: "       + rs.getString("reminder_status")
                );
            }
            System.out.println("--------------------------------------");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── READ (Check Due Refills) ───────────────
    public void checkDueRefills() {
        try {
            String sql = "SELECT * FROM refill_reminders WHERE quantity_left <= 5";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n⚠️ MEDICINES NEEDING REFILL SOON ⚠️");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                    "❗ Patient: "      + rs.getString("patient_name")     +
                    " | Medicine: "     + rs.getString("medicine_name")    +
                    " | Qty Left: "     + rs.getInt   ("quantity_left")    +
                    " | Next Refill: "  + rs.getString("next_refill_date")
                );
            }
            if (!found) System.out.println("✅ No urgent refills needed!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── UPDATE ─────────────────────────────────
    public void updateRefill(int id, int newQty, String newNextDate) {
        try {
            String sql = "UPDATE refill_reminders SET quantity_left=?, next_refill_date=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt   (1, newQty);
            ps.setString(2, newNextDate);
            ps.setInt   (3, id);
            ps.executeUpdate();
            System.out.println("✅ Refill Reminder Updated!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── DELETE ─────────────────────────────────
    public void deleteRefill(int id) {
        try {
            String sql = "DELETE FROM refill_reminders WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("🗑️ Refill Reminder Deleted!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}