
package br.edu.eventos.DAO;

import edu.org.eventos.model.Unidade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UnidadeDAO {
    
    public boolean Cadastrar( Unidade unidade ){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        try{        
            em.getTransaction().begin();
            em.persist(unidade);
            em.getTransaction().commit();
        }
        catch( Exception e ){
            return false;
        }
        finally{
            em.close();
            emf.close();
        }
        return true;
    }
    
    public Unidade findById( Long id ){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        try{
            return em.find(Unidade.class, id);
        }
        catch( Exception e ){
            e.printStackTrace();
        }
        finally{
            em.close();
            emf.close();
        }
        return null;
    }
    
    public List<Unidade> findAll(){
        List<Unidade> unidades;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("from Unidade e", Unidade.class);
        unidades = query.getResultList();
        em.close();
        emf.close();
        return unidades;
    }
    
}
