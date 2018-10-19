package inlämningsuppgift2;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class GymFactory {
    
    private final LocalDate today = LocalDate.now();
    private final Path customerDatabasePath = Paths.get("src/Inlämningsuppgift2/customers.txt");
    private final Path workoutLoggerPath = Paths.get("src/inlämningsuppgift2/workoutlog.txt");
    private Database database = new Database(customerDatabasePath, workoutLoggerPath);
    
    
    
    public String checkMember(String customerId) {
            Customer c = database.getCustomer(customerId);
            
            if (c != null) {
                if (!database.checkIfMembershipExpired(c, today)) {
                    database.logCustomerWorkout(c, today);
                    return String.format("%s, välkomen till Best Gym Ever!", c.getNamn());
                    
                } else {
                    return String.format("%s medlemskap har gått ut!", c.getNamn());
                }
                
            } else {
                return String.format("%s har aldrig varit medlem!", customerId);
            }
    }
    
    public String readWorkoutLog() {
        return database.getWorkoutLog();
    }
}
