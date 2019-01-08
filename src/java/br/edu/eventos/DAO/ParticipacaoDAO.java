package br.edu.eventos.DAO;

import edu.org.eventos.model.Participacao;
import edu.org.eventos.model.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ParticipacaoDAO {
    
    
    public boolean salvar( Participacao p ){        
        boolean resultado = false;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            em.persist(p);
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
    
    public void update( Participacao p ){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Participacao pa = em.find(Participacao.class, p.getId());
            if( pa != null ){
                em.merge(p);
            }
            em.getTransaction().commit();
        }
        catch( Exception e ){
            em.getTransaction().rollback();
        }
        finally{
            em.close();
        }
    }
    
    public int countPessoasPorAtividade( Long idAtividade ){    
        int total = 0;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        try{
            String sql = "select p from Participacao p where p.atividade.id = :idAtividade";
            Query query = em.createQuery(sql);
            query.setParameter("idAtividade", idAtividade);
            total = query.getResultList().size();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return total;
    }
    
    public boolean verificaInscricao( Long idPessoa, Long idAtividade ){
        boolean verificador = false;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        try{
            String sql = "select p from Participacao p where p.atividade.id = :idAtividade and p.pessoa.id = :idPessoa";
            Query query = em.createQuery(sql);
            query.setParameter("idAtividade", idAtividade);
            query.setParameter("idPessoa", idPessoa);
            List<Object> lista = query.getResultList();
            if( lista.size() > 0)
                verificador = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return verificador;
    }
    
    public List<Participacao> inscritos( Long idAtividade ){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaJPAPU");
        EntityManager em = emf.createEntityManager();
        
        List<Participacao> participantes = null;
        
        try{
            String sql = "select p from Participacao p where p.atividade.id = :idAtividade order by p.pessoa.nome ASC";
            Query query = em.createQuery(sql);
            query.setParameter("idAtividade", idAtividade);
            participantes = query.getResultList();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return participantes;
    }
}