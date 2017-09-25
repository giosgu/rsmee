package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Localidad;
import model.Provincia;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Localidad.class)
public abstract class Localidad_ {

	public static volatile SingularAttribute<Localidad, Long> codigo;
	public static volatile SingularAttribute<Localidad, Provincia> codigoProvincia;
	public static volatile SingularAttribute<Localidad, String> value;
	public static volatile SingularAttribute<Localidad, String> descripcion;
	public static volatile SingularAttribute<Localidad, String> label;

}

