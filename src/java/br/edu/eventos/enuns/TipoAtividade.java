/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.eventos.enuns;

public enum TipoAtividade {

    PALESTRA("a palestra"),
    MINICURSO("o minicurso"),
    OFICINA("a oficina"),
    EXPOSICAO("a exposição"),
    VISITACAO("a visitação"),
    MESAREDONDA("a mesa redonda"),
    WORKSHOP("o workShop"),
    DEBATE("o debate"),
    PROJETO("o projeto"),
    VISITATECNICA("a visita técnica"),
    SESSAOORAL("a sessão oral");
    
    TipoAtividade( String tipo ){
        this.tipo = tipo;
    }

    private String tipo;
    
    public String getTipo(){
        return this.tipo;
    }
    
}