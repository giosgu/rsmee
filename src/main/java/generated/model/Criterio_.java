package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Criterio;
import model.CriterioTipo;
import model.Mensaje;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Criterio.class)
public abstract class Criterio_ {

	public static volatile SingularAttribute<Criterio, Long> codigo;
	public static volatile SingularAttribute<Criterio, CriterioTipo> criterioTipo;
	public static volatile SingularAttribute<Criterio, Mensaje> mensaje;
	public static volatile SingularAttribute<Criterio, String> valorDescripcion;
	public static volatile SingularAttribute<Criterio, String> valorCodigo;

}

