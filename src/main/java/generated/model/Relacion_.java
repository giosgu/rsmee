package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.EstadoRelacion;
import model.Notificacion;
import model.Relacion;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Relacion.class)
public abstract class Relacion_ {

	public static volatile SingularAttribute<Relacion, Usuario> origenUsuario;
	public static volatile SingularAttribute<Relacion, Long> codigo;
	public static volatile SingularAttribute<Relacion, String> motivoDesasignacion;
	public static volatile SingularAttribute<Relacion, Usuario> destinoUsuario;
	public static volatile SingularAttribute<Relacion, Notificacion> notificacion;
	public static volatile SingularAttribute<Relacion, Date> fechaCreacion;
	public static volatile SingularAttribute<Relacion, EstadoRelacion> estadoRelacion;

}

