package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.ArchivoAdjunto;
import model.AuditoriaConsulta;
import model.Mensaje;
import model.MensajeDestino;
import model.MensajeRespuesta;
import model.TipoMensaje;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Mensaje.class)
public abstract class Mensaje_ {

	public static volatile SingularAttribute<Mensaje, Boolean> finalizado;
	public static volatile SingularAttribute<Mensaje, Boolean> noEsConsultaMedica;
	public static volatile SingularAttribute<Mensaje, String> texto;
	public static volatile SingularAttribute<Mensaje, Date> fecha;
	public static volatile SetAttribute<Mensaje, MensajeRespuesta> mensajeRespuestas;
	public static volatile SingularAttribute<Mensaje, ArchivoAdjunto> archivoAdjunto;
	public static volatile SingularAttribute<Mensaje, AuditoriaConsulta> auditoriaConsulta;
	public static volatile SingularAttribute<Mensaje, String> comenatrioAdministrador;
	public static volatile SingularAttribute<Mensaje, Long> codigo;
	public static volatile ListAttribute<Mensaje, MensajeDestino> mensajeDestinos;
	public static volatile SingularAttribute<Mensaje, TipoMensaje> tipoMensaje;
	public static volatile SingularAttribute<Mensaje, Date> fechaUltimoCierre;
	public static volatile SingularAttribute<Mensaje, Usuario> origen;

}

