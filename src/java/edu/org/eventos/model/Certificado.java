package edu.org.eventos.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Certificado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String idGerado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataGeracao;
    
    @OneToOne
    @JoinColumn(name="id_participacao")
    private Participacao participacao;

    public Date getDataGeracao() {
        return dataGeracao;
    }

    public String getIdGerado() {
        return idGerado;
    }

    public void setIdGerado(String idGerado) {
        this.idGerado = idGerado;
    }
    
    public void setDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public Participacao getParticipacao() {
        return participacao;
    }

    public void setParticipacao(Participacao participacao) {
        this.participacao = participacao;
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Certificado)) {
            return false;
        }
        Certificado other = (Certificado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.org.eventos.model.Certificado[ id=" + id + " ]";
    }
    
}
