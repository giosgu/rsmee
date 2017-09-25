package generated.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Ayuda;
import model.Usuario;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ayuda.class)
public abstract class Ayuda_ {

	public static volatile SingularAttribute<Ayuda, String> apellido;
	public static volatile SingularAttribute<Ayuda, String> nombre;
	public static volatile SingularAttribute<Ayuda, Long> id;
	public static volatile SingularAttribute<Ayuda, Date> fecha;
	public static volatile SingularAttribute<Ayuda, Usuario> usuario;
	public static volatile SingularAttribute<Ayuda, String> mailContacto;
	public static volatile SingularAttribute<Ayuda, String> textoConsulta;

}

