
package br.edu.eventos.bean;

import br.edu.eventos.DAO.LocalDAO;
import edu.org.eventos.model.Local;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class LocalBean {

    private Long id;
    private String nome;
    private int tamanho;

    public String salvar(){
        Local local = new Local();
        local.setNome(this.nome);
        local.setTamanho(this.tamanho);
        new LocalDAO().salvar(local);
        return "/faces/index.xhtml?faces-redirect=true";
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
    
}