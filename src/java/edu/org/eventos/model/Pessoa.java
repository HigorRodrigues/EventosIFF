package edu.org.eventos.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cpf;
    private String nome;
    private String email;
    private String instituicao;
    private String senha;
    private String perfil;
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) 
    {   this.cpf = cpf; }

    public String getNome() 
    {   return nome;    }

    public void setNome(String nome) 
    {   this.nome = nome;   }

    public String getEmail() 
    {   return email;   }

    public void setEmail(String email) 
    {   this.email = email; }

    public String getInstituicao() 
    {   return instituicao; }

    public void setInstituicao(String instituicao) 
    {   this.instituicao = instituicao; }

    public String getSenha() 
    {   return senha;   }

    public void setSenha(String senha) 
    {   this.senha = senha; }

    public String getPerfil() 
    {   return perfil;  }

    public void setPerfil(String perfil) 
    {   this.perfil = perfil;   }
    
    
    public Long getId() 
    {   return id;  }

    public void setId(Long id) 
    {   this.id = id;   }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.org.eventos.model.Pessoa[ id=" + id + " ]";
    }
    
}
