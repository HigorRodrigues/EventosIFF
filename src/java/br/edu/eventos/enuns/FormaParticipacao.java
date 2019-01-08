/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.eventos.enuns;

public enum FormaParticipacao {
    
    PARTICIPANTE("Participante"),
    MINISTRANTE("Ministrante"),
    MODERADOR("Moderador");
    
    private String modoParticipacao;
    
    FormaParticipacao( String modo ){
        this.modoParticipacao = modo;
    }
    
    public String getModoParticipacao(){
        return this.modoParticipacao;
    }
    
}