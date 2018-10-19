package inlämningsuppgift2;

import javax.swing.JOptionPane;

public class Inlämningsuppgift2 {

    public static void main(String[] args){
        
        GymFactory factory = new GymFactory();
        
        boolean done = false;
        
        while (!done) {
            String customerId = JOptionPane.showInputDialog("Kundens ID: ");
            
            if (customerId  == null || customerId.equals("")) { break; }
            
            JOptionPane.showMessageDialog(null, factory.checkMember(customerId));
        }
        
        JOptionPane.showMessageDialog(null, factory.readWorkoutLog());
        
    }

}
