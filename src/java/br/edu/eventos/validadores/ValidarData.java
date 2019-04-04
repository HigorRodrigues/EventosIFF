package br.edu.eventos.validadores;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator( value = "br.edu.eventos.validadores.ValidarData")
public class ValidarData implements Validator{
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {        
                
        //String dataInicio = (String) dtInicio.getValue();
        
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");            
        
        try {
            //Date data1 = parser.parse((String) dtInicio);
            //Date data2 = parser.parse((String) value);
            System.out.println("Data de Início: "+ component.getAttributes().get("dtInicio").toString());
            /*if( data2.after(data1) )
               return;
            if( data2.before(data1)){
                FacesMessage message = new FacesMessage("A data de fim não pode ser antes que a data de início");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);            
                throw new ValidatorException(message);
            } */           
        }            
        catch (Exception ex) {
            Logger.getLogger(ValidarData.class.getName()).log(Level.SEVERE, null, ex);
        }
                                        
    }
}
