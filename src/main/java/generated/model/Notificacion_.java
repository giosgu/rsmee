package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.EstadoNotificacion;
import model.Notificacion;
import model.TipoNotificacion;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Notificacion.class)
public abstract class Notificacion_ {

	public static volatile SingularAttribute<Notificacion, Usuario> destino;
	public static volatile SingularAttribute<Notificacion, Long> codigo;
	public static volatile SingularAttribute<Notificacion, Date> fecha;
	public static volatile SingularAttribute<Notificacion, String> parametro;
	public static volatile SingularAttribute<Notificacion, EstadoNotificacion> estadoNotificacion;
	public static volatile SingularAttribute<Notificacion, TipoNotificacion> tipoNotificacion;
	public static volatile SingularAttribute<Notificacion, Usuario> origen;

}

