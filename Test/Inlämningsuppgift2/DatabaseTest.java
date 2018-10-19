package Inlämningsuppgift2;

import inlämningsuppgift2.Database;
import inlämningsuppgift2.Customer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import junit.framework.TestCase;
import org.junit.Test;

public class DatabaseTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Path customerDatabasePath = Paths.get("Test/Inlämningsuppgift2/customers.txt");
    Path falseCustomerDatabasePath = Paths.get("Test/Inlämningsuppgift2/falsecustomers.txt");
    Path workoutLoggerPath = Paths.get("Test/Inlämningsuppgift2/workoutlog.txt");
    
    String testNamn = "Nahema Ninsson";
    String testPersonNr = "7805211234";
    LocalDate testDate = LocalDate.of(2018, Month.JANUARY, 04);
    
    String testNamn2 = "Marcus Damsgaard Jensen";
    String testPersonNr2 = "9203264396";
    LocalDate testDate2 = LocalDate.now().plusDays(1);
    
    LocalDate today = LocalDate.now();
   
    Database testDatabase = new Database(customerDatabasePath, workoutLoggerPath);
    Database falseDatabase = new Database(falseCustomerDatabasePath, workoutLoggerPath);
    Customer testCustomer1 = new Customer(testNamn, testPersonNr, testDate);
    Customer testCustomer2 = new Customer(testNamn2, testPersonNr2, testDate2);
    
    
    @Test
    public void readCustomerTest() {
        
        TestCase.assertTrue(testDatabase.getCustomer(testNamn).getClass() == testCustomer1.getClass());
        TestCase.assertTrue(testDatabase.getCustomer(testNamn).getId().equals(testCustomer1.getId()));
        TestCase.assertEquals(testDatabase.getCustomer(testPersonNr).getSignUpDate(), testCustomer1.getSignUpDate());
        
        TestCase.assertNotNull(testDatabase.getCustomer("7608021234"));
        TestCase.assertTrue(testDatabase.getCustomer("4") == null);
        
        TestCase.assertNotNull(testDatabase.getCustomer("Alhambra Aromes"));
        TestCase.assertTrue(testDatabase.getCustomer("hfjkndlssbhjas") == null);
    }
    
    
    
    @Test
    public void checkIfMembershipExpiredTest() {
        TestCase.assertFalse(testDatabase.checkIfMembershipExpired(testCustomer1, today));
        
        testCustomer1.setSignUpDate(today.minusYears(1));
        TestCase.assertTrue(testDatabase.checkIfMembershipExpired(testCustomer1, today));
    }
    
    @Test
    public void logCustomerWorkoutTest() throws IOException {
        
        Files.deleteIfExists(workoutLoggerPath);
        TestCase.assertFalse(Files.exists(workoutLoggerPath));
        
        testDatabase.logCustomerWorkout(testCustomer1, today);
        TestCase.assertTrue(Files.exists(workoutLoggerPath));
        
        String testln = String.format("%s : %s %s\n", formatter.format(today), testNamn, testPersonNr);
        TestCase.assertEquals(testln, testDatabase.getWorkoutLog());
        
        testDatabase.logCustomerWorkout(testCustomer2, testDate2);
        testln += String.format("%s : %s %s\n", formatter.format(testDate2), testNamn2, testPersonNr2);
        TestCase.assertEquals(testln, testDatabase.getWorkoutLog());
        
    }
}
