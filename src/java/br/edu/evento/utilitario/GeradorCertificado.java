package br.edu.evento.utilitario;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.org.eventos.model.Certificado;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GeradorCertificado {
    
    public static final String PATH = "/home/higor/NetBeansProjects/eventosIFF/web/resources/certificados/teste.pdf"
            + "";
    
    private Certificado certificado;
    
    public GeradorCertificado( Certificado certificado ){
        this.certificado = certificado;
    }
    
    public String gerarPdf() throws DocumentException, IOException{
        Document document = new Document();        
        
        String nomePdf = certificado.getIdGerado() + ".pdf";        
        File pdffile = new File(PATH, nomePdf);                
        
        PdfWriter.getInstance(document, new FileOutputStream(pdffile));
        
        document.open();        
        document.setPageSize(PageSize.A4);
        document.add(new Paragraph(this.certificado.getParticipacao().getPessoa().getNome().toUpperCase()));
        document.close();
        
        return nomePdf;
    } 

    public Certificado getCertificado() {
        return certificado;
    }

    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }   
            
}
