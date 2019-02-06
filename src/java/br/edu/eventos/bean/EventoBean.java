package br.edu.eventos.bean;

import br.edu.evento.utilitario.LerArquivoSerie;
import br.edu.eventos.DAO.EventoDAO;
import br.edu.eventos.DAO.PessoaDAO;
import br.edu.eventos.DAO.UnidadeDAO;
import edu.org.eventos.model.Atividade;
import edu.org.eventos.model.Evento;
import edu.org.eventos.model.Pessoa;
import edu.org.eventos.model.Unidade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@ManagedBean
@RequestScoped
public class EventoBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String nome, proponente;
    private Long id_unidade;
    private Unidade unidade;

    private Date dtInicio, dtFim;
    private boolean certificadoUnico, temInscricao;
    private Evento eventoSelecionado;
    private List<Evento> eventos;
    private List<Atividade> atividadePorEvento;        
     
    private Long idDonoSelecionado;
    private List<Pessoa> todasPessoas;    
    
    private Part image;
    
    @PostConstruct
    public void init(){
        this.todasPessoas = new PessoaDAO().findAll();        
    }
    
    /*
    public void search(){
        this.resultadoPessoas.clear();
        for(Pessoa pessoa : this.todasPessoas){
            if( pessoa.getNome().toUpperCase().startsWith( dono.toUpperCase() ) )
                this.resultadoPessoas.add(pessoa);
        }
        if( this.dono.equals("") )
            this.resultadoPessoas = null;
    }
    */    
    
    private void doUpload(String nome){
        try{            
            InputStream input = image.getInputStream();
            
            File file = new File("/home/higor/NetBeansProjects/eventosIFF/web/resources/imagens/" + nome + ".jpg");
            file.createNewFile();
            
            FileOutputStream output = new FileOutputStream(file);
           
            byte[] buffer = new byte[1024];
            int length;
            
            while( (length = input.read(buffer))>0){
                output.write(buffer, 0, length);
            }
            
            output.close();
            input.close();            
        }
        catch( IOException e ){
            e.printStackTrace(System.out);
        }
    }

    private String gerarIdEvento(Date data){        
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        return "A" + c.get(Calendar.YEAR) + "E" + 
            new LerArquivoSerie().retornaSerie("/home/higor/NetBeansProjects/eventosIFF/web/resources/geradores/geredorIdEvento.txt");
    }
    
    public String imagem(String nomeImagem){
        return "imagens/" + nomeImagem + ".jpg";
    }
    
    public String salvar(){
        Evento e = new Evento();        
        
        if( this.getId() == null ){
            e.setIdGerado(this.gerarIdEvento(dtInicio));
            this.doUpload(e.getIdGerado());
        }
        else{
            e.setId(id);            
        }
        e.setNome(nome);
        e.setDtInicio(dtInicio);
        e.setDtFim(dtFim);                        
        e.setDonoEvento(new PessoaDAO().findById(idDonoSelecionado));
        
        this.unidade = new UnidadeDAO().findById(id_unidade);                                
        e.setUnidade(unidade);    
        new EventoDAO().salvar(e);               
        return "/index.xhtml?faces-redirect=true";
    }
    
    public String prepararEdicao(Evento e){
        this.setId(e.getId());
        this.setNome(e.getNome());
        this.setId_unidade(e.getUnidade().getId());
        this.setDtInicio(e.getDtInicio());
        this.setDtFim(e.getDtFim());
        return "/eventoCadastro.xhtml?faces-redirect=true";
    }
    
    public String apagar(Long id){
        Evento e = new EventoDAO().findById(id);
        String imageId = e.getIdGerado();
        if( new EventoDAO().delete(e) ){
            new LerArquivoSerie().apagaArquivo("/home/higor/NetBeansProjects/eventosIFF/web/resources/imagens/" + imageId + ".jpg");
            this.adicionaMensagem(FacesMessage.SEVERITY_INFO, "Evento Removido com sucesso!");
        }
        else{
            this.adicionaMensagem(FacesMessage.SEVERITY_ERROR, "Houve um erro ao excluir evento!");
        }
        return "/index.xhtml?faces-redirect=true";
    }
    
    public List<Evento> findAll(){
        return new EventoDAO().findAll();
    }                
    
    //Aqui coloca o evento Selecionado na index.xhtml 
    //E coloca em uma variável na session
    public void mostrarEvento(Evento e) throws IOException{                
        HttpSession session = new AutenticadorBean().retornaSessao();
        session.setAttribute("evento", e);
        FacesContext.getCurrentInstance().getExternalContext().redirect("evento.xhtml?");
    }                
    
    private void adicionaMensagem(FacesMessage.Severity type, String mensagem){
        FacesMessage fm = new FacesMessage(mensagem);
        fm.setSeverity(type);
        FacesContext.getCurrentInstance().addMessage(
            null, fm);		
	FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);        
    }

    
    public Long getIdDonoSelecionado() {    
        return idDonoSelecionado;
    }

    //Início dos getters and setters
    public void setIdDonoSelecionado(Long idDonoSelecionado) {
        this.idDonoSelecionado = idDonoSelecionado;
    }

    public Part getImage() {
        return image;
    }
    
    public void setImage(Part image) {
        this.image = image;
    }
    
    public Evento getEventoSelecionado() {
        return eventoSelecionado;
    }

    public void setEventoSelecionado(Evento eventoSelecionado) {
        this.eventoSelecionado = eventoSelecionado;
    }

    public List<Atividade> getAtividadePorEvento() {
        return atividadePorEvento;
    }

    public void setAtividadePorEvento(List<Atividade> atividadePorEvento) {
        this.atividadePorEvento = atividadePorEvento;
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
        
    public Long getId_unidade() {
        return id_unidade;
    }

    public void setId_unidade(Long id_unidade) {
        this.id_unidade = id_unidade;
    }
    
    public boolean isCertificadoUnico() {
        return certificadoUnico;
    }

    public void setCertificadoUnico(boolean certificadoUnico) {
        this.certificadoUnico = certificadoUnico;
    }

    public boolean isTemInscricao() {
        return temInscricao;
    }

    public void setTemInscricao(boolean temInscricao) {
        this.temInscricao = temInscricao;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }   
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProponente() {
        return proponente;
    }

    public void setProponente(String proponente) {
        this.proponente = proponente;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
    
    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public List<Pessoa> getTodasPessoas() {
        return todasPessoas;
    }

    public void setTodasPessoas(List<Pessoa> todasPessoas) {
        this.todasPessoas = todasPessoas;
    }
    
}
