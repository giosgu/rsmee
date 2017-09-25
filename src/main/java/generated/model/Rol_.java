package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Rol;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rol.class)
public abstract class Rol_ {

	public static volatile SingularAttribute<Rol, Long> codigo;
	public static volatile SingularAttribute<Rol, Boolean> condicional;
	public static volatile SetAttribute<Rol, Rol> roles;
	public static volatile SingularAttribute<Rol, String> descripcion;

}

