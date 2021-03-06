package br.edu.eventos.bean;

import edu.org.eventos.model.Evento;
import edu.org.eventos.model.Pessoa;
import java.util.Objects;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named(value = "regrasBean")
@Dependent
public class RegrasBean {
    
    private HttpSession retornaSessao(){
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        return (HttpSession) ec.getSession(false);     
    }
    
    public boolean verificaAdministrador(){
        HttpSession sessao = this.retornaSessao();
        Pessoa user = (Pessoa) sessao.getAttribute("usuario");
        if( user == null || user.getPerfil() == null)
            return false;
        else
            return user.getPerfil().equals("Administrador");
    }
    
    public boolean isAdministradorOuDono(Evento e){
        HttpSession sessao = this.retornaSessao();
        Pessoa user = (Pessoa) sessao.getAttribute("usuario");
        if( user == null || user.getPerfil() == null)
            return false;
        else{
            boolean status = (user.getPerfil().equals("Administrador") || Objects.equals(e.getDonoEvento().getId(), user.getId()) );
            return status;
        }
    }
}
