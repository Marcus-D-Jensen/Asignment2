package Inlämningsuppgift2;

import junit.framework.TestCase;
import org.junit.Test;
import inlämningsuppgift2.GymFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GymFactoryTest {
    Path workoutLoggerPath = Paths.get("src/Inlämningsuppgift2/workoutlog.txt");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate today = LocalDate.now();
    GymFactory factory = new GymFactory();
    String customerId = "Liu Lingren";
    String expiredCustomerId = "Fritjoff Flacon";
    String notARealId = "hfjdks bwe";
    
    @Test
    public void CheckMemberTest() {
        TestCase.assertNotNull(factory.checkMember(customerId));
        TestCase.assertEquals(String.format("%s, välkomen till Best Gym Ever!", customerId), factory.checkMember(customerId));
        TestCase.assertEquals(String.format("%s medlemskap har gått ut!", expiredCustomerId), factory.checkMember(expiredCustomerId));
        TestCase.assertEquals(factory.checkMember(notARealId), String.format("%s har aldrig varit medlem!", notARealId));
    }
    
    @Test
    public void readWorkoutLogTest() throws IOException {
        
        Files.deleteIfExists(workoutLoggerPath);
        
        TestCase.assertEquals(factory.readWorkoutLog(), "Inga kunder har tränat ännu!");
        
        factory.checkMember(customerId);
        TestCase.assertEquals(factory.readWorkoutLog(), formatter.format(today) + " : Liu Lingren 9110261234\n");
    }
}
