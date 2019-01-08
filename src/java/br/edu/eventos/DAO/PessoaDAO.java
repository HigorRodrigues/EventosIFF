package br.edu.eventos.DAO;

import edu.org.eventos.model.Evento;
import edu.org.eventos.model.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PessoaDAO {
    
    public void salvar( Pessoa p ){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();            
            em.persist(p);
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
