package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Calificacion;
import model.CalificacionRespuesta;
import model.MensajeRespuesta;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CalificacionRespuesta.class)
public abstract class CalificacionRespuesta_ {

	public static volatile SingularAttribute<CalificacionRespuesta, Long> codigo;
	public static volatile SingularAttribute<CalificacionRespuesta, Usuario> usuario;
	public static volatile SingularAttribute<CalificacionRespuesta, Calificacion> calificacion;
	public static volatile SingularAttribute<CalificacionRespuesta, MensajeRespuesta> mensajeRespuesta;

}

