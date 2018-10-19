package Inlämningsuppgift2;

import junit.framework.TestCase;
import org.junit.Test;
import inlämningsuppgift2.Person;

public class PersonTest {
    Person testPerson = new Person("Kalle Karlsson", "9001164545");
    
    @Test
    public void GetSetPersonTest() {
        
        TestCase.assertEquals(testPerson.getNamn(), "Kalle Karlsson");
        TestCase.assertEquals(testPerson.getPersonNr(), "9001164545");
        testPerson.setNamn("Ture Sventon");
        testPerson.setPersonNr("9203264396");
        TestCase.assertNotSame(testPerson.getNamn(), "Kalle Karlsson");
        TestCase.assertNotSame(testPerson.getPersonNr(), "9001164545");
    }
}
