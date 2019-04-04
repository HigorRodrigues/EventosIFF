package br.edu.eventos.DAO;

import edu.org.eventos.model.Local;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class LocalDAO {

    public boolean salvar(Local l){
        boolean resultado = false;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            em.persist(l);
            em.getTransaction().commit();
            resultado = true;
        }
        catch( Exception e ){
            em.getTransaction().rollback();
        }
        finally{
            em.close();
        }        
        return resultado;
    }
    
    public boolean apagar( Long id ){
        boolean resultado = false;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            Local local = em.find(Local.class, id);
            em.remove(local);
            em.getTransaction().commit();
            resultado = true;
        }
        catch( Exception e ){
            em.getTransaction().rollback();
        }
        finally{
            em.close();
        }        
        return resultado;
    }
    
    public Local findById( Long id ){
        Local local;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        try{
            local = em.find(Local.class, id);
        }
        finally{
            emf.close();
        }
        return local;
    }
    
    public List<Local> findAll(){
        List<Local> todosLocais = new ArrayList();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        try{
            Query query = em.createQuery("from Local e", Local.class);
            todosLocais = query.getResultList();
        }
        finally{
            emf.close();
        }
        return todosLocais;
    }
}
