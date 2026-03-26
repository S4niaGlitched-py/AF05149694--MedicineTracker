import java.sql.*;

public class PatientHistoryDAO {

    Connection con = DBConnection.getConnection();

    // ───── CREATE ─────────────────────────────────
    public void addHistory(String patientName, String diagnosis,
                           String treatment, String visitDate,
                           String doctorName, String notes) {
        try {
            String sql = "INSERT INTO patient_history "
                       + "(patient_name, diagnosis, treatment, visit_date, doctor_name, notes) "
                       + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, patientName);
            ps.setString(2, diagnosis);
            ps.setString(3, treatment);
            ps.setString(4, visitDate);
            ps.setString(5, doctorName);
            ps.setString(6, notes);
            ps.executeUpdate();
            System.out.println("✅ Patient History Added!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── READ (View All) ────────────────────────
    public void viewAllHistory() {
        try {
            String sql   = "SELECT * FROM patient_history";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n-------- All Patient History --------");
            while (rs.next()) {
                System.out.println(
                    "ID: "           + rs.getInt   ("id")           +
                    " | Patient: "   + rs.getString("patient_name") +
                    " | Diagnosis: " + rs.getString("diagnosis")    +
                    " | Treatment: " + rs.getString("treatment")    +
                    " | Date: "      + rs.getString("visit_date")   +
                    " | Doctor: "    + rs.getString("doctor_name")  +
                    " | Notes: "     + rs.getString("notes")
                );
            }
            System.out.println("-------------------------------------");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── READ (Search by Patient) ───────────────
    public void searchPatientHistory(String patientName) {
        try {
            String sql = "SELECT * FROM patient_history WHERE patient_name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, patientName);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n---- History for: " + patientName + " ----");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                    "ID: "           + rs.getInt   ("id")           +
                    " | Diagnosis: " + rs.getString("diagnosis")    +
                    " | Treatment: " + rs.getString("treatment")    +
                    " | Date: "      + rs.getString("visit_date")   +
                    " | Doctor: "    + rs.getString("doctor_name")  +
                    " | Notes: "     + rs.getString("notes")
                );
            }
            if (!found) System.out.println("⚠️ No history found for: " + patientName);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── UPDATE ─────────────────────────────────
    public void updateHistory(int id, String newTreatment, String newNotes) {
        try {
            String sql = "UPDATE patient_history SET treatment=?, notes=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newTreatment);
            ps.setString(2, newNotes);
            ps.setInt   (3, id);
            ps.executeUpdate();
            System.out.println("✅ Patient History Updated!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── DELETE ─────────────────────────────────
    public void deleteHistory(int id) {
        try {
            String sql = "DELETE FROM patient_history WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("🗑️ Patient History Deleted!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}