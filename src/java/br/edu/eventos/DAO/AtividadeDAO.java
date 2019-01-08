
package br.edu.eventos.DAO;

import edu.org.eventos.model.Atividade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class AtividadeDAO {
    
    public void salvar( Atividade a ){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        try{            
            em.getTransaction().begin();            
            em.persist(a);            
            em.getTransaction().commit();            
        }
        catch( Exception exception ){
            exception.printStackTrace();
        }
        finally{
            em.close();
            emf.close();
        }
    }
    
    public List<Atividade> atividadeByEvento( Long idEvento ){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        String sql = "select a from Atividade a where a.evento.id = :idEvento";
        Query query = em.createQuery(sql);
        query.setParameter("idEvento", idEvento);
        List<Atividade> atividades = query.getResultList();
        em.close();
        emf.close();        
        return atividades;
    }
    
    public Atividade findById(Long id){        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        return em.find(Atividade.class, id);
    }
}