import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        MedicineDAO        medicineDAO   = new MedicineDAO();
        AppointmentDAO     appointDAO    = new AppointmentDAO();
        RefillDAO          refillDAO     = new RefillDAO();
        MedicineStockDAO   stockDAO      = new MedicineStockDAO();
        PatientHistoryDAO  historyDAO    = new PatientHistoryDAO();

        Scanner scanner = new Scanner(System.in);
        int choice      = 0;

        while (choice != 6) {
            System.out.println("\n==============================");
            System.out.println("  💊 Medicine Reminder &     ");
            System.out.println("     Tracker System          ");
            System.out.println("==============================");
            System.out.println("1. 💊 Medicine Management");
            System.out.println("2. 🏥 Doctor Appointment Tracker");
            System.out.println("3. 🔔 Refill Reminder");
            System.out.println("4. 📦 Medicine Stock Tracker");
            System.out.println("5. 📋 Patient History Tracker");
            System.out.println("6. 🚪 Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> medicineMenu(medicineDAO, scanner);
                case 2 -> appointmentMenu(appointDAO, scanner);
                case 3 -> refillMenu(refillDAO, scanner);
                case 4 -> stockMenu(stockDAO, scanner);
                case 5 -> historyMenu(historyDAO, scanner);
                case 6 -> System.out.println("Goodbye! 💊 Stay healthy!");
                default -> System.out.println("⚠️ Invalid choice.");
            }
        }
        scanner.close();
    }

    // ══════════════════════════════════════════════
    // 1. MEDICINE MENU
    // ══════════════════════════════════════════════
    static void medicineMenu(MedicineDAO dao, Scanner sc) {
        System.out.println("\n-- 💊 Medicine Management --");
        System.out.println("1. Add Medicine");
        System.out.println("2. View All Medicines");
        System.out.println("3. Search by Patient");
        System.out.println("4. Update Medicine");
        System.out.println("5. Delete Medicine");
        System.out.print("Enter choice: ");
        int c = sc.nextInt(); sc.nextLine();

        switch (c) {
            case 1:
                System.out.print("Patient name      : "); String pn = sc.nextLine();
                System.out.print("Medicine name     : "); String mn = sc.nextLine();
                System.out.print("Dosage            : "); String ds = sc.nextLine();
                System.out.print("Frequency         : "); String fr = sc.nextLine();
                System.out.print("Reminder time     : "); String rt = sc.nextLine();
                System.out.print("Start date        : "); String sd = sc.nextLine();
                System.out.print("End date          : "); String ed = sc.nextLine();
                dao.addMedicine(pn, mn, ds, fr, rt, sd, ed);
                break;
            case 2: dao.viewAllMedicines(); break;
            case 3:
                System.out.print("Patient name: "); String s = sc.nextLine();
                dao.searchByPatient(s);
                break;
            case 4:
                System.out.print("ID to update      : "); int uid = sc.nextInt(); sc.nextLine();
                System.out.print("New dosage        : "); String nd = sc.nextLine();
                System.out.print("New reminder time : "); String nr = sc.nextLine();
                System.out.print("New end date      : "); String ne = sc.nextLine();
                dao.updateMedicine(uid, nd, nr, ne);
                break;
            case 5:
                System.out.print("ID to delete: "); int did = sc.nextInt();
                dao.deleteMedicine(did);
                break;
            default: System.out.println("⚠️ Invalid choice.");
        }
    }

    // ══════════════════════════════════════════════
    // 2. APPOINTMENT MENU
    // ══════════════════════════════════════════════
    static void appointmentMenu(AppointmentDAO dao, Scanner sc) {
        System.out.println("\n-- 🏥 Doctor Appointment Tracker --");
        System.out.println("1. Add Appointment");
        System.out.println("2. View All Appointments");
        System.out.println("3. Search by Patient");
        System.out.println("4. Update Appointment");
        System.out.println("5. Delete Appointment");
        System.out.print("Enter choice: ");
        int c = sc.nextInt(); sc.nextLine();

        switch (c) {
            case 1:
                System.out.print("Patient name      : "); String pn = sc.nextLine();
                System.out.print("Doctor name       : "); String dn = sc.nextLine();
                System.out.print("Date              : "); String ad = sc.nextLine();
                System.out.print("Time              : "); String at = sc.nextLine();
                System.out.print("Reason            : "); String rs = sc.nextLine();
                System.out.print("Status            : "); String st = sc.nextLine();
                dao.addAppointment(pn, dn, ad, at, rs, st);
                break;
            case 2: dao.viewAllAppointments(); break;
            case 3:
                System.out.print("Patient name: "); String s = sc.nextLine();
                dao.searchByPatient(s);
                break;
            case 4:
                System.out.print("ID to update  : "); int uid = sc.nextInt(); sc.nextLine();
                System.out.print("New date      : "); String nd = sc.nextLine();
                System.out.print("New time      : "); String nt = sc.nextLine();
                System.out.print("New status    : "); String ns = sc.nextLine();
                dao.updateAppointment(uid, nd, nt, ns);
                break;
            case 5:
                System.out.print("ID to delete: "); int did = sc.nextInt();
                dao.deleteAppointment(did);
                break;
            default: System.out.println("⚠️ Invalid choice.");
        }
    }

    // ══════════════════════════════════════════════
    // 3. REFILL MENU
    // ══════════════════════════════════════════════
    static void refillMenu(RefillDAO dao, Scanner sc) {
        System.out.println("\n-- 🔔 Refill Reminder --");
        System.out.println("1. Add Refill Reminder");
        System.out.println("2. View All Reminders");
        System.out.println("3. Check Due Refills");
        System.out.println("4. Update Reminder");
        System.out.println("5. Delete Reminder");
        System.out.print("Enter choice: ");
        int c = sc.nextInt(); sc.nextLine();

        switch (c) {
            case 1:
                System.out.print("Patient name      : "); String pn = sc.nextLine();
                System.out.print("Medicine name     : "); String mn = sc.nextLine();
                System.out.print("Last refill date  : "); String lr = sc.nextLine();
                System.out.print("Next refill date  : "); String nr = sc.nextLine();
                System.out.print("Quantity left     : "); int ql    = sc.nextInt(); sc.nextLine();
                System.out.print("Status            : "); String rs = sc.nextLine();
                dao.addRefill(pn, mn, lr, nr, ql, rs);
                break;
            case 2: dao.viewAllRefills(); break;
            case 3: dao.checkDueRefills(); break;
            case 4:
                System.out.print("ID to update      : "); int uid = sc.nextInt(); sc.nextLine();
                System.out.print("New qty left      : "); int nq  = sc.nextInt(); sc.nextLine();
                System.out.print("New next refill   : "); String nn = sc.nextLine();
                dao.updateRefill(uid, nq, nn);
                break;
            case 5:
                System.out.print("ID to delete: "); int did = sc.nextInt();
                dao.deleteRefill(did);
                break;
            default: System.out.println("⚠️ Invalid choice.");
        }
    }

    // ══════════════════════════════════════════════
    // 4. STOCK MENU
    // ══════════════════════════════════════════════
    static void stockMenu(MedicineStockDAO dao, Scanner sc) {
        System.out.println("\n-- 📦 Medicine Stock Tracker --");
        System.out.println("1. Add Stock");
        System.out.println("2. View All Stock");
        System.out.println("3. Check Low Stock Alert");
        System.out.println("4. Update Stock");
        System.out.println("5. Delete Stock");
        System.out.print("Enter choice: ");
        int c = sc.nextInt(); sc.nextLine();

        switch (c) {
            case 1:
                System.out.print("Medicine name     : "); String mn = sc.nextLine();
                System.out.print("Quantity available: "); int qa    = sc.nextInt(); sc.nextLine();
                System.out.print("Expiry date       : "); String ed = sc.nextLine();
                System.out.print("Reorder level     : "); int rl    = sc.nextInt(); sc.nextLine();
                System.out.print("Last updated      : "); String lu = sc.nextLine();
                dao.addStock(mn, qa, ed, rl, lu);
                break;
            case 2: dao.viewAllStock(); break;
            case 3: dao.checkLowStock(); break;
            case 4:
                System.out.print("ID to update  : "); int uid = sc.nextInt(); sc.nextLine();
                System.out.print("New quantity  : "); int nq  = sc.nextInt(); sc.nextLine();
                System.out.print("Updated date  : "); String nd = sc.nextLine();
                dao.updateStock(uid, nq, nd);
                break;
            case 5:
                System.out.print("ID to delete: "); int did = sc.nextInt();
                dao.deleteStock(did);
                break;
            default: System.out.println("⚠️ Invalid choice.");
        }
    }

    // ══════════════════════════════════════════════
    // 5. PATIENT HISTORY MENU
    // ══════════════════════════════════════════════
    static void historyMenu(PatientHistoryDAO dao, Scanner sc) {
        System.out.println("\n-- 📋 Patient History Tracker --");
        System.out.println("1. Add History");
        System.out.println("2. View All History");
        System.out.println("3. Search by Patient");
        System.out.println("4. Update History");
        System.out.println("5. Delete History");
        System.out.print("Enter choice: ");
        int c = sc.nextInt(); sc.nextLine();

        switch (c) {
            case 1:
                System.out.print("Patient name  : "); String pn = sc.nextLine();
                System.out.print("Diagnosis     : "); String dg = sc.nextLine();
                System.out.print("Treatment     : "); String tr = sc.nextLine();
                System.out.print("Visit date    : "); String vd = sc.nextLine();
                System.out.print("Doctor name   : "); String dn = sc.nextLine();
                System.out.print("Notes         : "); String nt = sc.nextLine();
                dao.addHistory(pn, dg, tr, vd, dn, nt);
                break;
            case 2: dao.viewAllHistory(); break;
            case 3:
                System.out.print("Patient name: "); String s = sc.nextLine();
                dao.searchPatientHistory(s);
                break;
            case 4:
                System.out.print("ID to update    : "); int uid = sc.nextInt(); sc.nextLine();
                System.out.print("New treatment   : "); String nt2 = sc.nextLine();
                System.out.print("New notes       : "); String nn  = sc.nextLine();
                dao.updateHistory(uid, nt2, nn);
                break;
            case 5:
                System.out.print("ID to delete: "); int did = sc.nextInt();
                dao.deleteHistory(did);
                break;
            default: System.out.println("⚠️ Invalid choice.");
        }
    }
}