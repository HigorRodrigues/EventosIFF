package edu.org.eventos.model;

import edu.org.eventos.model.Atividade;
import edu.org.eventos.model.Pessoa;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-01-08T17:14:55")
@StaticMetamodel(Participacao.class)
public class Participacao_ { 

    public static volatile SingularAttribute<Participacao, Atividade> atividade;
    public static volatile SingularAttribute<Participacao, Boolean> statusParticipacao;
    public static volatile SingularAttribute<Participacao, Pessoa> pessoa;
    public static volatile SingularAttribute<Participacao, Date> dataInscricao;
    public static volatile SingularAttribute<Participacao, Long> id;

}