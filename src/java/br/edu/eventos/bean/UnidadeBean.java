
package br.edu.eventos.bean;

import br.edu.eventos.DAO.UnidadeDAO;
import edu.org.eventos.model.Unidade;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class UnidadeBean implements Serializable{

    private String nome, cidade;
    private List<String> cidades;
    private List<Unidade> unidades;

    public List<Unidade> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidade> unidades) {
        this.unidades = unidades;
    }
    
    @PostConstruct
    public void allCidades(){
        this.cidades = Arrays.asList(
                "Angra dos Reis", "Aperibe", "Araruama", "Areal", 
            "Armacao de Buzios", "Arraial do Cabo", "Barra Mansa", "Barra do Pirai",
            "Belford Roxo", "Bom Jardim", "Bom Jesus do Itabapoana", "Cabo Frio",
            "Cachoeiras de Macacu", "Cambuci", "Campos dos Goytacazes", "Cantagalo",
            "Carapebus", "Cardoso Moreira", "Carmo", "Casimiro de Abreu", 
            "Comendador Levy Gasparian", "Conceicao de Macabu", "Cordeiro", "Duas Barras",
            "Duque de Caxias", "Engenheiro Paulo de Frontin", "Guapimirim", "Iguaba Grande", 
            "Itaborai", "Itaguai", "Italva", "Itaocara", "Itaperuna", "Itatiaia", "Japeri",
            "Laje do Muriae", "Macae", "Macuco", "Mage", "Mangaratiba", "Marica", "Mendes",
            "Miguel Pereira", "Miracema", "Natividade", "Nilopolis", "Niteroi", "Nova Friburgo",
            "Nova Iguacu", "Paracambi", "Paraiba do Sul", "Parati", "Paty do Alferes", "Petropolis",
            "Pinheiral", "Pirai", "Porciuncula", "Porto Real", "Quatis", "Queimados",
            "Quissama", "Resende", "Rio Bonito", "Rio Claro", "Rio das Flores", "Rio das Ostras",
            "Rio de Janeiro", "Santa Maria Madalena", "Santo Antonio de Padua", "Sao Fidelis", 
            "Sao Francisco de Itabapoana", "Sao Goncalo", "Sao Joao da Barra", "Sao Joao de Meriti",
            "Sao Jose de Uba", "Sao Jose do Vale do Rio Preto", "Sao Pedro da Aldeia", "Sao Sebastiao do Alto",
            "Sapucaia", "Saquarema", "Seropedica", "Silva Jardim", "Sumidouro", "Tangua", "Teresopolis",
            "Trajano de Morais", "Tres Rios", "Valenca", "Varre-Sai", "Vassouras", "Volta Redonda");
            
    }

    public List<Unidade> allUnidades(){
        return new UnidadeDAO().findAll();
    }
    
    public String cadastrarUnidade(){
        Unidade unidade = new Unidade();
        unidade.setNome(nome);
        unidade.setCidade(cidade);
        
        if( new UnidadeDAO().Cadastrar(unidade) )
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( FacesMessage.SEVERITY_INFO, "Info", "Unidade cadastrada com sucesso!"));
        return "/cadastrarUnidade.xhtml?faces-redirect=true";
    }
    
    public List<String> getCidades() {
        return cidades;
    }

    public void setCidades(List<String> cidades) {
        this.cidades = cidades;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }        
}
