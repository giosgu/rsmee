package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Calificacion;
import model.EstadoMensaje;
import model.MensajeRespuesta;
import model.MensajeRespuestaDestino;
import model.Notificacion;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MensajeRespuestaDestino.class)
public abstract class MensajeRespuestaDestino_ {

	public static volatile SingularAttribute<MensajeRespuestaDestino, Calificacion> calificacionRespuesta;
	public static volatile SingularAttribute<MensajeRespuestaDestino, Long> codigo;
	public static volatile SingularAttribute<MensajeRespuestaDestino, EstadoMensaje> estadoMensaje;
	public static volatile SingularAttribute<MensajeRespuestaDestino, Usuario> usuarioOrigen;
	public static volatile SingularAttribute<MensajeRespuestaDestino, Usuario> usuarioDestino;
	public static volatile SingularAttribute<MensajeRespuestaDestino, Notificacion> notificacion;
	public static volatile SingularAttribute<MensajeRespuestaDestino, MensajeRespuesta> mensajeRespuesta;

}

