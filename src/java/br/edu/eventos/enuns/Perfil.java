package br.edu.eventos.enuns;

public enum Perfil {

    ADMINISTRADOR("Administrador"),
    ASSINANTE("Assinante"),     
    DONODEEVENTO("Dono de Evento"),
    USUARIOCOMUM("Usuário Comum");
    
    private String participacao;
    
    Perfil( String forma ){
        this.participacao = forma;
    }

    public String getParticipacao() {
        return participacao;
    }
        
}
