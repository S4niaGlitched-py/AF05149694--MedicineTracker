import java.sql.*;

public class MedicineDAO {

    Connection con = DBConnection.getConnection();

    // ───── CREATE ─────────────────────────────────
    public void addMedicine(String patientName, String medicineName, String dosage,
                            String frequency, String reminderTime,
                            String startDate, String endDate) {
        try {
            String sql = "INSERT INTO medicines "
                       + "(patient_name, medicine_name, dosage, frequency, reminder_time, start_date, end_date) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, patientName);
            ps.setString(2, medicineName);
            ps.setString(3, dosage);
            ps.setString(4, frequency);
            ps.setString(5, reminderTime);
            ps.setString(6, startDate);
            ps.setString(7, endDate);
            ps.executeUpdate();
            System.out.println("✅ Medicine Added Successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── READ (View All) ────────────────────────
    public void viewAllMedicines() {
        try {
            String sql   = "SELECT * FROM medicines";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n-------- All Medicine Records --------");
            while (rs.next()) {
                System.out.println(
                    "ID: "            + rs.getInt   ("id")            +
                    " | Patient: "    + rs.getString("patient_name")  +
                    " | Medicine: "   + rs.getString("medicine_name") +
                    " | Dosage: "     + rs.getString("dosage")        +
                    " | Frequency: "  + rs.getString("frequency")     +
                    " | Reminder: "   + rs.getString("reminder_time") +
                    " | Start: "      + rs.getString("start_date")    +
                    " | End: "        + rs.getString("end_date")
                );
            }
            System.out.println("--------------------------------------");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── READ (Search by Patient Name) ──────────
    public void searchByPatient(String patientName) {
        try {
            String sql = "SELECT * FROM medicines WHERE patient_name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, patientName);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n---- Medicines for: " + patientName + " ----");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                    "ID: "           + rs.getInt   ("id")            +
                    " | Medicine: "  + rs.getString("medicine_name") +
                    " | Dosage: "    + rs.getString("dosage")        +
                    " | Frequency: " + rs.getString("frequency")     +
                    " | Reminder: "  + rs.getString("reminder_time") +
                    " | Start: "     + rs.getString("start_date")    +
                    " | End: "       + rs.getString("end_date")
                );
            }
            if (!found) {
                System.out.println("⚠️ No records found for: " + patientName);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── UPDATE ─────────────────────────────────
    public void updateMedicine(int id, String newDosage,
                               String newReminderTime, String newEndDate) {
        try {
            String sql = "UPDATE medicines SET dosage=?, reminder_time=?, end_date=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newDosage);
            ps.setString(2, newReminderTime);
            ps.setString(3, newEndDate);
            ps.setInt   (4, id);
            ps.executeUpdate();
            System.out.println("✅ Medicine Updated Successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── DELETE ─────────────────────────────────
    public void deleteMedicine(int id) {
        try {
            String sql = "DELETE FROM medicines WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("🗑️ Medicine Record Deleted Successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}