package br.edu.evento.utilitario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class LerArquivoSerie {

    private int numero;
    
    public String retornaSerie(String arquivo){
        String serie = "";
        try{
            InputStream input = new FileInputStream(arquivo);
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader br = new BufferedReader(isr);
            serie = br.readLine();
            br.close();
            
            this.numero = this.stringToInt(serie);
            this.incrementaSerie(arquivo);            
        }
        catch( Exception e ){
            e.printStackTrace();
        }        
        return serie;
    }
    
    private void incrementaSerie(String arquivo){
        try{
            OutputStream os = new FileOutputStream(arquivo);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
                        
            String aux = String.format("%03d", this.incrementaNumero());
            
            bw.write(aux);            
            bw.close();
        }
        catch( Exception e ){
            e.printStackTrace();
        }
    }
    
    public void reiniciarArquivo(String arquivo ){
        try{
            OutputStream os = new FileOutputStream(arquivo);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write("001");
            bw.close();
        }
        catch( Exception io ){
            io.printStackTrace();
        }
    }
    
    public void apagaArquivo( String arquivo ){
        File arq = new File(arquivo);
        arq.delete();      
    }
    
    private int stringToInt( String serie ){
        return Integer.parseInt(serie);
    }
    
    private int incrementaNumero(){
        return this.numero+1;
    }    
}