package br.edu.eventos.bean;

import br.edu.evento.utilitario.EmailUtils;
import br.edu.evento.utilitario.MensagemEmail;
import br.edu.eventos.DAO.PessoaDAO;
import edu.org.eventos.model.Pessoa;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.mail.EmailException;

@ManagedBean
@SessionScoped
public class AutenticadorBean implements Serializable {
    
    private String email, senha;
    private Pessoa user;
        
    public boolean estado(){
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);
        return session.getAttribute("usuario") != null;
    }
    
    public HttpSession retornaSessao(){
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        return (HttpSession) ec.getSession(false);     
    }
    
    public String autentica(){
        this.user = new PessoaDAO().findUser(email, senha);        
        FacesContext fc = FacesContext.getCurrentInstance();
        
        if( this.user != null ){
            ExternalContext ec = fc.getExternalContext();
            HttpSession session = (HttpSession) ec.getSession(false);
            session.setAttribute("usuario", this.user);
            
            FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage("Login realizado com sucesso!"));
		
		FacesContext.getCurrentInstance()
			.getExternalContext()
			.getFlash().setKeepMessages(true);
                
            return "/index.xhtml?faces-redirect=true";
        }
        else{
            FacesMessage fm = new FacesMessage("Login ou senha inválidos");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(
				null, fm);
		
		FacesContext.getCurrentInstance()
			.getExternalContext()
			.getFlash().setKeepMessages(true);
                
            return "/login.xhtml?faces-redirect=true";
        }
    }
    
    public String desautentica(){
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(false);
        session.removeAttribute("usuario");
       
        return "/login.xhtml?faces-redirect=true";
    }
    
    public void enviaSenha(){
        if( this.email.equals("") ){
            FacesMessage fm = new FacesMessage("E-mail está vazio!");
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, fm);		
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
        else{
            this.user = new PessoaDAO().findUserByEmail(this.email);
            MensagemEmail m = new MensagemEmail();
            m.setDestino(this.user.getEmail());
            m.setMensagem("Sua senha no sistema EVENTOS IFF é " + '"' + this.user.getSenha() + '"');
            m.setTitulo("Lembrete de senha");
            try {
                EmailUtils.enviaEmail(m);
            } catch (EmailException ex) {
                Logger.getLogger(AutenticadorBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
            
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }    
    
}
