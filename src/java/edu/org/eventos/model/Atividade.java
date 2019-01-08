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

@Entity
public class Atividade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome; 
    private String idGerado;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtInicio;    

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtFim;    
    
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horaInicio;
    
    private int carga_horaria;
    
    private boolean statusEncerramento;   
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_evento")
    private Evento evento;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_local")
    private Local local;

    public boolean isStatusEncerramento() {
        return statusEncerramento;
    }

    public void setStatusEncerramento(boolean statusEncerramento) {
        this.statusEncerramento = statusEncerramento;
    }

    public String getIdGerado() {
        return idGerado;
    }

    public void setIdGerado(String idGerado) {
        this.idGerado = idGerado;
    }
    
    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }
    
    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
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

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

    public int getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }
        
    public Long getId() {
        return id;
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
        if (!(object instanceof Atividade)) {
            return false;
        }
        Atividade other = (Atividade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.org.eventos.model.Atividade[ id=" + id + " ]";
    }
    
}
