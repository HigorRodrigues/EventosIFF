package br.edu.eventos.bean;

import br.edu.eventos.DAO.AtividadeDAO;
import br.edu.eventos.DAO.ParticipacaoDAO;
import br.edu.eventos.DAO.PessoaDAO;
import edu.org.eventos.model.Atividade;
import edu.org.eventos.model.Participacao;
import edu.org.eventos.model.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class ParticipacaoBean implements Serializable {
    
    private String idCertificado;
    
    private List<Participacao> participantes = new ArrayList<Participacao>();    

    public void atualizar( Participacao p ){
        System.out.println("######### Id: " + p.getId());
        new ParticipacaoDAO().update(p);
    }
    public void buscaCertificado(){        
        //this.certificado = new CertificadoDAO().findByIdGerado(idCerticado);
        System.out.println("IdCertificado: " + idCertificado);
    }

    public void removerAusente(){
        List<Participacao> presentes = new ArrayList<Participacao>();
        for( Participacao p : this.participantes ){
            if( p.isStatusParticipacao() )
                presentes.add(p);
        }
        System.out.println("###\n####\nParticipantes: \n" + presentes);
    }
    
    public void salvar( Long atividadeId, Long pessoaId ){
        Pessoa p = new PessoaDAO().findById(pessoaId);
        Atividade a = new AtividadeDAO().findById(atividadeId);
        
        Participacao participacao = new Participacao();        
        participacao.setDataInscricao(Calendar.getInstance().getTime());
        participacao.setStatusParticipacao(false);
        participacao.setAtividade(a);        
        participacao.setPessoa(p);
        
        new ParticipacaoDAO().salvar(participacao);        
    }
    
    public String coletarParticipantes( Long idAtividade ){        
        this.participantes = new ParticipacaoDAO().inscritos(idAtividade);                       
        return "faces/listaDePresenca.xhtml?faces-redirect=true";        
    }
    
    public boolean verificaParticipacao( Long idPessoa, Long idAtividade ){
        return new ParticipacaoDAO().verificaInscricao(idPessoa, idAtividade);
    }
    
    public List<Participacao> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participacao> participantes) {
        this.participantes = participantes;
    }

    public String getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(String idCertificado) {
        this.idCertificado = idCertificado;
    }

    
}