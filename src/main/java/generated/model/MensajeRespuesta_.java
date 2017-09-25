package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.ArchivoAdjunto;
import model.CalificacionRespuesta;
import model.Mensaje;
import model.MensajeRespuesta;
import model.MensajeRespuestaDestino;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MensajeRespuesta.class)
public abstract class MensajeRespuesta_ {

	public static volatile SetAttribute<MensajeRespuesta, CalificacionRespuesta> calificacionRespuesta;
	public static volatile SingularAttribute<MensajeRespuesta, Long> codigo;
	public static volatile SingularAttribute<MensajeRespuesta, Date> fecha;
	public static volatile SingularAttribute<MensajeRespuesta, String> texto;
	public static volatile SingularAttribute<MensajeRespuesta, ArchivoAdjunto> archivoAdjunto;
	public static volatile ListAttribute<MensajeRespuesta, MensajeRespuestaDestino> mensajeRespuestaDestinos;
	public static volatile SingularAttribute<MensajeRespuesta, Usuario> usuarioOrigen;
	public static volatile SingularAttribute<MensajeRespuesta, Mensaje> mensaje;

}

