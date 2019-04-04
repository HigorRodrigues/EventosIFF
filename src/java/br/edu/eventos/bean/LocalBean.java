
package br.edu.eventos.bean;

import br.edu.eventos.DAO.LocalDAO;
import br.edu.eventos.DAO.UnidadeDAO;
import edu.org.eventos.model.Local;
import edu.org.eventos.model.Unidade;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class LocalBean {

    private Long id;
    private String nome;
    private int tamanho;
    private Long id_unidade;
    private Unidade unidade;

    public String salvar(){
        Local local = new Local();
        local.setNome(this.nome);
        local.setTamanho(this.tamanho);
        
        this.unidade = new UnidadeDAO().findById(id_unidade);
        local.setUnidade(unidade);                        
        
        if( new LocalDAO().salvar(local) )
            this.adicionaMensagem(FacesMessage.SEVERITY_INFO, "Local cadastrado com sucesso");
        else
            this.adicionaMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar local");
        return "/cadastroLocal.xhtml?faces-redirect=true";
    }
    
    public void apagar(Long id){
        if( !new LocalDAO().apagar(id) )
            this.adicionaMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao apagar local");
        else
            this.adicionaMensagem(FacesMessage.SEVERITY_INFO, "Local apagado com sucesso");
    }
    
    private void adicionaMensagem(FacesMessage.Severity type, String mensagem){
        FacesMessage fm = new FacesMessage(mensagem);
        fm.setSeverity(type);
        FacesContext.getCurrentInstance().addMessage(
            null, fm);		
	FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);        
    }
    
    public void resetValores(){
        this.setNome("");
        this.setTamanho(0);
        this.setUnidade(null);
    }
    
    public List<Local> allLocais(){
        return new LocalDAO().findAll();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTamanho() {
        return tamanho;
    }
    
    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }   

    public Long getId_unidade() {
        return id_unidade;
    }

    public void setId_unidade(Long id_unidade) {
        this.id_unidade = id_unidade;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }
    
    
    
}