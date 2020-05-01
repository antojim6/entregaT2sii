package mainPackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Trabajo_GrupalUP");
        EntityManager em = emf.createEntityManager();
        
        em.close();
        emf.close();
    }
    
}
