package eveniment;

import eveniment.UI.LoginForm;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Eveniment {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(Eveniment::Run);
    }
    
    public static void Run(){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EvenimentPU");
        
        LoginForm loginFrame = new LoginForm(emf);
        loginFrame.setVisible(true);
    }
    
}
