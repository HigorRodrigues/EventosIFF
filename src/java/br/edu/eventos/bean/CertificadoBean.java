package br.edu.eventos.bean;

import br.edu.evento.utilitario.GeradorCertificado;
import br.edu.evento.utilitario.LerArquivoSerie;
import br.edu.eventos.DAO.CertificadoDAO;
import edu.org.eventos.model.Atividade;
import edu.org.eventos.model.Certificado;
import edu.org.eventos.model.Participacao;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class CertificadoBean implements Serializable{
    
    private String caminhoCertificado = "";
    private boolean certificadoGerado = false;
    private String idCertificado; 
    private Certificado certificado = null;
    
    private String gerarIdCertificado(Atividade a){                
        return a.getIdGerado() + "C" + 
                new LerArquivoSerie().retornaSerie("/home/higor/NetBeansProjects/eventosIFF/web/resources/geradores/geradorIdCertificado.txt");
    }
    
    public void salvar( List<Participacao> participacoes ){        
        for( Participacao p : participacoes){            
            if( p.isStatusParticipacao() ){
                Certificado c = new Certificado();
                c.setDataGeracao(Calendar.getInstance().getTime());
                c.setIdGerado(this.gerarIdCertificado(p.getAtividade()));
                c.setParticipacao(p);
                new CertificadoDAO().salvar(c);
            }
        }
        new LerArquivoSerie().reiniciarArquivo("/home/higor/NetBeansProjects/eventosIFF/web/resources/geradores/geradorIdCertificado.txt");        
    }

    public void buscaCertificado(){        
        this.certificado = new CertificadoDAO().findByIdGerado(this.idCertificado);

        try{
            this.caminhoCertificado = new GeradorCertificado(this.certificado).gerarPdf();
            if( !this.caminhoCertificado.equals("") ){
                this.certificadoGerado = true;
            }
            else{
                this.certificadoGerado = false;
            }
        }
        catch( Exception e){
            e.printStackTrace();
        }
    }

    public String getIdCertificado() {
        return idCertificado;
    }

    public void setIdCertificado(String idCertificado) {
        this.idCertificado = idCertificado;
    }
        
    public Certificado getCertificado() {
        return certificado;
    }

    public String getCaminhoCertificado() {
        return caminhoCertificado;
    }

    public void setCaminhoCertificado(String caminhoCertificado) {
        this.caminhoCertificado = caminhoCertificado;
    }

    public boolean isCertificadoGerado() {
        return certificadoGerado;
    }

    public void setCertificadoGerado(boolean certificadoGerado) {
        this.certificadoGerado = certificadoGerado;
    }
                    
}