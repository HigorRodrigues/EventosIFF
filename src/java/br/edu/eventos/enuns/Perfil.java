/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.eventos.enuns;

public enum Perfil {

    PARTICIPANTE("Participante"),
    MINISTRANTE("Ministrante"),
    MODERADOR("Moderador");
    
    private String participacao;
    
    Perfil( String forma ){
        this.participacao = forma;
    }

    public String getParticipacao() {
        return participacao;
    }
        
}
