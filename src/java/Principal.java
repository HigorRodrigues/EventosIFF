
import edu.org.eventos.model.Pessoa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Principal {

    public static void main( String[] args ){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();

        
        em.close();
        emf.close();
    }
}
