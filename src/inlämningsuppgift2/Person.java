package inlämningsuppgift2;

public class Person {
    private String namn;
    private String personNr;
    
    public Person() {}
    
    public Person(String namn, String personNr) {
        this.namn = namn;
        this.personNr = personNr;
    }
    
    public void setNamn(String namn){
        this.namn = namn;
    }
    public void setPersonNr(String personNr) {
        this.personNr = personNr;
    }

    public String getNamn() {
        return namn;
    }
    public String getPersonNr() {
        return personNr;
    }
    
}
