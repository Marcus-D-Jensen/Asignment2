package Inlämningsuppgift2;

import junit.framework.TestCase;
import org.junit.Test;
import inlämningsuppgift2.Customer;
import java.time.LocalDate;

public class CustomerTest {
    
    LocalDate signUpDate = LocalDate.now().minusWeeks(32);
    String namn = "Kalle Karlsson";
    String personNr = "9001164562";
    Customer testCustomer1 = new Customer(namn, personNr, signUpDate);
    Customer testCustomer2 = new Customer();
    
    @Test
    public void GetSetCustomerTest() {
        TestCase.assertEquals(testCustomer1.getId(), "Kalle Karlsson 9001164562");
        TestCase.assertEquals(testCustomer1.getSignUpDate(), signUpDate);
        
        signUpDate = LocalDate.now().minusMonths(2);
        namn = "Ture Sventon";
        personNr = "6611141414";
        testCustomer2.setNamn(namn);
        testCustomer2.setPersonNr(personNr);
        testCustomer2.setSignUpDate(signUpDate);
        
        TestCase.assertNotSame(testCustomer1, testCustomer2);
        TestCase.assertEquals(testCustomer2.getId(), namn + " " + personNr);
        TestCase.assertFalse(testCustomer2.getSignUpDate().isEqual(LocalDate.now()));
                
    }

}
