package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.EstadoMensaje;
import model.Mensaje;
import model.MensajeDestino;
import model.Notificacion;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MensajeDestino.class)
public abstract class MensajeDestino_ {

	public static volatile SingularAttribute<MensajeDestino, Long> codigo;
	public static volatile SingularAttribute<MensajeDestino, EstadoMensaje> estadoMensaje;
	public static volatile SingularAttribute<MensajeDestino, Usuario> usuarioDestino;
	public static volatile SingularAttribute<MensajeDestino, Usuario> usuarioOrigen;
	public static volatile SingularAttribute<MensajeDestino, Notificacion> notificacion;
	public static volatile SingularAttribute<MensajeDestino, Mensaje> mensaje;

}

