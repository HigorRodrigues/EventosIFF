/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.eventos.enuns;

public enum TipoAtividade {

    PALESTRA("Palestra"),
    MINICURSO("Minicurso"),
    OFICINA("Oficina"),
    EXPOSICAO("Exposição"),
    VISITACAO("Visitação"),
    MESAREDONDA("Mesa Redonda"),
    WORKSHOP("WorkShop"),
    DEBATE("Debate"),
    PROJETO("Projeto"),
    VISITATECNICA("Visita Técnica"),
    SESSAOORAL("Sessão Oral");
    
    TipoAtividade( String tipo ){
        this.tipo = tipo;
    }

    private String tipo;
    
    public String getTipo(){
        return this.tipo;
    }
    
}