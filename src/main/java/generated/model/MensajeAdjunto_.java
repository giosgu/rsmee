package generated.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import model.Mensaje;
import model.MensajeAdjunto;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MensajeAdjunto.class)
public abstract class MensajeAdjunto_ {

	public static volatile SingularAttribute<MensajeAdjunto, Long> codigo;
	public static volatile SingularAttribute<MensajeAdjunto, String> pathArchivoAdjunto;
	public static volatile SingularAttribute<MensajeAdjunto, Mensaje> mensaje;

}

