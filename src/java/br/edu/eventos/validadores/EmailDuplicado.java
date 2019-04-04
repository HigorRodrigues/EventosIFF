package br.edu.eventos.validadores;

import br.edu.eventos.DAO.PessoaDAO;
import edu.org.eventos.model.Pessoa;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator( value = "br.edu.eventos.validadores.EmailDuplicado")
public class EmailDuplicado implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {        
        Pessoa pessoa = new PessoaDAO().findUserByEmail(value.toString());
        
        if( pessoa == null )
            return;
        else{
            FacesMessage message = new FacesMessage("E-mail já cadastrado");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);            
            throw new ValidatorException(message);
        }
    }
    
}
