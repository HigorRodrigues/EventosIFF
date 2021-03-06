package edu.org.eventos.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String idGerado;
    
    private String nome;    
    private String descricao;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtInicio;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtFim;
   
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_dono_do_evento")
    private Pessoa donoEvento;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_unidade")
    private Unidade unidade;                
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_assinante")
    private Pessoa assinante;

    public Pessoa getAssinante() {
        return assinante;
    }

    public void setAssinante(Pessoa assinante) {
        this.assinante = assinante;
    }
        
    public String getIdGerado() {
        return idGerado;
    }

    public void setIdGerado(String idGerado) {
        this.idGerado = idGerado;
    }
       
    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pessoa getDonoEvento() {
        return donoEvento;
    }

    public void setDonoEvento(Pessoa donoEvento) {
        this.donoEvento = donoEvento;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.org.eventos.model.Evento[ id=" + id + " ]";
    }
    
}
