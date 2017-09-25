package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.ArchivoDetalle;
import model.ErrorArchivo;
import model.ErrorDetalle;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ErrorDetalle.class)
public abstract class ErrorDetalle_ {

	public static volatile SingularAttribute<ErrorDetalle, Long> codigo;
	public static volatile SingularAttribute<ErrorDetalle, ArchivoDetalle> archivoDetalle;
	public static volatile SingularAttribute<ErrorDetalle, ErrorArchivo> errorArchivo;

}

