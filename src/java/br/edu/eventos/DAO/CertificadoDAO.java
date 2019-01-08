package br.edu.eventos.DAO;

import edu.org.eventos.model.Certificado;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CertificadoDAO {
    
    public boolean salvar( Certificado c){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        boolean sucesso = false;
        
        try{
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        }
        catch( Exception exception ){
            em.getTransaction().rollback();
            exception.printStackTrace();
        }
        finally{
            em.close();
            emf.close();
        }
        return sucesso;
    }
    
    public Certificado findByIdGerado( String idGerado ){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        
        Certificado c = null;
        
        try{
            em.getTransaction().begin();
            Query query = em.createQuery("select c from Certificado c where idGerado = :idGerado");
            query.setParameter("idGerado", idGerado);
            c = (Certificado) query.getSingleResult();            
        }
        catch( Exception exception ){
            exception.getStackTrace();
        }
        finally{
            em.close();
            emf.close();
        }
        return c;
    }
}
