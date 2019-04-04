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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@Named
@RequestScoped
public class EventoBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String nome, descricao;
    private Long id_unidade;
    private Unidade unidade;

    private String dtInicio, dtFim;
    private boolean certificadoUnico, temInscricao;
    private Evento eventoSelecionado;
    private List<Evento> eventos;
    private List<Atividade> atividadePorEvento;        
     
    private Long idDonoSelecionado;
    private Long idAssinanteSelecionado;
    
    private List<Pessoa> todasPessoas;
    private List<Pessoa> pessoasPerfilSelecionado;
    
    private int quantCaracteres = 0;
    
    private Part imageLogo, imageBanner;
    
    @PostConstruct
    public void init(){        
        this.todasPessoas = new PessoaDAO().findAll();
        this.pessoasPerfilSelecionado = new PessoaDAO().findByPerfil("Assinante");
    }
    
    public int quantidadeCaracteres(){
        int valor = 0;
        if( descricao == null )
            return valor;
        for( int x = 0; x < descricao.length(); x++)
            valor += 1;
        return valor;
    }    
    
    private void doUpload(String nome, Part image){
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
    
    public String imagem(String nomeImagem, String tipo){
        return "imagens/" + tipo + "_" +nomeImagem + ".jpg";
    }
    
    public String salvar() throws ParseException{
        Evento e = new Evento();        
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
        
        if( this.getId() == null ){
            e.setIdGerado(this.gerarIdEvento(parser.parse(dtInicio)));
            this.doUpload("logo_" + e.getIdGerado(), this.imageLogo);
            this.doUpload("banner_" + e.getIdGerado(), this.imageBanner);
        }
        else{
            e.setId(id);            
        }
        e.setNome(nome);        
        e.setDtInicio(parser.parse(dtInicio));
        e.setDtFim(parser.parse(dtFim)); 
        e.setDescricao(descricao);
        
        e.setDonoEvento(new PessoaDAO().findById(idDonoSelecionado));
        e.setAssinante(new PessoaDAO().findById(idAssinanteSelecionado));
        e.setUnidade(new UnidadeDAO().findById(id_unidade));                                
        
        new EventoDAO().salvar(e);               
        return "/index.xhtml?faces-redirect=true";
    }
    
    public String prepararEdicao(Evento e){
        this.setId(e.getId());
        this.setNome(e.getNome());
        this.setId_unidade(e.getUnidade().getId());
        //this.setDtInicio(e.getDtInicio());
        //this.setDtFim(e.getDtFim());
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

    public Part getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(Part imageLogo) {
        this.imageLogo = imageLogo;
    }

    public Part getImageBanner() {
        return imageBanner;
    }

    public void setImageBanner(Part imageBanner) {
        this.imageBanner = imageBanner;
    }   
    
    public Evento getEventoSelecionado() {
        return eventoSelecionado;
    }

    public void setEventoSelecionado(Evento eventoSelecionado) {
        this.eventoSelecionado = eventoSelecionado;
    }

    public List<Pessoa> getPessoasPerfilSelecionado() {
        return pessoasPerfilSelecionado;
    }

    public void setPessoasPerfilSelecionado(List<Pessoa> pessoasPerfilSelecionado) {
        this.pessoasPerfilSelecionado = pessoasPerfilSelecionado;
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

    public String getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(String dtInicio) {
        this.dtInicio = dtInicio;
    }

    public String getDtFim() {
        return dtFim;
    }

    public void setDtFim(String dtFim) {
        this.dtFim = dtFim;
    }   
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Long getIdAssinanteSelecionado() {
        return idAssinanteSelecionado;
    }

    public void setIdAssinanteSelecionado(Long idAssinanteSelecionado) {
        this.idAssinanteSelecionado = idAssinanteSelecionado;
    }    

    public int getQuantCaracteres() {
        return quantCaracteres;
    }

    public void setQuantCaracteres(int quantCaracteres) {
        this.quantCaracteres = quantCaracteres;
    }
        
}
