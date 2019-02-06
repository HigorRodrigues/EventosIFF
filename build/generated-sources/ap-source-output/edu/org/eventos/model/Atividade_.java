package edu.org.eventos.model;

import edu.org.eventos.model.Evento;
import edu.org.eventos.model.Local;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-06T14:29:11")
@StaticMetamodel(Atividade.class)
public class Atividade_ { 

    public static volatile SingularAttribute<Atividade, Integer> carga_horaria;
    public static volatile SingularAttribute<Atividade, Evento> evento;
    public static volatile SingularAttribute<Atividade, String> idGerado;
    public static volatile SingularAttribute<Atividade, Boolean> statusEncerramento;
    public static volatile SingularAttribute<Atividade, String> nome;
    public static volatile SingularAttribute<Atividade, Long> id;
    public static volatile SingularAttribute<Atividade, Date> dtInicio;
    public static volatile SingularAttribute<Atividade, Date> dtFim;
    public static volatile SingularAttribute<Atividade, Date> horaInicio;
    public static volatile SingularAttribute<Atividade, Local> local;

}