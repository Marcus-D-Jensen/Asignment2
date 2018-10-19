package inlämningsuppgift2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Database {
    
    private final Path customerDatabasePath;
    private final Path workoutLoggerPath;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public Database(Path customerDatbasePath, Path workoutLoggerPath) {
        this.customerDatabasePath = customerDatbasePath;
        this.workoutLoggerPath = workoutLoggerPath;
    }
    
    public Customer getCustomer(String customerId) {
        try (Scanner sc = new Scanner(customerDatabasePath)){
            Customer c = new Customer();
            String templn;
            
            while (sc.hasNext()) {
                String[] tempArray = sc.nextLine().split(", ");
                
                if (tempArray[0].equalsIgnoreCase(customerId) || tempArray[1].equalsIgnoreCase(customerId)) {
                    c.setPersonNr(tempArray[0]);
                    c.setNamn(tempArray[1]);
                    c.setSignUpDate(LocalDate.parse(sc.nextLine(), formatter));
                return c;
                
                }
                sc.nextLine();
            }
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Felaktig sökväg");
            e.printStackTrace();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Något gick fel!");
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean checkIfMembershipExpired(Customer c, LocalDate today) {
        return !today.minusYears(1).isBefore(c.getSignUpDate());
    }
    
    public void logCustomerWorkout(Customer c, LocalDate today) {
        
        try (BufferedWriter writer = Files.newBufferedWriter(workoutLoggerPath, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(formatter.format(today) + " : " + c.getId() + "\n");
        }
        catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Felaktig sökväg");
            e.printStackTrace();
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Något gick fel!");
            e.printStackTrace();
        }
    }
    
    public String getWorkoutLog() {
        
        String workoutLog = "";
        try (Scanner sc = new Scanner(workoutLoggerPath)) {
            while (sc.hasNext()) {
                workoutLog += sc.nextLine() + "\n";
            }
        }
        catch(IOException e) {
            return "Inga kunder har tränat ännu!";
            
        }
        
        return workoutLog;
    }
    
    public Path getcustomerDatabasePath() {
        return customerDatabasePath;
    }
    
    public Path getworkoutLoggerPath() {
        return workoutLoggerPath;
    }    
}