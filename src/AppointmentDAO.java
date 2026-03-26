import java.sql.*;

public class AppointmentDAO {

    Connection con = DBConnection.getConnection();

    // ───── CREATE ─────────────────────────────────
    public void addAppointment(String patientName, String doctorName,
                               String appointmentDate, String appointmentTime,
                               String reason, String status) {
        try {
            String sql = "INSERT INTO appointments "
                       + "(patient_name, doctor_name, appointment_date, appointment_time, reason, status) "
                       + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, patientName);
            ps.setString(2, doctorName);
            ps.setString(3, appointmentDate);
            ps.setString(4, appointmentTime);
            ps.setString(5, reason);
            ps.setString(6, status);
            ps.executeUpdate();
            System.out.println("✅ Appointment Added Successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── READ (View All) ────────────────────────
    public void viewAllAppointments() {
        try {
            String sql   = "SELECT * FROM appointments";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n-------- All Appointments --------");
            while (rs.next()) {
                System.out.println(
                    "ID: "           + rs.getInt   ("id")               +
                    " | Patient: "   + rs.getString("patient_name")     +
                    " | Doctor: "    + rs.getString("doctor_name")      +
                    " | Date: "      + rs.getString("appointment_date") +
                    " | Time: "      + rs.getString("appointment_time") +
                    " | Reason: "    + rs.getString("reason")           +
                    " | Status: "    + rs.getString("status")
                );
            }
            System.out.println("----------------------------------");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── READ (Search by Patient) ───────────────
    public void searchByPatient(String patientName) {
        try {
            String sql = "SELECT * FROM appointments WHERE patient_name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, patientName);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n---- Appointments for: " + patientName + " ----");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                    "ID: "         + rs.getInt   ("id")               +
                    " | Doctor: "  + rs.getString("doctor_name")      +
                    " | Date: "    + rs.getString("appointment_date") +
                    " | Time: "    + rs.getString("appointment_time") +
                    " | Reason: "  + rs.getString("reason")           +
                    " | Status: "  + rs.getString("status")
                );
            }
            if (!found) System.out.println("⚠️ No appointments found for: " + patientName);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── UPDATE ─────────────────────────────────
    public void updateAppointment(int id, String newDate,
                                  String newTime, String newStatus) {
        try {
            String sql = "UPDATE appointments SET appointment_date=?, appointment_time=?, status=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newDate);
            ps.setString(2, newTime);
            ps.setString(3, newStatus);
            ps.setInt   (4, id);
            ps.executeUpdate();
            System.out.println("✅ Appointment Updated!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ───── DELETE ─────────────────────────────────
    public void deleteAppointment(int id) {
        try {
            String sql = "DELETE FROM appointments WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("🗑️ Appointment Deleted!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}