
package br.edu.eventos.DAO;

import edu.org.eventos.model.Evento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EventoDAO {

    //salvar o evento no banco de dados
    public void salvar( Evento e ){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();        
        
        try{
            em.getTransaction().begin();            
            
            if( e.getId() == null )
                em.persist(e);
            else
                em.merge(e);
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
    
    //SELECT de todos os eventos do banco de dados
    public List<Evento> findAll(){
        List<Evento> eventos;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("from Evento e", Evento.class);
        eventos = query.getResultList();
        em.close();
        emf.close();
        return eventos;
    }        
    
    //SELECT do evento pelo ID
    public Evento findById( Long id ){
        Evento evento;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        evento = em.find(Evento.class, id);
        em.close();
        emf.close();
        return evento;
    }    
    
    //remove um evento
    public boolean delete( Evento e ){
        boolean resposta = false;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            if( !em.contains(e))
                e = em.merge(e);
            em.remove(e);
            
            em.getTransaction().commit();
            resposta = true;
        }
        catch( Exception exception ){
            em.getTransaction().rollback();
            exception.printStackTrace();
        }
        finally{
            em.close();
            emf.close();
        }        
        return resposta;
    }

}