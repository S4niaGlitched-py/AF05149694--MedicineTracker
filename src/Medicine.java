public class Medicine {

    int    id;
    String patientName;
    String medicineName;
    String dosage;
    String frequency;
    String reminderTime;
    String startDate;
    String endDate;

    // Constructor
    Medicine(int id, String patientName, String medicineName, String dosage,
             String frequency, String reminderTime, String startDate, String endDate) {
        this.id           = id;
        this.patientName  = patientName;
        this.medicineName = medicineName;
        this.dosage       = dosage;
        this.frequency    = frequency;
        this.reminderTime = reminderTime;
        this.startDate    = startDate;
        this.endDate      = endDate;
    }
}