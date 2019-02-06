package br.edu.eventos.bean;

import br.edu.evento.utilitario.LerArquivoSerie;
import br.edu.eventos.DAO.AtividadeDAO;
import br.edu.eventos.DAO.EventoDAO;
import br.edu.eventos.DAO.LocalDAO;
import br.edu.eventos.DAO.ParticipacaoDAO;
import edu.org.eventos.model.Atividade;
import edu.org.eventos.model.Evento;
import edu.org.eventos.model.Local;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;


@ManagedBean
@RequestScoped
public class AtividadeBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id, id_evento, id_local;
    private String nome; 
    private Date dtInicio;    
    private Date dtFim;        
    private Date horaInicio;    
    private int carga_horaria;    
    private Evento evento;
    private List<Atividade> atividades;
    private boolean statusEncerramento;
    
    private String gerarIdAtividade(Evento e){                
        return e.getIdGerado() + "A" + 
                new LerArquivoSerie().retornaSerie("/home/higor/NetBeansProjects/eventosIFF/web/resources/geradores/geradorIdAtividade.txt");
    }
    
    //método para salvar a atividade
    public String salvar(){                
        HttpSession session = new AutenticadorBean().retornaSessao();
        Evento eSession = (Evento) session.getAttribute("evento");
        Evento e = new EventoDAO().findById(eSession.getId());
        Local l = new LocalDAO().findById(id_local);
        
        Atividade atividade = new Atividade();
        atividade.setIdGerado(this.gerarIdAtividade(e));
        atividade.setNome(nome);
        atividade.setDtInicio(dtInicio);
        atividade.setDtFim(dtFim);
        atividade.setCarga_horaria(carga_horaria);
        atividade.setHoraInicio(horaInicio);
        atividade.setEvento(e);
        atividade.setLocal(l);
        atividade.setStatusEncerramento(false);
                
        System.out.println("Indo para o salvar");
        new AtividadeDAO().salvar(atividade);
        System.out.println("Saindo do salvar");
        return "/evento.xhtml?faces-redirect=true";
    }
    
    public int numerosInscritosPorAtividade( Long idAtividade ){
        Atividade a = new AtividadeDAO().findById(idAtividade);
        int inscritos = new ParticipacaoDAO().countPessoasPorAtividade(idAtividade);
        return ((100*inscritos)/a.getLocal().getTamanho());
    }
    
    //mostra todas as atividades pelo evento
    public List<Atividade> atividadesByEvento(Long idEvento){
        return new AtividadeDAO().atividadeByEvento(idEvento);       
    }        
    
    //métodos gettes() e setters()
    public Long getId_evento() {
        return id_evento;
    }            

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public boolean isStatusEncerramento() {
        return statusEncerramento;
    }

    public void setStatusEncerramento(boolean statusEncerramento) {
        this.statusEncerramento = statusEncerramento;
    }
    
    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }
    
    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Long getId() {
        return id;
    }

    private Object EventoBean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Long getId_local() {
        return id_local;
    }

    public void setId_local(Long id_local) {
        this.id_local = id_local;
    }
    
}
