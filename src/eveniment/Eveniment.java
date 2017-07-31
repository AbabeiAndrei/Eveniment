package eveniment;

import eveniment.DataLayer.CategoryJpaController;
import eveniment.Entities.Category;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Eveniment {

    public static void main(String[] args) {
        
        CategoryJpaController con = new CategoryJpaController(Persistence.createEntityManagerFactory("EvenimentPU"));
        
        Category cat = con.findCategory(1);
        
        System.out.println(cat.getName().toString());
    }
    
}
