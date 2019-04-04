package br.edu.eventos.DAO;

import edu.org.eventos.model.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PessoaDAO {
    
    public boolean salvar( Pessoa p ){
        boolean status = false;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();            
            em.persist(p);
            em.getTransaction().commit();
            status = true;
        }
        catch( Exception exception ){
            em.getTransaction().rollback();
            exception.printStackTrace();
        }
        finally{
            em.close();
            emf.close();
        }
        return status;
    }
    
    public List<Pessoa> findAll(){
        List<Pessoa> pessoas;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("from Pessoa e", Pessoa.class);
        pessoas = query.getResultList();
        em.close();
        emf.close();
        return pessoas;
    }
    
    public List<Pessoa> findByPerfil(String perfil){        
        List<Pessoa> pessoas = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();        
                
        try{
            em.getTransaction().begin();
            Query query = em.createQuery("select p from Pessoa p where p.perfil = :perfil");
            query.setParameter("perfil", perfil);        
            pessoas = query.getResultList();    
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            em.close();
            emf.close();
        }
        return pessoas;                 
    }
    
    public Pessoa findUser(String email, String senha){
        List<Pessoa> pessoas; 
        Pessoa pessoa = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("from Pessoa e", Pessoa.class);
        pessoas = query.getResultList();
        for( Pessoa p : pessoas ){
            if( p.getEmail().equals(email) && p.getSenha().equals(senha) ){
                pessoa = p;
                break;
            }
        }
        return pessoa;
    }
    
    public Pessoa findUserByEmail(String email){
        List<Pessoa> pessoas; 
        Pessoa pessoa = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("from Pessoa e", Pessoa.class);
        pessoas = query.getResultList();
        for( Pessoa p : pessoas ){
            if( p.getEmail().equals(email) ){
                pessoa = p;
                break;
            }
        }
        return pessoa;
    }        
    
    public Pessoa findById(Long id){        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        return em.find(Pessoa.class, id);
    }
    
}
