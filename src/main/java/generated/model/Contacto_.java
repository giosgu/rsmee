package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Contacto;
import model.Relacion;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contacto.class)
public abstract class Contacto_ {

	public static volatile SingularAttribute<Contacto, Usuario> destino;
	public static volatile SingularAttribute<Contacto, Long> codigo;
	public static volatile SingularAttribute<Contacto, Relacion> relacion;
	public static volatile SingularAttribute<Contacto, Date> fechaContacto;
	public static volatile SingularAttribute<Contacto, Usuario> origen;

}

