package inlämningsuppgift2;

import java.time.LocalDate;

public class Customer extends Person {
    
    private LocalDate signUpDate;
    
    public Customer() { }
    
    public Customer(String namn, String personNr, LocalDate signUpDate) {
        super(namn,personNr);
        this.signUpDate = signUpDate;
    }
    
    public void setSignUpDate (LocalDate signUpDate) {
        this.signUpDate = signUpDate;
    }
    
    public String getId() {
        return this.getNamn() + " " + this.getPersonNr();
    }
    
    public LocalDate getSignUpDate() {
        return signUpDate;
    }
}