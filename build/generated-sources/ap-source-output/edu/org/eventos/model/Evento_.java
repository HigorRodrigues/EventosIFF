package edu.org.eventos.model;

import edu.org.eventos.model.Pessoa;
import edu.org.eventos.model.Unidade;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-06T14:29:11")
@StaticMetamodel(Evento.class)
public class Evento_ { 

    public static volatile SingularAttribute<Evento, String> idGerado;
    public static volatile SingularAttribute<Evento, String> nome;
    public static volatile SingularAttribute<Evento, Unidade> unidade;
    public static volatile SingularAttribute<Evento, Long> id;
    public static volatile SingularAttribute<Evento, Date> dtInicio;
    public static volatile SingularAttribute<Evento, Date> dtFim;
    public static volatile SingularAttribute<Evento, Pessoa> donoEvento;

}