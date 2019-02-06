package edu.org.eventos.model;

import edu.org.eventos.model.Participacao;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-06T14:29:11")
@StaticMetamodel(Certificado.class)
public class Certificado_ { 

    public static volatile SingularAttribute<Certificado, String> idGerado;
    public static volatile SingularAttribute<Certificado, Long> id;
    public static volatile SingularAttribute<Certificado, Participacao> participacao;
    public static volatile SingularAttribute<Certificado, Date> dataGeracao;

}