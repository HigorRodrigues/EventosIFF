package br.edu.eventos.bean;

import br.edu.eventos.DAO.PessoaDAO;
import br.edu.eventos.enuns.Perfil;
import edu.org.eventos.model.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class PessoaBean implements Serializable{
    
    private String cpf;
    private String nome;
    private String email;
    private String instituicao;
    private String senha;
    private String perfil;        
    
    public String salvar(){        
        Pessoa p = new Pessoa();
        if (perfil == null )
            p.setPerfil("Participante");
        else
            p.setPerfil(perfil);
        p.setNome(nome);
        p.setEmail(email);
        p.setCpf(cpf);
        p.setInstituicao(instituicao);
        p.setSenha(senha);
        
        if( new PessoaDAO().salvar(p) )
            adicionaMensagem(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso");
        else
            adicionaMensagem(FacesMessage.SEVERITY_ERROR, "Cadastro realizado com sucesso");
        return "/index?faces-redirect=true";
    }
    
    private void adicionaMensagem(Severity type, String mensagem){
        FacesMessage fm = new FacesMessage(mensagem);
        fm.setSeverity(type);
        FacesContext.getCurrentInstance().addMessage(
            null, fm);		
	FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);        
    }

    public List<String> perfis(){
        List<String> perfis = new ArrayList<>();
        for( Perfil p : Perfil.values()){
            perfis.add(p.getParticipacao());
        }
        return perfis;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
