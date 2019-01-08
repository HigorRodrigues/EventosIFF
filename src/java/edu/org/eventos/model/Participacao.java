/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.org.eventos.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Participacao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_atividade")
    private Atividade atividade;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_pessoa")
    private Pessoa pessoa;

    private boolean statusParticipacao;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInscricao;
    
    
    //inicio dos getters and setters
    public Long getId() 
    {   return id;      }

    public void setId(Long id) {
        this.id = id;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public boolean isStatusParticipacao() {
        return statusParticipacao;
    }

    public void setStatusParticipacao(boolean statusParticipacao) 
    {   this.statusParticipacao = statusParticipacao;    }

    public Date getDataInscricao() 
    {   return dataInscricao;    }

    public void setDataInscricao(Date dataInscricao) 
    {   this.dataInscricao = dataInscricao; }
        
    //fim dos getters and setters
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participacao)) {
            return false;
        }
        Participacao other = (Participacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.org.eventos.model.Participacao[ id=" + id + " ]";
    }
    
}